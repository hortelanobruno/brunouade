
import portal.db.controller.PackEntityJpaController;
import portal.db.entity.PackEntity;
import portal.util.PlanType;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brunoli
 */
public class Main {

    public Main() {
        solver();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void solver() {
        PackEntityJpaController con = new PackEntityJpaController();
        PackEntity entity = new PackEntity();
        entity.setDuration(100L);
        entity.setName("Pack 2");
        entity.setPackValue(300L);
        entity.setPlanType(PlanType.MIXTO);
        entity.setPrice(30D);
        entity.setStock(300);
        con.create(entity);
    }
}
