import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * EmployeePayrollServiceTest contains JUnit test cases for the Employee Payroll
 * Service.
 * Tests writing employees to file, printing payroll data, counting entries,
 * and reading payroll data from file.
 *
 * UC4: Write Employee Payroll to File
 * UC5: Print and Count Payroll Entries
 * UC6: Read Employee Payroll File for Analysis
 *
 * @author Kartikeya
 * @version 1.0
 */
public class EmployeePayrollServiceTest {

    /**
     * Tests that 3 employees when written to file should match the expected entry
     * count of 3.
     * Also verifies printing and counting functionality.
     */
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(1, "Jeff Bezos", 100000.0),
                new EmployeePayrollData(2, "Bill Gates", 200000.0),
                new EmployeePayrollData(3, "Mark Zuckerberg", 300000.0)
        };
        EmployeePayrollService employeePayrollService;
        employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assert.assertEquals(3, entries);
    }

    /**
     * Tests that reading the payroll file returns the correct number of employee
     * records.
     * This verifies UC6 - reading payroll file for analysis.
     */
    @Test
    public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        Assert.assertEquals(3, entries);
    }
}
