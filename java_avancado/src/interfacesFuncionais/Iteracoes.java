package interfacesFuncionais;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Iteracoes {
    public static void main(String[] args) {
        String[] nomes = {"Joao", "Paulo", "Oliveira", "Santos", "Instrutor", "Java"};
        Integer[] numeros = {1,2,3,4,5};
        imprimitNomesFiltrados(nomes);
    }

    public static void imprimitNomesFiltrados(String... nomes) {
        String nomesParaImprimirDoFor = "";

        for (int i=0; i<nomes.length; i++){
            if (nomes[i].equals("Joao")){
                nomesParaImprimirDoFor += " " + nomes[i];
            }
        }

        System.out.println("Nomes For: " + nomesParaImprimirDoFor);

        String nomesParaImprimirDaStream = Stream.of(nomes)
                .filter(nome -> nome.equals("teste"))
                .collect(Collectors.joining()); // Transforma Array em um único valor

        System.out.println("Nomes Stream: " + nomesParaImprimirDaStream);
    }
}
