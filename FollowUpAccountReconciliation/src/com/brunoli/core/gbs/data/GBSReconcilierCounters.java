/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.core.gbs.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brunoli
 */
public class GBSReconcilierCounters {

    private Map<String, Integer> reconStatusCounts;
    private Integer total = 0;
    private final String completedReconStatusName = "Approved";

    public GBSReconcilierCounters(List<String> reconStatusTypes) {
        reconStatusCounts = new HashMap<String, Integer>();
        for (String reconStatusType : reconStatusTypes) {
            reconStatusCounts.put(reconStatusType, 0);
        }
    }

    public void addDataFromDatabase(String reconStatus) {
        reconStatusCounts.put(reconStatus, reconStatusCounts.get(reconStatus) + 1);
        total++;
    }

    public void printData() {
        for (String reconStatusType : reconStatusCounts.keySet()) {
            System.out.println(reconStatusType + ": " + reconStatusCounts.get(reconStatusType));
        }
        System.out.println("Total: " + total);
    }

    public Map<String, Integer> getData() {
        return reconStatusCounts;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPercentajeCompleted() {
        return (reconStatusCounts.get(completedReconStatusName) * 100) / total;
    }
}
