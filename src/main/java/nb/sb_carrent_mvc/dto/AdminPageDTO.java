package nb.sb_carrent_mvc.dto;

import java.util.List;

public class AdminPageDTO {
    private String apiKey;
    private String response;
    private List<ReservationDTO> allReservation;
    private List<CarDTO> allCar;

    public AdminPageDTO(String apiKey, String response, List<ReservationDTO> allReservation, List<CarDTO> allCar) {
        this.apiKey = apiKey;
        this.response = response;
        this.allReservation = allReservation;
        this.allCar = allCar;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<ReservationDTO> getAllReservation() {
        return allReservation;
    }

    public void setAllReservation(List<ReservationDTO> allReservation) {
        this.allReservation = allReservation;
    }

    public List<CarDTO> getAllCar() {
        return allCar;
    }

    public void setAllCar(List<CarDTO> allCar) {
        this.allCar = allCar;
    }
}