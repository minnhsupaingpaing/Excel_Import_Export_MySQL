package com.fortune.broadband.controller;

import com.fortune.broadband.ExcelHelper;
import com.fortune.broadband.entity.Customer;
import com.fortune.broadband.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/import")
    public ResponseEntity<String> importExcelData(@RequestParam("file") MultipartFile file) throws IOException {
        List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
        excelService.importExcelData(customers);
        return ResponseEntity.ok("File imported successfully.");

    }

    @GetMapping("/export-list")
    public List<Customer> exportExcelData() {
        return excelService.getAllCustomers();
    }

    @GetMapping("/export-excel")
    public ResponseEntity<String> exportExcelDataFile() throws IOException {
        excelService.exportDataToExcelFile();
        return ResponseEntity.ok("File exported successfully.");

    }
}


