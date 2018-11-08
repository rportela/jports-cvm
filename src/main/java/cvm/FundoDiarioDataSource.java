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

public class FundoDiarioDataSource {

	private static final CsvAspect<FundoDiario> FUNDO_DIARIO_ASPECT;

	static {
		FUNDO_DIARIO_ASPECT = new CsvAspect<FundoDiario>(FundoDiario.class);
		FUNDO_DIARIO_ASPECT.setCapacity(200000);
	}

	public String getUrl() {
		return "http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/inf_diario_fi_%d%02d.csv";
	}

	public String getName() {
		return "Fundos de Investimento: Documentos: Informe Diário";
	}

	public String getSummary() {
		return "O INFORME DIÁRIO é um demonstrativo que contém as seguintes informações do fundo, \r\n" +
				"relativas à data de competência:\r\n" +
				"Valor total da carteira do fundo;\r\n" +
				"Patrimônio líquido;\r\n" +
				"Valor da cota;\r\n" +
				"Captações realizadas no dia;\r\n" +
				"Resgates pagos no dia;\r\n" +
				"Número de cotistas";
	}

	public List<FundoDiario> fetch() throws MalformedURLException, IOException {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_MONTH) < 3) {
			cal.add(Calendar.MONTH, -1);
		}
		return fetch(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
	}

	public LinkedHashMap<String, List<FundoDiario>> fetch(int year) throws IOException {
		URL zipUrl = new URL(
				String.format(
						"http://dados.cvm.gov.br/dados/FI/DOC/INF_DIARIO/DADOS/HIST/inf_diario_fi_%d.zip",
						year));
		ZipEntry entry;
		try (InputStream is = zipUrl.openStream()) {
			try (ZipInputStream zis = new ZipInputStream(is)) {
				LinkedHashMap<String, List<FundoDiario>> map = new LinkedHashMap<String, List<FundoDiario>>(12);
				while ((entry = zis.getNextEntry()) != null) {
					String name = entry.getName();
					List<FundoDiario> list = FUNDO_DIARIO_ASPECT.parse(zis);
					map.put(name, list);
				}
				zis.close();
				return map;
			} finally {
				is.close();
			}
		}
	}

	public List<FundoDiario> fetch(int year, int month) throws MalformedURLException, IOException {
		String url = String.format(getUrl(), year, month);
		return FUNDO_DIARIO_ASPECT.parse(new URL(url));
	}

}
