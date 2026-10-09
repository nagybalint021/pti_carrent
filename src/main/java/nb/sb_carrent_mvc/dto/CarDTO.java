package nb.sb_carrent_mvc.dto;

public class CarDTO {
    private Integer id;
    private String type;
    private Integer price;
    private String image;
    private Boolean active;

    public CarDTO(Integer id, String type, Integer price, String image, Boolean active) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.image = image;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}