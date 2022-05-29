import java.util.LinkedList;
import java.util.Queue;

public class Carro implements Runnable{

    int id;
    int fechada;
    private long tempoCarro = 0;
    private MontanhaRussa montanhaRussa;
    private Queue<Passageiros> passageiros; // lista de passageiros que estão no carro
   
    Thread t;

    public Carro(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.fechada = 0;
        this.passageiros = new LinkedList<>();
        t = new Thread(this, Integer.toString (id));
        t.start();
        System.out.println("Carro "+t.getName()+" Criado");
    }

    public void run(){
        while(true){
            this.embarcarPassageiros();
            if(this.passageiros.size() == this.montanhaRussa.getC() || !this.passageiros.isEmpty() && this.fechada == 1 && this.passageiros.size() < this.montanhaRussa.getC()){
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
            if(montanhaRussa.fila == montanhaRussa.getN()){
                this.fechada = 1;
            }

            if (!montanhaRussa.getFilaPassageiro().isEmpty() && montanhaRussa.getFilaPassageiro().size() >= montanhaRussa.getC() || !montanhaRussa.getFilaPassageiro().isEmpty() && montanhaRussa.getFilaPassageiro().size() < montanhaRussa.getC() && this.fechada == 1){
                Thread.sleep((long) montanhaRussa.getTE() * 1000);
                
                int tamanhoCarro = (this.fechada == 1 && montanhaRussa.getFilaPassageiro().size() < 4? montanhaRussa.getFilaPassageiro().size():montanhaRussa.getC());
                
                for (int i = 0; i < tamanhoCarro; i++) {
                   Passageiros passageiro = montanhaRussa.getFilaPassageiro().poll();
                   passageiro.setSaidaPassageiro(System.currentTimeMillis());
                   System.out.println("O passageiro "+passageiro.t.getName() + " embarcou no carro " + this.t.getName());
                   this.passageiros.add(passageiro);
                }
            }
        }catch (InterruptedException e){
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
            System.out.println("Carinho "+this.t.getName() + " entrou no trilho.");
            Thread.sleep((long) montanhaRussa.getTM() * 1000);
            tempoFinal = System.currentTimeMillis();
            System.out.println("Carinho "+this.t.getName() + " voltou de viagem.");
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
            this.montanhaRussa.setFinal(this.passageiros.size());
            this.passageiros.clear();
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

