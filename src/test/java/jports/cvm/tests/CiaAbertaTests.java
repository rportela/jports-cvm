package jports.cvm.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvm.CiaAberta;
import cvm.CiaAbertaDataSource;

public class CiaAbertaTests {

	@Test
	public void testFetch() throws MalformedURLException, IOException {

		List<CiaAberta> list = new CiaAbertaDataSource().fetch();
		Assert.assertTrue(list.size() > 0);

	}

}
