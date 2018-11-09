package cvm;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
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
	public List<CvmCiaAberta> fetchCiasAbertas() throws MalformedURLException, IOException {
		CsvAspect<CvmCiaAberta> aspect = new CsvAspect<>(CvmCiaAberta.class);
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
	public List<CvmFundoDiario> fetchFundoDiario() throws MalformedURLException, IOException {
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
	 * @param year
	 * @return
	 * @throws IOException
	 */
	public LinkedHashMap<String, List<CvmFundoDiario>> fetchFundoDiario(int year) throws IOException {
		final CsvAspect<CvmFundoDiario> aspect = new CsvAspect<CvmFundoDiario>(CvmFundoDiario.class);
		aspect.setCapacity(200000);
		final URL zipUrl = new URL(
				String.format(
						"http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/HIST/inf_diario_fi_%d.zip",
						year));
		ZipEntry entry;
		try (InputStream is = zipUrl.openStream()) {
			try (ZipInputStream zis = new ZipInputStream(is)) {
				LinkedHashMap<String, List<CvmFundoDiario>> map = new LinkedHashMap<String, List<CvmFundoDiario>>(12);
				while ((entry = zis.getNextEntry()) != null) {
					String name = entry.getName();
					List<CvmFundoDiario> list = aspect.parse(zis);
					map.put(name, list);
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
	 * @param year
	 * @param month
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<CvmFundoDiario> fetchFundoDiario(int year, int month) throws MalformedURLException, IOException {
		final CsvAspect<CvmFundoDiario> aspect = new CsvAspect<CvmFundoDiario>(CvmFundoDiario.class);
		aspect.setCapacity(200000);
		String url = String.format(
				"http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/inf_diario_fi_%d%02d.csv",
				year,
				month);
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
	public List<CvmParticipanteIntermediario> fetchParticipantesIntermediarios()
			throws MalformedURLException, IOException {
		CsvAspect<CvmParticipanteIntermediario> aspect = new CsvAspect<>(CvmParticipanteIntermediario.class);
		return aspect.parse(
				new URL(
						"http://dados.cvm.gov.br/dados/INTERMEDIARIO/CAD/DADOS/inf_cadastral_intermediario.csv"));
	}

}
