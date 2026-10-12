package nb.sb_carrent_mvc.dto;

import java.time.LocalDate;

public class UserResDTO {
    private String name;
    private String email;
    private String address;
    private String phone;
    private UserSelectionDTO userSelectionDTO;

    public UserResDTO(String name, String email, String address, String phone, Integer carId, Integer totalPrice, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.userSelectionDTO = new UserSelectionDTO(carId, totalPrice, startDate, endDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserSelectionDTO getUserSelectionDTO() {
        return userSelectionDTO;
    }

    public void setUserSelectionDTO(UserSelectionDTO userSelectionDTO) {
        this.userSelectionDTO = userSelectionDTO;
    }
}