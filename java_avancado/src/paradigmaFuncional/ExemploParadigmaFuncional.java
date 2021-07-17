package paradigmaFuncional;

import java.util.function.UnaryOperator;

public class ExemploParadigmaFuncional {
    public static void main(String[] args) {
        /**
         *
         * O UnaryOperator <T> é uma interface funcional que estende a interface Function .
         * Representa uma operação que aceita um parâmetro e retorna um resultado do mesmo tipo que seu parâmetro de entrada.
         * O método apply () da interface Function e os métodos padrão : andThen () e compose () são herdados da interface UnaryOperator .
         * Uma expressão lambda e uma referência de método podem usar objetos UnaryOperator como destino .
         *
         * */
        UnaryOperator<Integer> calcularValor = valor -> valor*3;
        int valor = 10;
        System.out.println("O resultado é :: " + calcularValor.apply(valor));
    }
}
