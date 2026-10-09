package nb.sb_carrent_mvc.dto;

public class ReservationDTO {
    private Integer id;
    private UserResDTO userResDTO;

    public ReservationDTO(Integer id, UserResDTO userResDTO) {
        this.id = id;
        this.userResDTO = userResDTO;
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