package com.sdp.wdm.employee_attendance_upload;

import org.springframework.boot.SpringApplication;

public class TestEmployeeAttendanceUploadApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeeAttendanceUploadApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
