import java.util.LinkedList;
public class Passageiros extends Thread implements Runnable{
    private int id;
    private MontanhaRussa montanhaRussa;
    
    public Passageiros(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        //new Thread(this, Integer.toString (id)).start();
        System.out.println("Passageiro criado Id: "+this.id);
    }

    public void run(){
        try {
                //this.join();
                colocarNaFila(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
        }            
    }

    public synchronized void colocarNaFila() throws InterruptedException{
        Thread.sleep(this.montanhaRussa.getTP());
        System.out.println(this.montanhaRussa.getTP());
        this.montanhaRussa.getFilaPassageiro().add(this);
        System.out.println("Entrou na fila: "+Thread.currentThread().getName()+" Id: "+this.id);
    }
    
    /*public int getId(){
        return this.id;
    }*/
    
    public MontanhaRussa getMontanhaRussa(){
        return this.montanhaRussa;
    }

    public void setMontanhaRussa(MontanhaRussa montanhaRussa){
        this.montanhaRussa = montanhaRussa;
    }

    public void setId(int id){
        this.id = id;
    }
}
