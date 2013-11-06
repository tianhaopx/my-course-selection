package com.ui.bcswing;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.UIManager;

import com.ui.myswing.MButton;
import com.ui.myswing.MComboBox;
import com.ui.myswing.MFrame;
import com.ui.myswing.MLabel;
import com.ui.myswing.MPanel;
import com.ui.myswing.MTextField;

public class CourseEditPanel extends MPanel {
	MLabel namel;
	MLabel idl;
	MLabel locl;
	MLabel creditl;
	MLabel numl;
	MLabel typel;
	MLabel gradel;
	MLabel facultyl;
	MLabel periodl;

	MLabel timel;

	MTextField namet;
	MTextField idt;
	MTextField loct;
	MTextField creditt;
	MTextField numt;
	MTextField typet;
	MTextField gradet;
	MTextField facultyt;
	MTextField periodt;

	MComboBox week;
	MComboBox start;
	MComboBox end;

	MButton timeB;
	
	public CourseEditPanel(Dimension size) {
		super();
		creatComponent();
	}

	private void creatComponent() {
		namel = new MLabel(new Point(0, 0), new Dimension(100, 20), "课程名称:");
		idl = new MLabel(new Point(0, 30), new Dimension(100, 20), "课程编号:");
		locl = new MLabel(new Point(0, 60), new Dimension(100, 20), "上课地点:");
		creditl = new MLabel(new Point(0, 90), new Dimension(100, 20), "学分:");
		numl = new MLabel(new Point(0, 120), new Dimension(100, 20), "上课人数:");
		typel = new MLabel(new Point(0, 150), new Dimension(100, 20), "课程类型:");
		gradel = new MLabel(new Point(0, 180), new Dimension(100, 20), "年级:");
		facultyl = new MLabel(new Point(0, 210), new Dimension(100, 20),
				"课程院系:");
		periodl = new MLabel(new Point(0, 250), new Dimension(100, 20), "上课周数:");
		timel = new MLabel(new Point(0, 280), new Dimension(100, 20), "上课时间:");

		namet = new MTextField(new Point(150, 0), new Dimension(100, 20));
		idt = new MTextField(new Point(150, 30), new Dimension(100, 20));
		loct = new MTextField(new Point(150, 60), new Dimension(100, 20));
		creditt = new MTextField(new Point(150, 90), new Dimension(100, 20));
		numt = new MTextField(new Point(150, 120), new Dimension(100, 20));
		typet = new MTextField(new Point(150, 150), new Dimension(100, 20));
		gradet = new MTextField(new Point(150, 180), new Dimension(100, 20));
		facultyt = new MTextField(new Point(150, 210), new Dimension(100, 20));
		periodt = new MTextField(new Point(150, 250), new Dimension(100, 20));

		String[] weekModel = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天" };
		week = new MComboBox(weekModel, new Point(90, 280), new Dimension(80,
				20));

		String[] timeModel = { "第一节", "第二节", "第三节", "第四节", "第五节", "第六节", "第七节",
				"第八节", "第九节", "第十节" };
		start= new MComboBox(timeModel, new Point(180, 280), new Dimension(80,
				20));
		end=new MComboBox(timeModel, new Point(270, 280), new Dimension(80,
				20));
		
		timeB=new MButton(null,null,null,new Point(360,280),new Dimension(30,20));
		timeB.setText("+");

		this.add(namel);
		this.add(idl);
		this.add(locl);
		this.add(creditl);
		this.add(numl);
		this.add(typel);
		this.add(gradel);
		this.add(facultyl);
		this.add(periodl);
		this.add(timel);

		this.add(namet);
		this.add(idt);
		this.add(loct);
		this.add(creditt);
		this.add(numt);
		this.add(typet);
		this.add(gradet);
		this.add(facultyt);
		this.add(periodt);
		this.add(week);
		this.add(start);
		this.add(end);
		
		this.add(timeB);

		this.validate();
		this.repaint();
	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MFrame f = new MFrame(new Dimension(500, 500));
		f.add(new CourseEditPanel(f.getSize()));
		f.validate();
	}
}
