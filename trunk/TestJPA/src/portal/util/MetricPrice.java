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
public enum MetricPrice implements Serializable {

    PESOS("$", "$"),
    DOLARES("U$S", "U$S");
    private String value;
    private String valueSimplified;

    private MetricPrice(String a, String b) {
        value = a;
        valueSimplified = b;
    }

    public String getValue() {
        return value;
    }

    public String getValueSimplified() {
        return valueSimplified;
    }

    public static MetricPrice getType(String a) {
        for (MetricPrice aa : MetricPrice.values()) {
            if (aa.getValue().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }

    public static MetricPrice getTypeSimplified(String a) {
        for (MetricPrice aa : MetricPrice.values()) {
            if (aa.getValueSimplified().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }
}
