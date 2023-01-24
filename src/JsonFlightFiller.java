import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonFlightFiller extends Thread{

    ArrayList<Flight> list = new ArrayList<Flight>();

    public JsonFlightFiller(String jsonString,World w) {
        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    String al_name = result.getJsonObject("airline").get("name").toString();
                    String al_code = result.getJsonObject("airline").get("iata").toString();
                    String arr_str = strip(String.valueOf(result.getJsonObject("arrival").get("scheduled")));
                    LocalDateTime arr_time = LocalDateTime.parse(arr_str,DateTimeFormatter.ISO_DATE_TIME);
                    String dep_str = strip(String.valueOf(result.getJsonObject("departure").get("scheduled")));
                    LocalDateTime dep_time = LocalDateTime.parse(dep_str,DateTimeFormatter.ISO_DATE_TIME);
                    String number = strip(result.getJsonObject("flight").get("number").toString());
                    Aeroport arr = w.findByCode(strip(result.getJsonObject("arrival").get("iata").toString()));
                    Aeroport dep = w.findByCode(strip(result.getJsonObject("departure").get("iata").toString()));
                    System.out.println("dep = " + dep);
                    list.add(new Flight(al_code,al_name,arr_time,dep_time,dep,arr,Integer.valueOf(number)));

             }
                catch(Exception e){

                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getList() {
        return list;
    }

    public void displayFlight(){
        for (Flight f: list
             ) {
            System.out.println("f = " + f.toString());
        }
    }
    private static String strip(String s){
        return (s.replaceAll("\"",""));
    }
}

