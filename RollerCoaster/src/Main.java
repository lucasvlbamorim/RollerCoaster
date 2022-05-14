//import java.util.ArrayList;
import java.util.LinkedList;
public class Main {

    public static void main(String[] args) throws Exception {

        //MontanhaRussa teste1 = new MontanhaRussa(52,2,4,20,2*60,10,30);
        MontanhaRussa teste1 = new MontanhaRussa(52,2,4,10,2*10,0,5);
        
        LinkedList<Passageiros> passageiros = new LinkedList<Passageiros>();
        LinkedList<Carro> carros = new LinkedList<Carro>();

        for (int i = 0; i < teste1.getN(); i++) {
            passageiros.add(new Passageiros(i, teste1));
        }

        for (int i =0; i < passageiros.size(); i++){
            System.out.println(i+" = "+passageiros.get(i));
        }
    }
}
