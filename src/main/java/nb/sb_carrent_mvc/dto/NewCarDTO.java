package nb.sb_carrent_mvc.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewCarDTO {
    private String type;
    private Integer price;
    private Boolean active;
    private MultipartFile image;

    public NewCarDTO(String type, Integer price, Boolean active, MultipartFile image) {
        this.type = type;
        this.price = price;
        this.active = active;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}