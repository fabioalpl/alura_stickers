import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        // Fazer uma conexão HTTP e buscar os top 250 filmes.
        String url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        //System.out.println(body);
        
        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listadeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listadeFilmes) {
            //System.out.println(filme);

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem)
                                .openStream();
            String nomeArquivoSaida = "../saida/" + titulo + ".png";

            var geradora = new GeradorDeFigurinhas();
            geradora.cria(inputStream, nomeArquivoSaida);

            System.out.println(filme.get("imDbRating"));
            System.out.println();


            //Busando pela chave
            /*System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();*/
        }
    }
}
