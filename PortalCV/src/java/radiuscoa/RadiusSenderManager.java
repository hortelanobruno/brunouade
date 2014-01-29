/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiuscoa;

/**
 *
 * @author bruno
 */
public class RadiusSenderManager {

    private static RadiusSenderManager manager;
    private String radiusServer = "10.0.0.1";
    private Integer radiusport = 1700;
    private String sharedSecret = "callis";
    private String initialVector = "IIIIIIIIIIIIIIII";

    public RadiusSenderManager() {
    }

    public RadiusSenderManager(String radiusServer, Integer radiusport, String sharedSecret) {
        this.radiusServer = radiusServer;
        this.radiusport = radiusport;
        this.sharedSecret = sharedSecret;
    }

    public static RadiusSenderManager getInstance() {
        if (manager == null) {
            manager = new RadiusSenderManager();
        }
        return manager;
    }

    public String getRadiusServer() {
        return radiusServer;
    }

    public Integer getRadiusport() {
        return radiusport;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }
}
