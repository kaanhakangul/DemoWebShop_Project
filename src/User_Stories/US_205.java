package User_Stories;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Base64;

public class US_205 extends BaseDriver {

    @Test
    public void US_205(){

        Actions action = new Actions(driver);
        WebElement logIn1 = driver.findElement(By.xpath("//*[text()='Log in']"));
        action.moveToElement(logIn1).click().build().perform();
        WebElement logInBtn1 = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        action.moveToElement(logInBtn1).click().build().perform();
        WebElement withoutEmailPassword=driver.findElement(By.xpath("//li[text()='No customer account found']"));
        Assert.assertTrue(withoutEmailPassword.getText().equals("No customer account found"));

        WebElement logIn2 = driver.findElement(By.xpath("//*[text()='Log in']"));
        action.moveToElement(logIn2).click().build().perform();
        WebElement email = driver.findElement(By.xpath("//*[@id='Email']"));
        email.sendKeys("sdettears@gmail.com");
        WebElement logInBtn2 = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        action.moveToElement(logInBtn2).click().build().perform();
        WebElement withoutPassword=driver.findElement(By.xpath("//li[text()='The credentials provided are incorrect']"));
        Assert.assertTrue(withoutPassword.getText().equals("The credentials provided are incorrect"));

        WebElement logIn3 = driver.findElement(By.xpath("//*[text()='Log in']"));
        action.moveToElement(logIn3).click().build().perform();
        WebElement password = driver.findElement(By.xpath("//*[@id='Password']"));
        password.sendKeys("Pass1234");
        WebElement logInBtn3 = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        action.moveToElement(logInBtn3).click().build().perform();
        WebElement withoutEmail=driver.findElement(By.xpath("//li[text()='No customer account found']"));
        Assert.assertTrue(withoutEmail.getText().equals("No customer account found"));

        WebElement logIn4 = driver.findElement(By.xpath("//*[text()='Log in']"));
        action.moveToElement(logIn4).click().build().perform();
        WebElement email2 = driver.findElement(By.xpath("//*[@id='Email']"));
        email2.sendKeys("gkk@gmail.com");
        WebElement password2 = driver.findElement(By.xpath("//*[@id='Password']"));
        password2.sendKeys("Pass4321");
        WebElement logInBtn4 = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        action.moveToElement(logInBtn4).click().build().perform();
        WebElement incorrectEmailPassword=driver.findElement(By.xpath("//li[text()='No customer account found']"));
        Assert.assertTrue(incorrectEmailPassword.getText().equals("No customer account found"));

        WaitClose();

    }
}
