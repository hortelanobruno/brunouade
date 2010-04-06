/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.vo;

/**
 *
 * @author brunoli
 */
public class ServiceVO {

    private Long id;
    private String name;
    private Double price;
    private Long duration;//seconds
    private Long packValue;//kb
    private Integer stock;

    public ServiceVO() {
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPackValue() {
        return packValue;
    }

    public void setPackValue(Long packValue) {
        this.packValue = packValue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
