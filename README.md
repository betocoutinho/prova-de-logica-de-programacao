# Calculadora de Custo de Importação

*Este trabalho tem por objetivo servir de avaliação da segunda unidade para a disciplina de lógica de programação. Todo o trabalho foi desenvolvido utilizando somente lógica de programação, evitando o uso dos paradigmas da orientação a objetos já que não era o objetivo da disciplina*



## O SISTEMA

Esse é um "sistemazinho" que tem por finalidade registrar toda a operação de um calculo de custo de importação.



### Tela Inicial:

![Tela inicial](https://github.com/robercout/prova-de-logica-de-programacao/raw/master/img/tela inicial.png)

A partir desta tela você pode escolher se quer cadastrar, listar, excluir ou editar um registro de importação.



### Operação de cadastro de registro de importação

Nesta tela será informado os itens para cadastro. Esses itens são:

1. Nomenclatura Comum do Mercosul;
2. A moeda de negociação(exemplo: dólar, euro, libra-esterlina);
3. A cotação do dia da moeda informada anteriormente;
4. Descrição do produto importado;
5. O preço FOB do produto informado; - [Para saber mais sobre os termos de comercio internacional](https://www.fazcomex.com.br/blog/incoterms-2020-todos-termos/)
6. O Valor do frete marítimo;
7. O valor do seguro;
8. A alíquota do imposto de importação;
9. alíquota do I.P.I;
10. aliquota do I.C.M.S;
11. aliquota da taxa de renovação da marinha mercante;
12. Despesas com logística;
13. Taxa do SISCOMEX.



### Tela de Registros cadastrados

![Tela de Registro](https://github.com/robercout/prova-de-logica-de-programacao/raw/master/img/lista.png)



### Tela para edição do Registro

![edição1](https://github.com/robercout/prova-de-logica-de-programacao/raw/master/img/edicao1.png)



![edição2](https://github.com/robercout/prova-de-logica-de-programacao/raw/master/img/edicao2.png)

## Requisitos da avaliação

### Laboratório para ajudar no TRABALHO EM GRUPO PARA AVALIAÇÃO DA UNIDADE II (OPCIONAL):

1 - Imagine um objeto do mundo real cujo qual você deseja realizar um cadastro. Sobre esse objeto, elabore um REGISTRO e preencha as informações deste registro, uma a uma, através da solicitação das informações ao usuário (o usuário vai informar os valores).

Obs.: Há um exemplo nos arquivos de aulas chamado "REGISTRO DE CARRO" que poderá ajudar.

2 - Ainda sobre o objeto da questão anterior e do registro criado, insira agora um VETOR com a possibilidade de cadastrar 20 REGISTROS, conforme criados na questão anterior.

3 - Ainda em relação às questões anteriores, elabore agora um PROCEDIMENTO responsável pela inserção (Cadastro) de CADA UM DOS REGISTROS, onde o usuário irá informar se deseja continuar realizando os registros. SE a resposta for positiva, o sistema deverá chamar novamente o mesmo PROCEDIMENTO de cadastro.

4 - Ainda em relação às questões anteriores, elabore outro PROCEDIMENTO responsável por EXIBIR todos os registros cadastrados. Ele deverá ser chamado SE o usuário informar que NÃO deseja continuar realizando registros.

5 - Ainda em relação às questões anteriores, elabore um PROCEDIMENTO chamado MOSTRAR MENU, responsável pelas opções de escolhas: 1 - INSERIR; 2 - LISTAR. Insira uma lógica em que o usuário, ao entrar na aplicação, escolha a opção. E, ao realizar uma das operações acima escolhidas, volte ao menu novamente.

Obs.: Há um exemplo nos arquivos de aulas chamado "CALCULADORA_RECURSIVA" que poderá ajudar.

### TRABALHO EM GRUPO PARA AVALIAÇÃO DA UNIDADE II:

Seguindo a ideia do TRABALHO EM GRUPO 01, a ideia deste trabalho é:

1 - Identificar um "Objeto ou entidade" do mundo real, o qual a equipe tenha interesse de gravar informações;
2 - Elaborar um Algoritmo, em relação ao "Objeto ou entidade" identificado na questão anterior que:
A) Utilizar um "Registro" (Ou um "Struct" ou uma "Classe", dependendo da linguagem escolhida (verificar requisitos abaixo)) para mapear o "Objeto ou entidade";
B) O "Objeto ou entidade" deve ser gravado em um Vetor ou Array;
C) Em relação ao registro deste  "Objeto ou entidade", o Algoritmo ou Sistema deve ser capaz de: Inserir um novo registro, Listar todos os registros inseridos, Excluir um dos registros (Ou limpar o espaço do vetor em que ele está inserido), após localizar o registro, Localizar o registro e exibir informações sobre ele, Alterar as informações do registro.
3 - O sistema ou Algoritmo deve ainda possuir um "Menu", com as opções acima listadas (Inserir, Listar, Localizar, Excluir a Alterar), onde o usuário pode, após executar cada uma das funções supracitadas, voltar ao Menu para que o usuário escolha uma nova opção;

Requisitos Trabalho:
1 - O sistema deverá rodar sem erros;
2 - Utilizar o conceito de Registros, Struct ou Classe;
3 - Utilizar o conceito de Procedimentos, Funções ou Métodos (dependendo da linguagem);
4 - Ser em uma das seguintes linguagens: Portugol, C#, Java, PHP, Python, C ou C++;
5 - Apresentar e contextualizar o sistema que foi criado;
6 - Grupos de 3 a 4 pessoas;

