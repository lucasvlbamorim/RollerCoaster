import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Carro implements Runnable{

    private int id;
    private MontanhaRussa montanhaRussa;
    private Queue<Passageiros> passageiros; // lista de passageiros que estão no carro

    public Carro(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.passageiros = new LinkedList<>();
        //new Thread(this, Integer.toString (id)).start();
    }

    public void run(){
        while(true){
            this.embarcarPassageiros();
        }
    }

    public void embarcarPassageiros(){
        if (!montanhaRussa.getFilaPassageiro().isEmpty() && montanhaRussa.getFilaPassageiro().size() >= montanhaRussa.getC()){
            try{
                Thread.sleep((long) montanhaRussa.getTE() * 1000);
                for (int i = 0; i < montanhaRussa.getC(); i++) {
                   Passageiros passageiro = montanhaRussa.getFilaPassageiro().poll();
                   System.out.println(passageiro + " Embarcou no carro: " + this);
                   this.passageiros.add(passageiro);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
                
        }
    }
}
