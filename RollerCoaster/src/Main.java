import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int n,m;
        long minTempoFila = 0;
        long maxTempoFila = 0;
        long mediaTempoFila = 0;
        long totalTempoFila = 0;
        //MontanhaRussa teste1 = new MontanhaRussa(52,2,4,20,2*60,10,30);
        Scanner r = new Scanner(System.in);
				
        System.out.println("Entre com o número de pessoas no parque: ");
        n = r.nextInt();
        if(n<=0){
			System.out.println("O número de pessoas deve ser maior que zero.");
			return;
		}
        
        System.out.println("Entre com a quantidade de carros: ");
        m = r.nextInt();

		if(m<=1){
			System.out.println("A quantidade de carro deve ser maior que um.");
			return;
		}
        MontanhaRussa montanhaRussa = new MontanhaRussa(n,m,4,2,2*5,2,3);
        
        LinkedList<Passageiros> passageiros = new LinkedList<Passageiros>();
        LinkedList<Carro> carros = new LinkedList<Carro>();

        for (int i = 0; i < montanhaRussa.getN(); i++) {
            passageiros.add(new Passageiros(i, montanhaRussa)); 
        }

        for (int i = 0; i < montanhaRussa.getM(); i++) {
            carros.add(new Carro(i, montanhaRussa));
        }

        for (Passageiros passageiro : passageiros){
            passageiro.t.start();
            passageiro.t.join();
        }
        

        for (Carro carro : carros){
            //carro.t.start();
            carro.t.join();
        }
        
        minTempoFila = passageiros.getFirst().getSaidaPassageiro() - passageiros.getFirst().getEntradaPassageiro();
        maxTempoFila = passageiros.getFirst().getSaidaPassageiro() - passageiros.getFirst().getEntradaPassageiro();
        for (Passageiros passageiro : passageiros){
            long tempo = passageiro.getSaidaPassageiro() - passageiro.getEntradaPassageiro();
            if(tempo < minTempoFila){
                minTempoFila = tempo;
            }
            if(tempo > maxTempoFila){
                maxTempoFila = tempo;
            }

            totalTempoFila += tempo;
            System.out.println("Passageiro id = "+tempo);
        }
        mediaTempoFila = totalTempoFila/montanhaRussa.getN();

        System.out.println("Tempo minímo da fila: "+minTempoFila+"ms");
        System.out.println("Tempo máximo da fila: "+maxTempoFila+"ms");
        System.out.println("Tempo Média da fila: "+mediaTempoFila+"ms");
    }
}
