package com.example.diary.util;

import org.springframework.http.MediaType;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.*;

public class FileUploadUtil {

    public static String makeUploadDirectory(String uploadRootPath){
        //년,월,일 정보 가져오기
        LocalDateTime now=LocalDateTime.now();
        int y=now.getYear();
        int m = now.getMonthValue();
        int d = now.getDayOfMonth();

        //폴더생성
        String[] dateInfo = {
                String.valueOf(y)
                , len2(m)
                , len2(d)
        };
        String newUploadPath = uploadRootPath;

        // File.separator : 운영체제에 맞는 디렉토리 경로구분문자를 생성
        // 리눅스 : / ,  윈도우 : \
        for(String date : dateInfo){
            newUploadPath+= File.separator+date;

            File dirName=new File(newUploadPath);
            if(!dirName.exists())dirName.mkdir();
        }
        return newUploadPath;

    }//makeUploadDirectory_end

    private static String len2(int n) {
        return new DecimalFormat("00").format(n);
    }

    //확장자 추출해서 미디어타입 추출(img/png..)
    public static MediaType getMediaType (String filePath){
        //화장자 추출
        String ext=filePath.substring(filePath.lastIndexOf(".")+1);//문자열 자르기....slice()와 비슷
        switch (ext.toUpperCase()){
            case "PNG":
                return IMAGE_PNG;
            case "GIF":
                return IMAGE_GIF;
            case "JPG": case "JPEG":
                return IMAGE_JPEG;
            default:
                return null;
        }
    }


}//class_end
