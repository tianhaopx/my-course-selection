package com.client.ui.teacherUI.CourseMagagement;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.basicdata.Identity;
import com.client.rmi.StudentMethodController;
import com.client.rmi.TeacherMethodController;
import com.client.ui.dataAdapter.CourseListToVectorAdapter;
import com.client.ui.teacherUI.TeacherUISwitchController;
import com.data.po.Course;
import com.data.po.Teacher;
import com.logicService.StudentMethod;
import com.logicService.TeacherMethod;
import com.ui.bcswing.CourseInforPane;
import com.ui.bcswing.CourseScriptPane;
import com.ui.bcswing.MScrollTable;
import com.ui.bcswing.titleBar.TeacherTitleBar;
import com.ui.bcswing.titleBar.TitleBar;
import com.ui.myswing.MButton;
import com.ui.myswing.MPanel;

public class CourseManagementPanel extends MPanel {
	private TitleBar title;
	private MButton editB;
	private MButton inforB;
	private MButton recordB;
	private MScrollTable table;

	public CourseManagementPanel(Point loc, Dimension size) {
		super(loc, size);
		createComponent();
		addListener();
		init();
	}

	private void createComponent() {
		title = new TeacherTitleBar(new Point(0, 0), new Dimension(
				this.getWidth(), 95));
		editB = new MButton(null, null, null, new Point(30, 95), new Dimension(
				60, 25));
		inforB = new MButton(null, null, null, new Point(100, 95),
				new Dimension(80, 25));
		recordB = new MButton(null, null, null, new Point(190, 95),
				new Dimension(180, 25));
		table = new MScrollTable(new Point(20, 130), new Dimension(810, 480));
		String[] c = { "课程编号", "课程模块", "课程名称", "学分", "开设学期" };
		table.setColumnIdentifiers(c);
		editB.setText("编辑");
		inforB.setText("详细信息");
		recordB.setText("查看学生列表／登记成绩");

		this.add(title);
		this.add(editB);
		this.add(inforB);
		this.add(recordB);
		this.add(table);
	}

	private void addListener() {

		title.addReturnMenu(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherUISwitchController controller = TeacherUISwitchController
						.getUISwitchController();
				controller.switchToMainFrame();
			}
		});

		recordB.addActionListener(new RecordListener());

		editB.addActionListener(new EditListener());

		inforB.addActionListener(new CourseInforListener());
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TeacherMethod method = TeacherMethodController.getMethod();
				if (e.getClickCount() == 2) {
					// JOptionPane.showMessageDialog(null, "doubleClicked!");
					int index = table.getSelectedRow();
					if (index >= 0) {
						String id = (String) table.getValueAt(index, 0);
						try {
							Course c = method.getCourse(id);
							CourseInforPane pane = new CourseInforPane(c);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}

					}
				}
			}
		});

	}

	private void init() {
		TeacherMethod method = TeacherMethodController.getMethod();
		Teacher teacher = (Teacher) Identity.getIdentity();
		String id = teacher.getID();
		try {
			List<Course> list = method.getMyCourseList(id);
			table.setDataVector(CourseListToVectorAdapter.adapter(list));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	class RecordListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int index = table.getSelectedRow();
			if (index >= 0) {
				String id = (String) table.getValueAt(index, 0);
				TeacherUISwitchController controller = TeacherUISwitchController
						.getUISwitchController();
				controller.switchToRecordScore(id);
			}
		}

	}

	class EditListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int index = table.getSelectedRow();
			if (index >= 0) {
				String id = (String) table.getValueAt(index, 0);
				TeacherMethod method = TeacherMethodController.getMethod();
				try {
					TeacherUISwitchController controller = TeacherUISwitchController
							.getUISwitchController();
					CourseScriptPane scriptPane = new CourseScriptPane(
							controller.getLoc(),
							CourseManagementPanel.this.getSize(),
							method.getCourse(id));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	class CourseInforListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int index = table.getSelectedRow();
			if (index >= 0) {
				String id = (String) table.getValueAt(index, 0);
				TeacherMethod method = TeacherMethodController.getMethod();
				try {
					CourseInforPane inforPane = new CourseInforPane(
							method.getCourse(id));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		TeacherMethod method = TeacherMethodController.getMethod();
		try {
			Identity.setIdentity(method.getSelf("100000001"));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TeacherUISwitchController controller = TeacherUISwitchController
				.getUISwitchController();
		controller.switchToCourseManagement();
	}

}
