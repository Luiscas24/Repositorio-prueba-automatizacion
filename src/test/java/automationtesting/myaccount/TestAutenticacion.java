package automationtesting.myaccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutenticacion {
    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void autenticacionExitosa(){
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/");
        driver.findElement(By.id("menu-item-50")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String valorEsperado = "Login";
        final WebElement loginText = driver.findElement(By.className("u-column1")).findElement(By.tagName("h2"));
        Boolean loginClass = driver.findElements(By.tagName("form")).get(1).getAttribute("class").equals("login");

        Assert.assertEquals(valorEsperado, loginText.getText());
        Assert.assertTrue(loginClass);

//        Assert.assertEquals("Login", driver.findElement(By.xpath("//*[@class='u-column1 col-1']/h2")).getText());
//        Assert.assertEquals("Login", driver.findElement(By.cssSelector(".u-column1")).findElement(By.tagName("h2")).getText());
    }

    /*
    id
    name
    css selector
    xpath
    */

    @After
    public void close(){
//        driver.quit();
    }
}
