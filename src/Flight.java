import java.time.LocalDateTime;

public class Flight {

    private String airLineCode;
    private String airLineName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    private Aeroport departure;
    private Aeroport arrival;
    int number;


    public Flight(String airLineCode, String airLineName, LocalDateTime arrivalTime, LocalDateTime departureTime, Aeroport departure, Aeroport arrival, int number) {
        this.airLineCode = airLineCode;
        this.airLineName = airLineName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.departure = departure;
        this.arrival = arrival;
        this.number = number;
    }

    public Aeroport getDeparture() {
        return departure;
    }

    public Aeroport getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airLineCode='" + airLineCode + '\'' +
                ", airLineName='" + airLineName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", number=" + number +
                '}';
    }
}
