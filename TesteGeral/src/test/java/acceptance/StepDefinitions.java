package acceptance;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igorb on 15/11/2017.
 */
public class StepDefinitions {

    private WebDriver driver = null;
    private BaseSteps baseSteps = new BaseSteps();

    @cucumber.api.java.Before
    public void setUp() throws Exception {
        driver = baseSteps.seleniumSetUp();
        driver.manage().window().maximize();
    }

    @cucumber.api.java.After
    public void tearDown() throws Exception {
        driver.quit();
    }


    @Given("^Eu esteja na tela inicial das \"([^\"]*)\"$")
    public void euEstejaNaTelaInicialDas(String url) throws Throwable {
        baseSteps.goToUrl(driver, url);
    }


    @When("^eu logar no site com o usuario \"([^\"]*)\" e a senha \"([^\"]*)\"$")
    public void euLogarNoSiteComOUsuarioEASenha(String user, String password) throws Throwable {
        baseSteps.logIn(driver, user, password);
    }

    @And("^eu entrar no menu minha conta$")
    public void euEntrarNoMenuMinhaConta() throws Throwable {
        baseSteps.navigateToMyAccount(driver);
    }

    @And("^validar que eu nao possuo pedidos recentes$")
    public void validarQueEuNaoPossuoPedidosRecentes() throws Throwable {
        baseSteps.assertMessage(driver, "semPedidosRecentes");
    }

    @And("^Validar a presença do botão \"([^\"]*)\"$")
    public void validarAPresençaDoBotão(String btn) throws Throwable {
        baseSteps.isBtnVisible(driver, btn);
    }

    @And("^Validar o nome do usuario \"([^\"]*)\"$")
    public void validarONomeDoUsuario(String user) throws Throwable {
        baseSteps.assertField(driver, "nome", user);
    }

    @When("^eu pesquisar pelo produto \"([^\"]*)\"$")
    public void euPesquisarPeloProduto(String product) throws Throwable {
        baseSteps.searchForProduct(driver, product);
    }

    @And("^selecionar o primeiro produto$")
    public void selecionarOPrimeiroProduto() throws Throwable {
        baseSteps.clickOnButtonByXpath(driver, "primeiroProduto");
    }

    @And("^conferir se a url atual confere com a \"([^\"]*)\"$")
    public void conferirSeAUrlAtualConfereComA(String expectedUrl) throws Throwable {
        baseSteps.assertField(driver, "url", expectedUrl);
    }

    @And("^validar as informacoes do produto \"([^\"]*)\"$")
    public void validarAsInformacoesDoProduto(String product) throws Throwable {
        String myProduct = "";
        switch (product) {
            case "smartTv":
                myProduct = "Smart TV LED 32\" Samsung 32J4300 HD com Conversor Digital 2 HDMI 1 USB Wi-Fi 120Hz";
                break;
        }
        baseSteps.assertField(driver, "nomeProduto", myProduct);
    }

    @When("^clicar no botao \"([^\"]*)\"$")
    public void clicarNoBotao(String btn) throws Throwable {
        baseSteps.clickOnButtonById(driver, baseSteps.findId(btn));
    }

    @Then("^irei validar o campo \"([^\"]*)\"$")
    public void ireiValidarOCampo(String field) throws Throwable {
        baseSteps.isFieldVisible(driver, field);
    }


    @And("^validar elemento \"([^\"]*)\" da pagina com a informacao \"([^\"]*)\"$")
    public void validarElementoDaPaginaComAInformacao(String field, String expectedMessage) throws Throwable {
        baseSteps.assertField(driver, field, expectedMessage);
    }

    @And("^Validar o \"([^\"]*)\" com os campos \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void validarOComOsCampos(String arg0, String arg1, String arg2, String arg3, String arg4) throws Throwable {
        List<String> dados = new ArrayList<>();
        switch (arg0) {
            case "endereco":
                dados.add("nome");
                dados.add("rua");
                dados.add("cep");
                dados.add("cidade");
                break;
            case "produto":
                dados.add("descricaoProduto");
                dados.add("quantidade");
                dados.add("valorUnitario");
                dados.add("valorTotal");
                break;
            case "frete":
                Thread.sleep(4000);
                dados.add("rapida");
                dados.add("economica");
                dados.add("agendada");
                dados.add("");
                break;
        }

        baseSteps.assertField(driver, dados.get(0), arg1);
        baseSteps.assertField(driver, dados.get(1), arg2);
        baseSteps.assertField(driver, dados.get(2), arg3);
        baseSteps.assertField(driver, dados.get(3), arg4);
    }

    @And("^preencher o campo \"([^\"]*)\" com os dados \"([^\"]*)\"$")
    public void preencherOCampoComOsDados(String field, String data) throws Throwable {
        baseSteps.fillDataById(driver,baseSteps.findId(field),data);
    }
}
