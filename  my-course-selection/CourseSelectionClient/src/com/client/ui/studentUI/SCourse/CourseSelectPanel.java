package com.client.ui.studentUI.SCourse;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.basicdata.Identity;
import com.client.rmi.StudentMethodController;
import com.client.ui.studentUI.StudentUISwitchController;
import com.ui.bcswing.titleBar.StudentTitleBar;
import com.ui.bcswing.titleBar.TitleBar;
import com.ui.myswing.MButton;
import com.ui.myswing.MPanel;

public class CourseSelectPanel extends MPanel{
	protected TitleBar title;
	private MButton btn1;
	private MButton btn2;
	private MButton btn3;
	private MButton btn4;
	
	StudentUISwitchController controller;
	public CourseSelectPanel(Point loc, Dimension size){
		super(loc, size);
		createComponent();
		addListener();
	}
	
	private void createComponent(){
		title = new StudentTitleBar(new Point(0, 0), new Dimension(
				this.getWidth(), 95));
		btn1 = new MButton(new ImageIcon("resource//liberal.png"), null, new ImageIcon("resource//liberal3.png"), new Point(
				60, 134), new Dimension(128, 145));
		btn1.setText("通识课选课");
		btn1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn1.setVerticalTextPosition(SwingConstants.BOTTOM);

		btn2 = new MButton(new ImageIcon("resource//elective.png"), null, new ImageIcon("resource//elective3.png"), new Point(
				248, 134), new Dimension(128, 145));
		btn2.setText("专业选修课选课");
		btn2.setHorizontalTextPosition(SwingConstants.CENTER);
		btn2.setVerticalTextPosition(SwingConstants.BOTTOM);

		btn3 = new MButton(new ImageIcon("resource//pe.png"), null, new ImageIcon("resource//pe3.png"), new Point(
				436, 134), new Dimension(128, 145));
		btn3.setText("体育课选课");
		btn3.setHorizontalTextPosition(SwingConstants.CENTER);
		btn3.setVerticalTextPosition(SwingConstants.BOTTOM);

		btn4 = new MButton(new ImageIcon("resource//other.png"), null, new ImageIcon("resource//other3.png"), new Point(
				624, 134), new Dimension(128, 145));
		btn4.setText("跨院系选课");
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		btn4.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		this.add(title);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
	}
	
	private void addListener(){
		controller = StudentUISwitchController
				.getUISwitchController();
		
		title.addReturnMenu(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToMainFrame();
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToLiberalSelect();
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToElectiveCourseSelec();
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToPESelect();
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToOtherFacultyCourseSelect();
			}
		});
		
	}
	
	public static void main(String[] args) {
		try {
			Identity.setIdentity(StudentMethodController.getMethod().getSelf(
					"121250011"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		StudentUISwitchController controller = StudentUISwitchController
				.getUISwitchController();
		controller.switchToCourseSelect();
	}
}
