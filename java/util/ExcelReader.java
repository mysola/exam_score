package util;

import entity.Score;
import entity.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamSource;

import java.io.IOException;
import java.util.*;

public class ExcelReader {
    public static List<Student> readExcel2007AsStudent(InputStreamSource inputStreamSource) throws IOException {
        List<Student> lists = new ArrayList<>();
        //代表整个excel文件
        XSSFWorkbook wb = new XSSFWorkbook(inputStreamSource.getInputStream());
        //第一个sheet表
        XSSFSheet sheet = wb.getSheetAt(0);
        //一行
        XSSFRow row = null;
        int stuNum = 0;
        String stuName = null;
        boolean flag = false;
        //遍历每一行
        for(int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum() ; i++ ) {
            row = sheet.getRow(i);
            if(!flag){
                if(row.getCell(0).getStringCellValue().equals("编号")){
                    flag = true;
                }
                continue;
            }
            stuNum = (int)row.getCell(0).getNumericCellValue();
            if(stuNum==0)
                break;
            stuName = row.getCell(1).getStringCellValue().trim();
            if("".equals(stuName))
                throw new IllegalArgumentException("请检查编号为"+stuNum+"的学生姓名");
            lists.add(new Student(stuNum,stuName));
        }
        if(lists.isEmpty())
            throw new IllegalArgumentException("学生文件解析结果为空");
        return lists;
    }
    public static Map<String,List<Score>> readExcel2007AsScore(InputStreamSource inputStreamSource,
                                                               Integer examIndexInFile) throws IOException {
        List<Score> lists = new ArrayList<>();
        Map<String,List<Score>> map = new HashMap<>();
        //代表整个excel文件
        XSSFWorkbook wb = new XSSFWorkbook(inputStreamSource.getInputStream());
        //第一个sheet表
        XSSFSheet sheet = wb.getSheetAt(0);
        //一行
        XSSFRow row = null;
        double score = 0.0;
        int stuNum = 0;
        String examName = null;
        boolean flag = false;
        //遍历每一行
        for(int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum() ; i++ ) {
            row = sheet.getRow(i);
            if(!flag){
                if(row.getCell(0).getStringCellValue().equals("编号")){
                    flag = true;
                    examName = row.getCell(examIndexInFile-1).getStringCellValue().trim();
                    if("".equals(examName))
                        throw new IllegalArgumentException("请检查列序号为"+examIndexInFile+"的列是否有考试");
                }
                continue;
            }
            stuNum = (int)row.getCell(0).getNumericCellValue();
            if(stuNum==0)
                break;
            score = row.getCell(examIndexInFile-1).getNumericCellValue();
            if(score<0||score>100)
                throw new IllegalArgumentException("请检查编号为"+stuNum+"的学生分数");
            lists.add(new Score(stuNum,score));
        }
        if(lists.isEmpty())
            throw new IllegalArgumentException("考试文件解析结果为空");
        map.put(examName,lists);
        return map;
    }
}
