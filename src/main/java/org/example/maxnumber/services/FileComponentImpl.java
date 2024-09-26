package org.example.maxnumber.services;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.maxnumber.web.request.RequestInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;

@Component
public class FileComponentImpl implements FileComponent{

    @Value("${column.number}")
    private int columnNumber;

    @Value("${extension.xlsx}")
    private String extension;

    @Value("${exception.message.data}")
    private String exceptionMessageData;

    @Value("${exception.message.file.not.exist}")
    private String exceptionFileNotExist;

    @Value("${exception.message.wrong.extension}")
    private String exceptionWrongExtension;

    @Override
    public int getMax(RequestInfo requestInfo) {
        String path = getPath(Optional.of(requestInfo.getPath())
                .orElseThrow(()->new RuntimeException(exceptionMessageData)));
        File file = new File(path);

        if(!file.exists()||file.isDirectory()){
           throw new RuntimeException(exceptionFileNotExist);
        }

        if(!getExtension(path).equals(extension) ){
            throw  new RuntimeException(exceptionWrongExtension);
        }

        return max(getData(requestInfo));
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private List<Integer> getData(RequestInfo requestInfo) {
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(requestInfo.getPath());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fileIn);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(0);
        List<Integer> data = new ArrayList<>();

        for(int i =0;i<requestInfo.getCount();i++){
            data.add((int) sheet.getRow(i).getCell(columnNumber).getNumericCellValue());
        }

        return data;
    }

    private int max(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i =0; i < numbers.size(); i++){
            if(numbers.get(i)>max){
                max= numbers.get(i);
            }
        }

        return max;
    }

    private String getPath(String pathS){
        String homePath = FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");
        String path = pathS.replaceAll("\\\\","/");

        if(path.contains(homePath))  return Paths.get(path).toString();

        return Paths.get(homePath +"/"+ path).toString();
    }
}
