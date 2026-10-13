package nb.sb_carrent_mvc.dto;

import java.util.List;

public class APICarsResponseDTO {
    private List<CarDTO> cars;

    public APICarsResponseDTO(List<CarDTO> cars) {
        this.cars = cars;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }
}