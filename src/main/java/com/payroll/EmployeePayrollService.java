import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * EmployeePayrollService is the main service class for managing employee payroll data.
 * It supports reading employee payroll information from the console and writing it back.
 * The service uses an IOService enum to determine the mode of operation (CONSOLE_IO or FILE_IO).
 *
 * UC1: Read and Write Employee Payroll to Console
 * UC4/UC5/UC6: Extended to support FILE_IO operations
 *
 * @author Kartikeya
 * @version 1.0
 */
public class EmployeePayrollService {

    /**
     * Enum to distinguish between different IO modes supported by the service.
     */
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    /** List to hold all employee payroll data records */
    private List<EmployeePayrollData> employeePayrollList;

    /**
     * Default constructor that initializes an empty payroll list.
     */
    public EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<>();
    }

    /**
     * Constructor that initializes the payroll list with a given list of employees.
     *
     * @param employeePayrollList pre-populated list of EmployeePayrollData objects
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    /**
     * Main method — entry point for the console-based Employee Payroll Service.
     * Reads employee data from console and prints it back to console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    /**
     * Reads employee payroll data from the console using the provided Scanner.
     * Prompts the user to enter Employee ID, Name, and Salary.
     *
     * @param consoleInputReader the Scanner object to read input from the console
     */
    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    /**
     * Writes employee payroll data to the configured IO service.
     * Currently supports CONSOLE_IO and FILE_IO modes.
     *
     * @param ioService the IO mode to use for writing (CONSOLE_IO or FILE_IO)
     */
    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO)) {
            System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
        } else if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
        }
    }

    /**
     * Reads employee payroll data from the configured IO service.
     * Returns the count of records read.
     *
     * @param ioService the IO mode to use for reading (FILE_IO supported)
     * @return number of employee records read
     */
    public long readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            this.employeePayrollList = new EmployeePayrollFileIOService().readData();
        }
        return this.employeePayrollList.size();
    }

    /**
     * Prints employee payroll data from the configured IO service.
     *
     * @param ioService the IO mode to use for printing (FILE_IO supported)
     */
    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }
    }

    /**
     * Counts the number of entries in the payroll data store.
     *
     * @param ioService the IO mode to use for counting (FILE_IO supported)
     * @return the count of entries in the file
     */
    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return this.employeePayrollList.size();
    }
}
