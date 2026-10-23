package com.sauce.hooks;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sauce.base.Base;
import com.sauce.utiliese.ExcelUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Base {
	
	@Before
	public void browsersetup() {
		setup();
		try {
			ExcelUtils.openExcel(System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx", "Sheet1");
		} catch (Exception e) {
			quitDriver();
			throw new IllegalStateException("Could not open the scenario test data workbook.", e);
		}
	}
	
    @After
    public void browserteardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                try {
                    scenario.attach(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES),
                            "image/png", scenario.getName());
                } catch (RuntimeException screenshotFailure) {
                    System.err.println("Could not capture failure screenshot: " + screenshotFailure.getMessage());
                }
            }
        } finally {
            try {
                ExcelUtils.closeExcel();
            } catch (Exception closeFailure) {
                System.err.println("Could not close test data workbook: " + closeFailure.getMessage());
            } finally {
                quitDriver();
            }
        }
    }

}
