package nb.sb_carrent_mvc.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private Integer id;
    private UserResDTO userResDTO;

    public ReservationDTO(Integer id, String name, String email, String address, String phone, Integer carId, Integer totalPrice, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userResDTO = new UserResDTO(name, email, address, phone, carId, totalPrice, startDate, endDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserResDTO getUserResDTO() {
        return userResDTO;
    }

    public void setUserResDTO(UserResDTO userResDTO) {
        this.userResDTO = userResDTO;
    }
}