package acceptance;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseSteps {

    public WebDriver seleniumSetUp() throws Exception {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new ChromeDriver();
    }

    public void goToUrl(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    public void logIn(WebDriver driver, String user, String password) throws InterruptedException {
        clickOnButtonById(driver, findId("login"));
        clickOnButtonById(driver, findId("entrar"));
        fillDataById(driver, findId("user"), user);
        fillDataById(driver, findId("password"), password);
        clickOnButtonById(driver, findId("continuar"));
    }

    public String findId(String field) {
        switch (field) {
            case "login":
                return "h_user";
            case "entrar":
                return "h_usr-signin";
            case "user":
                return "email-input";
            case "password":
                return "password-input";
            case "continuar":
                return "login-button";
            case "comprar":
                return "btn-buy";
            case "continue":
                return "btn-continue";
            case "cep":
                return "cep";
            case "calcularFrete":
                return "calculate-freight-button";

            default:
                return "id not found";
        }
    }

    public void fillDataById(WebDriver driver, String myId, String inputData) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(myId)));
        driver.findElement(By.id(myId)).sendKeys(inputData);
    }

    public void clickOnButtonById(WebDriver driver, String myId) throws InterruptedException {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(myId)));
        driver.findElement(By.id(myId)).click();
        Thread.sleep(500);
    }

    public void navigateToMyAccount(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        goToUrl(driver, "https://minhaconta.americanas.com.br/#/account/home");
        Thread.sleep(5000);
    }

    public void assertMessage(WebDriver driver, String msg) {
        String expectedMessage = "";
        String webMessage = "";
        switch (msg) {
            case "semPedidosRecentes":
                expectedMessage = "você não tem pedidos recentes.";
                try {
                    webMessage = driver.findElement(By.xpath("//*[@id=\"mainUiView\"]/div/div[5]/div/div/h4[1]/strong")).getText();
                } catch (Exception e) {
                }
                break;
        }

        Assert.assertEquals(expectedMessage, webMessage);
    }

    public void assertField(WebDriver driver, String field, String expectedMessage) {
        String webMessage = "";
        switch (field) {
            case "nome":
                webMessage = driver.findElement(By.xpath("//*[@id=\"mainUiView\"]/div/div[9]/div/div[1]/div/div/div/div[1]/div[2]/div/p[1]")).getText();
                break;
            case "rua":
                webMessage = driver.findElement(By.xpath("//*[@id=\"mainUiView\"]/div/div[9]/div/div[1]/div/div/div/div[1]/div[2]/div/p[2]")).getText();
                break;
            case "cep":
                webMessage = driver.findElement(By.xpath("//*[@id=\"mainUiView\"]/div/div[9]/div/div[1]/div/div/div/div[1]/div[2]/div/p[3]")).getText();
                break;
            case "cidade":
                webMessage = driver.findElement(By.xpath("//*[@id=\"mainUiView\"]/div/div[9]/div/div[1]/div/div/div/div[1]/div[2]/div/p[4]")).getText();
                break;
            case "url":
                webMessage = driver.getCurrentUrl();
                break;
            case "nomeProduto":
                webMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/main/section/div/div/div/div[3]/div/div[2]/section/h1")).getText();
                break;
            case "infoProduto":
                webMessage = driver.findElement(By.className("card-product--title")).getText();
                break;
            case "codigoProduto":
                webMessage = driver.findElement(By.className("card-product--code")).getText();
                break;
            case "descricaoProduto":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[1]/section/article/ul/li/ul/li[1]/div/div[2]/h5/a")).getText();
                break;
            case "quantidade":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[2]/div[2]/div/div/table/tbody/tr[1]/td[1]")).getText().substring(0,1);
                break;
            case "valorUnitario":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[1]/section/article/ul/li/ul/li[6]")).getText();
                break;
            case "valorTotal":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[1]/section/article/ul/li/ul/li[7]")).getText();
                break;
            case "rapida":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[2]/div[1]/div/ul/li[1]/div/label/span[1]")).getText();
                break;
            case "agendada":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[2]/div[1]/div/ul/li[3]/div/label/span[1]")).getText();
                break;
            case "economica":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[2]/div[1]/div/ul/li[2]/div/label/span[1]")).getText();
                break;
            case "frete":
                webMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/article/div[2]/div[2]/div/div/table/tbody/tr[2]/td[3]")).getText();
                break;
            case "valorTotalComFrete":
                webMessage = driver.findElement(By.xpath("//*[@id=\"total-amount\"]")).getText();
                break;
        }
        Assert.assertEquals(expectedMessage, webMessage);
    }

    public void isBtnVisible(WebDriver driver, String btn) {
        String myXpath = "";
        switch (btn){
            case "ver todos os pedidos":
                myXpath = "//*[@id=\"mainUiView\"]/div/div[6]/div/button";
                break;
        }

        Assert.assertTrue(driver.findElement(By.xpath(myXpath)).isDisplayed());
    }

    public void clickOnButtonByXpath(WebDriver driver, String s) throws InterruptedException {
        String myXpath = "";
        switch (s){
            case "primeiroProduto":
                myXpath = "//*[@id=\"root\"]/div/div/div/div[3]/div/div[1]/div/div[2]/div[5]/div/div/div/div[2]/div[1]/section/a";
                break;
        }
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(myXpath)));
//        Thread.sleep(5000);
        scrollDown(driver);
        driver.findElement(By.xpath(myXpath)).click();
    }

    public void searchForProduct(WebDriver driver, String product) {
        driver.findElement(By.id("h_search-input")).sendKeys(product);
        driver.findElement(By.id("h_search-input")).sendKeys(Keys.RETURN);
    }

    private void scrollDown(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("scroll(0, 500)"); // if the element is on bottom.
    }

    public void isFieldVisible(WebDriver driver, String field) {
        String myClassName = "";
        switch (field){
            case "sem_garantia_estendida":
                myClassName = "garantia_estendida-option-2 ";
                break;
        }

        Assert.assertTrue(driver.findElement(By.className(myClassName)).isDisplayed());
    }
}