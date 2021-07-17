package interfacesFuncionais;

public class FuncaoAltaOrdem {
    public static void main(String[] args) {
        Calculo soma = (a, b) -> a + b;
        Calculo subtracao = (a, b) -> a - b;
        Calculo multiplicacao = (a, b) -> a * b;
        Calculo divisao = (a, b) -> a / b;

        System.out.println(executarOperacao(soma, 6f, 3f));
        System.out.println(executarOperacao(subtracao, 6f, 3f));
        System.out.println(executarOperacao(multiplicacao, 6f, 3f));
        System.out.println(executarOperacao(divisao, 6f, 3f));
    }

    /**
     * Função de Alta ordem:
     * Recebe uma função como parâmetro ou que retorna uma função.
     */
    public static float executarOperacao(Calculo calculo, float a, float b){
        return calculo.calcular(a,b);
    }
}

@FunctionalInterface
interface Calculo {
    public float calcular(float a, float b);
}