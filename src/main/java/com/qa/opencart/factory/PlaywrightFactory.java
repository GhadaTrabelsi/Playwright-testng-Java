package com.qa.opencart.factory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	Page page;
	BrowserContext browserContext;
	Properties prop;
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}
 public Page initBrowser(Properties prop) {
	String browserName =  prop.getProperty("browser").trim();
	 System.out.println("Browser name is "+browserName);
	 
	 //Playwright.create() --> Crée une instance de Playwright pour démarrer les interactions avec les navigateurs.
	// playwright = Playwright.create();
	tlPlaywright.set(Playwright.create());
	 
	 switch(browserName.toLowerCase()) {
	 case  "chromium" :
		// browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));

		 break;
	 case  "firefox" :
		 //browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));

		 break;
		 
	 case  "safari" :
		// browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));

		 break;
	 
	 case  "chrome" :
		// browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		 tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
		 break;
		 default:
			 System.out.println("Please pass me the right browser name........");
			 
 }
	 /*Par défaut, Playwright utilise Chromium lorsqu'on utilise playwright.chromium().
Si vous spécifiez .setChannel("chrome"), vous dites à Playwright de lancer Google Chrome au lieu de Chromium.
Cela est utile si vous voulez tester avec la version exacte de Chrome installée sur votre machine (par exemple, pour des fonctionnalités ou extensions spécifiques de Chrome).*/
	
	 /* browser.newContext() : Crée un nouveau contexte pour isoler les sessions.
browserContext.newPage() : Crée un nouvel onglet (page).
page.navigate : Ouvre l'URL spécifiée.

	   */
	 
	 
// browserContext = browser.newContext();
// page = browserContext.newPage();
// page.navigate(prop.getProperty("url").trim());
// 
	 tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
	 
// return page;
		return getPage();

}
 /**
  * This method is used to initialize the properties from config file
  * **/
 public Properties init_prop()  {
	 try {
		FileInputStream ip = new FileInputStream("src\\test\\resources\\config\\config.properties");
	prop = new Properties();
	prop.load(ip);
	 } catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
return prop;
 }
 
 public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);
		
		return base64Path;
	}
 
}
