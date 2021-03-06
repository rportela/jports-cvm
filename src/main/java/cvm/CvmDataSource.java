package cvm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jports.GenericLogger;
import jports.text.CsvAspect;

/**
 * An open access to cvm public data portal;
 * 
 * @author rportela
 *
 */
public class CvmDataSource {

	private static final String ROOT_FUNDOS = "fundos";
	private static final String ROOT_CADASTROS = "cadastros";

	private final String localCachePath;

	public CvmDataSource(String localCachePath) {
		this.localCachePath = localCachePath;
	}

	private String formatDailyFileName(String prefix, String sufix) {
		String ymd = new SimpleDateFormat("_yyyy_MM_dd").format(new Date());
		return prefix + ymd + sufix;
	}

	private InputStream locateInputStream(URL remote, boolean override, String... local) throws IOException {
		if (localCachePath == null) {
			return remote.openStream();
		} else {
			Path localPath = Paths.get(localCachePath, local);
			if (override || !localPath.toFile().exists()) {
				Files.createDirectories(localPath.getParent());
				try (InputStream in = remote.openStream()) {
					try (OutputStream out = Files.newOutputStream(localPath)) {
						byte[] buff = new byte[4096];
						for (int n = in.read(buff); n >= 0; n = in.read(buff)) {
							out.write(buff, 0, n);
						}
					}
				}
			}
			return Files.newInputStream(localPath);
		}
	}

