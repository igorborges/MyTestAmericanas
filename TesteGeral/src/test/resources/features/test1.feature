Feature: Efetuar “Pré” Compra no site das Lojas Americanas

  Scenario Outline: 1.1 - Informar Dados Cadastrais
    Given Eu esteja na tela inicial das "<lojas_americanas>"
    When eu logar no site com o usuario "<user>" e a senha "<password>"
    And eu entrar no menu minha conta
    And validar que eu nao possuo pedidos recentes
    And Validar o "endereco" com os campos "<nome>" "<rua>" "<cep>" "<cidade>"
    And Validar a presença do botão "ver todos os pedidos"
    And Validar o nome do usuario "<nome>"

    Examples:
      | lojas_americanas               | user        | password  | nome     | rua          | cep     | cidade     |
      | https://www.americanas.com.br/ | __Usuario__ | __Senha__ | __nome__ | __Endereço__ | __CEP__ | __Cidade__ |


  Scenario Outline: 1.2 - Selecionar Compra
    Given Eu esteja na tela inicial das "<lojas_americanas>"
    When eu pesquisar pelo produto "<tv>"
    And selecionar o primeiro produto
    And conferir se a url atual confere com a "<urlDesejada>"
    And validar as informacoes do produto "smartTv"

    Examples:
      | lojas_americanas               | tv       | urlDesejada                                                                                                                                                                                                             |
      | https://www.americanas.com.br/ | smart tv | https://www.americanas.com.br/produto/122701411/smart-tv-led-32-samsung-32j4300-hd-com-conversor-digital-2-hdmi-1-usb-wi-fi-120hz?pfm_carac=smart%20tv&pfm_index=0&pfm_page=search&pfm_pos=grid&pfm_type=search_page%20 |


  Scenario Outline: 1.3 - Selecionar Compra
    Given Eu esteja na tela inicial das "<urlProduto>"
    When clicar no botao "comprar"
    Then irei validar o campo "sem_garantia_estendida"
    And validar elemento "infoProduto" da pagina com a informacao "<infoProduto>"
    And validar elemento "codigoProduto" da pagina com a informacao "<codigoProduto>"

    Examples:
      | codigoProduto    | infoProduto                                                  | urlProduto            |
      | (cód: 122701411) | Smart TV LED 32 Samsung HD UN32J4300AGXZD 2HDMI 1 USB 120 Hz | https://goo.gl/1ihxE6 |


  Scenario Outline: 1.4 - Preencher o CEP em “Minha Cesta”
    Given Eu esteja na tela inicial das "<urlProduto>"
    When clicar no botao "continue"
    And Validar o "produto" com os campos "<descricaoProduto>" "<quantidade>" "<valorUnitario>" "<valorTotal>"
    And preencher o campo "cep" com os dados "<cep>"
    And clicar no botao "calcularFrete"
    And Validar o "frete" com os campos "rápida" "econômica" "agendada" ""
    Then validar elemento "frete" da pagina com a informacao "24,99"
    Then validar elemento "valorTotalComFrete" da pagina com a informacao "1.124,98"

    Examples:
      | cep      | descricaoProduto                                             | quantidade | valorUnitario | valorTotal  | urlProduto            |
      | 35400000 | Smart TV LED 32 Samsung HD UN32J4300AGXZD 2HDMI 1 USB 120 Hz | 1          | R$ 1.099,99   | R$ 1.099,99 | https://goo.gl/71aKPx |
