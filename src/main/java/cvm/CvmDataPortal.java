package cvm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jports.adapters.InputStreamAdapter;
import jports.text.CsvAspect;

/**
 * An open access to cvm public data portal;
 * 
 * @author rportela
 *
 */
public class CvmDataPortal {

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
	public List<CiaAberta> fetchCiasAbertas() throws MalformedURLException,
			IOException {
		CsvAspect<CiaAberta> aspect = new CsvAspect<>(CiaAberta.class);
		aspect.setCapacity(2500);
		return aspect.parse(
				new URL(
						"http://dados.cvm.gov.br/dados/CIA_ABERTA/CAD/DADOS/inf_cadastral_cia_aberta.csv"));
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
	public List<FundoDiario> fetchFundoDiario() throws MalformedURLException,
			IOException {
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
	public LinkedHashMap<String, List<FundoDiario>> fetchFundoDiario(int ano) throws IOException {
		final CsvAspect<FundoDiario> aspect = new CsvAspect<FundoDiario>(FundoDiario.class);
		aspect.setCapacity(200000);
		final URL zipUrl = new URL(
				String.format(
						"http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/HIST/inf_diario_fi_%d.zip",
						ano));
		ZipEntry entry;
		try (InputStream is = zipUrl.openStream()) {
			try (ZipInputStream zis = new ZipInputStream(is)) {
				LinkedHashMap<String, List<FundoDiario>> map = new LinkedHashMap<String, List<FundoDiario>>(12);
				while ((entry = zis.getNextEntry()) != null) {
					String name = entry.getName();
					try {
						List<FundoDiario> list = aspect.parse(zis);
						map.put(name, list);
					} catch (Exception e) {
						throw new RuntimeException(zipUrl + " -> Unable to parse on " + name, e);
					}
				}
				zis.close();
				return map;
			} finally {
				is.close();
			}
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
	public List<FundoDiario> fetchFundoDiario(int ano, int mes) throws MalformedURLException,
			IOException {
		final CsvAspect<FundoDiario> aspect = new CsvAspect<FundoDiario>(FundoDiario.class);
		aspect.setCapacity(200000);
		String url = String.format(
				"http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/inf_diario_fi_%d%02d.csv",
				ano,
				mes);
		return aspect.parse(new URL(url));
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
			throws MalformedURLException,
			IOException {
		CsvAspect<Intermediario> aspect = new CsvAspect<>(Intermediario.class);
		return aspect.parse(
				new URL(
						"http://dados.cvm.gov.br/dados/INTERMEDIARIO/CAD/DADOS/inf_cadastral_intermediario.csv"));
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
	public List<Fundo> fetchFundosEstruturados() throws MalformedURLException,
			IOException {
		CsvAspect<Fundo> aspect = new CsvAspect<>(Fundo.class);
		return aspect.parse(
				new URL(
						"http://dados.cvm.gov.br/dados/FIE/CAD/DADOS/inf_cadastral_fie.csv"));
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
	public List<Fundo> fetchFundos(Date data) throws MalformedURLException,
			IOException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			cal.add(Calendar.DAY_OF_MONTH, -2);
			break;
		case Calendar.SATURDAY:
			cal.add(Calendar.DAY_OF_MONTH, -1);
			break;
		}

		String urlFormat = "http://dados.cvm.gov.br/dados/FI/CAD/DADOS/inf_cadastral_fi_%d%02d%02d.csv";
		String urlString = String.format(
				urlFormat,
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH));

		CsvAspect<Fundo> aspect = new CsvAspect<>(Fundo.class);
		return aspect.parse(new URL(urlString));
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
	public List<Fundo> fetchFundos() throws MalformedURLException,
			IOException {
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
			throws MalformedURLException,
			IOException {
		String url = String.format(
				"http://dados.cvm.gov.br/dados/FIE/MEDIDAS/DADOS/medidas_mes_fie_%d%02d.csv",
				ano,
				mes);
		final CsvAspect<FundoMedida> aspect = new CsvAspect<FundoMedida>(FundoMedida.class);
		aspect.setCapacity(20000);
		return aspect.parse(new URL(url));

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
	public List<FundoInfoEventual> fetchFundoInfosEventuais(int ano) throws MalformedURLException,
			IOException {
		String url = String.format(
				"http://dados.cvm.gov.br/dados/FI/DOC/EVENTUAL/DADOS/eventual_fi_%d.csv",
				ano);
		final CsvAspect<FundoInfoEventual> aspect = new CsvAspect<FundoInfoEventual>(FundoInfoEventual.class);
		aspect.setCapacity(20000);
		return aspect.parse(new URL(url));
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

		final String url = String.format(
				"http://dados.cvm.gov.br/dados/FI/DOC/CDA/DADOS/cda_fi_%d%d.zip",
				ano, mes);

		return parseFundoCarteiraZip(url);
	}

	/**
	 * Histórico desde 2005 (arquivos não sujeitos à política de atualização)
	 * 
	 * @param ano
	 * @return
	 * @throws IOException
	 */
	public FundoCarteira fetchFundoCarteiras(int ano) throws IOException {

		final String url = String.format("http://dados.cvm.gov.br/dados/FI/DOC/CDA/DADOS/HIST/cda_fi_%d.zip",
				ano);

		return parseFundoCarteiraZip(url);

	}

	/**
	 * Some actual parsing of the URL and data;
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private FundoCarteira parseFundoCarteiraZip(String url) throws IOException {

		byte[] zipBytes = null;
		try (InputStream us = new URL(url).openStream()) {
			zipBytes = new InputStreamAdapter().toBytes(us);
			us.close();
		}

		final FundoCarteira carteira = new FundoCarteira();
		try (ByteArrayInputStream is = new ByteArrayInputStream(zipBytes)) {
			ZipInputStream zis = new ZipInputStream(is);
			for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
				String entry_name = entry.getName().toLowerCase();
				System.out.println("parsing " + entry_name);
				if (entry_name.startsWith("cda_fi_blc_1_")) {
					new CsvAspect<FundoCarteiraItemTituloPublico>(FundoCarteiraItemTituloPublico.class)
							.parse(zis, carteira.titulos_publicos);
				} else if (entry_name.startsWith("cda_fi_blc_2_")) {
					new CsvAspect<FundoCarteiraItemCotaDeFundo>(FundoCarteiraItemCotaDeFundo.class)
							.parse(zis, carteira.cotas_de_fundos);
				} else if (entry_name.startsWith("cda_fi_blc_3_")) {
					new CsvAspect<FundoCarteiraItemSwap>(FundoCarteiraItemSwap.class)
							.parse(zis, carteira.swaps);
				} else if (entry_name.startsWith("cda_fi_blc_4_")) {
					new CsvAspect<FundoCarteiraItemAtivoCodificado>(FundoCarteiraItemAtivoCodificado.class)
							.parse(zis, carteira.ativos_codificados);
				} else if (entry_name.startsWith("cda_fi_blc_5_")) {
					new CsvAspect<FundoCarteiraItemDepositoAPrazo>(FundoCarteiraItemDepositoAPrazo.class)
							.parse(zis, carteira.depositos_a_prazo);
				} else if (entry_name.startsWith("cda_fi_blc_6_")) {
					new CsvAspect<FundoCarteiraItemCreditoPrivado>(FundoCarteiraItemCreditoPrivado.class)
							.parse(zis, carteira.credito_privado);
				} else if (entry_name.startsWith("cda_fi_blc_7_")) {
					new CsvAspect<FundoCarteiraItemInvestimentoExterior>(FundoCarteiraItemInvestimentoExterior.class)
							.parse(zis, carteira.investimentos_no_exterior);
				} else if (entry_name.startsWith("cda_fi_blc_8_")) {
					new CsvAspect<FundoCarteiraItemNaoCodificado>(FundoCarteiraItemNaoCodificado.class)
							.parse(zis, carteira.demais_nao_codificados);
				}
			}
			zis.close();
			is.close();
		}
		return carteira;
	}
}
