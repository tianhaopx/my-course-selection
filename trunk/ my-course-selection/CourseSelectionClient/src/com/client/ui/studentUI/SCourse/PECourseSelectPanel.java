package com.client.ui.studentUI.SCourse;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.UIManager;

import com.basicdata.Identity;
import com.client.rmi.StudentMethodController;
import com.client.ui.dataAdapter.PECourseSelectListToVectorAdapter;
import com.client.ui.studentUI.StudentUISwitchController;
import com.data.po.Course;
import com.data.po.Student;
import com.logicService.StudentMethod;
import com.ui.bcswing.MScrollTable;
import com.ui.bcswing.titleBar.StudentTitleBar;
import com.ui.bcswing.titleBar.TitleBar;
import com.ui.myswing.MButton;
import com.ui.myswing.MPanel;

public class PECourseSelectPanel extends MPanel {
	private TitleBar title;
	private MButton select;
	private MScrollTable table;

	public PECourseSelectPanel(Point loc, Dimension size) {
		super(loc, size);
		createComponent();
		addListener();
		init();
	}

	private void createComponent() {
		title = new StudentTitleBar(new Point(0, 0), new Dimension(
				this.getWidth(), 75));
		select = new MButton(null, null, null, new Point(660, 95),
				new Dimension(50, 25));
		select.setText("选择");
		table = new MScrollTable(new Point(10, 130), new Dimension(780, 430));
		String[] c = { "课程编号", "课程名称", "上课地点", "上课时间", "剩余人数" };
		table.setColumnIdentifiers(c);

		this.add(title);
		this.add(select);
		this.add(table);
	}

	private void addListener() {

		title.addReturnMenu(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentUISwitchController controller = StudentUISwitchController
						.getUISwitchController();
				controller.switchToCourseSelect();
			}
		});

		select.addActionListener(new SelectListener());

	}

	private void init() {
		StudentMethod method = StudentMethodController.getMethod();
		try {
			List<Course> list=method.getTypeCourse("G");
			table.setDataVector(PECourseSelectListToVectorAdapter.adapter(list));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Student student = (Student) (Identity.getIdentity());
			int index = table.getSelectedRow();
			if (index >= 0) {
				String cID = (String) table.getValueAt(index, 0);
				StudentMethod method = StudentMethodController.getMethod();
				try {
					boolean admit = method.selectCourse(student.getID(), cID);
					if (admit) {
						System.out.println("选课成功");
					} else {
						System.out.println("选课失败");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			Identity.setIdentity(StudentMethodController.getMethod().getSelf(
					"121250041"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		StudentUISwitchController controller = StudentUISwitchController
				.getUISwitchController();
		controller.switchToPESelect();
	}
}
