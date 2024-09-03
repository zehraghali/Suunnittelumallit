package Composite;
public class Main {
    public static void main(String[] args) {

        Employee emp1 = new Employee("John Doe", 50000);
        Employee emp2 = new Employee("Jane Smith", 60000);
        Employee emp3 = new Employee("James Brown", 55000);

        Department devDepartment = new Department("Development");
        Department hrDepartment = new Department("Human Resources");

        devDepartment.add(emp1);
        devDepartment.add(emp2);
        hrDepartment.add(emp3);

        Department headOffice = new Department("Head Office");
        headOffice.add(devDepartment);
        headOffice.add(hrDepartment);

        System.out.println("Organizational Structure in XML format:");
        headOffice.printStructure();

        System.out.println("Total Salary of the Organization: " + headOffice.getTotalSalary());
    }
}
