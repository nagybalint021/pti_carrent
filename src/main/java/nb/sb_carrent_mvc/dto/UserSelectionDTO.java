package nb.sb_carrent_mvc.dto;

import java.time.LocalDate;

public class UserSelectionDTO {
    private Integer carId;
    private Integer totalPrice;
    private ResDateDTO resDateDTO;

    public UserSelectionDTO(Integer carId, Integer totalPrice, LocalDate startDate, LocalDate endDate) {
        this.carId = carId;
        this.totalPrice = totalPrice;
        this.resDateDTO = new ResDateDTO(startDate, endDate);
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ResDateDTO getResDateDTO() {
        return resDateDTO;
    }

    public void setResDateDTO(ResDateDTO resDateDTO) {
        this.resDateDTO = resDateDTO;
    }

    public Integer getRentDays() {
        return resDateDTO.getEndDate().compareTo(resDateDTO.getStartDate()) + 1;
    }
}