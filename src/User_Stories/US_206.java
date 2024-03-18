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

import java.awt.*;
import java.util.List;

public class US_206 extends BaseDriver {

    By cmptr = By.xpath("(//a[@href='/computers'])[1]");
    By dsktp = By.xpath("(//a[@href='/desktops'])[1]");
    By addtoC = By.xpath("//input[@id='add-to-cart-button-72']");

    By sepeteEklendiMsg = By.xpath("//p[@class='content']");

    By chart = By.xpath("(//a[@class='ico-cart'])[1]");

    @Test
    public void US_206() throws AWTException {

        GirisYap();

        //Computers kısmına gider.
        Actions actionDriver = new Actions(driver);
        WebElement computers = driver.findElement(cmptr);
        actionDriver.moveToElement(computers).build().perform();

        //Desktops grubuna tıklar
        WebElement desktops = driver.findElement(dsktp);
        actionDriver.moveToElement(desktops).click().build().perform();

        //Bilgisayar seçer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement masaustu = driver.findElement(By.xpath("(//img[@alt='Picture of Build your own cheap computer'])[1]"));
        String cmptrInfo = masaustu.getText();
        js.executeScript("arguments[0].click();", masaustu);

        //Sepete ekler
        WebElement sepeteEkle = driver.findElement(addtoC);
        actionDriver.moveToElement(sepeteEkle).click().build().perform();

        //Sepete eklendikten sonra alının doğrulama mesaajı
        WebElement eklendiMsg = driver.findElement(sepeteEklendiMsg);
        Assert.assertTrue(eklendiMsg.getText().contains("The product has been added to your "));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sepeteEklendiMsg));

        //Sepete gidiyor
        WebElement ySepet = driver.findElement(chart);
        actionDriver.moveToElement(ySepet).click().build().perform();

        //Eklenen ürün ile sepetteki ürünün aynı olup olmadığının doğrulanması
        WebElement sepettekiIsim = driver.findElement(By.xpath("(//a[@href='/build-your-cheap-own-computer'])[1]"));
        Assert.assertTrue("Eklenen ürün ile sepetteki ürün aynı değil", sepettekiIsim.getText().contains(cmptrInfo));

        //Ulke seçimi
        WebElement country = driver.findElement(By.xpath("//select[@id='CountryId']"));
        Select countrySelect = new Select(country);
        countrySelect.selectByIndex(1);
        wait.until(ExpectedConditions.visibilityOf(country));

        //Province seçimi
        WebElement province = driver.findElement(By.xpath("//select[@id='StateProvinceId']"));
        Select provinceSelect = new Select(province);
        provinceSelect.selectByIndex(5);
        wait.until(ExpectedConditions.visibilityOf(province));

        //Use of terms onaylanması ve checkout a tıklanma adımı
        WebElement termsofService = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        actionDriver.moveToElement(termsofService).click().build().perform();
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        actionDriver.moveToElement(checkout).click().build().perform();
        Assert.assertTrue("Ödeme sayfasına geçilemedi.", driver.getCurrentUrl().contains("https://demowebshop.tricentis.com/onepagecheckout"));
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/onepagecheckout"));

        //Billing adres dolu ise aşağıya devam edecek boş ise aşağıdaki parametreler ile dolduracak.
        List<WebElement> elements = driver.findElements(By.cssSelector("label[for='billing-address-select']"));
        if (elements.isEmpty()) {

            //Sipariş detaylarının doldurulması
            WebElement company = driver.findElement(By.xpath("//input[@name='BillingNewAddress.Company']"));
            actionDriver.moveToElement(company).click().sendKeys("Techno Study").build().perform();

            WebElement Country = driver.findElement(By.cssSelector("[id='BillingNewAddress_CountryId']"));
            Select select = new Select(Country);
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

        //
        WebElement billingAddress = driver.findElement(By.name("billing_address_id"));
        String billingADDRESS = billingAddress.getText();

        //Billing adress continue
        WebElement cont = driver.findElement(By.xpath("(//input[@value='Continue'])[1]"));
        actionDriver.moveToElement(cont).click().build().perform();

        //Adreslerin doğrulanması ve InStore pickup seçeneğinin checklenmesi ve continue
        WebElement shippingAddress = driver.findElement(By.name("billing_address_id"));
        wait.until(ExpectedConditions.visibilityOf(shippingAddress));
        Assert.assertTrue("Adresler uyuşmuyor.", shippingAddress.getText().equals(billingADDRESS));
        WebElement check = driver.findElement(By.cssSelector("[id='PickUpInStore']"));
        actionDriver.moveToElement(check).click().build().perform();
        WebElement secondCont = driver.findElement(By.cssSelector("[onclick='Shipping.save()']"));
        actionDriver.moveToElement(secondCont).click().build().perform();

        //Kredi kartının seçilmesi ve devam edilmesi
        WebElement creditCard = driver.findElement(By.cssSelector("[alt='Credit Card']"));
        String creditCardText = creditCard.getText();
        actionDriver.moveToElement(creditCard).click().build().perform();
        WebElement paymentCont = driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']"));
        wait.until(ExpectedConditions.visibilityOf(paymentCont));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].click();", paymentCont);

        //Kredi kartı seçeneğinin seçilip seçilmediğinin doğrulanması
        WebElement creditCardYazisi = driver.findElement(By.cssSelector("[for='CreditCardTypes']"));
        wait.until(ExpectedConditions.visibilityOf(creditCardYazisi));
        Assert.assertTrue(creditCardYazisi.getText().contains("credit card"));

        //Kredi kartı bilgilerinin doldurulması
        WebElement Name = driver.findElement(By.id("CardholderName"));
        wait.until(ExpectedConditions.elementToBeClickable(Name));
        actionDriver.moveToElement(Name).click().sendKeys("Kaan Hakan Gül").perform();

        WebElement cardNo = driver.findElement(By.id("CardNumber"));
        wait.until(ExpectedConditions.elementToBeClickable(cardNo));
        actionDriver.moveToElement(cardNo).click().sendKeys("4242 4242 4242 4242").perform();

        WebElement ay = driver.findElement(By.id("ExpireMonth"));
        Select expireMonth = new Select(ay);
        expireMonth.selectByValue("1");

        WebElement yil = driver.findElement(By.id("ExpireYear"));
        Select expireYear = new Select(yil);
        expireYear.selectByValue("2032");

        WebElement code = driver.findElement(By.id("CardCode"));
        wait.until(ExpectedConditions.elementToBeClickable(code));
        actionDriver.moveToElement(code).click().sendKeys("123").perform();

        WebElement paymentInfCont = driver.findElement(By.cssSelector("[onclick='PaymentInfo.save()']"));
        actionDriver.moveToElement(paymentInfCont).click().build().perform();


        //Ödemi bilgilerinin doğrulandığı adım, tutar ve kredi kartı doğrulama adımı
        WebElement dogrulama = driver.findElement(By.cssSelector("[class='payment-method']"));
        wait.until(ExpectedConditions.visibilityOf(dogrulama));
        Assert.assertTrue("Ödeme yöntemi seçtiğinizden farklıdır.", dogrulama.getText().contains("Credit Card"));

        //Fiyatların doğrulanması
        WebElement subTotal = driver.findElement(By.cssSelector("[class='product-subtotal']"));
        wait.until(ExpectedConditions.visibilityOf(subTotal));
        WebElement total = driver.findElement(By.xpath("//span[@class='product-price order-total']//strong"));
        wait.until(ExpectedConditions.visibilityOf(total));

        Assert.assertTrue(subTotal.getText().equals(total.getText()));

        //Confirm order doğrulama
        WebElement confirmOrder = driver.findElement(By.xpath("//*[text()='Confirm order']"));
        Assert.assertTrue(confirmOrder.getText().toLowerCase().contains("confirm order"));
        WebElement confirmSon = driver.findElement(By.xpath("//*[@onclick='ConfirmOrder.save()']"));
        wait.until(ExpectedConditions.visibilityOf(confirmSon));
        actionDriver.moveToElement(confirmSon).click().build().perform();

        //Başarılı şekilde oluşturuldu mesajı
        WebElement successfully = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertTrue("Siparişiniz başarılı bir şekilde oluşturulamadı.", successfully.getText().contains("successfully"));

        WaitClose();


    }

}
