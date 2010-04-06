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
public class Volume implements Serializable {

    private Long value;

    public Volume() {
    }

    public Volume(Long kiloBits) {
        setValueInKiloBits(kiloBits);
    }

    public Volume(Long thValue, MetricVolumen mv) {
        switch (mv) {
            case BYTES:
                setValueInBytes(thValue);
                break;
            case GIGABYTES:
                setValueInGigaBytes(thValue);
                break;
            case KILOBYTES:
                setValueInKiloBytes(thValue);
                break;
            case MEGABYTES:
                setValueInMegaBytes(thValue);
                break;
        }
    }

    public void setValueInBits(Long value) {
        this.value = value;
    }

    public void setValueInBytes(Long value) {
        setValueInBits(value * 8);
    }

    public void setValueInKiloBytes(Long value) {
        setValueInBytes(value * 1024);
    }

    public void setValueInKiloBits(Long value) {
        setValueInBits(value * 1024);
    }

    public void setValueInMegaBytes(Long value) {
        setValueInKiloBytes(value * 1024);
    }

    public void setValueInMegaBits(Long value) {
        setValueInKiloBits(value * 1024);
    }

    public void setValueInGigaBytes(Long value) {
        setValueInMegaBytes(value * 1024);
    }

    public void setValueInGigaBits(Long value) {
        setValueInMegaBits(value * 1024);
    }

    public Long getValueInBits() {
        return value;
    }

    public Long getValueInBytes() {
        return getValueInBits() / 8;
    }

    public Long getValueInKiloBits() {
        return getValueInBits() / 1024;
    }

    public Long getValueInKiloBytes() {
        return getValueInBytes() / 1024;
    }

    public Long getValueInMegaBits() {
        return getValueInKiloBits() / 1024;
    }

    public Long getValueInMegaBytes() {
        return getValueInKiloBytes() / 1024;
    }

    public Long getValueInGigaBits() {
        return getValueInMegaBits() / 1024;
    }

    public Long getValueInGigaBytes() {
        return getValueInMegaBytes() / 1024;
    }

    @Override
    public String toString() {
        if ((getValueInBytes() % 1073741824) == 0) {
            int a = (int) (getValueInBytes() / (1073741824));
            return a + " " + MetricVolumen.GIGABYTES.getValueSimplified();
        } else if ((getValueInBytes() % (1048576)) == 0) {
            int a = (int) (getValueInBytes() / (1048576));
            return a + " " + MetricVolumen.MEGABYTES.getValueSimplified();
        } else if ((getValueInBytes() % 1024) == 0) {
            int a = (int) ((getValueInBytes() / 1024));
            return a + " " + MetricVolumen.KILOBYTES.getValueSimplified();
        } else {
            return getValueInBytes() + " " + MetricVolumen.BYTES.getValueSimplified();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
}
