import java.text.DecimalFormat;
import java.util.Scanner;

//UNINASSAU OLINDA - SISTEMAS DE INFORMA��O - NOITE
//AVALIA��O DA SEGUNDA UNIDADE DE LOGICA DE PROGRAMA��O ALGORITIMICA
//PROFESSOR: RAONI MONTEIRO
//ALUNOS: ROBERTO GOMES, KATHLEEN CALVALCANTI, CARLOS EDUARDO MEIRA E HUGO HENRIQUE
public class CalculadoraDeImportacao {

	// atributos
	int controladorDeCriacaoDeRegistro = 0;
	int controladorDeExclusaoDeRegistro = 0;
	RegistroDeImportacao[] regImp = new RegistroDeImportacao[20];
	Scanner leitor = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("###,###.##");
	
	
	
	
	// Fun��es e Procedimentos
	public void listarImportacao(RegistroDeImportacao[] itensImp, int flag) {

		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("N�o existe registros de importa��o");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		}

		int cont = 1;

		for (int i = 0; i <= this.controladorDeCriacaoDeRegistro - 1; i++) {

			if (itensImp[i] == null) {
				cont++;
				continue;
			}

			System.out
					.println("--------------------------------------------------------------------------------------");
			System.out.println("Numero do Registro: " + cont);
			System.out.println("Moeda Utilizada na opera��o: " + itensImp[i].moeda);
			System.out.println("Valor da moeda Registrado na Fatura: " + itensImp[i].cambio);
			System.out.println("NCM: " + itensImp[i].nomeclaturaComumDoMercosul);
			System.out.println("Descri��o: " + itensImp[i].descricao);
			System.out.println("Pre�o FOB ($): " + df.format(itensImp[i].precofob));
			System.out.println("Frete Maritimo ($): " + itensImp[i].freteMar + " Seguro (R$): " + itensImp[i].seguro);
			System.out.println();
			System.out.println("Impostos: ");
			System.out.println("I.I (%): " + itensImp[i].impostoImportacao);
			System.out.println("IPI (%): " + itensImp[i].ipi);
			System.out.println("ICMS (%): " + itensImp[i].icms);
			System.out.println();
			System.out.println("Outras Despesas: ");
			System.out.println("Taxa de Renova��o da Marinha Mercante (%): " + itensImp[i].taxaMar);
			System.out.println("Despesas com log�stica (R$): " + itensImp[i].despesasLog);
			System.out.println("Taxa do Siscomex (R$): " + itensImp[i].taxaSiscomex);
			
			System.out.println();
			
			System.out.println("Despesa Total de Importa��o (R$): " + df.format(itensImp[i].DespesaTotalDeImportacao));

			System.out
					.println("--------------------------------------------------------------------------------------");
			
			cont++;
		}

		System.out.println();

