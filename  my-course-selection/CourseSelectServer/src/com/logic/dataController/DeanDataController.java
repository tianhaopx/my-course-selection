package com.logic.dataController;


import com.data.dataImpl.DeanDatabaseImpl;
import com.dataService.DeanDatabaseMethod;

public class DeanDataController {
	private static DeanDatabaseMethod method = null;

	public static DeanDatabaseMethod getMethod() {
//		if (method == null) {
//			method = new DeanDatabaseImpl();
//		}
		return method;
	}
	public static void setMethod(DeanDatabaseMethod method){
		DeanDataController.method=method;
	}
}
