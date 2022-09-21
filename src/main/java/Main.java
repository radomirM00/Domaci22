import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            write("domaci22.xlsx");
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }catch (IOException e){
            System.out.println("File invalid for writing!");
        } catch (NullPointerException e){
            System.out.println("Invalid!");
        }

        try {
            read("domaci22.xlsx");
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }catch (IOException e){
            System.out.println("File invalid for writing!");
        }catch (NullPointerException e){
            System.out.println("");
        }
    }
    public static void write(String fileName) throws FileNotFoundException, IOException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        Faker faker = new Faker();
        XSSFRow rowFirst = sheet.createRow(0);
        rowFirst.createCell(0).setCellValue("Era");
        rowFirst.createCell(1).setCellValue("Ojdanic");
        for (int i = 1; i < 9; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 1; j++) {
                XSSFCell cellFirstName = row.createCell(j);
                cellFirstName.setCellValue(faker.name().firstName());
                XSSFCell cellLastName = row.createCell(j+1);
                cellLastName.setCellValue(faker.name().lastName());
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void read(String fileName) throws FileNotFoundException, IOException, NullPointerException{
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int i = 0;
        do {
            XSSFRow row = sheet.getRow(i);
            i++;
            XSSFCell cellName = row.getCell(0);
            XSSFCell cellLastName = row.getCell(1);
            System.out.print(cellName.getStringCellValue()+ " " + cellLastName.getStringCellValue());
            System.out.println();
        }while (sheet.getRow(i).getCell(0).getStringCellValue() != null);
    }
}