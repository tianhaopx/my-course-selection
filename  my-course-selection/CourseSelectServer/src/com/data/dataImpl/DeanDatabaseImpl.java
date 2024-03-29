package com.data.dataImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.data.dataImpl.method.DatabaseConnection;
import com.data.dataImpl.method.dean.*;
import com.dataService.DeanDatabaseMethod;



public class DeanDatabaseImpl extends DatabaseImpl implements DeanDatabaseMethod{

	public String max(String tableName, String clueName) {
		Connection conn = DatabaseConnection.getConnection();
		Statement st;
		List<String> list = new ArrayList<String>();
		String result="";
		try {
			String sql="SELECT "+"MAX("+clueName+")"+" FROM "+tableName;
			st = (Statement) conn.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()){
				result=res.getString(1);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println("读取失败" + ex.getMessage());
		}
		return result;
	}
	public static void main(String[] args){
		new DeanDatabaseImpl().max("course", "ID");
	}
	
}
