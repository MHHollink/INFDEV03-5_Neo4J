package nl.hro.mhollink.dev5.rest.dto;

public class FlightDTO {

    private String code;
    private String plane;
    private String from;
    private Double distance;
    private Double price;
    private Long time;

    public FlightDTO() {
    }

    public FlightDTO(String code, String plane, Double price) {
        this.code = code;
        this.plane = plane;
        this.price = price;
    }

    public FlightDTO(String code, String plane, String from, Double distance, Long time) {
        this.code = code;
        this.plane = plane;
        this.from = from;
        this.distance = distance;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "code='" + code + '\'' +
                ", plane='" + plane + '\'' +
                ", from='" + from + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
