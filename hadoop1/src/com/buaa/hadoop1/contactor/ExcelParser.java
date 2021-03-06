package com.buaa.hadoop1.contactor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/*
 * excel解析
 */
public class ExcelParser {
	private static final Log LOG = LogFactory.getLog(ExcelParser.class);
	private StringBuilder currentStream = null;
	private Long bytesRead = 0l;
	
	//定义解析excel方法
	public String parseExcelData(InputStream in){
		try {
			HSSFWorkbook wbook = new HSSFWorkbook(in);
			HSSFSheet sheet = wbook.getSheetAt(0);//得到第一个sheet
			Iterator<Row> rowIterator = sheet.iterator();//行迭代器
			currentStream = new StringBuilder();
			//读取数据
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				//对于每一行，遍历每一列
				Iterator<Cell> cellIterator = row.cellIterator();
				//读每一列
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					if(cell.getCellStyle().equals(Cell.CELL_TYPE_BOOLEAN)){
						bytesRead++;
						currentStream.append(cell.getBooleanCellValue() + "\t");
						
					}else if(cell.getCellStyle().equals(Cell.CELL_TYPE_NUMERIC)){
						bytesRead++;
						currentStream.append(cell.getNumericCellValue() + "\t");
						
					}else if(cell.getCellStyle().equals(Cell.CELL_TYPE_STRING)){
						bytesRead++;
						currentStream.append(cell.getStringCellValue() + "\n");
						
					}										
				}
				//换行
				currentStream.append("\n");
			}
			//关闭流
			in.close();
			
		} catch (IOException e) {
			
			LOG.error("File is not been found!" + e);
		}
		
		return currentStream.toString();
	}
	
	public long getBytesRead(){
		return bytesRead;
	}
}
