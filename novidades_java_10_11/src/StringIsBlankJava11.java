public class StringIsBlankJava11 {
    public static void main(String[] args) {
        // Antes do Java 11
//        String espaco = "                 ";
//        System.out.println(espaco!= null && espaco.length() == 0 && espaco.chars().allMatch(c -> c == ' '));

        String espaco = "                 ";
        System.out.println(espaco.isBlank());


    }
}
