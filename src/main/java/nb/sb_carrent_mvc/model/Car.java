package nb.sb_carrent_mvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Car {
    @Id
    @Column
    private Integer id;

    @Column
    private String type;

    @Column
    private Integer dailyPrice;

    @Column
    private Byte[] image;

    @Column
    private Boolean active;

    public Car(Integer id, String type, Integer dailyPrice, Byte[] image, Boolean active) {
        this.id = id;
        this.type = type;
        this.dailyPrice = dailyPrice;
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

    public Integer getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Integer dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}