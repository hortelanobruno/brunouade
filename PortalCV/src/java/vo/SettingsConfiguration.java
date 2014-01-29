/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Settings;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bruno
 */
public class SettingsConfiguration implements Serializable {

    private Map<String, String> properties;

    public SettingsConfiguration() {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
    }

    public void putProperty(Settings set) {
        properties.put(set.getName(), set.getValue());
    }

    public void putProperty(String name, String value) {
        properties.put(name, value);
    }

    public String getProperty(String pro) {
        if (properties.containsKey(pro)) {
            return properties.get(pro);
        }
        return null;
    }

    public Set<String> getAllNames() {
        return properties.keySet();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SettingsConfiguration other = (SettingsConfiguration) obj;
        if (this.properties != other.properties && (this.properties == null || !this.properties.equals(other.properties))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.properties != null ? this.properties.hashCode() : 0);
        return hash;
    }
}
