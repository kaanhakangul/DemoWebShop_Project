package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;

    static { //bunun şartı extends olması ve başta yer alması
        driver = new ChromeDriver();
        driver.manage().window().maximize();//Ekranı max yapıyor
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // 20 sn mühlet: elementi bulma mühleti
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://demowebshop.tricentis.com/");


    }

    public static void WaitClose() {
        MyFunc.Bekle(1);
        driver.quit();

    }
    public static void GirisYap(){
        WebElement Login=driver.findElement(By.xpath("//a[text()='Log in']"));
        Actions aksiyon=new Actions(driver);

        aksiyon.moveToElement(Login).click().build().perform();
        WebElement email=driver.findElement(By.id("Email"));
        aksiyon.moveToElement(email).click().sendKeys("sdettears@gmail.com").build().perform();

        WebElement Password=driver.findElement(By.id("Password"));
        aksiyon.moveToElement(Password).click().sendKeys("Pass1234").build().perform();

        WebElement btn=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        aksiyon.moveToElement(btn).click().build().perform();


    }
}
