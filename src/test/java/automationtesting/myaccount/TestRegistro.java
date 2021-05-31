package automationtesting.myaccount;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestRegistro {
    public static WebDriver driver;
    private final String email = "pruebaauto9@gmail.com";
    private final String password = "Pp123456Prueba++";

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void registroExitoso() {
        start();

        final WebElement emailField = driver.findElement(By.id("reg_email"));
        final WebElement passwordField = driver.findElement(By.id("reg_password"));
        final WebElement registerButton = driver.findElement(By.name("register"));

        emailField.click();
        emailField.sendKeys(email);

        passwordField.click();
        TypeInField(passwordField, this.getPassword());

        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        registerButton.click();

        String[] partsEmail = this.getEmail().split("@");
        String expectedValue = "Hello "+partsEmail[0]+" (not) "+partsEmail[0]+"? Sign out)";
        WebElement labelResult = driver.findElement(By.xpath("//div[@class='woocommerce-MyAccount-content']/p[2]"));

        assertEquals(expectedValue, labelResult.getText());

        //div[@class='woocommerce-MyAccount-content']/p[1]
    }

    @Test
    public void validarCorreoExistente(){
        start();

        final WebElement emailField = driver.findElement(By.id("reg_email"));
        final WebElement passwordField = driver.findElement(By.id("reg_password"));
        final WebElement registerButton = driver.findElement(By.name("register"));

        emailField.click();
        emailField.sendKeys(email);

        passwordField.click();
        TypeInField(passwordField, this.getPassword());
    }

    private void start() {
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/my-account/");
    }

    public void TypeInField(WebElement field, String value){
        String val = value;
        field.click();
        field.clear();
        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            field.sendKeys(s);
        }
    }

    @After
    public void close(){
//        driver.quit();
    }


}
