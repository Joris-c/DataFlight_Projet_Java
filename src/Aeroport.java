public class Aeroport {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String IATA;
    private final String country;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public Aeroport(String name, double latitude, double longitude, String IATA, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.IATA = IATA;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", IATA='" + IATA + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
