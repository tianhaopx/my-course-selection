package com.client.ui.studentUI;

import com.client.ui.main.MainFrame;

public class StudentUISwitchController {
	private static StudentUISwitchController controller = null;
	private static MainFrame frame=null;
	private StudentUISwitchController() {

	}
	public static StudentUISwitchController getUISwitchController(){
		if(controller==null){
			controller=new StudentUISwitchController();
		}
		if(frame==null){
			frame=new MainFrame();
		}
		return controller;
	}

}