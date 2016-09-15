package Ultil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.opencsv.CSVReader;

public class ReadWriteExcel {
	// Đọc File XLS-----Chưa Hoàn Thành
	public void readExcelXLS(String path) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							System.out.println(cell.toString());
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	public List<List<String>> readExcelCSV(String path) {
		CSVReader reader = null;
		List<String> lstCells = null;
		List<List<String>> listRows = new ArrayList<List<String>>();
		try {
			reader = new CSVReader(new InputStreamReader(new FileInputStream(
					path), "UTF-16LE"), ';', '\'', 1);
			String[] rows;
			while ((rows = reader.readNext()) != null) {
				for (int i = 0; i < rows.length; i++) {
					String[] cells = null;
					cells = rows[i].split("\t");
					lstCells = new ArrayList<String>();
					for (int j = 0; j < 18; j++) {
						lstCells.add(cells[j]);
					}
				}
				listRows.add(lstCells);
				//System.out.println();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listRows;

	}
}
