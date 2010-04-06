/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.util;

import java.io.Serializable;

/**
 *
 * @author brunoli
 */
public enum PlanType implements Serializable {

    PREPAID_VOLUME("Plan Prepago Volumen"),
    PREPAID_TIME("Plan Prepago Tiempo"),
    POSPAID_VOLUME("Plan Pospago Volumen"),
    POSPAID_TIME("Plan Pospago Tiempo"),
    PERIODIC_VOLUME("Plan Periodico Volumen"),
    PERIODIC_TIME("Plan Periodico Tiempo"),
    MIXTO("Plan Mixto");
    private String value;

    private PlanType(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public PlanType getType(String v) {
        for (PlanType aa : PlanType.values()) {
            if (aa.getValue().equalsIgnoreCase(v)) {
                return aa;
            }
        }
        return null;
    }
}
