package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */



public class ExcelReader {

    public static Object[][] readRoomData(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Object[]> dataList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Object[] rowData = new Object[6];
            //rowData[0] = (int) row.getCell(0).getNumericCellValue(); // roomid
            rowData[0] = String.valueOf((int)row.getCell(0).getNumericCellValue()); // roomName
            rowData[1] = row.getCell(1).getStringCellValue(); // type
            rowData[2] = row.getCell(2).getBooleanCellValue(); // accessible
            rowData[3] = (int) row.getCell(3).getNumericCellValue(); // price
            rowData[4] = row.getCell(4).getStringCellValue(); // description
            rowData[5] = row.getCell(5).getStringCellValue().split(","); // features
            dataList.add(rowData);
        }

        workbook.close();
        fis.close();
        return dataList.toArray(new Object[0][0]);
    }
    
    
    public static Object[][] readBookingData(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Object[]> dataList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Object[] rowData = new Object[7];
        
            rowData[0] = row.getCell(0).getStringCellValue(); // firstname
            rowData[1] = row.getCell(1).getStringCellValue(); // lastname
            rowData[2] = row.getCell(2).getStringCellValue(); // email
            rowData[3] = BigDecimal.valueOf(row.getCell(3).getNumericCellValue()).toPlainString(); // phone  
            rowData[4] = row.getCell(4).getBooleanCellValue(); // deposit paid
            rowData[5] = row.getCell(5).getStringCellValue(); // checkIn
            rowData[6] = row.getCell(6).getStringCellValue(); // checkOut
            dataList.add(rowData);
        }

        workbook.close();
        fis.close();
        return dataList.toArray(new Object[0][0]);
    }
}


