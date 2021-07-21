public class StringRepeatJava11 {
    public static void main(String[] args) {
        // Antes do Java 11
//        String nome = "Joao";
//        String aux = "";
//        for(int i = 0; i<10;i++){
//            aux += nome;
//        }
//        System.out.println(aux);

        // No Java 11
        System.out.println("Joao" . repeat(10));

    }
}
