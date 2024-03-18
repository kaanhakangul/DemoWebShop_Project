package User_Stories;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class US_208 extends BaseDriver {

    By cmptr=By.xpath("(//a[@href='/computers'])[1]");
    By dsktp=By.xpath("(//a[@href='/desktops'])[1]");
    By addtoC=By.xpath("//input[@id='add-to-cart-button-72']");

    By sepeteEklendiMsg=By.xpath("//p[@class='content']");

    By chart=By.xpath("(//a[@class='ico-cart'])[1]");

    @Test
    public void US_208(){

        GirisYap();

        //Computers kategorisine gider
        Actions actionDriver=new Actions(driver);
        WebElement computers=driver.findElement(cmptr);
        actionDriver.moveToElement(computers).build().perform();

        //Desktops grubuna tıklar
        WebElement desktops= driver.findElement(dsktp);
        actionDriver.moveToElement(desktops).click().build().perform();

        //İlk bilgisayarın ismini alır ve bilgisayara tıklar
        JavascriptExecutor js=(JavascriptExecutor) driver;
        WebElement masaustu=driver.findElement(By.xpath("(//img[@alt='Picture of Build your own cheap computer'])[1]"));
        String cmptrInfo=masaustu.getText();
        js.executeScript("arguments[0].click();", masaustu);

        //Sepete ekler
        WebElement sepeteEkle= driver.findElement(addtoC);
        actionDriver.moveToElement(sepeteEkle).click().build().perform();

        //Sepete eklendikten sonra alının doğrulama mesaajı
        WebElement eklendiMsg= driver.findElement(sepeteEklendiMsg);
        Assert.assertTrue(eklendiMsg.getText().contains("The product has been added to your "));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sepeteEklendiMsg));

        //Yukarıdaki sepete gidiyor ve tıklıyor
        WebElement ySepet= driver.findElement(chart);
        actionDriver.moveToElement(ySepet).click().build().perform();

        //Sepetteki ürün ile eklenen ürünün aynı olup olmadığını doğruladık
        WebElement sepettekiIsim= driver.findElement(By.xpath("(//a[@href='/build-your-cheap-own-computer'])[1]"));
        Assert.assertTrue("Eklenen ürün ile sepetteki ürün aynı değil",sepettekiIsim.getText().contains(cmptrInfo));

        //Kupon eklemeyi denemeliyim ve kuponum olmadığı için hata mesajı almalıyım.
        WebElement applyCoupon=driver.findElement(By.xpath("//input[@name='applydiscountcouponcode']"));
        actionDriver.moveToElement(applyCoupon).click().build().perform();
        WebElement couponNegativeMsg= driver.findElement(By.xpath("//div[@class='message']"));
        Assert.assertTrue("kupon başarılı bir şekilde uygulandı.",couponNegativeMsg.getText().contains("The coupon code you entered couldn't be applied to your order");

        //Gift card eklemeyi denemeliyim ve gift kartım olmadığı için hata mesajı almalıyım.
        WebElement applyGiftCard=driver.findElement(By.xpath("//input[@name='applygiftcardcouponcode']"));
        actionDriver.moveToElement(applyGiftCard).click().build().perform();
        //Scroll ekle buraya
        WebElement giftNegativeMsg= driver.findElement(By.xpath("//div[@class='message']"));
        Assert.assertTrue("Hediye kartı başarılı bir şekilde uygulandı.",giftNegativeMsg.getText().contains("The coupon code you entered couldn't be applied to your order"));


        //Sözleşmenin checklenmesi, ödeme butonuna tıklanması ve sayfanın doğrulanması
        WebElement termsofService=driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        actionDriver.moveToElement(termsofService).click().build().perform();
        WebElement checkout= driver.findElement(By.xpath("//button[@id='checkout']"));
        actionDriver.moveToElement(checkout).click().build().perform();
        Assert.assertTrue("Ödeme sayfasına geçilemedi.",driver.getCurrentUrl().contains("https://demowebshop.tricentis.com/onepagecheckout"));

        List<WebElement> elements = driver.findElements(By.cssSelector("label[for='billing-address-select']"));
        if (elements.isEmpty()) {

            //Sipariş detaylarının doldurulması
            WebElement company = driver.findElement(By.xpath("//input[@name='BillingNewAddress.Company']"));
            actionDriver.moveToElement(company).click().sendKeys("Techno Study").build().perform();

            WebElement country = driver.findElement(By.cssSelector("[id='BillingNewAddress_CountryId']"));
            Select select = new Select(country);
            select.selectByVisibleText("United States");

            WebElement state = driver.findElement(By.cssSelector("[id='BillingNewAddress_StateProvinceId']"));
            Select select2 = new Select(state);
            select2.selectByVisibleText("Colorado");

            WebElement cityText = driver.findElement(By.xpath("//input[@name='BillingNewAddress.City']"));
            wait.until(ExpectedConditions.visibilityOf(cityText));
            actionDriver.moveToElement(cityText).click().sendKeys("İstanbul").build().perform();

            WebElement adres1 = driver.findElement(By.cssSelector("[id='BillingNewAddress_Address1']"));
            actionDriver.moveToElement(adres1).click().sendKeys("Çengelköy").build().perform();

            WebElement adres2 = driver.findElement(By.cssSelector("[id='BillingNewAddress_Address2']"));
            actionDriver.moveToElement(adres2).click().sendKeys("Hekimbaşı").build().perform();

            WebElement postaKodu = driver.findElement(By.cssSelector("[id='BillingNewAddress_ZipPostalCode']"));
            actionDriver.moveToElement(postaKodu).click().sendKeys("34000").build().perform();

            WebElement phoneNumber = driver.findElement(By.cssSelector("[id='BillingNewAddress_PhoneNumber']"));
            actionDriver.moveToElement(phoneNumber).click().sendKeys("5313567867").build().perform();

            WebElement fax = driver.findElement(By.cssSelector("[id='BillingNewAddress_FaxNumber']"));
            actionDriver.moveToElement(fax).click().sendKeys("537534").build().perform();

        }

        WebElement cont=driver.findElement(By.xpath("(//input[@value='Continue'])[1]"));
        actionDriver.moveToElement(cont).click().build().perform();

        //In store seçeneğinin checklenmesi ve continue butonuna tıklanması
        WebElement check=driver.findElement(By.cssSelector("[id='PickUpInStore']"));
        actionDriver.moveToElement(check).click().build().perform();
        WebElement secondCont=driver.findElement(By.cssSelector("[onclick='Shipping.save()']"));
        actionDriver.moveToElement(secondCont).click().build().perform();

        //Payment continue
        WebElement paymentCont=driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']"));
        wait.until(ExpectedConditions.visibilityOf(paymentCont));
        JavascriptExecutor js2=(JavascriptExecutor) driver;
        js2.executeScript("arguments[0].click();", paymentCont);

        //Payment Information control
        WebElement paymentInformation=driver.findElement(By.cssSelector("[class='info']"));
        Assert.assertTrue("Ödeme bilgileriniz doğrulanamadı",paymentInformation.getText().contains("You will pay by COD"));
        WebElement paymentInfCont=driver.findElement(By.cssSelector("[onclick='PaymentInfo.save()']"));
        actionDriver.moveToElement(paymentInfCont).click().build().perform();

        //Confirm order doğrulama
        WebElement confirmOrder=driver.findElement(By.xpath("//*[text()='Confirm order']"));
        Assert.assertTrue(confirmOrder.getText().toLowerCase().contains("confirm order"));
        WebElement confirmSon=driver.findElement(By.xpath("//*[@onclick='ConfirmOrder.save()']"));
        wait.until(ExpectedConditions.visibilityOf(confirmSon));
        actionDriver.moveToElement(confirmSon).click().build().perform();

        //Başarılı şekilde oluşturuldu mesajı
        WebElement successfully=driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertTrue("Siparişiniz başarılı bir şekilde oluşturulamadı.",successfully.getText().contains("successfully"));

        WaitClose();
    }

}
