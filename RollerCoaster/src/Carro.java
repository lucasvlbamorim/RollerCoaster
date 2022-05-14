public class Carro implements Runnable{

    private int id;
    private MontanhaRussa montanhaRussa;

    public Carro(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        new Thread(this, Integer.toString (id)).start();
    }

    public void run(){

    }
}
