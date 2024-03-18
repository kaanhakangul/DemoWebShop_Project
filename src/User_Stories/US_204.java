package User_Stories;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class US_204 extends BaseDriver {

    @Test
    public void US_204() {

        WebElement Login = driver.findElement(By.xpath("//a[text()='Log in']"));
        Actions aksiyon = new Actions(driver);

        aksiyon.moveToElement(Login).click().build().perform();
        WebElement email = driver.findElement(By.id("Email"));
        aksiyon.moveToElement(email).click().sendKeys("sdettears@gmail.com").build().perform();

        WebElement Password = driver.findElement(By.id("Password"));
        aksiyon.moveToElement(Password).click().sendKeys("Pass1234").build().perform();

        WebElement btn = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        aksiyon.moveToElement(btn).click().build().perform();

        WebElement confirm = driver.findElement(By.xpath(" //a[text()='sdettears@gmail.com']"));
        Assert.assertTrue("giriş yapılamadı", confirm.getText().contains("sdettears@gmail.com"));

        WaitClose();
    }
}