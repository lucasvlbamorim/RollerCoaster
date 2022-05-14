import java.lang.Math;

public class MontanhaRussa {
    private int N; // Número de passageiros
    private int M; // Número de carros
    private int C; // Número bancos em um carro
    private int TE; // Tempo de embarque e desembarque em um carro
    private int TM; // Tempo que um carro leva para dar uma volta
    private int TP_MIN; // Tempo mínimo de chegada dos passageiros à montanha russa
    private int TP_MAX; // Tempo máximo de chegada dos passageiros à montanha russa
    private double TP;
 
    MontanhaRussa(int N, int M, int C, int TE, int TM, int TP_MIN, int TP_MAX) {
        this.N = N;
        this.M = M;
        this.C = C;
        this.TE = TE;
        this.TM = TM;
        this.TP_MIN = TP_MIN;
        this.TP_MAX = TP_MAX;
        this.setTP();
    }
    
    /*private static MontanhaRussa gerarMontanhaRussa1(String s) {
        int N = 52;
        int M = 2;
        int C = 4;
        int TE = 20;
        int TM = 2*60;
        int TP_MIN = 10;
        int TP_MAX = 30;
        return new MontanhaRussa(N, M, C, TE, TM, TP_MIN, TP_MAX);
    }*/
    
    public int getN(){
        return this.N;
    }
    
    public int getM(){
        return this.M;
    }
    
    public int getC(){
        return this.C;
    }
    
    public int getTE(){
        return this.TE;
    }

    public int getTM(){
        return this.TM;
    }

    public int getTP_MIN(){
        return this.TP_MIN;
    }
    
    public int getTP_MAX(){
        return this.TP_MAX;
    }

    public long getTP(){
        return (long) TP;
    }

    public void setN(int N){
        this.N = N;
    }

    public void setM(int M){
        this.M = M;
    }

    public void setC(int C){
        this.C = C;
    }

    public void setTE(int TE){
        this.TE = TE;
    }

    public void setTM(int TM){
        this.M = TM;
    }
    
     public void setTP_MAX(int TP_MAX){
        this.TP_MAX = TP_MAX;
    }

    public void setTP_MIN(int TP_MIN){
        this.TP_MIN = TP_MIN;
    }

    public void setTP(){
        this.TP = (Math.random() * (this.TP_MAX - this.TP_MIN) + this.TP_MIN) * 1000; 
    }
    

    public String toString() {
        return "M:" +this.M+
               ", N:" +this.N+
               ", C:" +this.C+
               ", TE:" +this.TE+
               ", TM:" +this.TM+
               ", TP_MIN:" +this.TP_MIN+
               ", TP_MAX:" +this.TP_MAX+
               ", TP: "+this.TP;
    }
}