package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvm.CvmCiaAberta;
import cvm.CvmDataPortal;
import cvm.CvmFundoDiario;
import cvm.CvmParticipanteIntermediario;

public class CvmDataPortalTests {

	@Test
	public void fetchCiasAbertas() throws MalformedURLException, IOException {
		List<CvmCiaAberta> list = new CvmDataPortal().fetchCiasAbertas();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void fetchFundoDiario() throws MalformedURLException, IOException {
		List<CvmFundoDiario> list = new CvmDataPortal().fetchFundoDiario();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioJanOf2017() throws MalformedURLException, IOException {
		List<CvmFundoDiario> list = new CvmDataPortal().fetchFundoDiario(2017, 01);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void fetchFundoDiarioYearOf2016() throws MalformedURLException, IOException {
		LinkedHashMap<String, List<CvmFundoDiario>> map = new CvmDataPortal().fetchFundoDiario(2016);
		Assert.assertTrue(map.size() == 12);
	}

	@Test
	public void fetchParticipantesIntermediarios() throws MalformedURLException, IOException {
		List<CvmParticipanteIntermediario> list = new CvmDataPortal().fetchParticipantesIntermediarios();
		Assert.assertTrue(list.size() > 10);
	}

}
