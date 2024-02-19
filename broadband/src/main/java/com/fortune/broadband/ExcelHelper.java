package com.fortune.broadband;

import com.fortune.broadband.entity.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<Customer> excelToCustomers(InputStream inputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        List<Customer> customers = new ArrayList<>();

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (currentRow.getRowNum() == 0) {
                continue; // skip header row
            }

            Customer customer = new Customer();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            customer.setCreationDate(getDateCellValue(currentRow.getCell(0), dateFormat));
            customer.setActivationDate(getDateCellValue(currentRow.getCell(1), dateFormat));
            customer.setStatusChangeDate(getDateCellValue(currentRow.getCell(2), dateFormat));

            customer.setCustomerName(getStringCellValue(currentRow.getCell(3)));
            customer.setCustomerAccountNumber(getStringCellValue(currentRow.getCell(4)));
            customer.setBillingAccountNumber(getStringCellValue(currentRow.getCell(5)));
            customer.setCustomerCategory(getStringCellValue(currentRow.getCell(6)));
            customer.setPlanStatus(getStringCellValue(currentRow.getCell(7)));
            customer.setPlanName(getStringCellValue(currentRow.getCell(8)));
            customer.setRegion(getStringCellValue(currentRow.getCell(9)));
            customer.setPhoneNo(getStringCellValue(currentRow.getCell(10)));
            customer.setEmailId(getStringCellValue(currentRow.getCell(11)));
            customer.setBillingAddress(getStringCellValue(currentRow.getCell(12)));

            customers.add(customer);
        }

        workbook.close();
        return customers;
    }

    private static Date getDateCellValue(Cell cell, SimpleDateFormat dateFormat) {
        try {
            return cell.getDateCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    private static String getStringCellValue(Cell cell) {
        return cell == null ? null : cell.getStringCellValue();
    }
}

