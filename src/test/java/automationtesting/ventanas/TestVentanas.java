package automationtesting.ventanas;

import automationtesting.utils.Utilidades;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.transform.stream.StreamSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static automationtesting.utils.Utilidades.*;

public class TestVentanas {
    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void startPage() {
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Windows.html");
    }

    @Test
    public void  prueba1Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana inicial: "+ventanaInicial);
        System.out.println("Titulo de la ventana inicial: "+driver.getTitle());

        clickElement(driver, "btn-info", "className");

        for(String manejadorVentanas : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentanas)){
                driver.switchTo().window(manejadorVentanas);
                break;
            }
        }

        System.out.println("ID ventana 2: "+driver.getWindowHandle());
        System.out.println("Título de la ventana 2: "+driver.getTitle());

        driver.close();

        driver.switchTo().window(ventanaInicial);
        System.out.println("ID ventana inicial: "+ventanaInicial);
        System.out.println("Titulo de la ventana inicial: "+driver.getTitle());

        String textMessageLabel = "If you set the target attribute to \"_blank\" , the link will open in a new browser window or a new tab";
        String messageLabelLocator = driver.findElement(By.id("Tabbed")).findElement(By.tagName("p")).getText();
        Assert.assertEquals(textMessageLabel, messageLabelLocator);
    }

    @Test
    public void  prueba2Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        startPage();
        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana inicial: "+ventanaInicial);
        System.out.println("Titulo de la ventana inicial: "+driver.getTitle());

        clickElement(driver, "//a[@href='#Seperate']", "xpath");
        clickElement(driver, "btn-primary", "className");

        for(String manejadorVentanas : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentanas)){
                driver.switchTo().window(manejadorVentanas);
                break;
            }
        }

        /*
         * Se automatiza segunda página
         *
         */

        js.executeScript("window.scrollBy(0, 100)");

        List<WebElement> contenedorCursos = driver.findElement(By.className("getting-started-topic-container")).findElements(By.tagName("h3"));
        List<String> list = new ArrayList<>();

        for(int i = 0; i< contenedorCursos.size(); i++){
            list.add(contenedorCursos.get(i).getText());
        }

        List<String> cursosEsperados = new ArrayList<>();
        cursosEsperados.add("Selenium Grid");
        cursosEsperados.add("Selenium IDE");
        cursosEsperados.add("Selenium WebDriver");

        Collections.sort(cursosEsperados);
        Collections.sort(list);

        Assert.assertArrayEquals(cursosEsperados.toArray(), list.toArray());

        driver.close();
        driver.switchTo().window(ventanaInicial);
        System.out.println("ID ventana inicial: "+ventanaInicial);
        System.out.println("Titulo de la ventana inicial: "+driver.getTitle());

        String textMessageLabel = "click the button to open a new window with some specifications";
        String messageLabelLocator = driver.findElement(By.id("Seperate")).findElement(By.tagName("p")).getText();
        Assert.assertEquals(textMessageLabel, messageLabelLocator);
    }

    @Test
    public void  prueba3Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        startPage();
        String ventanaInicial = driver.getWindowHandle();

        clickElement(driver, "//a[@href='#Multiple']", "xpath");
        driver.findElement(By.cssSelector("#Multiple > button")).click();

        for(String manejadorVentanas : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentanas)){
                driver.switchTo().window(manejadorVentanas);
                break;
            }
        }

        Utilidades.waitOwn(10000);
//
//        /*
//         * Se automatiza segunda página
//         *
//         */
//
//        js.executeScript("window.scrollBy(0, 100)");
//
//        List<WebElement> contenedorCursos = driver.findElement(By.className("getting-started-topic-container")).findElements(By.tagName("h3"));
//        List<String> list = new ArrayList<>();
//
//        for(int i = 0; i< contenedorCursos.size(); i++){
//            list.add(contenedorCursos.get(i).getText());
//        }
//
//        List<String> cursosEsperados = new ArrayList<>();
//        cursosEsperados.add("Selenium Grid");
//        cursosEsperados.add("Selenium IDE");
//        cursosEsperados.add("Selenium WebDriver");
//
//        Collections.sort(cursosEsperados);
//        Collections.sort(list);
//
//        Assert.assertArrayEquals(cursosEsperados.toArray(), list.toArray());
//
//        driver.close();
//        driver.switchTo().window(ventanaInicial);
//        System.out.println("ID ventana inicial: "+ventanaInicial);
//        System.out.println("Titulo de la ventana inicial: "+driver.getTitle());
//
//        String textMessageLabel = "click the button to open a new window with some specifications";
//        String messageLabelLocator = driver.findElement(By.id("Seperate")).findElement(By.tagName("p")).getText();
//        Assert.assertEquals(textMessageLabel, messageLabelLocator);
    }

    @After
    public void close(){
//        driver.quit();
    }
}
