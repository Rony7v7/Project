package model;

import java.util.Calendar;

public class Controller {

	private Project[] projects;

	public Controller() {

		projects = new Project[10];
	
	}
	
	public String RegisterProject(String name, String clientName, String projecType, Calendar startPlannedDate, Calendar endPlannedDate, double budget) {
		int pos = firstValidPosition();
		String msg = "\nLimite de proyectos alcanzados";

		if(pos != -1) {
			projects[pos] = new Project(name, clientName, projecType, startPlannedDate, endPlannedDate, budget);
			msg = "\nProyecto creado con exito";
		}

		return msg;
	}


	// Date class also has their own before() and after() method
	public String[] searchProjectsAfterDate(Calendar date) {
		String[] projectsPos = new String[10];
		int arraySize =(firstValidPosition() != -1) ? firstValidPosition():10;

		if(firstValidPosition()!= -1) {
			for(int i  = 0 ; i < arraySize ; i++) {
				if(projects[i].getInitialDate().compareTo(date) > 0) {
					projectsPos[i] = projects[i].getName();
				}
			}
		}

		return projectsPos;

	}
	
	// Date class also has their own before() and after() method
	public String[] searchProjectsBeforeDate(Calendar date) {
		String[] projectsPos = new String[10];
		int arraySize =(firstValidPosition() != -1) ? firstValidPosition():10;

		if(firstValidPosition()!= -1) {
			for(int i  = 0 ; i < arraySize ; i++) {
				if(projects[i].getInitialDate().compareTo(date) < 0) {
					projectsPos[i] = projects[i].getName();
				}
			}
		}

		return projectsPos;
	}

	public int firstValidPosition() {
		int pos = -1;
		boolean isFound = false;

		for(int i = 0; i < 10 && !isFound ; i++) {
			if(projects[i] == null) {
				pos = i;
				isFound = true;
			}
		}

		return pos;
	}
}
