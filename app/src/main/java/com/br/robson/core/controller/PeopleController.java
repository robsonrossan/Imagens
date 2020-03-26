package com.br.robson.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.robson.core.model.People;
import com.br.robson.core.service.PeopleService;


@RestController
@RequestMapping("/coreJasmin")
public class PeopleController {

	@Autowired
	private PeopleService service;

	List<People> listPeople;
	
	
	@GetMapping(value = "/createxlsFile/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public void createXLS(HttpServletResponse response, @PathVariable("firstName") String firstName) throws IOException {
		
		try {
			List<People> listPersons = service.findPersonByName(firstName);
			
			Workbook workbook = new XSSFWorkbook();

			Sheet sheet = workbook.createSheet("Persons");
			sheet.setColumnWidth(0, 6000);
			sheet.setColumnWidth(1, 4000);
			 
			Row header = sheet.createRow(0);
			 
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 
			XSSFFont font = ((XSSFWorkbook) workbook).createFont();
			font.setFontName("Arial");
			font.setFontHeightInPoints((short) 16);
			font.setBold(true);
			headerStyle.setFont(font);
			 
			// Header do arquivo xls
			Cell headerCell = header.createCell(0);
			headerCell.setCellValue("Firstname");
			headerCell.setCellStyle(headerStyle);
			 
			headerCell = header.createCell(1);
			headerCell.setCellValue("LastName");
			headerCell.setCellStyle(headerStyle);
			
			headerCell = header.createCell(2);
			headerCell.setCellValue("Adress");
			headerCell.setCellStyle(headerStyle);
			
			headerCell = header.createCell(3);
			headerCell.setCellValue("Gender");
			headerCell.setCellStyle(headerStyle);
			
			headerCell = header.createCell(4);
			headerCell.setCellValue("Enable");
			headerCell.setCellStyle(headerStyle);				
			
			//style no arquivo xls
			CellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			 
			//Valores das linhas 
			int rowCount = 1;

			for (People user : listPersons) {
				Row userRow = sheet.createRow(rowCount++);
				userRow.createCell(0).setCellValue(user.getFirstName());
				userRow.createCell(1).setCellValue(user.getLastName());
				userRow.createCell(2).setCellValue(user.getAddress());
				userRow.createCell(3).setCellValue(user.getGender());
				userRow.createCell(4).setCellValue(user.getEnabled());
			}
			
			//Escreve o arquivo em disco
			File currDir = new File("D:\\projetos_todos\\projetos_spring\\CoreJasmin\\DIRETORIO_ARQUIVOS_EXPORTADOS\\" + "ArquivoExcelSB.xlsx");
			String path = currDir.getAbsolutePath();
			
			if(!new File(path).exists()) {
				FileOutputStream outputStream = new FileOutputStream(path);
				workbook.write(outputStream);
			}
			workbook.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping(value = "/readxlsFile", produces = { "application/json", "application/xml", "application/x-yaml" })
	public String readingXLS(HttpServletResponse response) throws IOException {
		
		File currDir = new File("D:\\projetos_todos\\projetos_spring\\CoreJasmin\\DIRETORIO_ARQUIVOS_EXPORTADOS\\" + "ArquivoExcelSB.xlsx");
		String path = currDir.getAbsolutePath();
		Map<Integer, List<String>> data = new HashMap<>();
		try {
			FileInputStream file = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(file);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			int i = 0;
			for (Row row : sheet) {
			    data.put(i, new ArrayList<String>());
			    for (Cell cell : row) {
			        switch (cell.getCellTypeEnum()) {			            
				        case STRING: 
				        	data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
			            break;
			            case NUMERIC: 
			            	if (DateUtil.isCellDateFormatted(cell)) {
			            		data.get(i).add(cell.getDateCellValue() + "");
	            			} else {
	            				data.get(i).add(cell.getNumericCellValue() + "");
			            	}
		            	break;	            	
			            case BOOLEAN: 
			            	data.get(i).add(cell.getBooleanCellValue() + "");
		            	break;
			            case FORMULA: 
			            	data.get(i).add(cell.getCellFormula() + "");
		            	break;
				            default: data.get(new Integer(i)).add(" ");
			        }
			    }
			    i++;
			}
			System.out.println("Valor_XLS:" + data);
			workbook.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
