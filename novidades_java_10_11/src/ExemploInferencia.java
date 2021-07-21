import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

// Comandos para setar o Tamanho MaxHeapSize no Docker
// Funciona somente no java 10 ou maior
// docker container run -it -m512M -entrypoint bash openjdk:10-jdk
// java -xx:+PrintFlagsFinal -version | grep MaxHeapSize


public class ExemploInferencia {
    public static void main(String[] args) {
        URL url = null;

        try {
            // No Java 10 o tipo da variável pode ser substituido pela valavra var
            // Não Consegue:
            // var nao pode ser utilizado em nível de classe
            // var nao pode ser utilizado como parametro
            // var nao pode ser utilizado em variaveis locais não inicializadas

            // Consegue:
            // Variáveis locais inicializadas
            // Variáveis suporte do enhaced for
            // Variáveis suporte for iterativo
            url = new URL("https://docs.oracle.com/javase/10/language");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLConnection urlConnection = null; // Abre Conexao

        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * urlConnection.getInputStream() Pega as informações da URL
         * InputStreamReader Converte para um reader trazendo todos os caracteres
         * BufferedReader Transforma em linhas para facilitar a leitura
        */
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Collectors.joining() Concatena as linhas
        //System.out.println(bufferedReader.lines().collect(Collectors.joining()));

        // Dessa forma quebra as linhas
        System.out.println(bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n"));

    }
}
