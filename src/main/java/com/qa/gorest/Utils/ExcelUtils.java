package com.qa.gorest.Utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
public class ExcelUtils {
    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/TestData/API_Data.xlsx";
    private static Workbook book;
    private static Sheet sheet;
    public static Object[][] getTestData(String sheetName) {
        Object data[][] = null;
        try{
            FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
            book = WorkbookFactory.create(fis);
            sheet =  book.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for(int i = 0; i<sheet.getLastRowNum() ; i++){
                for(int j = 0 ; j<sheet.getRow(0).getLastCellNum() ; j++){
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }




}
