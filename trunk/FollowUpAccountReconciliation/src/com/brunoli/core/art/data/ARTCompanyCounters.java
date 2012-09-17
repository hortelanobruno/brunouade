/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.core.art.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brunoli
 */
public class ARTCompanyCounters {

    private List<String> reconStatusTypes;
    private Map<String, ARTReconcilierCounters> reconciliers;

    public ARTCompanyCounters(List<String> reconStatusTypes) {
        this.reconStatusTypes = reconStatusTypes;
        this.reconciliers = new HashMap<String, ARTReconcilierCounters>();
    }

    public void addDataFromDatabase(String reconcilier, String reconStatus) {
        if (!reconciliers.containsKey(reconcilier)) {
            reconciliers.put(reconcilier, new ARTReconcilierCounters(reconStatusTypes));
        }
        reconciliers.get(reconcilier).addDataFromDatabase(reconStatus);
    }

    public void printData() {
        for(String reconcilier : reconciliers.keySet()){
            System.out.println("Reconcilier: "+reconcilier);
            reconciliers.get(reconcilier).printData();
        }
    }

    public Map<String, ARTReconcilierCounters> getData() {
        return reconciliers;
    }
}
