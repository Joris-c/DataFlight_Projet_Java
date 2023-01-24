import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request extends Thread{

    public String requete(Aeroport a){
        try {
        System.out.println("requete http");
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("http://api.aviationstack.com/v1/flights?access_key=367de72a690d5732c70ac8f4dfeb6737&arr_iata=" + a.getIATA());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.aviationstack.com/v1/flights?access_key=cfaf27d3b7c76c08bafee49ddb0df72c&arr_iata=" + a.getIATA() + "&limit=20"))
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.body().toString() = " + response.body().toString());
            return response.body().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void run(Aeroport a) {
        requete(a);
    }
}
