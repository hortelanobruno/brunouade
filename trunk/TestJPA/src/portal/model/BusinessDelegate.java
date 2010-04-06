/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.model;

import portal.db.controller.PackEntityJpaController;
import portal.db.controller.PlanEntityJpaController;
import portal.db.controller.ProvisioningEntityJpaController;
import portal.db.controller.ServiceEntityJpaController;
import portal.db.controller.UserEntityJpaController;
import portal.db.controller.UserPersonalInformationEntityJpaController;
import portal.db.controller.WalletEntityJpaController;

/**
 *
 * @author brunoli
 */
public class BusinessDelegate {

    private PackEntityJpaController packEntityJpaController;
    private PlanEntityJpaController planEntityJpaController;
    private ProvisioningEntityJpaController provisioningEntityJpaController;
    private ServiceEntityJpaController serviceEntityJpaController;
    private UserEntityJpaController userEntityJpaController;
    private UserPersonalInformationEntityJpaController userPersonalInformationEntityJpaController;
    private WalletEntityJpaController walletEntityJpaController;

    public BusinessDelegate() {
        packEntityJpaController = new PackEntityJpaController();
        planEntityJpaController = new PlanEntityJpaController();
        provisioningEntityJpaController = new ProvisioningEntityJpaController();
        serviceEntityJpaController = new ServiceEntityJpaController();
        userEntityJpaController = new UserEntityJpaController();
        userPersonalInformationEntityJpaController = new UserPersonalInformationEntityJpaController();
        walletEntityJpaController = new WalletEntityJpaController();
    }
}
