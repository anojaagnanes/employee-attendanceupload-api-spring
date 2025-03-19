package com.sdp.wdm.employee_attendance_upload.service.impl;

import com.sdp.wdm.employee_attendance_upload.entity.AttendanceFile;
import com.sdp.wdm.employee_attendance_upload.repository.AttendanceFileRepository;
import com.sdp.wdm.employee_attendance_upload.utility.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileStorageService {

    @Autowired
    private AttendanceFileRepository repository;

    public void saveExcelFile(MultipartFile file) {
        try {
            List<AttendanceFile> employees = ExcelHelper.excelToEmployeeList(file.getInputStream());
            repository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Error saving Excel file: " + e.getMessage());
        }
    }

    public List<AttendanceFile> getAllEmployees() {
        return repository.findAll();
    }

    public List<AttendanceFile> filterBy(String searchQuery, String attendanceType) {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchQuery, searchQuery);
        } else if (attendanceType != null) {
            return repository.findByAttendanceType(attendanceType);
        } else {
            return repository.findAll();
        }
    }
}