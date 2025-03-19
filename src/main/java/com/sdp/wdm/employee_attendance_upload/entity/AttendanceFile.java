package com.sdp.wdm.employee_attendance_upload.entity;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "attendance_file")
public class AttendanceFile {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empId;
    private String teamRole;
    private String email;
    private String stack;
    private String destination;
    private String name;
    private String tier;
    private LocalDate attendanceDate;
    private String attendanceType;

}