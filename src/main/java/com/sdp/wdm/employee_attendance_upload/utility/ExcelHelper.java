//package com.sdp.wdm.employee_attendance_upload.utility;
//
//import com.sdp.wdm.employee_attendance_upload.entity.AttendanceFile;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ExcelHelper {
//
//    public static List<AttendanceFile> excelToEmployeeList(InputStream is) {
//        List<AttendanceFile> employees = new ArrayList<>();
//        try (Workbook workbook = new XSSFWorkbook(is)) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rows = sheet.iterator();
//            rows.next(); // Skip header row
//
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//                AttendanceFile employee = new AttendanceFile();
//                employee.setEmpId(currentRow.getCell(0).getStringCellValue());
//                employee.setTeamRole(currentRow.getCell(1).getStringCellValue());
//                employee.setEmail(currentRow.getCell(2).getStringCellValue());
//                employee.setStack(currentRow.getCell(3).getStringCellValue());
//                employee.setDestination(currentRow.getCell(4).getStringCellValue());
//                employee.setName(currentRow.getCell(5).getStringCellValue());
//                employee.setTier(currentRow.getCell(6).getStringCellValue());
//                employee.setAttendanceDate(currentRow.getCell(7).getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
//                employee.setAttendanceType(currentRow.getCell(8).getStringCellValue());
//                employees.add(employee);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return employees;
//    }
//
//}

package com.sdp.wdm.employee_attendance_upload.utility;

import com.sdp.wdm.employee_attendance_upload.entity.AttendanceFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<AttendanceFile> excelToEmployeeList(InputStream is) throws IOException { // Add IOException to the throws clause
        List<AttendanceFile> employees = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header row

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                AttendanceFile employee = new AttendanceFile();

                // Handle EmpId, TeamRole, Email, Stack, Destination, Name
                employee.setEmpId(getCellValueAsString(currentRow.getCell(0)));
                employee.setTeamRole(getCellValueAsString(currentRow.getCell(1)));
                employee.setEmail(getCellValueAsString(currentRow.getCell(2)));
                employee.setStack(getCellValueAsString(currentRow.getCell(3)));
                employee.setDestination(getCellValueAsString(currentRow.getCell(4)));
                employee.setName(getCellValueAsString(currentRow.getCell(5)));

                // Handle Tier (can be numeric)
                employee.setTier(getCellValueAsString(currentRow.getCell(6)));

                // Handle AttendanceDate
                Cell dateCell = currentRow.getCell(7);
                if (dateCell != null && dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                    employee.setAttendanceDate(dateCell.getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                } else {
                    employee.setAttendanceDate(null); // Or set a default date, or handle the null case as needed.
                }

                // Handle AttendanceType
                employee.setAttendanceType(getCellValueAsString(currentRow.getCell(8)));

                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error processing excel file", e);
        }
        return employees;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue()); // Convert numeric to String
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}