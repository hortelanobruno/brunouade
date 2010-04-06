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
public class Price implements Serializable {

    private MetricPrice metric;
    private Double value;

    public Price() {
    }

    public Price(MetricPrice metric, Double value) {
        this.metric = metric;
        this.value = value;
    }

    public MetricPrice getMetric() {
        return metric;
    }

    public void setMetric(MetricPrice metric) {
        this.metric = metric;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return metric.getValue() + value + ".-";
    }
}
