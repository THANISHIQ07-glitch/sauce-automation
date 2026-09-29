package com.sauce.hooks;
import java.io.IOException;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @After
    public void browserteardown(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            byte[] screenshot =
                    ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    scenario.getName()
            );
        }

        ExcelUtils.closeExcel();
        tearDown();
    }

}
