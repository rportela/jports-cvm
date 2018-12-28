package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvm.CiaAberta;
import cvm.CvmDataPortal;
import cvm.Fundo;
import cvm.FundoCarteira;
import cvm.FundoDiario;
import cvm.FundoInfoEventual;
import cvm.FundoMedida;
import cvm.Intermediario;

public class CvmDataPortalTests {

	@Test
	public void fetchCiasAbertas() throws MalformedURLException,
			IOException {
		List<CiaAberta> list = new CvmDataPortal().fetchCiasAbertas();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void fetchFundoDiario() throws MalformedURLException,
			IOException {
		List<FundoDiario> list = new CvmDataPortal().fetchFundoDiario();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioJan2017() throws MalformedURLException,
			IOException {
		List<FundoDiario> list = new CvmDataPortal().fetchFundoDiario(2017, 01);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioYear2016() throws MalformedURLException,
			IOException {
		LinkedHashMap<String, List<FundoDiario>> map = new CvmDataPortal().fetchFundoDiario(2016);
		Assert.assertTrue(map.size() == 12);
	}

	@Test
	public void fetchParticipantesIntermediarios() throws MalformedURLException,
			IOException {
		List<Intermediario> list = new CvmDataPortal().fetchIntermediarios();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundosEstruturados() throws MalformedURLException,
			IOException {
		List<Fundo> list = new CvmDataPortal().fetchFundosEstruturados();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundos() throws MalformedURLException,
			IOException {
		List<Fundo> list = new CvmDataPortal().fetchFundos();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosJan2017() throws MalformedURLException,
			IOException {
		List<FundoMedida> list = new CvmDataPortal().fetchMedidasFundosEstruturados(2017, 1);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosOut2018() throws MalformedURLException,
			IOException {
		List<FundoMedida> list = new CvmDataPortal().fetchMedidasFundosEstruturados(2018, 10);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoInfosEventuais2018() throws MalformedURLException,
			IOException {
		List<FundoInfoEventual> list = new CvmDataPortal().fetchFundoInfosEventuais(2018);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchCarteirasDosFundosOut2018() throws IOException {
		System.out.println(new Date());
		FundoCarteira carteiras = new CvmDataPortal().fetchFundoCarteiras(2018, 10);
		System.out.println(new Date());
		Assert.assertTrue(carteiras.titulos_publicos.size() > 0);
	}

	@Test
	public void fetchCarteirasDosFundos2017() throws IOException {
		System.out.println(new Date());
		FundoCarteira carteiras = new CvmDataPortal().fetchFundoCarteiras(2017);
		System.out.println(new Date());
		Assert.assertTrue(carteiras.titulos_publicos.size() > 0);
	}

}
