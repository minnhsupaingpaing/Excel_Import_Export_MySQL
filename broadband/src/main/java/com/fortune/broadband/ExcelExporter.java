package com.fortune.broadband;

import com.fortune.broadband.entity.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelExporter {

    public static void exportToExcel(List<Customer> customers) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customers");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int rowNum = 0;
        for (Customer customer : customers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dateFormat.format(customer.getCreationDate()));
            row.createCell(1).setCellValue(dateFormat.format(customer.getActivationDate()));
            row.createCell(2).setCellValue(dateFormat.format(customer.getStatusChangeDate()));
            row.createCell(3).setCellValue(customer.getCustomerName());
            row.createCell(4).setCellValue(customer.getCustomerAccountNumber());
            row.createCell(5).setCellValue(customer.getBillingAccountNumber());
            row.createCell(6).setCellValue(customer.getCustomerCategory());
            row.createCell(7).setCellValue(customer.getPlanStatus());
            row.createCell(8).setCellValue(customer.getPlanName());
            row.createCell(9).setCellValue(customer.getRegion());
            row.createCell(10).setCellValue(customer.getPhoneNo());
            row.createCell(11).setCellValue(customer.getEmailId());
            row.createCell(12).setCellValue(customer.getBillingAddress());
        }

        try (FileOutputStream fileOut = new FileOutputStream("exported_customers.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}

