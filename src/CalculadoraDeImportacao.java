import java.util.Scanner;

public class CalculadoraDeImportacao {

	// Area de execu��o
	public static void main(String[] args) {
		CalculadoraDeImportacao calc1 = new CalculadoraDeImportacao();

		calc1.telaInicial();

	}

	// atributos
	int controladorDeCriacaoDeRegistro = 0;
	int controladorDeExclusaoDeRegistro = 0;
	RegistroDeImportacao[] regImp = new RegistroDeImportacao[20];
	Scanner leitor = new Scanner(System.in);

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
			cont++;
			System.out.println("Dolar Registrado na Fatura: " + itensImp[i].dolar);
			System.out.println(
					"NCM: " + itensImp[i].nomeclaturaComumDoMercosul + " -- Descri��o: " + itensImp[i].descricao);
			System.out.println("Pre�o FOB: " + itensImp[i].precofob);
			System.out.println("Frete Maritimo: " + itensImp[i].freteMar + " Seguro: " + itensImp[i].seguro);
			System.out.println();
			System.out.println("Impostos: ");
			System.out.println("I.I: " + itensImp[i].impostoImportacao);
			System.out.println("IPI: " + itensImp[i].ipi);
			System.out.println("ICMS: " + itensImp[i].icms);
			System.out.println("Taxa de Renova��o da Marinha Mercante: " + itensImp[i].taxaMar);
			System.out.println("Despesas com log�stica: " + itensImp[i].despesasLog);
			System.out.println(" Taxa do Siscomex: " + itensImp[i].taxaSiscomex);
			System.out.println("Despesa Total de Importa��o: " + itensImp[i].DespesaTotalDeImportacao);

			System.out
					.println("--------------------------------------------------------------------------------------");
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
		double etapa1 = (itemImp.precofob + itemImp.freteMar) * itemImp.dolar;

		// etapa 2 - adi��o de I.I
		double etapa2 = (etapa1 + itemImp.seguro) * (1 + (itemImp.impostoImportacao / 100));

		// etapa 3 - IPI
		double etapa3 = etapa2 * (1 + (itemImp.ipi / 100));

		// etapa 4 - ICMS

		double etapa4 = etapa3 + (etapa3 / ((100 - itemImp.icms) / 100) * (itemImp.icms / 100));

		// etapa 5 - outras despesas
		double etapa5 = etapa4 + ((itemImp.freteMar * itemImp.dolar) * (itemImp.taxaMar / 100)) + itemImp.despesasLog
				+ itemImp.taxaSiscomex;

