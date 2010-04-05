/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testjpa;

import java.util.logging.Level;
import java.util.logging.Logger;
import testjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author brunoli
 */
public class Main {

    public Main() {
        solver();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void solver() {
        try {
            PlanEntityJpaController pej = new PlanEntityJpaController();
            PlanEntity pe = new PlanEntity();
            pe.setPackageId(1);
            pe.setSubscriptionPrice(200.0);
            pe.setTestPackageId(6);
            pe.setTestTime(160L);
            pej.create(pe);
            WalletJpaController walletJpa = new WalletJpaController();
            Wallet wallet = new Wallet();
            wallet.setPrice(4.3);
            ObjectoConListaJpaController ocl = new ObjectoConListaJpaController();
            ObjectoConLista o = ocl.findObjectoConLista(203L);
            o.getPlanesTesteados().add(pe);
            ocl.edit(o);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
