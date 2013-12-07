package com.data.excellIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

import com.client.ui.dataAdapter.StudentListToVectorAdapter;
import com.data.po.Student;

public class StudentListExcelIn {
	public static Vector read(String fileName) {
		List<Student> teaList = new ArrayList<Student>();
		try {
			Workbook book = Workbook.getWorkbook(new File(fileName+".xls"));
			Sheet sheet = book.getSheet(0);
			int rownum = sheet.getRows();
			for (int i = 1; i < rownum; i++) {
				teaList.add(new Student(sheet.getCell(0, i).getContents(),
						sheet.getCell(1, i).getContents(),
						sheet.getCell(2, i).getContents(), 
						Integer.parseInt(sheet.getCell(3, i).getContents())));
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return StudentListToVectorAdapter.adapter(teaList);
	}
}