		return etapa5;
	}

	public void cadastraImportacao() {

		regImp[this.controladorDeCriacaoDeRegistro] = new RegistroDeImportacao();

		System.out.println("Informe o NCM - Nomeclatura Comum do MercoSul: ");
		regImp[this.controladorDeCriacaoDeRegistro].nomeclaturaComumDoMercosul = this.leitor.nextInt();

		System.out.println("Informe a descri��o do Produto: ");
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();

		System.out.println("Informe o pre�o do Dolar hoje: ");
		regImp[this.controladorDeCriacaoDeRegistro].dolar = leitor.nextDouble();

		System.out.println("informe o pre�o Fob em Dolar: ");
		regImp[this.controladorDeCriacaoDeRegistro].precofob = leitor.nextDouble();

		System.out.println("Informe o valor em dolar do frete Maritimo: ");
		regImp[this.controladorDeCriacaoDeRegistro].freteMar = leitor.nextDouble();

		System.out.println("Informe o valor do seguro contratado no Brasil em Reais: ");
		regImp[this.controladorDeCriacaoDeRegistro].seguro = leitor.nextDouble();

		System.out.println("Informe a aliquota do Imposoto de Importa��o(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].impostoImportacao = leitor.nextDouble();

		System.out.println("Informe a aliquota do IPI(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].ipi = leitor.nextDouble();

		System.out.println("Informe a aliquota do ICMS(%: ");
		regImp[this.controladorDeCriacaoDeRegistro].icms = leitor.nextDouble();

		System.out.println("Informe a taxa de renova��o da marinha Mercante(%)");
		regImp[this.controladorDeCriacaoDeRegistro].taxaMar = leitor.nextDouble();

		System.out.println("Informe as despesas log�sticas em reais: ");
		regImp[this.controladorDeCriacaoDeRegistro].despesasLog = leitor.nextDouble();

		System.out.println(" informe a taxa do Siscomex em reais: ");
		regImp[this.controladorDeCriacaoDeRegistro].taxaSiscomex = leitor.nextDouble();

		regImp[this.controladorDeCriacaoDeRegistro].DespesaTotalDeImportacao = this
				.calculaImportacao(regImp[this.controladorDeCriacaoDeRegistro]);

		System.out.println();

		System.out.println("Voc� gostaria de continuar registrando: ");
		System.out.println("1 - para continuar");
		System.out.println("0 - para Voltar a tela inicial");

		int alternativa = this.leitor.nextInt();

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

		if (alternativa == 1) {
			this.controladorDeCriacaoDeRegistro++;
			this.cadastraImportacao();

		} else {
			this.controladorDeCriacaoDeRegistro++;
			this.telaInicial();

		}

	}

	public void telaInicial() {

		System.out.println();
		System.out.println("----------------Registro de Importa��o----------------");
		System.out.println();
		System.out.println();

		System.out.println("Escolhas as op��es: ");
		System.out.println("1 - Cadastrar Importa��o");
		System.out.println("2 - Listar importa��es cadastradas");
		System.out.println("3 - Editar registros de Importa��o");
		System.out.println("4 - Excluir Registro de Importa��o");
		System.out.println();
		System.out.println("0 - Encerra Aplica��o");

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
		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("N�o existe registros de importa��o");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		}

		this.listarImportacao(regImp, 1);
		System.out.println();
		int variavelDeControle1 = 0;

		System.out.println("Escolha o numero do registro para a altera��o");
		int opcao = leitor.nextInt() - 1;
		System.out.println();

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
			System.out.println("11 - Alterar o valor do Dolar");
			System.out.println();
			System.out.println("12 - para escolher outro registro de Importa��o");
			System.out.println("0 - para sair");

			int opcao2 = this.leitor.nextInt();
			int controlador = 0;

			// Controle de Op��o
			do {
				if (opcao2 >= 0 && opcao2 <= 12) {
					controlador = 0;
				} else {
					System.out.println();
					System.out.println("Valor Incorreto! Digite novamente: ");
					opcao2 = this.leitor.nextInt();
					System.out.println();
					controlador = 1;
				}
			} while (controlador == 1);

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
				break;
			case 3:
				System.out.println("Digite o novo pre�o FOB: ");
				regImp[opcao].precofob = this.leitor.nextDouble();
				break;
			case 4:
				System.out.println("Digite novo o valor do Frete Maritimo: ");
				regImp[opcao].freteMar = this.leitor.nextDouble();
				break;
			case 5:
				System.out.println("Digite o novo valor do Seguro: ");
				regImp[opcao].seguro = this.leitor.nextDouble();
				break;
			case 6:
				System.out.println("Digite o novo valor do Imposto de Importa��o: ");
				regImp[opcao].impostoImportacao = this.leitor.nextDouble();
				break;
			case 7:
				System.out.println("Digite o novo valor para o IPI: ");
				regImp[opcao].ipi = this.leitor.nextDouble();
				break;
			case 8:
				System.out.println("Digite o novo valor para o ICMS: ");
				regImp[opcao].icms = this.leitor.nextDouble();
				break;
			case 9:
				System.out.println("Digite a nova taxa de renova��o da Marinha Mercante: ");
				regImp[opcao].taxaMar = this.leitor.nextDouble();
				break;
			case 10:
				System.out.println("Digite o novo valor para a taxa do SisComex: ");
				regImp[opcao].taxaSiscomex = this.leitor.nextDouble();
				break;
			case 11:
				System.out.println("Digite o novo valor para o Dolar: ");
				regImp[opcao].dolar = this.leitor.nextDouble();
				break;
			case 12:
				this.editarRegistroDeImportacao();
				break;
			default:
				break;
			}

			regImp[opcao].DespesaTotalDeImportacao = this.calculaImportacao(regImp[opcao]);

			System.out.println();

			// Controle de Op��o
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
		int vc3 = this.leitor.nextInt();

		int controle1 = 0;

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

		if (vc3 == 1) {
			this.editarRegistroDeImportacao();
		} else {
			this.telaInicial();
		}

	}

	public void deletarRegistroDeImportacao() {

		if (this.controladorDeCriacaoDeRegistro == this.controladorDeExclusaoDeRegistro && this.regImp[0] == null) {
			this.controladorDeCriacaoDeRegistro = 0;
			this.controladorDeExclusaoDeRegistro = 0;
		}

		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("N�o existe registros de importa��o");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		} else {

			this.listarImportacao(regImp, 1);
			System.out.println();
			System.out.println("Informe o numero do registro de importa��o para deletar: ");
			int numeroRegistro = this.leitor.nextInt() - 1;

			int vc2 = 0;
			do {
				if (regImp[numeroRegistro] == null && this.controladorDeExclusaoDeRegistro > 0) {
					System.out.println("Esse registro n�o Existe, digite novamente");
					numeroRegistro = this.leitor.nextInt() - 1;
					vc2 = 1;
				} else {
					vc2 = 0;
				}
			} while (vc2 == 1);

			this.regImp[numeroRegistro] = null;
			this.controladorDeExclusaoDeRegistro++;

			System.out.println();
			System.out.println("Gostaria de deletar outro Registro [1] para Sim e [0] para N�o: ");
			int vc3 = this.leitor.nextInt();

			int controle1 = 0;
			do {
				if (vc3 >= 0 && vc3 <= 1) {
					controle1 = 0;
				} else {
					System.out.println();
					System.out.println("Numero Invalido");
					vc3 = this.leitor.nextInt();
					controle1 = 1;
				}
			} while (controle1 == 1);

			if (vc3 == 1) {
				this.deletarRegistroDeImportacao();
			} else {
				this.telaInicial();
			}

		}

	}

	public void encerraApp() {

	}

	
}