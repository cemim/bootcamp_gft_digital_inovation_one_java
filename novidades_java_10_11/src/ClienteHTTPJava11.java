import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ClienteHTTPJava11 {

    static ExecutorService executor = Executors.newFixedThreadPool(6, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            System.out.println("Nova Thread Criada " + (thread.isDaemon() ? "daemon" : "") + " ThreadGroup " + thread.getThreadGroup());
            return thread;
        }
    });

    public static void main(String[] args) throws Exception {
        //connectAndPrintURLJavaOracle();
        connectHttpAkanaiHttpClient();
    }

    private static void connectHttpAkanaiHttpClient() throws Exception {
        System.out.println("Running HTTP/2 example...");

        try {
//            HttpClient httpClient = HttpClient.newBuilder()
//                    .version(HttpClient.Version.HTTP_1_1)
//                    .proxy(ProxySelector.getDefault())
//                    .build();
            // Versão 2
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .proxy(ProxySelector.getDefault())
                    .build();

            double start = System.currentTimeMillis();

            HttpRequest mainRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> response = httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Status Headers: " + response.headers());
            String body = response.body();
            System.out.println(body);

            List<Future<?>> future = new ArrayList<>();

            // Exibe o nome das imagens da URL
//            body.lines()
//                    .filter(line -> line.trim().startsWith("<img heigh"))
//                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
//                    .forEach(image -> System.out.println(image));

            body.lines()
                 .filter(line -> line.trim().startsWith("<img heigh"))
                 .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                 .forEach(image -> {
                     Future<?> imgFuture = executor.submit(() -> {
                         HttpRequest imgRequest = HttpRequest.newBuilder()
                                .uri(URI.create("https://http2.akamai.com" + image))
                                 .build();

                         try {
                             HttpResponse<String> imageResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());
                             System.out.println("Imagem Carregada: " + image + ", status code: " + imageResponse.statusCode());
                         } catch (IOException | InterruptedException e) {
                             System.out.println("Mensagem de Erro Durante Requisicao para imagem" + image);
                         }
                     });
                     future.add(imgFuture);
                     System.out.println("Imagens Futuras Submetidas " + image);
                 });

            future.forEach(f-> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Erro ao esperar carregar imagem");
                }
            });

            double end = System.currentTimeMillis();
            System.out.println("Tempo de Carregamento total: " + ((end - start)/1000) + " s");

        } finally {
            executor.shutdown(); // Encerra as tasks
        }
    }

    public static void connectAndPrintURLJavaOracle() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create("https://docs.oracle.com/javase/10/language/"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code :: " + response.statusCode());
        System.out.println("Headers response :: " + response.headers());
        System.out.println(response.body());
    }
}
