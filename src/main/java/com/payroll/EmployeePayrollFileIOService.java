import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeePayrollFileIOService handles all File IO operations for the Employee
 * Payroll Service.
 * It supports writing, reading, printing, and counting employee payroll data
 * using Java NIO.
 * The payroll data is stored in a flat text file named "payroll-file.txt".
 *
 * UC4: Write Employee Payroll to File
 * UC5: Print Payroll lines and Count Entries
 * UC6: Read Payroll File for analysis
 *
 * @author Kartikeya
 * @version 1.0
 */
public class EmployeePayrollFileIOService {

    /** The name of the payroll file where employee data is stored */
    public static String PAYROLL_FILE_NAME = "payroll-file.txt";

    /**
     * Writes a list of EmployeePayrollData objects to the payroll file.
     * Each employee record is stored on a separate line.
     *
     * @param employeePayrollList the list of employee payroll records to write
     */
    public void writeData(List<EmployeePayrollData> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads employee payroll data from the payroll file and returns as a list.
     * Parses each line to reconstruct EmployeePayrollData objects.
     *
     * @return a list of EmployeePayrollData objects read from the file
     */
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(line -> {
                // Parse line: id=1, name='Jeff', salary=100000.0
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split("=")[1]);
                String name = parts[1].split("=")[1].replace("'", "");
                double salary = Double.parseDouble(parts[2].split("=")[1]);
                employeePayrollList.add(new EmployeePayrollData(id, name, salary));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    /**
     * Prints all lines from the payroll file to the standard output.
     * Useful for displaying payroll roster from file.
     */
    public void printData() {
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of entries (lines) in the payroll file.
     * Each line represents one employee record.
     *
     * @return the total count of employee records in the file
     */
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}
