package com.shree.crud;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shree.entity.Employee;

public class RunCrudOpration {

	public static void main(String[] args) {
		while(true) {

			SessionFactory factory=new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class)
					.buildSessionFactory();

			Session session=factory.getCurrentSession();

			System.out.println("WELCOME TO THE STUDENT DATA BASE");
			System.out.println("1.Add employee data");
			System.out.println("2.Read employee data based on id");
			System.out.println("3.Updata employee data based on id");
			System.out.println("4.Delete employee data based on id ");
			System.out.println("5.Get all the employee data");
			System.out.println("6.Exit");
			System.out.println("\nEnter your choice");

			Scanner scan=new Scanner(System.in);
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				try {
					Employee emp=new Employee();
					System.out.println("Enter first name");
					emp.setFirstName(scan.next());

					System.out.println("Enter last name");
					emp.setLastName(scan.next());

					System.out.println("Enter salary");
					emp.setSalary(scan.nextInt());
					
					System.out.println("Enter job");
					emp.setJob(scan.next());
					
					System.out.println("Enter department");
					emp.setDepartment(scan.next());

					session.beginTransaction();
					session.save(emp);

					session.getTransaction().commit();
					System.out.println("Added succesfully");

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					session.close();
					factory.close();
				}
				break;
			case 2:
				try {
					System.out.println("Enter the id");
					int id=scan.nextInt();

					session.beginTransaction();
					Employee emp=session.get(Employee.class, id);

					//	System.out.println(session.get(Student.class, iid));
					System.out.println(emp);

					session.getTransaction().commit();
					System.out.println("Done..");

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					System.out.println("Enter the id");
					int id=scan.nextInt();

					session.beginTransaction();
					Employee emp=session.get(Employee.class, id);

					System.out.println("Enter the first name");
					emp.setFirstName(scan.next());

					System.out.println("Enter the last name");
					emp.setLastName(scan.next());
					
					System.out.println("Enter the salary");
					emp.setSalary(scan.nextInt());
					
					System.out.println("Enter the job");
					emp.setJob(scan.next());
					
					System.out.println("Enter the department");
					emp.setDepartment(scan.next());


					session.getTransaction().commit();
					System.out.println("Done..");

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					System.out.println("Enter the employee id");
					session.beginTransaction();
					Employee emp=session.get(Employee.class, scan.nextInt());

					session.delete(emp);

					session.getTransaction().commit();
					System.out.println("deleted succesfully");

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					session.close();
					factory.close();
				}
				break;
			case 5:	
				try {
					session.beginTransaction();

					List<Employee> theEmployee=session.createQuery("from Employee").list();

					for(Employee tempEmployee:theEmployee) {
						System.out.println(tempEmployee);
					}


					session.getTransaction().commit();
					System.out.println("Added succesfully");

				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					session.close();
					factory.close();
				}
				break;
			case 6:
				System.out.println("Thank You");
				System.exit(0);

				break;
			default:
				System.out.println("Enter valid choice");

			}

		}
	}

}
