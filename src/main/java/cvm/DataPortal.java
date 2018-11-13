package cvm;

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

import jports.text.CsvAspect;

/**
 * An open access to cvm public data portal;
 * 
 * @author rportela
 *
 */
public class DataPortal {

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
	public List<CiaAberta> fetchCiasAbertas() throws MalformedURLException, IOException {
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
	public List<FundoDiario> fetchFundoDiario() throws MalformedURLException, IOException {
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
	public List<FundoDiario> fetchFundoDiario(int ano, int mes) throws MalformedURLException, IOException {
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
			throws MalformedURLException, IOException {
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
	public List<Fundo> fetchFundosEstruturados() throws MalformedURLException, IOException {
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
	public List<Fundo> fetchFundos(Date data) throws MalformedURLException, IOException {

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
	public List<Fundo> fetchFundos() throws MalformedURLException, IOException {
		return fetchFundos(new Date());
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
			throws MalformedURLException, IOException {
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
	public List<FundoInfoEventual> fetchFundoInfosEventuais(int ano) throws MalformedURLException, IOException {
		String url = String.format(
				"http://dados.cvm.gov.br/dados/FI/DOC/EVENTUAL/DADOS/eventual_fi_%d.csv",
				ano);
		final CsvAspect<FundoInfoEventual> aspect = new CsvAspect<FundoInfoEventual>(FundoInfoEventual.class);
		aspect.setCapacity(20000);
		return aspect.parse(new URL(url));
	}

}
