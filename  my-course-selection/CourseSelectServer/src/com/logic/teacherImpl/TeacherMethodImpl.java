package com.logic.teacherImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import com.data.po.Course;
import com.data.po.Student;
import com.data.po.Teacher;
import com.logic.method.TeacherRelative.CourseStudentScoreGetter;
import com.logic.method.TeacherRelative.ScoreRecord;
import com.logic.method.TeacherRelative.TeacherCourseListGetter;
import com.logic.method.TeacherRelative.TeacherGetter;
import com.logic.method.courseRelative.CourseGetter;
import com.logic.method.courseRelative.CourseInforFiln;
import com.logic.method.courseRelative.CourseStudentGetter;
import com.logic.method.userRelative.Login;
import com.logic.method.userRelative.PasswordChange;
import com.logicService.TeacherMethod;
import com.timeControllerService.TimeController;
import com.timeControllerService.TimeControllerController;

public class TeacherMethodImpl extends UnicastRemoteObject implements TeacherMethod{
	TimeController time=TimeControllerController.getMethod();

	public TeacherMethodImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean login(String ID, String password) throws RemoteException {
		// TODO Auto-generated method stub
		boolean admit=Login.teacherLogin(ID, password);
		return admit;
	}

	@Override
	public boolean changePassword(String ID, String originalPassword,
			String password) throws RemoteException {
		// TODO Auto-generated method stub
		boolean admit=PasswordChange.teacherChangePassword(ID, originalPassword, password);
		return admit;
	}

	@Override
	public boolean filnCourseInfor(String cID, String text)
			throws RemoteException {
		// TODO Auto-generated method stub
		boolean admit=CourseInforFiln.filnCourseInfor(cID, text);
		return admit;
	}

	@Override
	public List<Student> getCourseStudent(String cID) throws RemoteException {
		// TODO Auto-generated method stub
		return CourseStudentGetter.getCourseStudent(cID);
	}

	@Override
	public boolean recordScore(String cID, Map score) throws RemoteException {
		// TODO Auto-generated method stub
		ScoreRecord.recordScore(cID, score);
		return true;
	}

	@Override
	public Teacher getSelf(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		Teacher t=TeacherGetter.getConcreteTeacher(ID);
		return t;
	}

	@Override
	public List<Course> getMyCourseList(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		List<Course> list=TeacherCourseListGetter.getCourseList(ID);
		return list;
	}

	@Override
	public Course getCourse(String courseID) throws RemoteException {
		// TODO Auto-generated method stub
		return CourseGetter.getConcreteCourse(courseID);
	}

	@Override
	public Map<Student, Integer> getScore(String cID) throws RemoteException {
		// TODO Auto-generated method stub
		return CourseStudentScoreGetter.ScoreGetter(cID);
	}

	@Override
	public boolean isTimeForPublishCourse() throws RemoteException{
		// TODO Auto-generated method stub
		return time.isTimeForPublishCourse();
	}

	@Override
	public boolean isTimeForSelectCourse() throws RemoteException{
		// TODO Auto-generated method stub
		return time.isTimeForSelectCourse();
	}

	@Override
	public boolean isTimeForQuitCourse() throws RemoteException{
		// TODO Auto-generated method stub
		return time.isTimeForQuitCourse();
	}

	@Override
	public boolean isTimeForGradeOneSelectCourse() throws RemoteException{
		// TODO Auto-generated method stub
		return time.isTimeForGradeOneSelectCourse();
	}

	@Override
	public boolean isTimeForByElection() throws RemoteException {
		// TODO Auto-generated method stub
		return time.isTimeForByElection();
	}

	@Override
	public boolean isTimeForSystemSelect() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsTimeForPublishCourse(boolean admit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForSelectCourse(boolean admit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForGradeOneSelectCourse(boolean admit)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForQuitCourse(boolean admit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForByElection(boolean admit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsTimeForSystemSelect(boolean admit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
