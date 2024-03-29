package com.ui.bcswing.courseEditPane;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import com.basicdata.CourseTimeKind;
import com.basicdata.CourseTypeKind;
import com.data.po.Course;
import com.data.po.Teacher;
import com.ui.bcswing.MObservable;
import com.ui.input.NumLimitInput;
import com.ui.myswing.MButton;
import com.ui.myswing.MComboBox;
import com.ui.myswing.MFrame;
import com.ui.myswing.MLabel;
import com.ui.myswing.MPanel;
import com.ui.myswing.MTextField;

public class CourseEditPanel extends MPanel {
	private static int default_height_add = 40;
	private MObservable observe;
	private MLabel namel;
	private MLabel locl;
	private MLabel creditl;
	private MLabel numl;
	private MLabel typel;
	private MLabel gradel;
	private MLabel periodl;

	private MLabel timel;

	protected MTextField namet;
	protected MTextField loct;
	protected MTextField creditt;
	protected MTextField numt;
	protected MComboBox typeSelect;
	protected MTextField gradet;
	protected MTextField periodt;

	private MButton confirm;

	private String courseID = "";
	private String[] typeModel = { "NONE" };

	CourseTimePanel time;
	ArrayList<CourseTimePanel> timeList;

	public CourseEditPanel(Point loc, Dimension size) {
		super(loc, size);
		creatComponent();
		addListener();
		init();
	}

	private void creatComponent() {
		namel = new MLabel(new Point(50, 30), new Dimension(60, 25), "课程名称");// 1
		locl = new MLabel(new Point(50, 240), new Dimension(60, 25), "上课地点");// 7
		creditl = new MLabel(new Point(50, 100), new Dimension(60, 25), "学分");// 3
		numl = new MLabel(new Point(50, 170), new Dimension(60, 25), "上课人数");// 5
		typel = new MLabel(new Point(50, 65), new Dimension(60, 25), "课程类型");// 2
		gradel = new MLabel(new Point(50, 135), new Dimension(60, 25), "年级");// 4
		periodl = new MLabel(new Point(50, 205), new Dimension(60, 25), "上课周数");// 6
		timel = new MLabel(new Point(50, 275), new Dimension(60, 25), "上课时间");// 8

		namet = new MTextField(new Point(230, 30), new Dimension(180, 25));
		loct = new MTextField(new Point(230, 240), new Dimension(180, 25));
		creditt = new MTextField(new Point(230, 100), new Dimension(180, 25));
		numt = new MTextField(new Point(230, 170), new Dimension(180, 25));
		typeSelect = new MComboBox(typeModel, new Point(230, 65),
				new Dimension(180, 25));

		gradet = new MTextField(new Point(230, 135), new Dimension(180, 25));
		periodt = new MTextField(new Point(230, 205), new Dimension(180, 25));

		confirm = new MButton(null, null, null, new Point(210, 300),
				new Dimension(60, 25));
		confirm.setText("确定");

		time = new CourseTimePanel(new Point(150, 240));
		timeList = new ArrayList<CourseTimePanel>();

		this.add(namel);
		this.add(locl);
		this.add(creditl);
		this.add(typel);
		this.add(numl);
		this.add(periodl);
		this.add(timel);
		this.add(gradel);

		this.add(namet);
		this.add(loct);
		this.add(creditt);
		this.add(typeSelect);
		this.add(periodt);
		this.add(numt);
		this.add(confirm);
		this.add(gradet);

		this.validate();
		this.repaint();
	}

	protected void addListener() {

		observe = new MObservable();

		typeSelect.addItemListener(new TypeItemListener());

		new TimeButtonListener().actionPerformed(null);

		NumLimitInput limit = new NumLimitInput();
		creditt.addKeyListener(limit);
		numt.addKeyListener(limit);
		gradet.addKeyListener(limit);
	}

	protected void init() {

	}

	protected void initType() {
		typeSelect.setSelectedIndex(-1);
		typeSelect.setSelectedIndex(0);
	}

	protected void setTypeModel(String[] typeModel) {
		this.typeModel = typeModel;
		typeSelect.setModel(new DefaultComboBoxModel<String>(typeModel));
	}

	public void setCourse(Course c) {
		namet.setText(c.getName());
		loct.setText(c.getLoc());
		creditt.setText(c.getCredit() + "");
		numt.setText(c.getNum() + "");
		gradet.setText(c.getGrade() + "");
		periodt.setText(c.getPeriod());
		typeSelect.setSelectedItem(CourseTypeKind.getName(c.getType()));
		courseID = c.getID();
		List<String> courseTime = c.getTime();
		Iterator<String> it = courseTime.iterator();
		Iterator<String> timeIt;
		while (it.hasNext()) {
			timeIt = CourseTimeKind.getSeperateTime(it.next());
			time.setWeek(timeIt.next());
			time.setStart(timeIt.next());
			time.setEnd(timeIt.next());
			if (it.hasNext()) {
				new TimeButtonListener().actionPerformed(null);
			}
		}
	}

