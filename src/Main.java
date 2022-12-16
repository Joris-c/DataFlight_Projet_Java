public class Main {
    public static void main(String[] args) {


        World w = new World("src/ressource/airport-codes_no_comma.csv");
        System.out.println("Found " + w.getList().size() + " airports.");
        //Vérifier le find nearest
        Aeroport test = w.findNearestAirport(0,0);
        //Aeroport test = w.findNearestAirport(2,50);
        Aeroport test2 = w.findByCode("OSL");
        System.out.println(test.toString());
        System.out.println(test2.toString());
        double distance = w.distance(48.7845774,2.3846886,test.getLatitude(),test.getLongitude());
        System.out.println("distance = " + distance + "km");


    }
}