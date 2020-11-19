import java.util.Scanner;

public class CalculadoraDeImportacaoExportacao {

	// Area de execução
	public static void main(String[] args) {
		CalculadoraDeImportacaoExportacao calc1 = new CalculadoraDeImportacaoExportacao();

		calc1.telaInicial();

	}

	// atributos
	int controlador = 0;
	RegistroDeImportacao[] regImp = new RegistroDeImportacao[20];
	Scanner leitor = new Scanner(System.in);

	// Funções e Procedimentos
	public void listarImportacao(RegistroDeImportacao[] itensImp) {
		int cont = 1;


		for (int i = 0; i <= this.controlador - 1; i++) {
			System.out.println("Numero do Registro: " + cont);
			cont++;
			System.out.println("Dolar Registrado na Fatura: " + itensImp[i].dolar);
			System.out
					.println("NCM: " + itensImp[i].nomeclaturaComumDoMercosul + " Descrição: " + itensImp[i].descricao);
			System.out.println("Preço FOB: " + itensImp[i].precofob);
			System.out.println("Frete Maritimo: " + itensImp[i].freteMar + " Seguro: " + itensImp[i].seguro);
			System.out.println();
			System.out.println("Impostos: ");
			System.out.println("I.I: " + itensImp[i].impostoImportacao);
			System.out.println("IPI: " + itensImp[i].ipi);
			System.out.println("ICMS: " + itensImp[i].icms);
			System.out.println("Taxa de Renovação da Marinha Mercante: " + itensImp[i].taxaMar);
			System.out.println("Despesas com logística: " + itensImp[i].despesasLog);
			System.out.println(" Taxa do Siscomex: " + itensImp[i].taxaSiscomex);
			System.out.println("Despesa Total de Importação: " + itensImp[i].DespesaTotalDeImportacao);

			System.out
					.println("--------------------------------------------------------------------------------------");
		}

	}

	public double calculaImportacao(RegistroDeImportacao itemImp) {

		// etapa 1 - conversão de moeda
		double etapa1 = (itemImp.precofob + itemImp.freteMar) * itemImp.dolar;

		// etapa 2 - adição de I.I
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

		regImp[this.controlador] = new RegistroDeImportacao();

		System.out.println("Informe o NCM - Nomeclatura Comum do MercoSul: ");
		regImp[this.controlador].nomeclaturaComumDoMercosul = this.leitor.nextInt();

		System.out.println("Informe a descrição do Produto: ");
		regImp[this.controlador].descricao = this.leitor.nextLine();
		regImp[this.controlador].descricao = this.leitor.nextLine();

		System.out.println("Informe o preço do Dolar hoje: ");
		regImp[this.controlador].dolar = leitor.nextDouble();

		System.out.println("informe o preço Fob em Dolar: ");
		regImp[this.controlador].precofob = leitor.nextDouble();

		System.out.println("Informe o valor em dolar do frete Maritimo: ");
		regImp[this.controlador].freteMar = leitor.nextDouble();

		System.out.println("Informe o valor do seguro contratado no Brasil em Reais: ");
		regImp[this.controlador].seguro = leitor.nextDouble();

		System.out.println("Informe a aliquota do Imposoto de Importação(%): ");
		regImp[this.controlador].impostoImportacao = leitor.nextDouble();

		System.out.println("Informe a aliquota do IPI(%): ");
		regImp[this.controlador].ipi = leitor.nextDouble();

		System.out.println("Informe a aliquota do ICMS(%: ");
		regImp[this.controlador].icms = leitor.nextDouble();

		System.out.println("Informe a taxa de renovação da marinha Mercante(%)");
		regImp[this.controlador].taxaMar = leitor.nextDouble();

		System.out.println("Informe as despesas logísticas em reais: ");
		regImp[this.controlador].despesasLog = leitor.nextDouble();

		System.out.println(" informe a taxa do Siscomex em reais: ");
		regImp[this.controlador].taxaSiscomex = leitor.nextDouble();

		regImp[this.controlador].DespesaTotalDeImportacao = this.calculaImportacao(regImp[this.controlador]);

		System.out.println();

		System.out.println("Você gostaria de continuar registrando: ");
		System.out.println("1 - para continuar");
		System.out.println("0 - para Voltar a tela inicial");

		int alternativa = this.leitor.nextInt();

		if (alternativa == 1) {
			this.controlador++;
			this.cadastraImportacao();

		} else {
			this.controlador++;
			this.telaInicial();

		}

	}

	public void telaInicial() {

		System.out.println("----------------Registro de Importação----------------");
		System.out.println();
		System.out.println();

		System.out.println("Escolhas as opções: ");
		System.out.println("1 - Cadastrar Importação");
		System.out.println("2 - Listar importações cadastradas");
		System.out.println("3 - Editar registros de Importação");

		int opcao = this.leitor.nextInt();

		switch (opcao) {
		case 1:
			this.cadastraImportacao();
			break;
		case 2:
			this.listarImportacao(regImp);
			break;
		case 3:
			break;
		default:
			break;
		}

	}

}
