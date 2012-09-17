/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.core.gbs.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author brunoli
 */
public class GBSDataCalculator {

    private List<String> reconStatusTypes;
    private List<String> reconcilierNames;
    private Map<String, GBSCompanyCounters> companyCounters;
    private Map<String, Integer> reconStatusSubTotal;
    private String[] reconStatusTypesDefault = {"Approved", "In-Process", "Rejected"};
    private String abcd = "abcdefghijklmnopqrstuvwxyz";

    public GBSDataCalculator() {
        reconStatusTypes = new ArrayList<String>();
        reconcilierNames = new ArrayList<String>();
        companyCounters = new HashMap<String, GBSCompanyCounters>();
        reconStatusSubTotal = new HashMap<String, Integer>();
    }

    public void addReconStatusTypes(List<String> newReconStatusTypes) {
        if (newReconStatusTypes != null) {
            for (String newReconStatusType : newReconStatusTypes) {
                if (!reconStatusTypes.contains(newReconStatusType)) {
                    reconStatusTypes.add(newReconStatusType);
                }
            }
        }
    }

    public void addReconcilierNames(List<String> newReconcilierNames) {
        if (newReconcilierNames != null) {
            for (String newReconcilierName : newReconcilierNames) {
                if (!reconcilierNames.contains(newReconcilierName)) {
                    reconcilierNames.add(newReconcilierName);
                }
            }
        }
    }

    public void addDataFromDatabase(String reconcilier, String companyName, String reconStatus) {
        if (reconcilierNames.contains(reconcilier)) {
            if (!companyCounters.containsKey(companyName)) {
                companyCounters.put(companyName, new GBSCompanyCounters(reconStatusTypes));
            }
            companyCounters.get(companyName).addDataFromDatabase(reconcilier, reconStatus);
            if (!reconStatusSubTotal.containsKey(reconStatus)) {
                reconStatusSubTotal.put(reconStatus, 0);
            }
            reconStatusSubTotal.put(reconStatus, reconStatusSubTotal.get(reconStatus) + 1);
        }
    }

    public void printData() {
        for (String companyName : companyCounters.keySet()) {
            System.out.println("Company: " + companyName);
            companyCounters.get(companyName).printData();
        }
    }

    public void generateOutputExcelFile(String outputPath) {
        FileOutputStream fileOut = null;
        try {
            Workbook wb = new HSSFWorkbook();
            //Workbook wb = new XSSFWorkbook();
            CreationHelper createHelper = wb.getCreationHelper();
            CellStyle cs = wb.createCellStyle();
            cs.setDataFormat(createHelper.createDataFormat().getFormat("00.00%"));
            cs.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
            CellStyle cs2 = wb.createCellStyle();
            cs2.setDataFormat(createHelper.createDataFormat().getFormat("00.00%"));
            cs2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            cs2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            CellStyle cs3 = wb.createCellStyle();
            Font font = wb.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cs3.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            cs3.setFont(font);
            cs3.setFillPattern(CellStyle.SOLID_FOREGROUND);
            Sheet sheet = wb.createSheet("new sheet");
            // Create a row and put some cells in it. Rows are 0 based.
            int rowCount = 0;
            Row row = sheet.createRow((short) rowCount++);
            // Or do it on one line.
            int celCount = 0;
            Cell ce;
            ce = row.createCell(celCount++);
            ce.setCellValue(createHelper.createRichTextString("ME NAME"));
            ce.setCellStyle(cs3);
            ce = row.createCell(celCount++);
            ce.setCellValue(createHelper.createRichTextString("RECONCILIER"));
            ce.setCellStyle(cs3);
            for (String reconStatusType : reconStatusTypesDefault) {
                if (reconStatusTypes.contains(reconStatusType)) {
                    ce = row.createCell(celCount++);
                    ce.setCellValue(createHelper.createRichTextString(reconStatusType));
                    ce.setCellStyle(cs3);
                }
            }
            ce = row.createCell(celCount++);
            ce.setCellValue(createHelper.createRichTextString("Total General"));
            ce.setCellStyle(cs3);
            int colTotal = celCount;
            ce = row.createCell(celCount++);
            ce.setCellValue(createHelper.createRichTextString("Percent Complete"));
            ce.setCellStyle(cs3);



            Map<String, GBSReconcilierCounters> recons;
            Map<String, Integer> reconStatusCounters;
            int reconCount = 0;
            int total = 0;
            int percent = 0;
            int sumtotal = 0;

            Integer aux = 0;
            for (String companyName : companyCounters.keySet()) {
                recons = companyCounters.get(companyName).getData();
                reconCount = 0;
                for (String reconcilier : recons.keySet()) {
                    aux = 0;
                    reconStatusCounters = recons.get(reconcilier).getData();
                    total = recons.get(reconcilier).getTotal();
                    sumtotal += total;
                    percent = recons.get(reconcilier).getPercentajeCompleted();
                    //creo row
                    row = sheet.createRow((short) rowCount++);
                    celCount = 0;
                    if (reconCount == 0) {
                        row.createCell(celCount++).setCellValue(createHelper.createRichTextString(companyName));
                    } else {
                        celCount++;
                    }
                    row.createCell(celCount++).setCellValue(createHelper.createRichTextString(reconcilier));
                    for (String reconStatusType : reconStatusTypesDefault) {
                        if (reconStatusTypes.contains(reconStatusType)) {
                            if (reconStatusType.equals(reconStatusTypesDefault[0]) || reconStatusType.equals(reconStatusTypesDefault[1])) {
                                aux += reconStatusCounters.get(reconStatusType);
                            }
                            row.createCell(celCount++).setCellValue(reconStatusCounters.get(reconStatusType));
                        }
                    }
                    row.createCell(celCount++).setCellValue(total);
                    ce = row.createCell(celCount++);
                    if (aux.intValue() == total) {
                        ce.setCellStyle(cs);
                    } else {
                        ce.setCellStyle(cs2);
                    }
                    ce.setCellFormula("(C" + rowCount + "+D" + rowCount + ")/" + ("" + abcd.charAt(colTotal - 1)).toUpperCase() + rowCount);
                    reconCount++;
                }
            }
            aux = 0;
            row = sheet.createRow((short) rowCount++);
            celCount = 0;
            row.createCell(celCount++).setCellValue(createHelper.createRichTextString("Grand total"));
            celCount++;
            for (String reconStatusType : reconStatusTypes) {
                if (reconStatusType.equals(reconStatusTypesDefault[0]) || reconStatusType.equals(reconStatusTypesDefault[1])) {
                    aux += reconStatusSubTotal.get(reconStatusType);
                }
                row.createCell(celCount++).setCellValue(reconStatusSubTotal.get(reconStatusType));
            }
            row.createCell(celCount++).setCellValue(sumtotal);
            ce = row.createCell(celCount++);
            if (aux.intValue() == sumtotal) {
                ce.setCellStyle(cs);
            } else {
                ce.setCellStyle(cs2);
            }
            ce.setCellFormula("(C" + rowCount + "+D" + rowCount + ")/" + ("" + abcd.charAt(colTotal - 1)).toUpperCase() + rowCount);

            for (int i = 0; i < celCount; i++) {
                sheet.autoSizeColumn(i);
            }
            fileOut = new FileOutputStream(outputPath);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            Logger.getLogger(GBSDataCalculator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(GBSDataCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
