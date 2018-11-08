package cvm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jports.text.CsvAspect;

public class CiaAbertaDataSource {

	private static final CsvAspect<CiaAberta> CIA_ABERTA_ASPECT = new CsvAspect<CiaAberta>(CiaAberta.class);

	public String getUrl() {
		return "http://dados.cvm.gov.br/dados/CIA_ABERTA/CAD/DADOS/inf_cadastral_cia_aberta.csv";
	}

	public String getName() {
		return "Cias. Abertas";
	}

	public String getSummary() {
		return "Dados cadastrais de Companhias Abertas";
	}

	public List<CiaAberta> fetch() throws MalformedURLException, IOException {
		return CIA_ABERTA_ASPECT.parse(new URL(getUrl()));
	}

}