	/**
	 * Cias. Abertas: Informação Cadastral
	 * 
	 * Dados cadastrais de companhias abertas, como CNPJ, data de registro e
	 * situação do registro.
	 * 
	 * O conjunto de dados disponibiliza as informações cadastrais referentes ao
	 * último dia útil.
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public List<CiaAberta> fetchCiasAbertas() throws IOException {
		final String url = "http://dados.cvm.gov.br/dados/CIA_ABERTA/CAD/DADOS/cad_cia_aberta.csv";
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_CADASTROS,
				formatDailyFileName("cad_cia_aberta", ".csv"))) {

			return new CsvAspect<>(CiaAberta.class)
					.setCapacity(2500)
					.parse(is);
		}
	}

	/**
	 * Arquivo de cias estrangeiras registradas conforme link disponível em:
	 * http://sistemas.cvm.gov.br/
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<CiaEstrangeira> fetchCiasEstrangeiras() throws IOException {
		final String url = "http://sistemas.cvm.gov.br/cadastro/SPW_CIA_ESTRANG.ZIP";
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_CADASTROS,
				formatDailyFileName("SPW_CIA_ESTRANG", ".zip"))) {

			return new CsvAspect<>(CiaEstrangeira.class)
					.setCapacity(2500)
					.parseZip(is)
					.values()
					.stream()
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
		}
	}

	/**
	 * Arquivo de cias estrangeiras registradas conforme link disponível em:
	 * http://sistemas.cvm.gov.br/
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<CiaIncentivada> fetchCiasIncentivadasRegistradas() throws IOException {
		final String url = "http://sistemas.cvm.gov.br/cadastro/SPW_CIA_INCENT.ZIP";
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_CADASTROS,
				formatDailyFileName("SPW_CIA_INCENT", ".zip"))) {

			return new CsvAspect<>(CiaIncentivada.class)
					.setCapacity(2500)
					.parseZip(is)
					.values()
					.stream()
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
		}
	}

	/**
	 * O INFORME DIÁRIO é um demonstrativo que contém as seguintes informações do
	 * fundo, relativas à data de competência:
	 * 
	 * Valor total da carteira do fundo;
	 * 
	 * Patrimônio líquido;
	 * 
	 * Valor da cota;
	 * 
	 * Captações realizadas no dia;
	 * 
	 * Resgates pagos no dia;
	 * 
	 * Número de cotistas
	 * 
	 * O conjunto de dados disponibiliza os informes diários referentes aos Fundos
	 * de Investimento nos últimos doze meses.
	 * 
	 * Os arquivos referentes ao mês corrente (M) e anterior (M-1) serão atualizados
	 * diariamente com as eventuais reapresentações. A atualização ocorre de terça a
	 * sábado, às 08:00h, com os dados recebidos pelo CVMWeb até as 23:59h do dia
	 * anterior.
	 * 
	 * Os arquivos referentes aos meses M-2 e M-3 serão atualizados semanalmente com
	 * as eventuais reapresentações.
	 * 
	 * Os arquivos referente aos meses M-4, M-5, ..., até M-11 serão atualizados
	 * mensalmente com as eventuais reapresentações.
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<FundoDiario> fetchFundoDiario() throws IOException {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_MONTH) < 3) {
			cal.add(Calendar.MONTH, -1);
		}
		return fetchFundoDiario(
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1);
	}

	/**
	 * O INFORME DIÁRIO é um demonstrativo que contém as seguintes informações do
	 * fundo, relativas à data de competência:
	 * 
	 * Valor total da carteira do fundo;
	 * 
	 * Patrimônio líquido;
	 * 
	 * Valor da cota;
	 * 
	 * Captações realizadas no dia;
	 * 
	 * Resgates pagos no dia;
	 * 
	 * Número de cotistas
	 * 
	 * O conjunto de dados disponibiliza os informes diários referentes aos Fundos
	 * de Investimento nos últimos doze meses.
	 * 
	 * Os arquivos referentes ao mês corrente (M) e anterior (M-1) serão atualizados
	 * diariamente com as eventuais reapresentações. A atualização ocorre de terça a
	 * sábado, às 08:00h, com os dados recebidos pelo CVMWeb até as 23:59h do dia
	 * anterior.
	 * 
	 * Os arquivos referentes aos meses M-2 e M-3 serão atualizados semanalmente com
	 * as eventuais reapresentações.
	 * 
	 * Os arquivos referente aos meses M-4, M-5, ..., até M-11 serão atualizados
	 * mensalmente com as eventuais reapresentações.
	 * 
	 * @param ano
	 * @return
	 * @throws IOException
	 */
	public Map<String, List<FundoDiario>> fetchFundoDiario(int ano) throws IOException {
		final String fileName = String.format("inf_diario_fi_%d.zip", ano);
		final String url = "http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/HIST/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"informe_diario",
				fileName)) {

			return new CsvAspect<>(FundoDiario.class)
					.setCapacity(200000)
					.parseZip(is);
		}
	}

	/**
	 * O INFORME DIÁRIO é um demonstrativo que contém as seguintes informações do
	 * fundo, relativas à data de competência:
	 * 
	 * Valor total da carteira do fundo;
	 * 
	 * Patrimônio líquido;
	 * 
	 * Valor da cota;
	 * 
	 * Captações realizadas no dia;
	 * 
	 * Resgates pagos no dia;
	 * 
	 * Número de cotistas
	 * 
	 * O conjunto de dados disponibiliza os informes diários referentes aos Fundos
	 * de Investimento nos últimos doze meses.
	 * 
	 * Os arquivos referentes ao mês corrente (M) e anterior (M-1) serão atualizados
	 * diariamente com as eventuais reapresentações. A atualização ocorre de terça a
	 * sábado, às 08:00h, com os dados recebidos pelo CVMWeb até as 23:59h do dia
	 * anterior.
	 * 
	 * Os arquivos referentes aos meses M-2 e M-3 serão atualizados semanalmente com
	 * as eventuais reapresentações.
	 * 
	 * Os arquivos referente aos meses M-4, M-5, ..., até M-11 serão atualizados
	 * mensalmente com as eventuais reapresentações.
	 * 
	 * @param ano
	 * @param mes
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<FundoDiario> fetchFundoDiario(int ano, int mes) throws IOException {

		final String fileName = String.format("inf_diario_fi_%d%02d.csv", ano, mes);
		final String url = "http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/" + fileName;

		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"informe_diario",
				fileName)) {

			return new CsvAspect<>(FundoDiario.class)
					.setCapacity(200000)
					.parse(is);
		}
	}

	/**
	 * Participantes Intermediários: Informação Cadastral
	 * 
	 * Dados cadastrais de Participantes Intermediários: Bancos Comerciais, Bancos
	 * de Investimentos, Bancos Múltiplos, Caixas Econômicas, Cooperativas de
	 * Crédito, Corretoras e Distribuidoras.
	 * 
	 * O conjunto de dados disponibiliza as informações cadastrais referentes ao
	 * último dia útil.
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<Intermediario> fetchIntermediarios()
			throws IOException {

		final String url = "http://dados.cvm.gov.br/dados/INTERMEDIARIO/CAD/DADOS/inf_cadastral_intermediario.csv";

		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_CADASTROS,
				formatDailyFileName("inf_cadastral_intermediario", ".csv"))) {

			return new CsvAspect<>(Intermediario.class).parse(is);
		}
	}

	/**
	 * Fundos Estruturados: Informação Cadastral
	 * 
	 * Dados cadastrais de fundos estruturados (FII, FACFIF, FAPI, FIDC, FIF, FIIM,
	 * FIP, FMAI, FMIEE, FMP-FGTS, FMP-FGTS CL e FUNCINE), tais como CNPJ, data de
	 * registro e situação do fundo.
	 * 
	 * O conjunto de dados disponibiliza as informações cadastrais referentes ao
	 * último dia útil.
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public List<Fundo> fetchFundosEstruturados() throws IOException {

		final String url = "http://dados.cvm.gov.br/dados/FIE/CAD/DADOS/inf_cadastral_fie.csv";

		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_CADASTROS,
				formatDailyFileName("inf_cadastral_fie", ".csv"))) {

			return new CsvAspect<>(Fundo.class).parse(is);
		}
	}

	/**
	 * Fundos de Investimento: Informação Cadastral
	 * 
	 * Dados cadastrais de fundos de investimento referentes à instrução da CVM
	 * número 555, como CNPJ, data de registro e situação do fundo.
	 * 
	 * O conjunto de dados disponibiliza as informações cadastrais referentes aos
	 * últimos noventa dias, mas existe um histórico desde Julho de 2017.
	 * 
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public List<Fundo> fetchFundos(Date data) throws IOException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		String fileName = String.format(
				"inf_cadastral_fi_%d%02d%02d.csv",
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH));

		String url = "http://dados.cvm.gov.br/dados/FI/CAD/DADOS/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"cadastro",
				fileName)) {

			return new CsvAspect<>(Fundo.class)
					.setCapacity(2500)
					.parse(is);
		}
	}

	/**
	 * Fundos de Investimento: Informação Cadastral
	 * 
	 * Dados cadastrais de fundos de investimento referentes à instrução da CVM
	 * número 555, como CNPJ, data de registro e situação do fundo.
	 * 
	 * O conjunto de dados disponibiliza as informações cadastrais referentes aos
	 * últimos noventa dias, mas existe um histórico desde Julho de 2017.
	 * 
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public List<Fundo> fetchFundos() throws IOException {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.HOUR) > 8) {
			cal.add(Calendar.DAY_OF_MONTH, -3);
		} else {
			cal.add(Calendar.DAY_OF_MONTH, -2);
		}
		return fetchFundos(cal.getTime());
	}

	/**
	 * Fundos Estruturados: Medidas
	 * 
	 * Medidas de fundos estruturados (FIP, FIDC, FII, etc.): Patrimônio Líquido e
	 * Número de Cotistas.
	 * 
	 * O conjunto de dados disponibiliza as medidas referentes aos últimos doze
	 * meses.
	 * 
	 * Os arquivos referentes ao último mês completo (M-1) e anterior (M-2) serão
	 * atualizados semanalmente com as eventuais reapresentações.
	 * 
	 * Os arquivos referente aos meses M-3, M-4, ..., até M-12 serão atualizados
	 * mensalmente com as eventuais reapresentações.
	 * 
	 * Histórico desde 01/2017 (incluindo arquivos não sujeitos à política de
	 * atualização)
	 * 
	 * 
	 * @param ano
	 * @param mes
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public List<FundoMedida> fetchMedidasFundosEstruturados(int ano, int mes)
			throws IOException {

		String fileName = String.format(
				"medidas_mes_fie_%d%02d.csv",
				ano,
				mes);

		String url = "http://dados.cvm.gov.br/dados/FIE/MEDIDAS/DADOS/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"medidas",
				fileName)) {

			return new CsvAspect<>(FundoMedida.class)
					.setCapacity(20000)
					.parse(is);

		}
	}

	/**
	 * Fundos de Investimento: Documentos: Eventuais, DFs e Demonstrativos
	 * Trimestrais
	 * 
	 * O conjunto de dados disponibiliza os Documentos Eventuais, Demonstrações
	 * Financeiras (DFs) e Demonstrativos Trimestrais de Fundos de Investimento nos
	 * últimos cinco anos.
	 * 
	 * São Documentos Eventuais:
	 * 
	 * Ata de Assembléia Geral Ordinária (AGO)
	 * 
	 * Edital de Convocação para Ass. Geral Ordinária (EDITAL AGO)
	 * 
	 * Outros
	 * 
	 * Fato Relevante
	 * 
	 * Demonstrações Contábeis
	 * 
	 * Regulamento de Fundos
	 * 
	 * Prospecto de Fundos
	 * 
	 * Prospecto de Distribuição
	 * 
	 * Aviso ao Mercado
	 * 
	 * Proposta do Administrador
	 * 
	 * Relatório de Classificação de Risco
	 * 
	 * Anúncio de Início de Distribuição (AID)
	 * 
	 * Anúncio de Encerramento de Distribuição (AED)
	 * 
	 * Os arquivos referentes aos anos corrente e anterior serão atualizados
	 * semanalmente com as eventuais reapresentações.
	 * 
	 * Os arquivos referentes aos anos A-2, ..., A-4 serão atualizados mensalmente
	 * com as eventuais reapresentações.
	 * 
	 * @param ano
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<FundoInfoEventual> fetchFundoInfosEventuais(int ano) throws IOException {

		String fileName = String.format(
				"eventual_fi_%d.csv",
				ano);

		String url = "http://dados.cvm.gov.br/dados/FI/DOC/EVENTUAL/DADOS/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"eventuais",
				fileName)) {

			return new CsvAspect<>(FundoInfoEventual.class)
					.setCapacity(20000)
					.parse(is);

		}

	}

	/**
	 * <h1>Composição e Diversificação das Aplicações</h1>
	 * 
	 * <p>
	 * O documento COMPOSIÇÃO E DIVERSIFICAÇÃO DAS APLICAÇÕES (CDA) descreve os
	 * ativos que compõem as carteiras de fundos de investimento.
	 * </p>
	 * 
	 * <p>
	 * O conjunto de dados disponibiliza as carteiras dos seguintes tipos de fundos:
	 * </p>
	 * 
	 * <ul>
	 * <li>FACFIF: FUNDOS DE APLICAÇÃO EM COTAS DE FUNDOS DE INVESTIMENTO</li>
	 * <li>FAPI: FUNDOS DE APOSENTADORIA PROGRAMADA INDIVIDUAL</li>
	 * <li>FI: FUNDOS DE INVESTIMENTO ICVM 555</li>
	 * <li>FI-FGTS: FUNDOS DE INVESTIMENTO FGTS</li>
	 * <li>FIC-FITVM: FUNDOS DE INVESTIMENTO EM COTAS DE FITVM</li>
	 * <li>FIEX: FUNDOS DE INVESTIMENTO NO EXTERIOR</li>
	 * <li>FIF: FUNDOS DE INVESTIMENTO FINANCEIRO</li>
	 * <li>FIFDIV: FUNDOS DE INVESTIMENTO FINANCEIRO -DIVIDA ESTAD/MUN</li>
	 * <li>FIIM: FUNDOS DE INVESTIMENTO EM ÍNDICE DE MERCADO</li>
	 * <li>FIP: FUNDOS DE INVESTIMENTO EM PARTICIPAÇÕES</li>
	 * <li>FITVM: FUNDOS DE INVESTIMENTO EM TÍTULOS E VALORES MOBILIÁRIOS</li>
	 * <li>FMAI: FUNDOS MÚTUOS EM AÇÕES INCENTIVADAS</li>
	 * <li>FMIEE: FUNDOS MÚTUOS DE INVESTIMENTOS EM EMPR. EMERGENTES</li>
	 * <li>FMP-FGTS: FUNDOS MÚTUOS DE PRIVATIZAÇÃO – FGTS</li>
	 * <li>FMP-FGTS CL: FUNDOS MÚTUOS DE PRIVATIZAÇÃO - FGTS CARTEIRA LIVRE</li>
	 * <li>FUNCINE: FUNDOS FINANC. DA INDÚSTRIA CINEMATOGRÁFICA NACIONAL</li>
	 * </ul>
	 * 
	 * <p>
	 * As aplicações dos fundos estão organizadas nos arquivos de dados conforme a
	 * estrutura de blocos do padrão XML do Informe CDA.
	 * </p>
	 * 
	 * <ul>
	 * <li>Bloco 1: TÍTULOS PÚBLICOS DO SELIC</li>
	 * <li>Bloco 2: COTAS DE FUNDOS DE INVESTIMENTO</li>
	 * <li>Bloco 3: SWAP</li>
	 * <li>Bloco 4: DEMAIS ATIVOS CODIFICADOS</li>
	 * <li>Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF</li>
	 * <li>Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO</li>
	 * <li>Bloco 7: INVESTIMENTO NO EXTERIOR</li>
	 * <li>Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS</li>
	 * </ul>
	 * <p>
	 * Importante: Os detalhes sobre as aplicações somente estarão disponíveis após
	 * expirado o prazo de confidencialidade solicitado pelos administradores dos
	 * fundos no envio das informações à CVM.
	 * </p>
	 * <p>
	 * O conjunto de dados disponibiliza as carteiras dos Fundos de Investimento nos
	 * últimos doze meses, a partir de JAN/2018.
	 * 
	 * Os arquivos referentes aos últimos quatro meses completos (M-1, M-2, M-3 e
	 * M-4) serão atualizados semanalmente com as eventuais reapresentações.
	 * 
	 * Os arquivos referente aos meses M-5, M-6, ..., até M-12 serão atualizados
	 * mensalmente com as eventuais reapresentações.
	 * </p>
	 * 
	 * 
	 * @param ano
	 * @param mes
	 * @return
	 * @throws IOException
	 */
	public FundoCarteira fetchFundoCarteiras(int ano, int mes) throws IOException {

		String fileName = String.format(
				"cda_fi_%d%d.zip",
				ano, mes);

		String url = "http://dados.cvm.gov.br/dados/FI/DOC/CDA/DADOS/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"carteiras",
				fileName)) {

			return parseFundoCarteiraZip(is);

		}

	}

	/**
	 * Histórico desde 2005 (arquivos não sujeitos à política de atualização)
	 * 
	 * @param ano
	 * @return
	 * @throws IOException
	 */
	public FundoCarteira fetchFundoCarteiras(int ano) throws IOException {

		String fileName = String.format(
				"cda_fi_%d.zip",
				ano);

		String url = "http://dados.cvm.gov.br/dados/FI/DOC/CDA/DADOS/HIST/" + fileName;
		try (InputStream is = locateInputStream(
				new URL(url),
				false,
				ROOT_FUNDOS,
				"carteiras",
				fileName)) {

			return parseFundoCarteiraZip(is);

		}
	}

	/**
	 * Some actual parsing of the URL and data;
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private FundoCarteira parseFundoCarteiraZip(InputStream is) throws IOException {
		final FundoCarteira carteira = new FundoCarteira();
		try (ZipInputStream zis = new ZipInputStream(is)) {
			for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
				String entryName = entry.getName().toLowerCase();
				GenericLogger.info(getClass(), "parsing " + entryName);
				if (entryName.startsWith("cda_fi_blc_1_")) {
					new CsvAspect<FundoCarteiraItemTituloPublico>(FundoCarteiraItemTituloPublico.class)
							.parse(zis, carteira.titulos_publicos);
				} else if (entryName.startsWith("cda_fi_blc_2_")) {
					new CsvAspect<FundoCarteiraItemCotaDeFundo>(FundoCarteiraItemCotaDeFundo.class)
							.parse(zis, carteira.cotas_de_fundos);
				} else if (entryName.startsWith("cda_fi_blc_3_")) {
					new CsvAspect<FundoCarteiraItemSwap>(FundoCarteiraItemSwap.class)
							.parse(zis, carteira.swaps);
				} else if (entryName.startsWith("cda_fi_blc_4_")) {
					new CsvAspect<FundoCarteiraItemAtivoCodificado>(FundoCarteiraItemAtivoCodificado.class)
							.parse(zis, carteira.ativos_codificados);
				} else if (entryName.startsWith("cda_fi_blc_5_")) {
					new CsvAspect<FundoCarteiraItemDepositoAPrazo>(FundoCarteiraItemDepositoAPrazo.class)
							.parse(zis, carteira.depositos_a_prazo);
				} else if (entryName.startsWith("cda_fi_blc_6_")) {
					new CsvAspect<FundoCarteiraItemCreditoPrivado>(FundoCarteiraItemCreditoPrivado.class)
							.parse(zis, carteira.credito_privado);
				} else if (entryName.startsWith("cda_fi_blc_7_")) {
					new CsvAspect<FundoCarteiraItemInvestimentoExterior>(FundoCarteiraItemInvestimentoExterior.class)
							.parse(zis, carteira.investimentos_no_exterior);
				} else if (entryName.startsWith("cda_fi_blc_8_")) {
					new CsvAspect<FundoCarteiraItemNaoCodificado>(FundoCarteiraItemNaoCodificado.class)
							.parse(zis, carteira.demais_nao_codificados);
				}
			}
		}
		return carteira;
	}
}
