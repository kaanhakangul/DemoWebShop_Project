package User_Stories;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class US_203_Logout extends BaseDriver {

    @Test
    public void US_203() {

        GirisYap();

        Actions aksiyon = new Actions(driver);
        WebElement logout = driver.findElement(By.xpath("//a[text()='Log out']"));
        aksiyon.moveToElement(logout).click().build().perform();
        WebElement confirm = driver.findElement(By.xpath(" //a[text()='Register']"));
        Assert.assertTrue( "Çıkış yapılamadı maalesef",confirm.getText().contains("Register"));

        WaitClose();


    }
}
