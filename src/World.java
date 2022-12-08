import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {

    private final ArrayList <Aeroport> list = new ArrayList<>();

    public ArrayList<Aeroport> getList() {
        return list;
    }

    public World(String fileName) {
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");
                //Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[]=s.split(",");
                // Une bonne id´ee : placer un point d'arr^et ici pour du debuggage.
                if (fields[1].equals("large_airport")){
                    Aeroport aeroport = new Aeroport(fields[2],Double.parseDouble(fields[12]),Double.parseDouble(fields[11]),fields[9],fields[5]);
                    list.add(aeroport);
                }
                s = buf.readLine();
            }
        }
        catch (Exception e){
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }

    public Aeroport findNearestAirport(double latitude, double longitude){

        double lat2 = list.get(0).getLatitude();
        double long2 = list.get(0).getLongitude();
        double n = distance(latitude,longitude,lat2,long2);
        Aeroport nearest = list.get(0);

        for (Aeroport aeroport : list) {
            double distanceATester = distance(latitude, longitude,aeroport.getLatitude(), aeroport.getLongitude());
            if(n > distanceATester){
                n = distanceATester;
                nearest = aeroport;
            }
        }
        return nearest;
    }
    public double distance(double lat1, double long1, double lat2, double long2){
        double n = Math.pow((lat2- lat1),2) + Math.pow((long2-long1)*Math.cos((lat1+lat2)/2),2);
        return n;
    }
    public Aeroport findByCode(String code){
        Aeroport aeroport = null;
        code = code.toUpperCase();
        for (Aeroport aero : list) {
            if (aero.getIATA().equals(code)){
                return aero;
            }
        }
        return null;
    }
}