	public Course getCourse() {
		Course c;
		
		String selected = (String) typeSelect.getSelectedItem();
		
		String name = namet.getText();
		String loc = loct.getText();
		int credit = Integer.parseInt(creditt.getText());
		int num = 0;
		if (!numt.getText().equals("")) {
			num = Integer.parseInt(numt.getText());
		}
		int grade = 0;
		if (!gradet.getText().equals("")) {
			grade = Integer.parseInt(gradet.getText());
		}
		String period = periodt.getText();
		String type = CourseTypeKind.getType((String) typeSelect
				.getSelectedItem());

		List<String> time = new LinkedList<String>();
		Iterator<CourseTimePanel> it = timeList.iterator();
		CourseTimePanel timePanel;
		while (it.hasNext()) {
			timePanel = it.next();
			List<String> seperateTime = new LinkedList<String>();
			seperateTime.add(timePanel.getWeek());
			seperateTime.add(timePanel.getStart());
			seperateTime.add(timePanel.getEnd());
			time.add(CourseTimeKind.getTime(seperateTime.iterator()));
		}

		
		switch (selected) {
		case "通识教育课程":
			c = new Course(courseID, name, loc, type, grade, period, null, null,
					num, credit, time, null);
		case "体育课":
			c = new Course(courseID, name, loc, type,0, period, null, null,
					num, credit, time, null);
			break;
		case "专业选修课":
		case "专业必修课":
			c = new Course(courseID, name, loc, type, grade, period, null, null,
					num, credit, time, null);
			break;
		default:
			c = new Course(courseID, name,null, type, 0, period, null, null,
					0, credit, null, null);
		}
		return c;
	}

	public void setHeight(int height) {
		setChanged();
		notifyObservers(height - this.getSize().height);
		this.setSize(this.getSize().width, height);
		this.refresh();
	}

	public void addConfirmListener(ActionListener al) {
		confirm.addActionListener(al);
	}

	public synchronized void addObserver(Observer o) {
		observe.addObserver(o);
	}

	public synchronized void deleteObserver(Observer o) {
		observe.deleteObserver(o);
	}

	public void notifyObservers() {
		observe.notifyObservers();
	}

	public void notifyObservers(Object arg) {
		observe.notifyObservers(arg);
	}

	public synchronized void deleteObservers() {
		observe.deleteObservers();
	}

	protected synchronized void setChanged() {
		observe.setChanged();
	}

	protected synchronized void clearChanged() {
		observe.clearChanged();
	}

	public synchronized boolean hasChanged() {
		return observe.hasChanged();
	}

	public synchronized int countObservers() {
		return observe.countObservers();
	}

	class TimeButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Point loc = time.getLocation();
			time = new CourseTimePanel(new Point(loc.x, loc.y
					+ default_height_add));
			time.addActionListener(new TimeButtonListener());
			time.addDeleteActionListener(new TimeDeleteButtonListener());
			timeList.add(time);
			CourseEditPanel.this.add(time);
			CourseEditPanel.this.setHeight(CourseEditPanel.this.getHeight()
					+ default_height_add);
			loc = confirm.getLocation();
			confirm.setLocation(loc.x, loc.y + default_height_add);

		}

	}

	class TimeDeleteButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (timeList.size() > 1) {
				CourseTimePanel p;
				MButton b = (MButton) e.getSource();
				Iterator<CourseTimePanel> it = timeList.iterator();
				while (it.hasNext()) {
					p = it.next();
					if (p.timeDB == b) {
						it.remove();
						CourseEditPanel.this.remove(p);
						break;
					}
				}
				while (it.hasNext()) {
					p = it.next();
					Point loc = p.getLocation();
					p.setLocation(new Point(loc.x, loc.y - default_height_add));
				}
				it = timeList.iterator();
				while (it.hasNext()) {
					time = it.next();
				}

				CourseEditPanel.this.setHeight(CourseEditPanel.this.getHeight()
						- default_height_add);

				Point loc = confirm.getLocation();
				confirm.setLocation(loc.x, loc.y - default_height_add);
			}
		}

	}

	class TypeItemListener implements ItemListener {
		int time = 0;

		public void itemStateChanged(ItemEvent e) {
			time++;
			System.out.println(time);
			if (time % 2 == 0) {
				String selected = (String) typeSelect.getSelectedItem();
				switch (selected) {
				case "通识教育课程":
				case "体育课":
					gradet.setText("");
					gradet.disable();
					numt.enable();
					loct.enable();
					CourseEditPanel.this.time.setEnabled(true);
					break;
				case "专业选修课":
				case "专业必修课":
					break;
				default:
					numt.setText("");
					loct.setText("");
					gradet.setText("");
					numt.disable();
					gradet.disable();
					loct.disable();

					Iterator<CourseTimePanel> it = timeList.iterator();
					while (it.hasNext()) {
						CourseTimePanel p = it.next();
						CourseEditPanel.this.remove(p);
						it.remove();
					}

					CourseEditPanel.this.time = new CourseTimePanel(new Point(
							150, 280));
					CourseEditPanel.this.time
							.addActionListener(new TimeButtonListener());
					CourseEditPanel.this.time
							.addDeleteActionListener(new TimeDeleteButtonListener());
					timeList.add(CourseEditPanel.this.time);
					CourseEditPanel.this.add(CourseEditPanel.this.time);
					CourseEditPanel.this.time.setEnabled(false);

					confirm.setLocation(new Point(210, 340));
				}
				System.out.println(selected);
				CourseEditPanel.this.refresh();
			}
		}

	}

}
