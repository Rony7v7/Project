package ui;

import java.util.Calendar;
import java.util.Scanner;
import model.Controller;

public class Main{

	private Scanner input;
	private Controller controller;

	public Main() {

		input = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {
		int option;

		Main exe = new Main();

		do{
			option = exe.menu();
			exe.executeOption(option);
		} while(option != 4);

	}


	public int menu() {
		int option = 0;
		
		do {

			System.out.print("\n--------- BIENVENIDO --------\n"+
                             "       ELige una opción\n\n"+
                             "1. Crear proyecto\n"+
							 "2. Listar proyectos que inician despues de una fecha\n"+
							 "3. Listar proyectos que finalizan antes de una fecha\n"+
							 "4. Salir\n"+
							 "\n>> ");
			option = input.nextInt();
			input.nextLine();

			if(option < 1 || option > 4) {
				System.out.println("\nOpción incorrecta");
			}

		} while(option < 1 || option > 4);

		return option;
	}

	public void executeOption(int option) {
        switch (option) {
            case 1: RegisterProject();
                break;
            case 2: searchProjectsAfterDate();
                break;
            case 3: searchProjectsBeforeDate();
                break;
            default:
                break;
        }
    }

	public void RegisterProject() {
		String msg;
		String projectName;
		String clientName;
		int projectTypePos;
		String projectType;
		String[] projectTypes = {"Desarrollo","Mantenimiento","Despliegue"};
		Calendar startPlannedDate = Calendar.getInstance();
		Calendar endPlannedDate = Calendar.getInstance();
		double budget;

		System.out.print("\nIngresa el nombre del proyecto: ");
		projectName = input.nextLine();

		System.out.print("\nIngresa el nombre del cliente: ");
		clientName = input.nextLine();

		System.out.println("\nElige el tipo de proyecto:\n");
		do {
			for(int i = 0; i < projectTypes.length; i++) {
				System.out.println((i+1)+". "+projectTypes[i]);
			}
			System.out.print("\n>> ");
			projectTypePos = input.nextInt() - 1;
			input.nextLine();

			if(projectTypePos < 0 || projectTypePos > 2) {
				System.out.println("\n Opción incorrecta.");
			}

		} while(projectTypePos < 0 || projectTypePos > 2);

		projectType = projectTypes[projectTypePos]; 
		
		System.out.println("\n- Fecha de inicio");
		startPlannedDate = inputDate(startPlannedDate);

		System.out.println("\n- Fecha de finalización");
		endPlannedDate = inputDate(endPlannedDate);

		System.out.print("\nPresupuesto del proyecto: ");
		budget = input.nextDouble();
		input.nextLine();

		msg = controller.RegisterProject(projectName, clientName, projectType, startPlannedDate, endPlannedDate, budget);

		System.out.println(msg);
	}

	public void searchProjectsAfterDate() {
		Calendar endDate = Calendar.getInstance();
		String[] projects;
		if(controller.firstValidPosition() != 0) {
			System.out.println("\nProyectos que empiezan despues de");
			endDate = inputDate(endDate);

			projects = controller.searchProjectsAfterDate(endDate);

			for(int i = 0; i < projects.length; i++) {
				if(projects[i] != null) {
					System.out.println("- "+projects[i]);
				}
			}
		} else {
			System.out.println("\nAun no hay proyectos creados");
		}
	}
	
	public void searchProjectsBeforeDate() {
		Calendar endDate = Calendar.getInstance();
		String[] projects;
		if(controller.firstValidPosition() == 0) {
			System.out.println("\nProyectos que terminan antes de");
			endDate = inputDate(endDate);

			projects = controller.searchProjectsBeforeDate(endDate);

			for(int i = 0; i < projects.length; i++) {
				if(projects[i] != null) {
					System.out.println("- "+projects[i]);
				}
			}
		} else {
			System.out.println("\nAun no hay proyectos creados");
		}
		
	}

	public Calendar inputDate(Calendar date) {
		int day;
		int month;
		int year;

		System.out.print("\nIngresa la fecha.\nDia: ");
		day = input.nextInt();
		input.nextLine();

		System.out.print("\nMes: ");
		month = input.nextInt();
		input.nextLine();

		System.out.print("\nAño: ");
		year = input.nextInt();
		input.nextLine();

		date.set(year, month, day);

		return date;
	}

}
