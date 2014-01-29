/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Settings;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vo.SettingsConfiguration;

/**
 *
 * @author bruno
 */
@Singleton
public class SettingsFacade extends AbstractFacade<Settings> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    private SettingsConfiguration settingsConfiguration;
    @EJB
    private SubscribersFacade subscribersFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SettingsFacade() {
        super(Settings.class);
    }

    public SettingsConfiguration getSettingsConfiguration() {
        if (settingsConfiguration == null) {
            try {
                List<Settings> setss = this.findAll();
                settingsConfiguration = generateSettingsConfiguratiuon(setss);
                return settingsConfiguration;
            } catch (Exception ex) {
                context.setRollbackOnly();
                ex.printStackTrace();
            }
        }
        return settingsConfiguration;
    }

    private SettingsConfiguration generateSettingsConfiguratiuon(List<Settings> setss) {
        SettingsConfiguration conf = new SettingsConfiguration();
        for (Settings set : setss) {
            conf.putProperty(set);
        }
        return conf;
    }

    public void saveSettings(SettingsConfiguration conf) {
        try {
            for (String name : conf.getAllNames()) {
                saveSetting(name, conf.getProperty(name));
            }
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void saveSettings(Map parameterMap) {
        try {
            for (Object name : parameterMap.keySet()) {
                if (name.toString().equals("defaultnoncvplan")) {
                    subscribersFacade.updatePlanDefault(((String[]) parameterMap.get(name))[0]);
                }
                saveSetting(name.toString(), ((String[]) parameterMap.get(name))[0]);
            }
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public void saveSetting(String name, String value) {
        Settings set;
        if (settingsConfiguration != null) {
            settingsConfiguration.putProperty(name, value);
        }
        try {
            set = (Settings) em.createNamedQuery("Settings.findByName").
                    setParameter("name", name).getSingleResult();
            set.setValue(value);
            em.persist(set);
        } catch (Exception ex) {
            set = new Settings();
            set.setName(name);
            set.setValue(value);
            em.persist(set);
        }
        em.flush();
    }

    public String getTemporaryAccountForCV() {
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "temporaryaccountcv").getSingleResult();
            return set.getValue();
        } catch (Exception ex) {
        }
        return null;
    }

    public String getTemporaryAccountForNOCV() {
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "temporaryaccountnocv").getSingleResult();
            return set.getValue();
        } catch (Exception ex) {
        }
        return null;
    }

    public String getDefaultCVPlan() {
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "defaultcvplan").getSingleResult();
            return set.getValue();
        } catch (Exception ex) {
        }
        return null;
    }

    public String getDefaultNONCVPlan() {
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "defaultnoncvplan").getSingleResult();
            return set.getValue();
        } catch (Exception ex) {
        }
        return null;
    }

    public boolean canRemovePlan(String planName) {
        boolean result = true;
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "defaultcvplan").getSingleResult();
            if (set.getValue().equals(planName)) {
                result = false;
            }
        } catch (Exception ex) {
        }
        try {
            Settings set = (Settings) em.createNamedQuery("Settings.findByName").setParameter("name", "defaultnoncvplan").getSingleResult();
            if (set.getValue().equals(planName)) {
                result = false;
            }
        } catch (Exception ex) {
        }
        return result;
    }
}
