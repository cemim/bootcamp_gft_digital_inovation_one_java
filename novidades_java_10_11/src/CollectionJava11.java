import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class CollectionJava11 {
    public static void main(String[] args) {
        // Antes do Java 11
        //Collection<String> nomes = Arrays.asList("Joao", "Paulo", "Oliveira", "Santos");

        // No Java 11
        //Collection<String> nomes = List.of("Joao", "Paulo", "Oliveira", "Santos");
        Collection<String> nomes = Set.of("Joao", "Paulo", "Oliveira", "Santos");
        System.out.println(nomes);
    }
}
