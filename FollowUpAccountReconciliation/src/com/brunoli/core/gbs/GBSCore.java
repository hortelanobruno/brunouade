/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.core.gbs;

import com.brunoli.core.gbs.data.GBSDataCalculator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author brunoli
 */
public class GBSCore {

    private List<String> names;
    private List<String> reconStatusTypes;

    public GBSCore() {
    }

    public static void main(String[] args) {
        String fileNames = "C:\\Users\\brunoli\\Documents\\NetBeansProjects\\FollowUpAccountReconciliation\\examples\\Names GBS.xlsx";
        String fileNames2 = "C:\\Users\\brunoli\\Documents\\NetBeansProjects\\FollowUpAccountReconciliation\\examples\\Reporte GBS tool.xlsx";
        String fileNames3 = "C:\\Users\\brunoli\\Documents\\NetBeansProjects\\FollowUpAccountReconciliation\\examples\\aux33.xlsx";
        GBSCore core = new GBSCore();
        core.calculateNames(fileNames, 0, 0);
        core.calculateDatabase(fileNames2, 23, 15, 0);
        core.generateOutput(fileNames2, fileNames3, 23, 15, 1, 0);
    }

    private void calculateNames(String fileName, Integer nfColReconciler, Integer pageNumber) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(fileName);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(pageNumber);
            //Obtengo lista de nombres y companias
            names = new ArrayList<String>();
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row;
            Cell cell, cell2;
            String name, name2;
            for (int i = 0; i < rowCount; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(nfColReconciler);
                if (cell != null) {
                    name = cell.getStringCellValue();
                    if (name != null && !name.trim().isEmpty()) {
                        names.add(name.trim());
                    }
                }
            }
            System.out.println(names);
        } catch (Exception ex) {
            Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void calculateDatabase(String fileName, Integer dfColReconStatus, Integer dfColReconciler, Integer pageNumber) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(fileName);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(pageNumber);
            //Obtengo lista de nombres y companias
            reconStatusTypes = new ArrayList<String>();
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row;
            Cell cell, cell2, cell3;
            String name, name2, name3;
            for (int i = 0; i < rowCount; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(dfColReconStatus);
                cell2 = row.getCell(dfColReconciler);
                if (cell != null) {
                    name = cell.getStringCellValue();
                    name2 = cell2.getStringCellValue();
                    if (name != null && !name.trim().isEmpty() && name2 != null && !name2.trim().isEmpty()) {
                        if (names.contains(name2)) {
                            if (!reconStatusTypes.contains(name.trim())) {
                                reconStatusTypes.add(name.trim());
                            }
                        }
                    }
                }
            }
            System.out.println(reconStatusTypes);
        } catch (Exception ex) {
            Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generateOutput(String fileName, String outputFile, Integer dfColReconStatus, Integer dfColReconciler, Integer dfColCompanyName, Integer pageNumber) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(fileName);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(pageNumber);
            //Obtengo lista de nombres y companias
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row;
            Cell cell, cell2, cell3, cell4;
            String name, name2, name3, name4;
            GBSDataCalculator calc = new GBSDataCalculator();
            calc.addReconcilierNames(names);
            calc.addReconStatusTypes(reconStatusTypes);
            for (int i = 0; i < rowCount; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(dfColReconStatus);
                cell2 = row.getCell(dfColCompanyName);
                cell3 = row.getCell(dfColReconciler);
                cell4 = row.getCell(0);
                if (cell != null && cell2 != null && cell3 != null) {
                    name = cell.getStringCellValue();
                    name2 = cell2.getStringCellValue();
                    name3 = cell3.getStringCellValue();
                    name4 = cell4.getStringCellValue();
                    if (name != null && !name.trim().isEmpty() && name2 != null && !name2.trim().isEmpty() && name3 != null && !name3.trim().isEmpty()
                            && name4 != null && !name4.trim().isEmpty()) {
                        if (name4.toLowerCase().equals("c") || name4.toLowerCase().equals("s")) {
                            calc.addDataFromDatabase(name3.trim(), name2.trim(), name.trim());
                        }
                    }
                }
            }
            calc.printData();
            calc.generateOutputExcelFile(outputFile);
        } catch (Exception ex) {
            Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(GBSCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generateOutput(String selectedDatabaseFileName, String selectedNamesFileName, String selectedOutputFileName, Integer dfColReconciler, Integer dfColReconStatus,
            Integer dfColLocalME, Integer nfColReconciler, Integer dfPageNumber, Integer nfPageNumber) {
        calculateNames(selectedNamesFileName, nfColReconciler, nfPageNumber);
        calculateDatabase(selectedDatabaseFileName, dfColReconStatus, dfColReconciler, dfPageNumber);
        generateOutput(selectedDatabaseFileName, selectedOutputFileName, dfColReconStatus, dfColReconciler, dfColLocalME, dfPageNumber);
    }
}
