package paradigmaFuncional;

public class ExemploLambda {
    public static void main(String[] args) {
        Funcao funcao = valor -> valor;
    }
}

// O compilador identifica em tempo de execução que é uma interface funcional
// portanto somente pode ter um metodo abstrato
@FunctionalInterface
interface Funcao {
    String gerar(String valor);
}
