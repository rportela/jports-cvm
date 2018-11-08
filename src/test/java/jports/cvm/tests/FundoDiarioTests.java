package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvm.FundoDiario;
import cvm.FundoDiarioDataSource;

public class FundoDiarioTests {

	@Test
	public void testCurrentMonth() throws MalformedURLException, IOException {
		List<FundoDiario> list = new FundoDiarioDataSource().fetch();
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void testJanOf2017() throws MalformedURLException, IOException {
		List<FundoDiario> list = new FundoDiarioDataSource().fetch(2017, 01);
		Assert.assertTrue(list.size() > 10);
	}

	@Test
	public void testYearOf2016() throws MalformedURLException, IOException {
		LinkedHashMap<String, List<FundoDiario>> map = new FundoDiarioDataSource().fetch(2016);
		Assert.assertTrue(map.size() == 12);
	}

}
