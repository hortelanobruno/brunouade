/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.vo;

/**
 *
 * @author brunoli
 */
public class WalletVO {

    private Long id;
    private Double value;

    public WalletVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
