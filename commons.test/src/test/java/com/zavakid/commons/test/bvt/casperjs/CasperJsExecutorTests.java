package com.zavakid.commons.test.bvt.casperjs;

import static org.junit.Assert.fail;

import java.net.URL;

import org.junit.Test;

import com.zavakid.commons.test.casperjs.CasperJsException;
import com.zavakid.commons.test.casperjs.CasperJsExecutor;

public class CasperJsExecutorTests {

	@Test
	public void testCasperjsWithSuccess(){
		URL url = CasperJsExecutorTests.class.getResource("/casperjs/test.js");
		CasperJsExecutor.execCasperJsFile(url.getFile());
	}
	
	@Test
	public void testCasperjsWithFail(){
		URL url = CasperJsExecutorTests.class.getResource("/casperjs/test-fail.js");
		try {
			CasperJsExecutor.execCasperJsFile(url.getFile());
		} catch (CasperJsException e) {
			// ignore
			return;
		}
		fail("unreachable code");
		
	}
}
