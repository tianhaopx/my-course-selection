package com.logic.dataController;

import com.data.dataImpl.TeacherDatabaseImpl;
import com.dataService.DatabaseMethod;
import com.dataService.TeacherDatabaseMethod;

public class TeacherDataController {
	private static TeacherDatabaseMethod method = null;

	public static TeacherDatabaseMethod getMethod() {
//		if (method == null) {
//			method = new TeacherDatabaseImpl();
//		}
		return method;
	}
	public static void setMethod(TeacherDatabaseMethod method){
		TeacherDataController.method=method;
	}
}
