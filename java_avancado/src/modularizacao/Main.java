package modularizacao;

public class Main {
    public static void main(String[] args) {
        // O Exemplo com os módulos está nas pastas CORE e UTILS
    }
}


// CÓDIGO DESAFIO
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Scanner;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        sc.useLocale(Locale.ENGLISH);
//        Locale.setDefault(new Locale("en", "US"));
//
////declare as variaveis corretamente e continue a sua solução
//
//        ArrayList<Integer> lista  = new ArrayList<Integer>();
//
//        int  numero  =sc.nextInt();
//        while( numero -->0)
//            lista.add(sc.nextInt());
//
//        SortedMap<Integer, Integer> contaQuant = new TreeMap<>();
//        lista.forEach(id -> contaQuant.compute(id, (k   , v) -> ( v   == null ? 1 : v + 1)));
//
//
//        contaQuant.entrySet().forEach(entry->{
//            System.out.printf("%d aparece %d vez(es)\n", entry.getKey(), entry.getValue());
//        });
//
//        sc.close();
//    }
//}
