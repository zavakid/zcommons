/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.zavakid.commons.jetty;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.zavakid.commons.jetty.servlet.TestServlet;

/**
 * @author zavakid 2013-5-25 上午9:29:40
 * @since 1.0
 */
public class JettyServerTest {

	// private static ChromeDriverService service;
	private WebDriver driver;

	@Test
	public void testJettyServer() throws InterruptedException {
		final JettyServer httpServer = new JettyServer();
		httpServer.setContextPath("/");
		httpServer.setMaxThreads(10);
		httpServer.setParentLoaderPriority(true);
		httpServer.setPort(8080);
		httpServer.setResourceBase(JettyServerTest.class.getResource(
				"/test-web").toString());
		httpServer.setThreadName("jetty-test-thread");
		httpServer.start();
		try {
			testPage();
		} finally {
			httpServer.stop();
			driver.quit();
			driver.close();
		}
	}

	private void testPage() {
		// driver = new RemoteWebDriver(service.getUrl(),
		// DesiredCapabilities.chrome());
		driver = new FirefoxDriver();
		driver.get("http://127.0.0.1:8080/test");
		String content = driver.getPageSource();
		System.out.println("Page content is: " + content);
		assertEquals(TestServlet.OUTPUT_TEXT, content);
	}

	@BeforeClass
	public static void createAndStartService() throws IOException {
		// service = new ChromeDriverService.Builder().usingDriverExecutable(new
		// File(getChromeDriverPath()))
		// .usingAnyFreePort()
		// .build();
		// service.start();
	}

	@AfterClass
	public static void createAndStopService() {
		// service.stop();
	}

	public static String getChromeDriverPath() {
		URL url = JettyServerTest.class.getResource("/");
		File file = new File(url.toString());
		File rootProjectPath = file.getParentFile().getParentFile()
				.getParentFile();
		String chromeDriverPath = rootProjectPath.toString() + File.separator
				+ "lib" + File.separator + "chromedriver";
		if (chromeDriverPath.startsWith("file:")) {
			chromeDriverPath = chromeDriverPath.substring("file:".length(),
					chromeDriverPath.length());
		}
		if (!chromeDriverPath.startsWith("/")) {
			chromeDriverPath = "/" + chromeDriverPath;
		}
		return chromeDriverPath;
	}
}
