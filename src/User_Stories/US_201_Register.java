package User_Stories;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class US_201_Register extends BaseDriver {

    @Test
    public void US_201_Register() {

        Actions aksiyonlar = new Actions(driver);
        WebElement register = driver.findElement(By.xpath("//a[text()='Register']"));
        aksiyonlar.moveToElement(register).click().build().perform();
        WebElement male = driver.findElement(By.id("gender-male"));
        aksiyonlar.moveToElement(male).click().build().perform();
        WebElement first = driver.findElement(By.id("FirstName"));
        aksiyonlar.moveToElement(first).click().sendKeys("Sdet").build().perform();
        WebElement last = driver.findElement(By.id("LastName"));
        aksiyonlar.moveToElement(last).click().sendKeys("Tears").build().perform();
        WebElement email = driver.findElement(By.id("Email"));
        aksiyonlar.moveToElement(email).click().sendKeys("sdettears@gmail.com").build().perform();
        WebElement pass = driver.findElement(By.id("Password"));
        aksiyonlar.moveToElement(pass).click().sendKeys("Pass1234").build().perform();
        WebElement cpass = driver.findElement(By.id("ConfirmPassword"));
        aksiyonlar.moveToElement(cpass).click().sendKeys("Pass1234").build().perform();
        WebElement rbtn = driver.findElement(By.id("register-button"));
        aksiyonlar.moveToElement(rbtn).click().build().perform();
        WebElement control = driver.findElement(By.xpath("//div[@class='result']"));
        Assert.assertTrue(control.getText().contains("completed"));
        WaitClose();


    }
}
