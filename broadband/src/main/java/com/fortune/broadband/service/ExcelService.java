package com.fortune.broadband.service;

import com.fortune.broadband.entity.Customer;
import com.fortune.broadband.repo.CustomerRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private CustomerRepository customerRepository;

    public void importExcelData(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


        public void exportDataToExcelFile() throws IOException {
            List<Customer> customers = customerRepository.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Customers");

            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            createHeaderRow(headerRow);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (Customer customer : customers) {
                Row row = sheet.createRow(rowNum++);
                createDataRow(row, customer, dateFormat);
            }
            // Specify the directory for saving the file
            String filePath = "C:/ExportedFiles/exported_customers.xlsx";

            // Create directories if they don't exist
            Files.createDirectories(Paths.get(filePath).getParent());

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            workbook.close();
        }

        private void createHeaderRow(Row headerRow) {
            headerRow.createCell(0).setCellValue("Creation Date");
            headerRow.createCell(1).setCellValue("Activation Date");
            headerRow.createCell(2).setCellValue("Status Change Date");
            headerRow.createCell(3).setCellValue("Customer Name");
            headerRow.createCell(4).setCellValue("Customer Account Number");
            headerRow.createCell(5).setCellValue("Billing Account Number");
            headerRow.createCell(6).setCellValue("Customer Category");
            headerRow.createCell(7).setCellValue("Plan Status");
            headerRow.createCell(8).setCellValue("Plan Name");
            headerRow.createCell(9).setCellValue("Region");
            headerRow.createCell(10).setCellValue("Phone No");
            headerRow.createCell(11).setCellValue("Email Id");
            headerRow.createCell(12).setCellValue("Billing Address");

        }

        private void createDataRow(Row row, Customer customer, SimpleDateFormat dateFormat) {
            if (customer.getCreationDate() != null)
                row.createCell(0).setCellValue(dateFormat.format(customer.getCreationDate()));
            else
                row.createCell(0).setCellValue("");

            if (customer.getActivationDate() != null)
                row.createCell(1).setCellValue(dateFormat.format(customer.getActivationDate()));
            else
                row.createCell(1).setCellValue("");

            if (customer.getStatusChangeDate() != null)
                row.createCell(2).setCellValue(dateFormat.format(customer.getStatusChangeDate()));
            else
                row.createCell(2).setCellValue("");

            if (customer.getCustomerName() != null)
                row.createCell(3).setCellValue(String.format(customer.getCustomerName()));
            else
                row.createCell(3).setCellValue("");

            if (customer.getCustomerAccountNumber() != null)
                row.createCell(4).setCellValue(String.format(customer.getCustomerAccountNumber()));
            else
                row.createCell(4).setCellValue("");

            if (customer.getBillingAccountNumber() != null)
                row.createCell(5).setCellValue(String.format(customer.getBillingAccountNumber()));
            else
                row.createCell(5).setCellValue("");

            if (customer.getCustomerCategory() != null)
                row.createCell(6).setCellValue(String.format(customer.getCustomerCategory()));
            else
                row.createCell(6).setCellValue("");

            if (customer.getPlanStatus() != null)
                row.createCell(7).setCellValue(String.format(customer.getPlanStatus()));
            else
                row.createCell(7).setCellValue("");

            if (customer.getPlanName() != null)
                row.createCell(8).setCellValue(String.format(customer.getPlanName()));
            else
                row.createCell(8).setCellValue("");

            if (customer.getRegion() != null)
                row.createCell(9).setCellValue(String.format(customer.getRegion()));
            else
                row.createCell(9).setCellValue("");

            if (customer.getPhoneNo() != null)
                row.createCell(10).setCellValue(String.format(customer.getPhoneNo()));
            else
                row.createCell(10).setCellValue("");

            if (customer.getEmailId() != null)
                row.createCell(11).setCellValue(String.format(customer.getEmailId()));
            else
                row.createCell(11).setCellValue("");

            if (customer.getBillingAddress() != null)
                row.createCell(12).setCellValue(String.format(customer.getBillingAddress()));
            else
                row.createCell(12).setCellValue("");

    }
}

