package com.client.test.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;











import com.data.po.Course;
import com.data.po.Student;
import com.data.po.Teacher;
import com.logicService.TeacherMethod;

public class TeacherMethod_Stub implements TeacherMethod {
	private String ID="1000000000";
	
	private String password="teacher";
	
	private String name="刘钦";
	
	private String faculty="1250";
	private String cID="0001";
	public boolean login(String arg0, String arg1) throws RemoteException {
		if(this.ID.equals(ID)&&this.password.equals(password)){
			return true;
		}
		return false;
	}
	
	public boolean changePassword(String ID,String originalPassword,String password) throws RemoteException
	{
		if(this.ID.equals(ID)&&this.password.equals(originalPassword)){
			
			this.password=password;
				
			return true;
				}
				
		return false;

	}
	public boolean filnCourseInfor(String cID,String text) throws RemoteException
	{
		List<String> time=new ArrayList<String>();
		time.add("2_5_7");
		time.add("1_1_4");
		Course c1=new Course("0001","软件工程与计算","仙2_303","F",3,"1_17","1250","打造全院最好软件教育",0, 3,time, null);
		time.clear();
		 if(cID.equals(c1.getID())) {
			 c1.setScript(text);
			 return true;
		 }
		 return false;
		
		
	}
	public List<Student> getCourseStudent(String cID) throws RemoteException{
		List<Student> list=new ArrayList<Student>();
		List<String> time=new ArrayList<String>();
		Student stu1=new Student("121250000","sam","1250",12);
		Student stu2=new Student("121250000","tom","1250",12);
		list.add(stu1);
		list.add(stu2);
		
		return list;
		
	}

	@Override
	public Teacher getSelf(String arg0) throws RemoteException {
		// TODO Auto-generated method stub
		return new Teacher(ID,name,faculty);
	}

	@Override
	public boolean recordScore(String arg0, Map arg1) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Course> getMyCourseList(String arg0) throws RemoteException {
		// TODO Auto-generated method stub
		List<Course> list = new ArrayList<Course>();

		List<String> time = new ArrayList<String>();

		time.add("2_5_7");

		time.add("1_1_4");

		Course c1 = new Course("0001", "软件工程与计算", "仙2_303", "F", 3, "1_17",
				"1250", "打造全院最好软件教育", 0,3, time, null);
		time.clear();
		time.add("1_5_7");
		time.add("3_1_4");
		Course c2 = new Course("0002", "c++程序设计语言", "仙2_304", "E", 3, "12_17",
				"1251", "你们应该学会怎么自己实现，而不是去用现成的。", 0, 3,time, null);
		list.add(c1);

		list.add(c2);

			return list;
	}

	@Override
	public Course getCourse(String arg0) throws RemoteException {
		// TODO Auto-generated method stub
		List<String> time = new ArrayList<String>();
		time.add("2_5_7");
		time.add("1_1_4");
		Course c = new Course("0001", "软件工程与计算", "仙2_303", "F", 3, "1_17",
				"1250", "打造全院最好软件教育", 0, 3,time, null);
		return c;
	}

	@Override
	public Map<Student, Integer> getScore(String arg0) throws RemoteException {
		// TODO Auto-generated method stub
		return new HashMap<Student, Integer>();
	}

	@Override
	public boolean isTimeForPublishCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimeForQuitCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimeForSelectCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimeForByElection() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimeForGradeOneSelectCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimeForSystemSelect() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsTimeForByElection(boolean arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForGradeOneSelectCourse(boolean arg0)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForPublishCourse(boolean arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForQuitCourse(boolean arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForSelectCourse(boolean arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForSystemSelect(boolean arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}


