package com.sauce.utiliese;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    public static void openExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);

        System.out.println("File: " + filePath);
        System.out.println("Requested Sheet: " + sheetName);
        System.out.println("Number of Sheets: " + workbook.getNumberOfSheets());

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("Sheet " + i + ": " + workbook.getSheetName(i));
        }

        sheet = workbook.getSheet(sheetName);

        System.out.println("Selected Sheet: " + sheet);
    }

    public static String getCellData(int rowNum, int colNum) {
        return sheet.getRow(rowNum).getCell(colNum).toString();
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static void closeExcel() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
    
    public static int getColumnNumber(String columnName) {

        Row headerRow = sheet.getRow(0);

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {

            if (headerRow.getCell(i).getStringCellValue()
                    .equalsIgnoreCase(columnName)) {

                return i;
            }
        }

        return -1;
    }
    
    public static String getCellData(int rowNum, String columnName) {

        int colNum = getColumnNumber(columnName);

        if (colNum == -1) {
            throw new IllegalArgumentException(
                    "Column not found: " + columnName);
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(
            sheet.getRow(rowNum).getCell(colNum)
        );
    }
}
