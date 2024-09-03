package Composite;

import java.util.ArrayList;
import java.util.List;


public interface OrganizationComponent {
    void printStructure();
    double getTotalSalary();
    void add(OrganizationComponent component);
    void remove(OrganizationComponent component);
}

class Employee implements OrganizationComponent {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void printStructure() {
        System.out.println("<Employee name=\"" + name + "\" salary=\"" + salary + "\"/>");
    }

    @Override
    public double getTotalSalary() {
        return salary;
    }

    @Override
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot add to an employee.");
    }

    @Override
    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot remove from an employee.");
    }
}

class Department implements OrganizationComponent {
    private String name;
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    @Override
    public void printStructure() {
        System.out.println("<Department name=\"" + name + "\">");
        for (OrganizationComponent component : components) {
            component.printStructure();
        }
        System.out.println("</Department>");
    }

    @Override
    public double getTotalSalary() {
        double totalSalary = 0;
        for (OrganizationComponent component : components) {
            totalSalary += component.getTotalSalary();
        }
        return totalSalary;
    }

    @Override
    public void add(OrganizationComponent component) {
        components.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        components.remove(component);
    }
}

