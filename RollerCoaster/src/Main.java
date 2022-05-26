//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int n,m;
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
       
        /*for (Carro carro : carros){
            //carro.t.start();
            carro.t.join();
        }*/
        
    }
}
