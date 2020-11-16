import java.util.Scanner;

public class CalculadoraDeImportacaoExportacao {
	
	public static void main(String[] args) {
		CalculadoraDeImportacaoExportacao x = new CalculadoraDeImportacaoExportacao();
		
		x.cadastraImportacao();
		x.imprimiImportacao();
	}
	
	//atributos
	RegistroDeImportacao[] regImp = new RegistroDeImportacao[99];
	Scanner leitor = new Scanner(System.in);
	static int contadorUniversalImp = 0;
	
	
	public void imprimiImportacao() {
		
		double saida = calculaImportacao(this.regImp[0]);
		System.out.println(saida);
		System.out.println(CalculadoraDeImportacaoExportacao.contadorUniversalImp);
	}
	
	public double calculaImportacao(RegistroDeImportacao itemImp) {
		
		//etapa 1 - conversão de moeda
		double etapa1 = (itemImp.precofob+itemImp.freteMar)*itemImp.dolar;
		
		//etapa 2 - adição de I.I
		double etapa2 = (etapa1 + itemImp.seguro)*(1+(itemImp.impostoImportacao/100));
		
		//etapa 3 - IPI
		double etapa3 = etapa2 * (1+(itemImp.ipi/100));
		
		//etapa 4 - ICMS
		
		double etapa4 = etapa3 + (etapa3/((100-itemImp.icms)/100)*(itemImp.icms/100));
		
		//etapa 5 - outras despesas
		double etapa5 = etapa4 + ((itemImp.freteMar*itemImp.dolar)*(itemImp.taxaMar/100)) + itemImp.despesasLog + itemImp.taxaSiscomex;
		
		
		return etapa5;
	}
	
	
	public void cadastraImportacao() {
		
		
		int opcao = 1;
		
		do {
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp] = new RegistroDeImportacao();
			
			System.out.println("Informe o preço do Dolar hoje: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].dolar = leitor.nextDouble();
			
			System.out.println("informe o preço Fob em Dolar: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].precofob = leitor.nextDouble();
			
			System.out.println("Informe o valor em dolar do frete Maritimo: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].freteMar =  leitor.nextDouble();
			
			System.out.println("Informe o valor do seguro contratado no Brasil em Reais: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].seguro = leitor.nextDouble();
			
			System.out.println("Informe a aliquota do Imposoto de Importação(%): ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].impostoImportacao = leitor.nextDouble();
			
			System.out.println("Informe a aliquota do IPI(%): ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].ipi = leitor.nextDouble();
			
			System.out.println("Informe a aliquota do ICMS(%: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].icms = leitor.nextDouble();
			
			System.out.println("Informe a taxa de renovação da marinha Mercante(%)");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].taxaMar = leitor.nextDouble();
			
			System.out.println("Informe as despesas logísticas em reais: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].despesasLog = leitor.nextDouble();
			
			System.out.println(" informe a taxa do Siscomex em reais: ");
			regImp[CalculadoraDeImportacaoExportacao.contadorUniversalImp].taxaSiscomex = leitor.nextDouble();
			
			System.out.println();
			
			System.out.println("Você gostaria de continuar registrando: ");
			System.out.println("1 - para continuar");
			System.out.println("0 - para parar");
			
			opcao = this.leitor.nextInt();
			
			if (opcao == 1) {
				CalculadoraDeImportacaoExportacao.contadorUniversalImp++;
			}else if(opcao == 0 && CalculadoraDeImportacaoExportacao.contadorUniversalImp == 0) {
				CalculadoraDeImportacaoExportacao.contadorUniversalImp++;
			}
			
			
			
		} while (opcao == 1);
		
		
		
	}
	

}
