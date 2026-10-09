package nb.sb_carrent_mvc.dto;

import java.util.List;

public class FreeCarsDTO {
    private List<CarDTO> freeCars;
    private ResDateDTO resDateDTO;

    public FreeCarsDTO(List<CarDTO> freeCars, ResDateDTO resDateDTO) {
        this.freeCars = freeCars;
        this.resDateDTO = resDateDTO;
    }

    public List<CarDTO> getFreeCars() {
        return freeCars;
    }

    public void setFreeCars(List<CarDTO> freeCars) {
        this.freeCars = freeCars;
    }

    public ResDateDTO getResDateDTO() {
        return resDateDTO;
    }

    public void setResDateDTO(ResDateDTO resDateDTO) {
        this.resDateDTO = resDateDTO;
    }
}