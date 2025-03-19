package com.sdp.wdm.employee_attendance_upload.repository;

import com.sdp.wdm.employee_attendance_upload.entity.AttendanceFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceFileRepository extends JpaRepository<AttendanceFile, Long> {

    List<AttendanceFile> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

    List<AttendanceFile> findByAttendanceType(String attendanceType);
}