		if (flag == 0) {
			System.out.println("Digite [0] para sair");
			int alternativa = this.leitor.nextInt();
			int controle1 = 0;
			do {
				if (alternativa == 0) {
					controle1 = 0;
				} else {
					System.out.println();
					System.out.println("Numero Invalido");
					alternativa = this.leitor.nextInt();
					controle1 = 1;
				}
			} while (controle1 == 1);

			if (alternativa == 0 && flag == 0) {
				this.telaInicial();
			}
		} else {

		}

	}

	public double calculaImportacao(RegistroDeImportacao itemImp) {

		// etapa 1 - convers�o de moeda
		double etapa1 = (itemImp.precofob + itemImp.freteMar) * itemImp.cambio;

		// etapa 2 - adi��o de I.I
		double etapa2 = (etapa1 + itemImp.seguro) * (1 + (itemImp.impostoImportacao / 100));

		// etapa 3 - IPI
		double etapa3 = etapa2 * (1 + (itemImp.ipi / 100));

		// etapa 4 - ICMS

		double etapa4 = etapa3 + (etapa3 / ((100 - itemImp.icms) / 100) * (itemImp.icms / 100));

		// etapa 5 - outras despesas
		double etapa5 = etapa4 + ((itemImp.freteMar * itemImp.cambio) * (itemImp.taxaMar / 100)) + itemImp.despesasLog
				+ itemImp.taxaSiscomex;
		
		

		return etapa5;
	}

	public void cadastraImportacao() {

		// Cria um objeto do registroImporta��o
		regImp[this.controladorDeCriacaoDeRegistro] = new RegistroDeImportacao();

		//Nos itens abaixo e escrito o que precisa ser digitado e em seguida o usuario vai informar o valor
		System.out.println("Informe o NCM - Nomeclatura Comum do MercoSul: ");
		
		//Essa estrutura � um objeto(registroDeImporta��o que esta num array, e receber um valor
		regImp[this.controladorDeCriacaoDeRegistro].nomeclaturaComumDoMercosul = this.leitor.nextInt();
		
		System.out.println("Informe a moeda de negocia��o: ");
		regImp[this.controladorDeCriacaoDeRegistro].moeda = this.leitor.nextLine();
		regImp[this.controladorDeCriacaoDeRegistro].moeda = this.leitor.nextLine();
		
		System.out.println("Informe o valor da moeda de negocia��o: ");
		regImp[this.controladorDeCriacaoDeRegistro].cambio = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a descri��o do Produto: ");
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();


		System.out.println("Informe o pre�o de origem ($): ");
		regImp[this.controladorDeCriacaoDeRegistro].precofob = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe o valor do frete Maritimo($): ");
		regImp[this.controladorDeCriacaoDeRegistro].freteMar = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe o valor do seguro contratado no Brasil (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].seguro = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do Imposoto de Importa��o(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].impostoImportacao = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do IPI(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].ipi = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do ICMS(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].icms = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a taxa de renova��o da marinha Mercante(%)");
		regImp[this.controladorDeCriacaoDeRegistro].taxaMar = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe as despesas log�sticas (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].despesasLog = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a taxa do Siscomex (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].taxaSiscomex = this.leitorDeNumerosInteirosPositivosFlutuantes();

		
		//Este objeto(registro de Importa��o recebe o valor de calculaImporta��o
		regImp[this.controladorDeCriacaoDeRegistro].DespesaTotalDeImportacao = this
				.calculaImportacao(regImp[this.controladorDeCriacaoDeRegistro]);

		System.out.println();
		System.out.println("Cadastro adicionado com sucesso!!!");
		
		System.out.println();
		
		
		// menu para cadastrar mais ou sair
		System.out.println("Voc� gostaria de continuar registrando: ");
		System.out.println("1 - para continuar");
		System.out.println("0 - para Voltar a tela inicial");

		int alternativa = this.leitor.nextInt();

		//Estrutura que bloqueia numeros que n�o sejam 0 e 1 e obriga o usuario a digitar o numero correto
		int controle1 = 0;
		do {
			if (alternativa >= 0 && alternativa <= 1) {
				controle1 = 0;
			} else {
				System.out.println();
				System.out.println("Numero Invalido");
				alternativa = this.leitor.nextInt();
				controle1 = 1;
			}
		} while (controle1 == 1);

		
		// se voc� digitou 1 voc� vai cadastrar novamente se foi 0 voc� para a tela inicial
		if (alternativa == 1) {
			// vai cadastrar outro registro
			this.controladorDeCriacaoDeRegistro++;
			this.cadastraImportacao();

		} else {
			//Volta para tela inicial
			this.controladorDeCriacaoDeRegistro++;
			this.telaInicial();

		}

	}

	public void telaInicial() {

		System.out.println();
		System.out.println("----------------CALCULADORA DE CUSTO DE IMPROTA��O----------------");
		System.out.println();
		System.out.println();

		System.out.println("ESCOLHAS AS OP��ES: ");
		System.out.println("1 - CADASTRAR REGISTRO DE IMPORTA��O");
		System.out.println("2 - LISTAR IMPORTA��ES CADASTRADAS COM O CUSTO CALCULADO");
		System.out.println("3 - EDITAR REGISTROS DE IMPORTA��O");
		System.out.println("4 - EXCLUIR REGISTROS DE IMPORTA��O");
		System.out.println();
		System.out.println("0 - ENCERRAR APLICA��O");

		int opcao = this.leitor.nextInt();

		switch (opcao) {
		case 0:
			this.encerraApp();
			break;
		case 1:
			this.cadastraImportacao();
			break;
		case 2:
			this.listarImportacao(regImp, 0);
			break;
		case 3:
			this.editarRegistroDeImportacao();
			break;
		case 4:
			this.deletarRegistroDeImportacao();
			break;
		default:
			break;
		}

	}

	public void editarRegistroDeImportacao() {
		
		//Neste bloco de codigo vai verificar se existe um registro de Importa��o
		//caso n�o tiver ele vai informar que n�o existe
		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("N�o existe registros de importa��o");
			System.out.println("-------------------------------------------------------");
			//volta para a tela inicial
			this.telaInicial();
		}

		
		//lista todos os registros guardado no vetor
		this.listarImportacao(regImp, 1);
		System.out.println();
		
		int variavelDeControle1 = 0;

		//escolha um numero para selecionar o registro
		System.out.println("Escolha o numero do registro para a altera��o");
		
		//Um vetor come�a de 0 a 19, por isso quando o usuario digita o valor 1 na verdade o sistema ler como o valor 0;
		int opcao = leitor.nextInt() - 1;
		System.out.println();

		//Estrtura para obrigar a digitar o numero correto
		int vc2 = 0;
		do {
			if (regImp[opcao] == null) {
				System.out.println("Esse registro n�o Existe, digite novamente");
				opcao = this.leitor.nextInt() - 1;
				vc2 = 1;
			} else {
				vc2 = 0;
			}
		} while (vc2 == 1);
		
		
		//Submenu para alterar os atributos do registro
		do {
			System.out.println("Registro selecionado: " + opcao + 1);
			System.out.println("Escolha uma das op��es abaixo para altera��o: ");
			System.out.println("1 - Alterar NCM");
			System.out.println("2 - Alterar Descri��o");
			System.out.println("3 - Alterar Pre�o FOB");
			System.out.println("4 - Alterar valor do Frete Maritimo");
			System.out.println("5 - Alterar Valor do seguro");
			System.out.println("6 - Alterar aliquota do Imposto de Importa��o");
			System.out.println("7 - Alterar aliquota do IPI");
			System.out.println("8 - Alterar aliquota do ICMS");
			System.out.println("9 - Alterar Taxa da Marinha Mercante");
			System.out.println("10 - Alterar despesas com log�stica");
			System.out.println("11 - Alterar o valor da moeda");
			System.out.println("12 - Alterar o nome da moeda");
			System.out.println();
			System.out.println("13 - para escolher outro registro de Importa��o");
			System.out.println("0 - para sair");

			int opcao2 = this.leitor.nextInt();
			
			int controlador = 0;

			// Estrutura para limitar o valor digitado entre 0 e 13
			do {
				if (opcao2 >= 0 && opcao2 <= 13) {
					controlador = 0;
				} else {
					System.out.println();
					System.out.println("Valor Incorreto! Digite novamente: ");
					opcao2 = this.leitor.nextInt();
					System.out.println();
					controlador = 1;
				}
			} while (controlador == 1);
			
			
			//Estrura para escolher as op��o
			switch (opcao2) {
			case 0:
				this.telaInicial();
				break;
			case 1:
				System.out.println("Digite o novo NCM:");
				regImp[opcao].nomeclaturaComumDoMercosul = this.leitor.nextInt();
				break;
			case 2:
				System.out.println("Digite a nova descri��o do produto:  ");
				regImp[opcao].descricao = this.leitor.nextLine();
				regImp[opcao].descricao = this.leitor.nextLine();
				break;
			case 3:
				System.out.println("Digite o novo pre�o FOB: ");
				regImp[opcao].precofob = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 4:
				System.out.println("Digite novo o valor do Frete Maritimo: ");
				regImp[opcao].freteMar = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 5:
				System.out.println("Digite o novo valor do Seguro: ");
				regImp[opcao].seguro = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 6:
				System.out.println("Digite o novo valor do Imposto de Importa��o: ");
				regImp[opcao].impostoImportacao = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 7:
				System.out.println("Digite o novo valor para o IPI: ");
				regImp[opcao].ipi = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 8:
				System.out.println("Digite o novo valor para o ICMS: ");
				regImp[opcao].icms = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 9:
				System.out.println("Digite a nova taxa de renova��o da Marinha Mercante: ");
				regImp[opcao].taxaMar = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 10:
				System.out.println("Digite o novo valor para a taxa do SisComex: ");
				regImp[opcao].taxaSiscomex = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 11:
				System.out.println("Digite o novo valor para a moeda: ");
				regImp[opcao].cambio = this.leitorDeNumerosInteirosPositivosFlutuantes();
				break;
			case 12:
				System.out.println("Digite o novo nome da moeda: ");
				regImp[opcao].moeda = this.leitor.nextLine();
				regImp[opcao].descricao = this.leitor.nextLine();
			case 13:
				this.editarRegistroDeImportacao();
				break;
			default:
				break;
			}
			
			
			//Aqui vai ser recalculado a despesa de importa��o depois de ser feita a edi��o
			regImp[opcao].DespesaTotalDeImportacao = this.calculaImportacao(regImp[opcao]);

			System.out.println();

			// Limitador para digitar entre 0 e 1
			System.out.println("Voc� quer alter outro item do registro [1] para sim e [0] para n�o");
			variavelDeControle1 = leitor.nextInt();

			int variavelDeControle2 = 0;

			do {
				if (variavelDeControle1 == 0 || variavelDeControle1 == 1) {
					variavelDeControle2 = 0;
				} else {
					System.out.println();
					System.out.println("Valor incorreto Digite novamente: ");
					variavelDeControle1 = this.leitor.nextInt();
					variavelDeControle2 = 1;
				}
			} while (variavelDeControle2 == 1);

		} while (variavelDeControle1 == 1);

		System.out.println("Voc� gostaria de acessar outro registro: [1] para sim e [0] para n�o");
		//variavel de controle de repeti��o = variavel de controle 3
		int vc3 = this.leitor.nextInt();

		int controle1 = 0;
		
		//Limitador entre 0 e 1
		do {
			if (vc3 == 0 || vc3 == 1) {
				controle1 = 0;

			} else {
				System.out.println();
				System.out.println("Valor incorreto Digite novamente: ");
				vc3 = this.leitor.nextInt();
				controle1 = 1;
			}
		} while (controle1 == 1);

		//Se vc3 for igual 1 voc� escolhe outro registro se nao volta para a tela inicial.
		if (vc3 == 1) {
			this.editarRegistroDeImportacao();
		} else {
			this.telaInicial();
		}

	}

	public void deletarRegistroDeImportacao() {

		//Tem a fun��o de informa se existe registro ou n�o
		if (this.controladorDeCriacaoDeRegistro == this.controladorDeExclusaoDeRegistro && this.regImp[0] == null) {
			this.controladorDeCriacaoDeRegistro = 0;
			this.controladorDeExclusaoDeRegistro = 0;
		}

		//Quando n�o existe registro de importa��o o usuario sera informa do mesmo, caso contrario sera pedido...
		//que informe o registro de importa��o
		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("N�o existe registros de importa��o");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		} else {
			
			//lista de importa��o
			this.listarImportacao(regImp, 1);
			System.out.println();
			System.out.println("Informe o numero do registro de importa��o para deletar: ");
			//Um vetor come�a de 0 a 19, por isso quando o usuario digita o valor 1 na verdade o sistema ler como o valor 0;
			int numeroRegistro = this.leitor.nextInt() - 1;

			int vc2 = 0;
			
			//Limitador que serve para que o usuario digite o valor correto
			do {
				if (regImp[numeroRegistro] == null && this.controladorDeExclusaoDeRegistro > 0) {
					System.out.println("Esse registro n�o Existe, digite novamente");
					numeroRegistro = this.leitor.nextInt() - 1;
					vc2 = 1;
				} else {
					vc2 = 0;
				}
			} while (vc2 == 1);

			//Quando o registro de importa��o e escolhido o registro que esta dentro do vetor recebe o valor null.
			this.regImp[numeroRegistro] = null;
			this.controladorDeExclusaoDeRegistro++;

			System.out.println();
			System.out.println("Gostaria de deletar outro Registro [1] para Sim e [0] para N�o: ");
			int vc3 = this.leitor.nextInt();

			//Estrutura para limitar o valor entre 0 e 1
			int controle1 = 0;
			do {
				if (vc3 >= 0 || vc3 <= 1) {
					controle1 = 0;
				} else {
					System.out.println();
					System.out.println("Numero Invalido");
					vc3 = this.leitor.nextInt();
					controle1 = 1;
				}
			} while (controle1 == 1);

			
			//Quando o valor for digitado corretamente, se foi 1 outro registro vai ser escolhido para deleter se nao
			//Volta para a tela inicial
			if (vc3 == 1) {
				this.deletarRegistroDeImportacao();
			} else {
				this.telaInicial();
			}

		}

	}

	public double leitorDeNumerosInteirosPositivosFlutuantes() {
		
		double numero;
		numero = this.leitor.nextDouble();
		int cont = 0;
		
		do {
			if (numero < 0) {
				System.out.println("Este campo n�o aceita numeros negativos, Digite novamente: ");
				numero = this.leitor.nextDouble();
				cont = 1;
			}else {
				cont = 0;
			}
		} while (cont == 1);
		
		
		return numero;
	}
	
	public void encerraApp() {
		System.exit(0);
	}

}