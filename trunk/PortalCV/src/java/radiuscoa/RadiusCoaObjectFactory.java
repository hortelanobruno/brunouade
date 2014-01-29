/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiuscoa;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 *
 * @author bruno
 */
public class RadiusCoaObjectFactory implements PoolableObjectFactory {

    private RadiusSenderManager manager;

    public RadiusCoaObjectFactory(RadiusSenderManager manager) {
        this.manager = manager;
    }

    @Override
    public Object makeObject() throws Exception {
        return new RadiusSender2(manager.getRadiusServer(), manager.getRadiusport(), manager.getSharedSecret());
    }

    @Override
    public void destroyObject(Object o) throws Exception {
        RadiusSender2 obj = (RadiusSender2) o;
        obj.close();
    }

    @Override
    public boolean validateObject(Object o) {
        return true;
    }

    @Override
    public void activateObject(Object o) throws Exception {
    }

    @Override
    public void passivateObject(Object o) throws Exception {
    }
}
