package nb.sb_carrent_mvc.dto;

public class UserSelectionDTO {
    private Integer carId;
    private ResDateDTO resDateDTO;

    public UserSelectionDTO(Integer carId, ResDateDTO resDateDTO) {
        this.carId = carId;
        this.resDateDTO = resDateDTO;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public ResDateDTO getResDateDTO() {
        return resDateDTO;
    }

    public void setResDateDTO(ResDateDTO resDateDTO) {
        this.resDateDTO = resDateDTO;
    }
}