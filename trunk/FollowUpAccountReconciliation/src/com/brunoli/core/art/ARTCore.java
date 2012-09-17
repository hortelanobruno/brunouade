/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.core.art;

import com.brunoli.core.art.data.ARTDataCalculator;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author brunoli
 */
public class ARTCore {

    private List<String> names;
    private Map<String, String> companies;
    private List<String> companyNames;
    private List<String> reconStatusTypes;
    

    public ARTCore() {
    }

    public static void main(String[] args) {
        String fileNames = "C:\\Sabrina\\Base nombres.xlsx";
        String fileNames2 = "C:\\Sabrina\\Base ART.xlsx";
        String fileNames3 = "C:\\Sabrina\\aux1.xlsx";
        ARTCore core = new ARTCore();
        core.calculateNames(fileNames, 2, 0, 3, 0);
        core.calculateDatabase(fileNames2, 0, 14, 1);
        core.generateOutput(fileNames2,fileNames3, 0, 14, 4, 1);
    }

    private void calculateNames(String fileName, Integer nfColLocalME, Integer nfColReconciler, Integer nfColMEName, Integer pageNumber) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(fileName);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(pageNumber);
            //Obtengo lista de nombres y companias
            names = new ArrayList<String>();
            companies = new HashMap<String, String>();
            companyNames = new ArrayList<String>();
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
                cell = row.getCell(nfColLocalME);
                cell2 = row.getCell(nfColMEName);
                if (cell != null && cell2 != null) {
                    name = cell.getStringCellValue();
                    name2 = cell2.getStringCellValue();
                    if (name != null && !name.trim().isEmpty() && name2 != null && !name2.trim().isEmpty()) {
                        companies.put(name.trim(), name2.trim());
                        if (!companyNames.contains(name2.trim())) {
                            companyNames.add(name2.trim());
                        }
                    }
                }
            }
            System.out.println(names);
            System.out.println(companies);
            System.out.println(companyNames);
        } catch (Exception ex) {
            Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generateOutput(String fileName, String outputFile, Integer dfColReconStatus, Integer dfColReconciler, Integer dfColLocalME, Integer pageNumber) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(fileName);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(pageNumber);
            //Obtengo lista de nombres y companias
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row;
            Cell cell, cell2, cell3;
            String name, name2, name3;
            ARTDataCalculator calc = new ARTDataCalculator();
            calc.addCompaniesData(companyNames, companies);
            calc.addReconcilierNames(names);
            calc.addReconStatusTypes(reconStatusTypes);
            for (int i = 0; i < rowCount; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(dfColReconStatus);
                cell2 = row.getCell(dfColLocalME);
                cell3 = row.getCell(dfColReconciler);
                if (cell != null && cell2 != null && cell3 != null) {
                    name = cell.getStringCellValue();
                    name2 = cell2.getStringCellValue();
                    name3 = cell3.getStringCellValue();
                    if (name != null && !name.trim().isEmpty() && name2 != null && !name2.trim().isEmpty() && name3 != null && !name3.trim().isEmpty()) {
                        calc.addDataFromDatabase(name3.trim(), name2.trim(), name.trim());
                    }
                }
            }
            calc.printData();
            calc.generateOutputExcelFile(outputFile);
        } catch (Exception ex) {
            Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(ARTCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generateOutput(String selectedDatabaseFileName, String selectedNamesFileName, String selectedOutputFileName, Integer dfColReconciler, Integer dfColReconStatus,
            Integer dfColLocalME, Integer nfColLocalME, Integer nfColReconciler, Integer nfColMEName, Integer dfPageNumber, Integer nfPageNumber) {
        calculateNames(selectedNamesFileName,nfColLocalME,nfColReconciler,nfColMEName,nfPageNumber);
        calculateDatabase(selectedDatabaseFileName,dfColReconStatus,dfColReconciler,dfPageNumber);
        generateOutput(selectedDatabaseFileName,selectedOutputFileName,dfColReconStatus,dfColReconciler,dfColLocalME,dfPageNumber);
    }
    
    
}
