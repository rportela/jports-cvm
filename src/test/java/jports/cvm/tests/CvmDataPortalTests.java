package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvm.CiaAberta;
import cvm.DataPortal;
import cvm.Fundo;
import cvm.FundoDiario;
import cvm.FundoInfoEventual;
import cvm.FundoMedida;
import cvm.Intermediario;

public class CvmDataPortalTests {

	@Test
	public void fetchCiasAbertas() throws MalformedURLException, IOException {
		List<CiaAberta> list = new DataPortal().fetchCiasAbertas();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void fetchFundoDiario() throws MalformedURLException, IOException {
		List<FundoDiario> list = new DataPortal().fetchFundoDiario();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioJanOf2017() throws MalformedURLException, IOException {
		List<FundoDiario> list = new DataPortal().fetchFundoDiario(2017, 01);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioYearOf2016() throws MalformedURLException, IOException {
		LinkedHashMap<String, List<FundoDiario>> map = new DataPortal().fetchFundoDiario(2016);
		Assert.assertTrue(map.size() == 12);
	}

	@Test
	public void fetchParticipantesIntermediarios() throws MalformedURLException, IOException {
		List<Intermediario> list = new DataPortal().fetchIntermediarios();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundosEstruturados() throws MalformedURLException, IOException {
		List<Fundo> list = new DataPortal().fetchFundosEstruturados();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundos() throws MalformedURLException, IOException {
		List<Fundo> list = new DataPortal().fetchFundos();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosJanOf2017() throws MalformedURLException, IOException {
		List<FundoMedida> list = new DataPortal().fetchMedidasFundosEstruturados(2017, 1);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosOutOf2018() throws MalformedURLException, IOException {
		List<FundoMedida> list = new DataPortal().fetchMedidasFundosEstruturados(2018, 10);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoInfosEventuais2018() throws MalformedURLException, IOException {
		List<FundoInfoEventual> list = new DataPortal().fetchFundoInfosEventuais(2018);
		Assert.assertTrue(list.size() > 10);
	}

}
