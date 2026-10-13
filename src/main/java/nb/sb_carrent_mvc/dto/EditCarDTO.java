package nb.sb_carrent_mvc.dto;

public class EditCarDTO {
    private String apiKey;
    private CarDTO selectedCar;

    public EditCarDTO(String apiKey, Integer carId, String type, Integer price, String image, Boolean active) {
        this.apiKey = apiKey;
        this.selectedCar = new CarDTO(carId, type, price, image, active);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public CarDTO getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(CarDTO selectedCar) {
        this.selectedCar = selectedCar;
    }
}