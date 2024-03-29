package com.client.ui.facultyUI.Course;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.basicdata.Identity;
import com.client.rmi.FacultyDeanMethodController;
import com.client.rmi.StudentMethodController;
import com.client.ui.dataAdapter.CourseListToVectorAdapter;
import com.client.ui.deanUI.DeanUISwitchController;
import com.client.ui.deanUI.coursePanel.CoursePanel;
import com.client.ui.facultyUI.FacultyUISwitchController;
import com.data.po.Course;
import com.data.po.FacultyDean;
import com.logicService.FacultyDeanMethod;
import com.logicService.StudentMethod;
import com.ui.bcswing.CourseInforPane;
import com.ui.bcswing.MPopupMenu;
import com.ui.bcswing.MScrollTable;
import com.ui.bcswing.TipFrame;
import com.ui.bcswing.courseEditPane.FacultyCoursePane;
import com.ui.bcswing.courseEditPane.MObserver;
import com.ui.bcswing.titleBar.FacultyTitleBar;
import com.ui.bcswing.titleBar.TitleBar;
import com.ui.myswing.MButton;
import com.ui.myswing.MPanel;

public class CourseManagementPanel extends MPanel implements MObserver {

	private TitleBar title;
	private MButton button1;
	private MButton button2;
	private MScrollTable table;
	private MPopupMenu popupMenu;

	private FacultyCoursePane pane;

	public CourseManagementPanel(Point loc, Dimension size) {
		super(loc, size);
		createComponent();
		addListener();
		init();
	}

	private void createComponent() {
		title = new FacultyTitleBar(new Point(0, 0), new Dimension(
				this.getWidth(), 95));
		button1 = new MButton(null, null, null, new Point(30, 95),
				new Dimension(100, 25));
		button1.setText("添加课程");
		button2 = new MButton(null, null, null, new Point(140, 95),
				new Dimension(50, 25));
		button2.setText("编辑");
		table = new MScrollTable(new Point(20, 130), new Dimension(810, 480));
		String[] c = { "课程编号", "课程模块", "课程名称", "学分", "开设周数" };
		table.setColumnIdentifiers(c);
		popupMenu = new MPopupMenu();
		table.add(popupMenu);
		this.add(title);
		this.add(button1);
		this.add(button2);
		this.add(table);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		refreshTable();
	}

	private void addListener() {

		title.addReturnMenu(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacultyUISwitchController controller = FacultyUISwitchController
						.getUISwitchController();
				controller.swicthToMainFrame();
			}
		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacultyDeanMethod method = FacultyDeanMethodController
						.getMethod();
				try {
					if (method.isTimeForPublishCourse()) {
						pane = new FacultyCoursePane();
						pane.addObserver(CourseManagementPanel.this);
					} else {
						FacultyUISwitchController controller = FacultyUISwitchController
								.getUISwitchController();
						TipFrame t = new TipFrame(controller.getLoc(),
								CourseManagementPanel.this.getSize(), 5,
								"未到课程发布时间");
						t.startEndClock();

					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}

		});

		button2.addActionListener(new CourseModifyListener());

		table.addMouseListener(new TableClickListener());
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FacultyDeanMethod method = FacultyDeanMethodController
						.getMethod();
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

		popupMenu.addTeacherAssignmentListener(new TeacherAssignmentListener());
	}

	private void refreshTable() {
		FacultyDean self = (FacultyDean) (Identity.getIdentity());
		FacultyDeanMethod method = FacultyDeanMethodController.getMethod();
		try {
			List<Course> list = method.getCourseList(self.getFaculty());
			table.setDataVector(CourseListToVectorAdapter.adapter(list));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void init() {
		refreshTable();
	}

	class CourseModifyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			FacultyDeanMethod method = FacultyDeanMethodController.getMethod();
			int index = table.getSelectedRow();
			if (index >= 0) {
				String id = (String) table.getValueAt(index, 0);
				try {
					Course c = method.getCourse(id);
					FacultyCoursePane pane = new FacultyCoursePane();
					pane.setCourse(c);
					pane.addObserver(CourseManagementPanel.this);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		}

	}

	class TableClickListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int mods = e.getModifiers();
			if (mods == InputEvent.BUTTON3_MASK
					|| e.getModifiers() == InputEvent.BUTTON2_MASK) {
				popupMenu.show(table, e.getX(), e.getY());
			}
		}
	}

	class TeacherAssignmentListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String facultyID;
			String courseID;

			FacultyDean facultyDean = (FacultyDean) Identity.getIdentity();
			facultyID = facultyDean.getFaculty();

			int index = table.rowAtPoint(popupMenu.getLocation());
			courseID = (String) table.getValueAt(index, 0);

			new FacultyDeanCourseTeacherAssignmentDislayPane(courseID,
					facultyID);
		}

	}

	public static void main(String[] args) {
		FacultyDeanMethod method = FacultyDeanMethodController.getMethod();
		try {
			Identity.setIdentity(method.getSelf("100000001"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacultyUISwitchController controller = FacultyUISwitchController
				.getUISwitchController();
		controller.switchToCourseManagementPanel();
	}

}
