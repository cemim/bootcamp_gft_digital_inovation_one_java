package processamentoAssincronoEParalelo;

public class ExemploThread {
    public static void main(String[] args) {
        // 1º Forma: Instanciar classes que extendem Thread
        BarraDeCarregamento2 barraDeCarregamento2 = new BarraDeCarregamento2();
        BarraDeCarregamento2 barraDeCarregamento22 = new BarraDeCarregamento2();

        barraDeCarregamento2.start();
        barraDeCarregamento22.start();

        // 2º Forma: Instanciar classes que implementa Runnable
        Thread thread = new Thread(new BarraDeCarregamento());
        Thread thread2 = new Thread(new BarraDeCarregamento());

        thread.start();
        thread2.start();
        System.out.println("Nome da thread: " + thread.getName());
        System.out.println("Nome da thread: " + thread2.getName());

    }
}

class GerarPDF implements Runnable{

    @Override
    public void run() {
        System.out.println("Gerar PDF");
    }
}

class BarraDeCarregamento implements Runnable{

    @Override
    public void run() {
        System.out.println("Loading...");
    }
}

class BarraDeCarregamento2 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("Rodei " + this.getName());
        /**
         * Exemplo para aguardar a execução de outra thread:
         * - Criar um construtor para receber a instancia da outra thread;
         *  private Thread iniciarGerenciadorPdf;
         *
         *  public BarraDeCarregamento2(Thread iniciarGerenciadorPdf){
         *      this.iniciarGerenciadorPdf = iniciarGerenciadorPdf;
         *  }
         *
         *  - Dentro do método run;
         * while(true){
         *     if(!iniciarGerenciadorPdf.isAlive()){
         *          break;
         *     }
         * }
         */
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}