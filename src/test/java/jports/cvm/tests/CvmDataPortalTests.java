package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cvm.CiaAberta;
import cvm.CiaEstrangeira;
import cvm.CvmDataSource;
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
		List<CiaAberta> list = new CvmDataSource(null).fetchCiasAbertas();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void fetchFundoDiario() throws MalformedURLException,
			IOException {
		List<FundoDiario> list = new CvmDataSource(null).fetchFundoDiario();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioJan2017() throws MalformedURLException,
			IOException {
		List<FundoDiario> list = new CvmDataSource(null).fetchFundoDiario(2017, 01);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioYear2016() throws MalformedURLException,
			IOException {
		Map<String, List<FundoDiario>> map = new CvmDataSource(null).fetchFundoDiario(2016);
		Assert.assertTrue(map.size() == 12);
	}

	@Test
	public void fetchParticipantesIntermediarios() throws MalformedURLException,
			IOException {
		List<Intermediario> list = new CvmDataSource(null).fetchIntermediarios();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundosEstruturados() throws MalformedURLException,
			IOException {
		List<Fundo> list = new CvmDataSource(null).fetchFundosEstruturados();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundos() throws MalformedURLException,
			IOException {
		List<Fundo> list = new CvmDataSource(null).fetchFundos();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosJan2017() throws MalformedURLException,
			IOException {
		List<FundoMedida> list = new CvmDataSource(null).fetchMedidasFundosEstruturados(2017, 1);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchMedidasFundosEstruturadosOut2018() throws MalformedURLException,
			IOException {
		List<FundoMedida> list = new CvmDataSource(null).fetchMedidasFundosEstruturados(2018, 10);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoInfosEventuais2018() throws MalformedURLException,
			IOException {
		List<FundoInfoEventual> list = new CvmDataSource(null).fetchFundoInfosEventuais(2018);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchCarteirasDosFundosOut2018() throws IOException {
		System.out.println(new Date());
		FundoCarteira carteiras = new CvmDataSource(null).fetchFundoCarteiras(2018, 10);
		System.out.println(new Date());
		Assert.assertTrue(carteiras.titulos_publicos.size() > 10);
	}

	@Test
	public void fetchCarteirasDosFundos2017() throws IOException {
		System.out.println(new Date());
		FundoCarteira carteiras = new CvmDataSource(null).fetchFundoCarteiras(2017);
		System.out.println(new Date());
		Assert.assertTrue(carteiras.titulos_publicos.size() > 10);
	}

	@Test
	public void fetchCiasEstrangeiras() throws IOException {
		System.out.println(new Date());
		List<CiaEstrangeira> ciasEstrangeiras = new CvmDataSource(null).fetchCiasEstrangeiras();
		System.out.println(new Date());
		Assert.assertTrue(ciasEstrangeiras.size() > 10);
	}

}
