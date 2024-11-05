The code shows a payroll system using an abstract Employee class and three subclasses: HourlyEmp, SalariedEmp, and ExecutiveEmp, 
each representing different types of employees with unique bonus and salary calculations.
The Employee class contains basic attributes (eId, eName, and des) and an abstract method calBonus() for subclasses to implement. 
The HourlyEmp class, representing hourly employees, calculates the weekly salary based on hourlyRate and hoursWorked, and computes a bonus as 5% of total earnings.
SalariedEmp represents salaried employees, who receive a 10% bonus based on monthlySalary, while ExecutiveEmp, a specialized subclass of SalariedEmp, includes an additional executive bonus percentage (bonusPer).
The PayrollSystem class, which contains the main method, creates instances of each employee type, displays their details, and calculates the total payroll by summing annual earnings and bonuses for all employees. 
The result is displayed as the total payroll for all employees
