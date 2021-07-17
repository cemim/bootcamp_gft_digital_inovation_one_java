package interfacesFuncionais;

import java.util.function.Function;

public class Funcoes {
    public static void main(String[] args) {
        Function<String,String> retornarNomeAoContrario = texto -> new StringBuilder(texto).reverse().toString();
        Function<String,Integer> converterStringParaInteiro = texto -> Integer.valueOf(texto); // Integer::valueOf Para imprimir Diretamente o valor convertido
        System.out.println(retornarNomeAoContrario.apply("Joao"));
        System.out.println(converterStringParaInteiro.apply("20") * 2);
    }
}
