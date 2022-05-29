import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static int a,n,m;
    public static void main(String[] args) throws Exception {
        long minTempoFila = 0;
        long maxTempoFila = 0;
        long mediaTempoFila = 0;
        long totalTempoFila = 0;
        long totalMovimentoCarro = 0;
        Scanner ler = new Scanner(System.in);

        System.out.println("Escolha:[1] para 1 carro e 52 passageiros\n        [2] para 2 carros e 52 passageiros\n        [3] para 3 carros e 100 passageiros\n        [4] carros e passageiros personalizados");
        System.out.print("Escolha: ");
        a = ler.nextInt();

        switch(a){
            case 1:
            n = 52;
            m = 1;
            break;
            case 2:
            n = 52;
            m = 2;
            break;
            case 3:
            n = 100;
            m = 3;
            break;
            case 4:
            System.out.print("Entre com o número de pessoas no parque:");
            n = ler.nextInt();
            System.out.print("Entre com a quantidade de carros: ");
            m = ler.nextInt();
            break;
        }
        
        MontanhaRussa montanhaRussa = new MontanhaRussa(n,m,4,20,120,10,30);

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

        }
        mediaTempoFila = totalTempoFila/montanhaRussa.getN();

        System.out.println("Tempo minímo da fila: "+(minTempoFila/1000)+"s");
        System.out.println("Tempo máximo da fila: "+(maxTempoFila/1000)+"s");
        System.out.println("Tempo Média da fila: "+(mediaTempoFila/1000)+"s");
        
        for(Carro carro : carros){
            System.out.println("Carro "+carro.id+" tempo movimentado: "+(carro.getTempoCarro()/1000)+"s");
            totalMovimentoCarro +=  carro.getTempoCarro();
        }
        System.out.println("Tempo movimentado: "+(totalMovimentoCarro/1000));
        ler.close();
    }
}
