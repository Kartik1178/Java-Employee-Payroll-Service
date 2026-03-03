/**
 * EmployeePayrollData class represents an employee's payroll information.
 * It holds the employee's ID, name, and salary.
 * This is the core data model used across the Employee Payroll Service.
 *
 * @author Kartikeya
 * @version 1.0
 */
public class EmployeePayrollData {

    /** Unique identifier for the employee */
    public int id;

    /** Full name of the employee */
    public String name;

    /** Monthly salary of the employee */
    public double salary;

    /**
     * Constructs an EmployeePayrollData object with the given id, name, and salary.
     *
     * @param id     the unique employee ID
     * @param name   the name of the employee
     * @param salary the salary of the employee
     */
    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    /**
     * Returns a string representation of the EmployeePayrollData object.
     *
     * @return formatted string with id, name, and salary
     */
    @Override
    public String toString() {
        return "id=" + id + ", name='" + name + "'" + ", salary=" + salary;
    }
}
