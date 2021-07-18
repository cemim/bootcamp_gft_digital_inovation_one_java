package processamentoAssincronoEParalelo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
* Usado quando os objetos não tem dependência um do outro
*/
public class ExemploParallelStream {
    public static void main(String[] args) {
        // Serial
        long inicio = System.currentTimeMillis();
        IntStream.range(1,100000).forEach(num->fatorial(num));
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de Execucao Serial :: " + (fim - inicio));
        // Fim Serial

        // Paralelo
        inicio = System.currentTimeMillis();
        IntStream.range(1,100000).parallel().forEach(num->fatorial(num));
        fim = System.currentTimeMillis();
        System.out.println("Tempo de Execucao Paralelo :: " + (fim - inicio));

        List<String> nomes = Arrays.asList("Joao", "Paulo", "Oliveira", "Santos");
        nomes.parallelStream().forEach(System.out::println);
        // Fim Paralelo
    }

    public static long  fatorial (long num){
        long fat = 1;

        for (long i = 2; i<=num; i++){
            fat*=i;
        }

        return fat;
    }
}
