package com.sdp.wdm.employee_attendance_upload.controller;

import com.sdp.wdm.employee_attendance_upload.entity.AttendanceFile;
import com.sdp.wdm.employee_attendance_upload.service.impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    private FileStorageService service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        service.saveExcelFile(file);
        return "File uploaded successfully!";
    }

    @GetMapping("/filter")
    public List<AttendanceFile> getFilteredEmployees(@RequestParam(required = false) String searchQuery,
                                                     @RequestParam(required = false) String attendanceType) {
        return service.filterBy(searchQuery, attendanceType);
    }
}