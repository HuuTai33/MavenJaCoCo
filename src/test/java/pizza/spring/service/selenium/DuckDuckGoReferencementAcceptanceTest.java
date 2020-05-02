package pizza.spring.service.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DuckDuckGoReferencementAcceptanceTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new SafariDriver();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test
	public void checkSeleniumCommanderAvecSucces() throws Exception {
		// Va sur la page d'accueil
		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/accueil");

		// Va sur la page Commander
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		// Selectionne une pizza
		WebElement selectPizza = webDriver.findElement(By.name("pizzaId"));
		selectPizza.selectByIndex(1);

		// Fourni un nom
		WebElement inputName = webDriver.findElement(By.name("nom"));
		inputName.sendKeys("HuuTai");

		// Fourni un numéro de téléphone
		WebElement inputTelephpne = webDriver.findElement(By.name("telephone"));
		inputTelephpne.sendKeys("0612345678");

		// Clique sur le bouton Commander
		WebElement commanderButton = webDriver.findElement(By.cssSelector("button"));
		commanderButton.click();

		// Verification de l'affiche de la page Récapitulatif de la commande
		List<WebElement> result = webDriver.findElements(By.id("recap-commande"));
		assertTrue("Test - Commander avec succès : OK", !result.isEmpty());
	}

	@Test
	public void checkSeleniumCommanderSansSelectionnerDePizza() throws Exception {
		// Va sur la page d'accueil
		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/accueil");

		// Va sur la page Commander
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		// Ne selectionne pas de pizza

		// Fourni un nom
		WebElement inputName = webDriver.findElement(By.name("nom"));
		inputName.sendKeys("HuuTai");

		// Fourni un numéro de téléphone
		WebElement inputTelephpne = webDriver.findElement(By.name("telephone"));
		inputTelephpne.sendKeys("0612345678");

		// Clique sur le bouton Commander
		WebElement commanderButton = webDriver.findElement(By.cssSelector("button"));
		commanderButton.click();

		// Verification de l'affiche du message d'erreur pour la pizza non selectionnée
		List<WebElement> result = webDriver.findElements(By.id("pizzaId.errors"));
		assertTrue("Test - Commander sans selectionner de pizza : OK", result.isEmpty());
	}

	@Test
	public void checkSeleniumCommanderSansFournirDeNumeroDeTelephone() throws Exception {
		// Va sur la page d'accueil
		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/accueil");

		// Va sur la page Commander
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		// Selectionne une pizza
		WebElement selectPizza = webDriver.findElement(By.name("pizzaId"));
		selectPizza.selectByIndex(1);

		// Fourni un nom
		WebElement inputName = webDriver.findElement(By.name("nom"));
		inputName.sendKeys("HuuTai");

		// Ne fourni de numéro de téléphone

		// Clique sur le bouton Commander
		WebElement commanderButton = webDriver.findElement(By.cssSelector("button"));
		commanderButton.click();

		// Verification de l'affiche du message d'erreur pour le numéro de téléphone non fourni
		List<WebElement> result = webDriver.findElements(By.id("telephone.errors"));
		assertTrue("Test - Commander sans fournir de numéro de télphone : OK", !result.isEmpty());
	}
}