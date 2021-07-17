package paradigmaFuncional;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class ExemploImutabilidade {
    public static void main(String[] args) {
        int[] valores = {1,2,3,4};

        // Paradigma Funciomal
        Arrays.stream(valores)
                .filter(numero -> numero % 2 == 0)
                .map(numero -> numero * 2)
                .forEach(numero -> System.out.println(numero));

        // Faz a mesma coisa que o código acima, sem usar paradigma funcional, os dois irão imprimir 4 e 8 no console
        // Paradigma Imperativo
        for (int i = 0; i < valores.length;i++){
            int valor = 0;
            if (valores[i] %2 == 0){
                valor = valores[i] * 2;
                if (valor != 0){
                    System.out.println(valor);
                }
            }
        }

        // Testando imutabilidade
        int valor = 20; // Este valor não pode ser alterado dentro do lambda
        UnaryOperator<Integer> retornarDobro = v -> {
            //valor = 30; // Retorna um erro, pois a variável não pode ser modificada
            return v * 2;
        };

        System.out.println(retornarDobro.apply(valor)); // Retorna o dobro
        System.out.println(valor); // Valor não será alterado
    }
}
