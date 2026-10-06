package com.office.fileupload;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	final private String CLASS_NAME = "[UploadFileService] ";
	
	@Value("${file.upload-dir}")
    private String uploadDir;
	
	public String upload(MultipartFile file) {
        System.out.println(CLASS_NAME.concat("upload()"));

        boolean result = false;

        String fileOriName = file.getOriginalFilename();
        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
//        String uploadDir = "c:\\upload";
        String uploadDir = this.uploadDir;

        UUID uuid = UUID.randomUUID();
        String uniqueFileName = uuid.toString().replaceAll("-", "");

        // 업로드 디렉터리
        File uploadDirectory  = new File(uploadDir);
        if(!uploadDirectory .exists())
        	uploadDirectory .mkdirs();

        // 실제 저장할 파일
        File saveFile =
                new File(uploadDirectory, uniqueFileName + fileExtension);
        
        try {

            file.transferTo(saveFile);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();

        }

        if (result) {
        	System.out.println(CLASS_NAME.concat("FILE UPLOAD SUCCESS!!"));
            return uniqueFileName + fileExtension;

        } else {
        	System.out.println(CLASS_NAME.concat("FILE UPLOAD FAIL!!"));
            return null;

        }
        
	}
	
}
