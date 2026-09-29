package com.sauce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	protected static WebDriver driver;
	protected static Properties prop;
	public void setup() {
		prop=new Properties();
		try {
			FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("app.url"));
	}
	
	public void tearDown() {
		driver.quit();
	}

}	
