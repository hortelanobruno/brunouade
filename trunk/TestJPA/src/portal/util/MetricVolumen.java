/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.util;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public enum MetricVolumen implements Serializable {

    BYTES("Bytes", "B"), KILOBYTES("Kilobytes", "KB"), MEGABYTES("Megabytes", "MB"), GIGABYTES("Gigabytes", "GB");
    private String value;
    private String valueSimplified;

    private MetricVolumen(String a, String b) {
        value = a;
        valueSimplified = b;
    }

    public String getValue() {
        return value;
    }

    public String getValueSimplified() {
        return valueSimplified;
    }

    public static MetricVolumen getType(String a) {
        for (MetricVolumen aa : MetricVolumen.values()) {
            if (aa.getValue().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }

    public static MetricVolumen getTypeSimplified(String a) {
        for (MetricVolumen aa : MetricVolumen.values()) {
            if (aa.getValueSimplified().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }
}
