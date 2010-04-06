
import portal.db.controller.PackEntityJpaController;
import portal.db.entity.PackEntity;
import portal.util.MetricPrice;
import portal.util.PlanType;
import portal.util.Price;
import portal.util.Time;
import portal.util.Volume;

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

        PackEntity ent = con.findPackEntity(2L);
        System.out.println("aaaaa");








//        PackEntity entity = new PackEntity();
//        entity.setDuration(new Time(100L));
//        entity.setName("Pack 2");
//        entity.setPackValue(new Volume(300L));
//        entity.setPlanType(PlanType.MIXTO);
//        entity.setPrice(new Price(MetricPrice.PESOS, 3.3D));
//        entity.setStock(300);
//        con.create(entity);
    }
}
