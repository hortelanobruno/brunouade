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
public enum MetricTime implements Serializable {

    DAYS("Days", "day"), HOURS("Hours", "hour"), MINUTES("Minutes", "min"), SECONDS("Seconds", "sec"), MILISECONDS("Miliseconds", "ms");
    private String value;
    private String valueSimplified;

    private MetricTime(String a, String b) {
        value = a;
        valueSimplified = b;
    }

    public String getValue() {
        return value;
    }

    public String getValueSimplified() {
        return valueSimplified;
    }

    public static MetricTime getType(String a) {
        for (MetricTime aa : MetricTime.values()) {
            if (aa.getValue().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }

    public static MetricTime getTypeSimplified(String a) {
        for (MetricTime aa : MetricTime.values()) {
            if (aa.getValueSimplified().equalsIgnoreCase(a)) {
                return aa;
            }
        }
        return null;
    }
}
