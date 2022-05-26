public class Passageiros implements Runnable{
    private int id;
    private MontanhaRussa montanhaRussa;
    private long entradaPassageiro;
    private long saidaPassageiro;
    Thread t;
    
    public Passageiros(int id, MontanhaRussa montanhaRussa){
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.entradaPassageiro = 0;
        this.saidaPassageiro = 0;

        t = new Thread(this, Integer.toString (id));
        //t.start();
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
        this.setEntradaPassageiro(System.currentTimeMillis());
        this.montanhaRussa.getFilaPassageiro().add(this);
        System.out.println("Entrou na fila: "+t.getName()+" Id: "+this.id);
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

    public long getEntradaPassageiro(){
        return this.entradaPassageiro;
    }

    
    public long getSaidaPassageiro(){
        return this.saidaPassageiro;
    }

    public void setEntradaPassageiro(long entradaPassageiro){
        this.entradaPassageiro = entradaPassageiro;
    }

    
    public void setSaidaPassageiro(long saidaPassageiro){
        this.saidaPassageiro = saidaPassageiro;
    }

    public void setId(int id){
        this.id = id;
    }
}
