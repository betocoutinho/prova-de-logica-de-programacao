import java.text.DecimalFormat;
import java.util.Scanner;

//UNINASSAU OLINDA - SISTEMAS DE INFORMAÇÃO - NOITE
//AVALIAÇÃO DA SEGUNDA UNIDADE DE LOGICA DE PROGRAMAÇÃO ALGORITIMICA
//PROFESSOR: RAONI MONTEIRO
//ALUNOS: ROBERTO GOMES, KATHLEEN CALVALCANTI, CARLOS EDUARDO MEIRA E HUGO HENRIQUE
public class CalculadoraDeImportacao {

	// atributos
	int controladorDeCriacaoDeRegistro = 0;
	int controladorDeExclusaoDeRegistro = 0;
	RegistroDeImportacao[] regImp = new RegistroDeImportacao[20];
	Scanner leitor = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("###,###.##");
	
	
	
	
	// Funções e Procedimentos
	public void listarImportacao(RegistroDeImportacao[] itensImp, int flag) {

		if (this.controladorDeCriacaoDeRegistro == 0 && this.regImp[0] == null) {
			System.out.println("-------------------------------------------------------");
			System.out.println("Não existe registros de importação");
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
			System.out.println("Moeda Utilizada na operação: " + itensImp[i].moeda);
			System.out.println("Valor da moeda Registrado na Fatura: " + itensImp[i].cambio);
			System.out.println("NCM: " + itensImp[i].nomeclaturaComumDoMercosul);
			System.out.println("Descrição: " + itensImp[i].descricao);
			System.out.println("Preço FOB ($): " + df.format(itensImp[i].precofob));
			System.out.println("Frete Maritimo ($): " + itensImp[i].freteMar + " Seguro (R$): " + itensImp[i].seguro);
			System.out.println();
			System.out.println("Impostos: ");
			System.out.println("I.I (%): " + itensImp[i].impostoImportacao);
			System.out.println("IPI (%): " + itensImp[i].ipi);
			System.out.println("ICMS (%): " + itensImp[i].icms);
			System.out.println();
			System.out.println("Outras Despesas: ");
			System.out.println("Taxa de Renovação da Marinha Mercante (%): " + itensImp[i].taxaMar);
			System.out.println("Despesas com logística (R$): " + itensImp[i].despesasLog);
			System.out.println("Taxa do Siscomex (R$): " + itensImp[i].taxaSiscomex);
			
			System.out.println();
			
			System.out.println("Despesa Total de Importação (R$): " + df.format(itensImp[i].DespesaTotalDeImportacao));

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

		// etapa 1 - conversão de moeda
		double etapa1 = (itemImp.precofob + itemImp.freteMar) * itemImp.cambio;

		// etapa 2 - adição de I.I
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

		regImp[this.controladorDeCriacaoDeRegistro] = new RegistroDeImportacao();

		System.out.println("Informe o NCM - Nomeclatura Comum do MercoSul: ");
		regImp[this.controladorDeCriacaoDeRegistro].nomeclaturaComumDoMercosul = this.leitor.nextInt();
		
		System.out.println("Informe a moeda de negociação: ");
		regImp[this.controladorDeCriacaoDeRegistro].moeda = this.leitor.nextLine();
		regImp[this.controladorDeCriacaoDeRegistro].moeda = this.leitor.nextLine();
		
		System.out.println("Informe o valor da moeda de negociação: ");
		regImp[this.controladorDeCriacaoDeRegistro].cambio = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a descrição do Produto: ");
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();
		regImp[this.controladorDeCriacaoDeRegistro].descricao = this.leitor.nextLine();


		System.out.println("Informe o preço de origem ($): ");
		regImp[this.controladorDeCriacaoDeRegistro].precofob = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe o valor do frete Maritimo($): ");
		regImp[this.controladorDeCriacaoDeRegistro].freteMar = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe o valor do seguro contratado no Brasil (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].seguro = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do Imposoto de Importação(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].impostoImportacao = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do IPI(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].ipi = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a aliquota do ICMS(%): ");
		regImp[this.controladorDeCriacaoDeRegistro].icms = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a taxa de renovação da marinha Mercante(%)");
		regImp[this.controladorDeCriacaoDeRegistro].taxaMar = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe as despesas logísticas (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].despesasLog = this.leitorDeNumerosInteirosPositivosFlutuantes();

		System.out.println("Informe a taxa do Siscomex (R$): ");
		regImp[this.controladorDeCriacaoDeRegistro].taxaSiscomex = this.leitorDeNumerosInteirosPositivosFlutuantes();

		regImp[this.controladorDeCriacaoDeRegistro].DespesaTotalDeImportacao = this
				.calculaImportacao(regImp[this.controladorDeCriacaoDeRegistro]);

		System.out.println();
		System.out.println("Cadastro adicionado com sucesso!!!");
		
		System.out.println();

		System.out.println("Você gostaria de continuar registrando: ");
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
		System.out.println("----------------CALCULADORA DE CUSTO DE IMPROTAÇÃO----------------");
		System.out.println();
		System.out.println();

		System.out.println("ESCOLHAS AS OPÇÕES: ");
		System.out.println("1 - CADASTRAR REGISTRO DE IMPORTAÇÃO");
		System.out.println("2 - LISTAR IMPORTAÇÕES CADASTRADAS COM O CUSTO CALCULADO");
		System.out.println("3 - EDITAR REGISTROS DE IMPORTAÇÃO");
		System.out.println("4 - EXCLUIR REGISTROS DE IMPORTAÇÃO");
		System.out.println();
		System.out.println("0 - ENCERRAR APLICAÇÃO");

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
			System.out.println("Não existe registros de importação");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		}

		this.listarImportacao(regImp, 1);
		System.out.println();
		int variavelDeControle1 = 0;

		System.out.println("Escolha o numero do registro para a alteração");
		int opcao = leitor.nextInt() - 1;
		System.out.println();

		int vc2 = 0;
		do {
			if (regImp[opcao] == null) {
				System.out.println("Esse registro não Existe, digite novamente");
				opcao = this.leitor.nextInt() - 1;
				vc2 = 1;
			} else {
				vc2 = 0;
			}
		} while (vc2 == 1);

		do {
			System.out.println("Registro selecionado: " + opcao + 1);
			System.out.println("Escolha uma das opções abaixo para alteração: ");
			System.out.println("1 - Alterar NCM");
			System.out.println("2 - Alterar Descrição");
			System.out.println("3 - Alterar Preço FOB");
			System.out.println("4 - Alterar valor do Frete Maritimo");
			System.out.println("5 - Alterar Valor do seguro");
			System.out.println("6 - Alterar aliquota do Imposto de Importação");
			System.out.println("7 - Alterar aliquota do IPI");
			System.out.println("8 - Alterar aliquota do ICMS");
			System.out.println("9 - Alterar Taxa da Marinha Mercante");
			System.out.println("10 - Alterar despesas com logística");
			System.out.println("11 - Alterar o valor da moeda");
			System.out.println("12 - Alterar o nome da moeda");
			System.out.println();
			System.out.println("13 - para escolher outro registro de Importação");
			System.out.println("0 - para sair");

			int opcao2 = this.leitor.nextInt();
			int controlador = 0;

			// Controle de Opção
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
				System.out.println("Digite a nova descrição do produto:  ");
				regImp[opcao].descricao = this.leitor.nextLine();
				regImp[opcao].descricao = this.leitor.nextLine();
				break;
			case 3:
				System.out.println("Digite o novo preço FOB: ");
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
				System.out.println("Digite o novo valor do Imposto de Importação: ");
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
				System.out.println("Digite a nova taxa de renovação da Marinha Mercante: ");
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

			regImp[opcao].DespesaTotalDeImportacao = this.calculaImportacao(regImp[opcao]);

			System.out.println();

			// Controle de Opção
			System.out.println("Você quer alter outro item do registro [1] para sim e [0] para não");
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

		System.out.println("Você gostaria de acessar outro registro: [1] para sim e [0] para não");
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
			System.out.println("Não existe registros de importação");
			System.out.println("-------------------------------------------------------");
			this.telaInicial();
		} else {

			this.listarImportacao(regImp, 1);
			System.out.println();
			System.out.println("Informe o numero do registro de importação para deletar: ");
			int numeroRegistro = this.leitor.nextInt() - 1;

			int vc2 = 0;
			do {
				if (regImp[numeroRegistro] == null && this.controladorDeExclusaoDeRegistro > 0) {
					System.out.println("Esse registro não Existe, digite novamente");
					numeroRegistro = this.leitor.nextInt() - 1;
					vc2 = 1;
				} else {
					vc2 = 0;
				}
			} while (vc2 == 1);

			this.regImp[numeroRegistro] = null;
			this.controladorDeExclusaoDeRegistro++;

			System.out.println();
			System.out.println("Gostaria de deletar outro Registro [1] para Sim e [0] para Não: ");
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

	public double leitorDeNumerosInteirosPositivosFlutuantes() {
		
		double numero;
		numero = this.leitor.nextDouble();
		int cont = 0;
		
		do {
			if (numero < 0) {
				System.out.println("Este campo não aceita numeros negativos, Digite novamente: ");
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