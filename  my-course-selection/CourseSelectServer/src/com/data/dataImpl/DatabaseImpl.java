package com.data.dataImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.data.dataImpl.method.DatabaseConnection;
import com.data.dataImpl.method.DatabaseDelete;
import com.data.dataImpl.method.DatabaseInsert;
import com.data.dataImpl.method.DatabaseMax;
import com.data.dataImpl.method.DatabaseNum;
import com.data.dataImpl.method.DatabaseSearch;
import com.data.dataImpl.method.DatabaseUpdate;
import com.data.dataImpl.method.dean.TimeGetter;
import com.dataService.DatabaseMethod;

public class DatabaseImpl implements DatabaseMethod{
	public Connection getConnection(){
		Connection con=DatabaseConnection.getConnection();
		return con;
	}
	public List<String> search(String tableName,String clueName,String clue,String aimName){
		List<String> res;
		res=DatabaseSearch.search(tableName, clueName, clue, aimName);
		return res;
	}
	public List<ArrayList<String>> search(String tableName,List<String> clueName,List<String> clue,List<String> aimName){
		List<ArrayList<String>> res;
		res=DatabaseSearch.search(tableName,clueName,clue,aimName);
		return res;
	}
 
	public boolean update(String tableName,String clueName,String clue,String aimName,String aim){
		boolean correct;
		correct=DatabaseUpdate.update(tableName, clueName, clue, aimName, aim);
		return correct;
	}
	public boolean insert(String tableName,List<String> clueName,List<String> clue){
		boolean correct;
		correct=DatabaseInsert.insert(tableName, clueName, clue);
		return correct;
	}
	public boolean delete(String tableName,String clueName,String clue){
		boolean correct;
		correct=DatabaseDelete.delete(tableName, clueName, clue);
		return correct;
	}
	public boolean delete(String tableName,List<String> clueName,List<String> clue){
		boolean correct;
		correct=DatabaseDelete.delete(tableName, clueName, clue);
		return correct;
	}
	@Override
	public String getMax(String tableName, String clueName) {
		// TODO Auto-generated method stub
		return DatabaseMax.getMax(tableName, clueName);
	}
	@Override
	public int getNum(String tableName, List<String> clueName, List<String> clue) {
		// TODO Auto-generated method stub
		return DatabaseNum.getNum(tableName, clueName, clue);
	}
	@Override
	public int getNum(String tableName, String clueName, String clue) {
		// TODO Auto-generated method stub
		return DatabaseNum.getNum(tableName, clueName, clue);
	}
	@Override
	public Date getTime(String period) {
		// TODO Auto-generated method stub
		return TimeGetter.getTime(period);
	}
	
}
