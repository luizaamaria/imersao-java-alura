import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

//        fazer uma conexão HTTP e buscar os top 250 filmes
//        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";

        /*  desafio 1: Consumir o endpoint de filmes mais populares da API do IMDB. */
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbmostpopularmovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        // método newBuilder dentro da classe HttpRequest que devolve um builder de requests que tem o método GET(post, delete)
        // que por sua vez depois se chama build que devolve um tipo HTTPRequest
        var request = HttpRequest.newBuilder(endereco).GET().build();
        //executando o request e pegando o body
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); //BodyHandlers é uma classe que vai criar as maneiras de ler os dados que no caso é string
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); //Map quando faz print, quando transforma em String em um Map ele poe chave e valor
        //esse for irá iterar entre todos os elementos dessa lista e para cada um deles será mostrado Nome, classificação, imagem e o enter para separar um do outro

        //exibir e manipular os dados
       // for (Map<String, String> filme : listaDeFilmes) {
        for(int i = 0; i < listaDeFilmes.size(); i++){ // percorrendo com um for do filme 1 ao 3.
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[37;1m\u001b[33;3mTitulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[37;1m\u001b[36;3mPoster:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[37;1m\u001b[31;3mClassificação:\u001b[m " + filme.get("imDbRating"));
            System.out.println();
            

        }

    }
}

