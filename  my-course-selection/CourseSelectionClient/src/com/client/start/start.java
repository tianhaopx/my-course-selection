package com.client.start;

import javax.swing.UIManager;

import com.client.ui.main.MainUISwitchController;

public class start {
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainUISwitchController controller = MainUISwitchController
				.getUISwitchController();
		controller.switchToLoginPanel();
	}
}