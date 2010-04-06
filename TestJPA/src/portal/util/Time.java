/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.util;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Time implements Serializable {

    private Long value;//milliseconds

    public Time() {
    }

    public Time(Long seconds) {
        setValueInSeconds(seconds);
    }

    public Time(Long va, MetricTime mt) {
        switch (mt) {
            case DAYS:
                setValueInHoues(va * 24);
                break;
            case HOURS:
                setValueInHoues(va);
                break;
            case MINUTES:
                setValueInMinutes(va);
                break;
            case SECONDS:
                setValueInSeconds(va);
                break;
        }
    }

    public void setValueInMilliSeconds(Long value) {
        this.value = value;
    }

    public void setValueInSeconds(Long value) {
        setValueInMilliSeconds(value * 1000);
    }

    public void setValueInMinutes(Long value) {
        setValueInSeconds(value * 60);
    }

    public void setValueInHoues(Long value) {
        setValueInMinutes(value * 60);
    }

    public Long getValueInMilliSeconds() {
        return value;
    }

    public Long getValueInSeconds() {
        return getValueInMilliSeconds() / 1000;
    }

    public Long getValueInMinutes() {
        return getValueInSeconds() / 60;
    }

    public Long getValueInHours() {
        return getValueInMinutes() / 60;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Time other = (Time) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        if ((value % (3600000 * 24)) == 0) {
            int a = (int) (value / (3600000 * 24));
            return a + " " + MetricTime.DAYS.getValueSimplified();
        } else if ((value % 3600000) == 0) {
            int a = (int) (value / (3600000));
            return a + " " + MetricTime.HOURS.getValueSimplified();
        } else if ((value % 60000) == 0) {
            int a = (int) (value / (60000));
            return a + " " + MetricTime.MINUTES.getValueSimplified();
        } else if ((value % 1000) == 0) {
            int a = (int) (value / (1000));
            return a + " " + MetricTime.SECONDS.getValueSimplified();
        } else {
            return value + " " + MetricTime.MILISECONDS.getValueSimplified();
        }
    }
}
