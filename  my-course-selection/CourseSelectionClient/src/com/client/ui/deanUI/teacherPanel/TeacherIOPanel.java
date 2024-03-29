package com.client.ui.deanUI.teacherPanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.basicdata.FacultyKind;
import com.client.rmi.DeanMethodController;
import com.client.ui.dataAdapter.FrameToVectorAdapter;
import com.client.ui.dataAdapter.TeacherListToVectorAdapter;
import com.client.ui.deanUI.DeanUISwitchController;
import com.client.ui.deanUI.studentPanel.StudentIOPanel;
import com.data.excellIO.TeacherListExcelIn;
import com.data.po.Frame;
import com.data.po.Student;
import com.data.po.Teacher;
import com.logicService.DeanMethod;
import com.ui.bcswing.MScrollTable;
import com.ui.bcswing.TipFrame;
import com.ui.bcswing.titleBar.DeanTitlebar;
import com.ui.bcswing.titleBar.TitleBar;
import com.ui.myswing.MButton;
import com.ui.myswing.MComboBox;
import com.ui.myswing.MLabel;
import com.ui.myswing.MPanel;
import com.ui.myswing.MTextField;

public class TeacherIOPanel extends MPanel {
	JFileChooser j1;
	private TitleBar title;
	private MLabel choose;
	private MComboBox<String> department;
	private MButton importFromFile;
	private MTextField search;
	private MScrollTable table;
	private String[] departmentItems = FacultyKind.getAllFaculty();

	public TeacherIOPanel(Point loc, Dimension size) {
		super(loc, size);
		createComponent();
		addListener();
		init();
	}

	private void createComponent() {
		j1 = new JFileChooser();
		title = new DeanTitlebar(new Point(0, 0), new Dimension(
				this.getWidth(), 95));
		choose = new MLabel(new Point(20, 95), new Dimension(75, 25), "选择院系：");
		department = new MComboBox<>(departmentItems, new Point(95, 95),
				new Dimension(150, 25));
		importFromFile = new MButton(null, null, null, new Point(255, 95),
				new Dimension(100, 25));
		importFromFile.setText("从文件导入...");
		search = new MTextField();
		search.setBounds(700, 95, 120, 25);
		MLabel searchLb = new MLabel(new ImageIcon("resource//search.png"));
		searchLb.setBounds(672, 95, 24, 24);
		table = new MScrollTable(new Point(20, 130), new Dimension(810, 480));
		String[] c = { "工号", "姓名", "院系" };
		table.setColumnIdentifiers(c);
		this.add(title);
		this.add(choose);
		this.add(department);
		this.add(importFromFile);
		this.add(search);
		this.add(table);
		this.add(searchLb);
	}

	private void addListener() {

		title.addReturnMenu(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeanUISwitchController controller = DeanUISwitchController
						.getUISwitchController();
				controller.swicthToMainFrame();
			}
		});

		importFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = j1.showOpenDialog(null);
				// String filename = j1.getSelectedFile().toString();
				if (n == JFileChooser.APPROVE_OPTION) {
					String filename = j1.getSelectedFile().toString();
					if (TeacherListExcelIn.testFile(filename))
						if (importTeacher(TeacherListExcelIn.read(filename))) {
							DeanUISwitchController controller = DeanUISwitchController
									.getUISwitchController();
							TipFrame t = new TipFrame(controller.getLoc(),
									TeacherIOPanel.this.getSize(), 5, "导入成功");
							t.startEndClock();
						} else
							JOptionPane.showConfirmDialog(null, "Fail!", "提示",
									JOptionPane.YES_OPTION);

				}
			}

			public boolean importTeacher(Vector<Vector> vector) {
				boolean val = false;
				DeanMethod method = DeanMethodController.getMethod();
				List<Teacher> teacherList = new LinkedList<Teacher>();

				Iterator<Vector> it = vector.iterator();
				while (it.hasNext()) {
					Vector<String> row = it.next();
					teacherList.add(new Teacher(row.get(0), row.get(1),
							FacultyKind.getID(row.get(2))));
				}
				try {
					val = method.importTeacher(teacherList);
					refreshTable();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				return val;
			}

		});

		search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String content = search.getText();
				table.regrexFilter(content);
			}
		});

		department.addItemListener(new FacultyListener());
	}

	private void init() {
		department.setSelectedIndex(-1);
		department.setSelectedIndex(0);
	}

	private void refreshTable() {

		String faculty = (String) department.getSelectedItem();
		faculty = FacultyKind.getID(faculty);
		DeanMethod method = DeanMethodController.getMethod();
		try {
			List<Teacher> teacher = method.getFacultyTeacher(faculty);
			table.setDataVector(TeacherListToVectorAdapter.adapter(teacher));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	class FacultyListener implements ItemListener {
		int time = 0;

		public void itemStateChanged(ItemEvent e) {
			time++;
			if (time % 2 == 0) {
				refreshTable();
			}
		}
	}

	public static void main(String[] args) {

		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DeanUISwitchController controller = DeanUISwitchController
				.getUISwitchController();
		controller.switchToTeacherPanel();
	}

}
