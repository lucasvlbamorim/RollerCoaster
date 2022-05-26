import java.util.LinkedList;
import java.util.Queue;

public class Carro implements Runnable{

    int id;
    private long tempoCarro = 0;
    private MontanhaRussa montanhaRussa;
    private Queue<Passageiros> passageiros; // lista de passageiros que estão no carro
    Thread t;

    public Carro(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.passageiros = new LinkedList<>();
        t = new Thread(this, Integer.toString (id));
        t.start();
        System.out.println("Carro "+t.getName()+" Criado");
    }

    public void run(){
        while(true){
            this.embarcarPassageiros();
            if (this.passageiros.size() == this.montanhaRussa.getC()) {
                noTrilho();
            }

            if(this.montanhaRussa.getFinal() >= this.montanhaRussa.getN()){
                return;
            }
        }
    }

    public void embarcarPassageiros(){
        try{
        this.montanhaRussa.getEntradaCarro().acquire();
            if (!montanhaRussa.getFilaPassageiro().isEmpty() && montanhaRussa.getFilaPassageiro().size() >= montanhaRussa.getC()){
                Thread.sleep((long) montanhaRussa.getTE() * 1000);
                for (int i = 0; i < montanhaRussa.getC(); i++) {
                   Passageiros passageiro = montanhaRussa.getFilaPassageiro().poll();
                   passageiro.setSaidaPassageiro(System.currentTimeMillis());
                   System.out.println("O passageiro "+passageiro.t.getName() + " embarcou no carro " + this.t.getName());
                   this.passageiros.add(passageiro);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            this.montanhaRussa.getEntradaCarro().release();
        }  
    }

    private void noTrilho() {
        long tempoInicio = 0, tempoFinal = 0;
        try {
            this.montanhaRussa.getControleTrilho().acquire();
            tempoInicio = System.currentTimeMillis();
            System.out.println("Carinho "+this.t.getName() + " entrou no trilho."+tempoInicio);
            Thread.sleep((long) montanhaRussa.getTM() * 1000);
            tempoFinal = System.currentTimeMillis();
            System.out.println("Carinho "+this.t.getName() + " voltou de viagem."+tempoFinal);
            tempoCarro += tempoFinal - tempoInicio;
            //plusTotalTimeInRoad(finalRidetime - initalRideTime); (Tempo total da corrida)
            desembarquePassageiros();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.montanhaRussa.getControleTrilho().release();
        }
    }

    private void desembarquePassageiros() {
        try {
            Thread.sleep((long) montanhaRussa.getTE() * 1000);
            for (Passageiros passageiro : this.passageiros) {
             System.out.println("O passageiro "+passageiro.t.getName() + " desembarcou do carro " + this.t.getName() );
            }
            this.passageiros.clear();
            this.montanhaRussa.setFinal(this.montanhaRussa.getC());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTempoCarro(long tempoCarro){
        this.tempoCarro = tempoCarro;
    }

    public long getTempoCarro(){
        return this.tempoCarro;
    }


}

