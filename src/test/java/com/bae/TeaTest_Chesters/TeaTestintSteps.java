package com.bae.TeaTest_Chesters;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TeaTestintSteps {

	WebDriver driver;

	@Before
	public void startup() {

		System.setProperty("webdriver.chrome.driver", Constants.driverLocation);

		driver = new ChromeDriver();

		driver.manage().window().maximize();
	}

	@Given("^the correct web address$")
	public void the_correct_web_address() {

		driver.get(Constants.URL);

	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		WebElement menuButton = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a"));
		menuButton.click();
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() {

		
		Runner.test = Runner.report.startTest("Starting Test ...");

		Runner.test.log(LogStatus.INFO, "Browser Started");
		
		WebElement menuTitle = driver
			    .findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1"));
		

		// test
		if (menuTitle.equals("Green Tea")) {
			Runner.test.log(LogStatus.PASS, "Verify browsing correct info");
		} else {
			Runner.test.log(LogStatus.FAIL, "Verify browsing correct info");
		}
		
		Runner.test.log(LogStatus.INFO, "Test Has Run");

		assertEquals("Not on the Menu page", "Menu" ,menuTitle.getText());

	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() {
		
		WebElement checkOut = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[5]/a"));
		checkOut.click();
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page()  {
		
		WebElement enterCard = driver
			    .findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000452010925\"]/div/div/form/fieldset[2]/div[1]/label"));
		
		if(enterCard.equals("​Card Type")) {
			Runner.test.log(LogStatus.PASS, "Verify checkout page info");
		}
		else {
			Runner.test.log(LogStatus.FAIL, "Verify checkout page info");
		}
		
		assertEquals("Not on the Chekout page", "Card Type" , enterCard.getText());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
