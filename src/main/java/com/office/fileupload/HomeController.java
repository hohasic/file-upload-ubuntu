package com.office.fileupload;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	final private String CLASS_NAME = "[HomeController] ";
	
	final private UploadFileService uploadFileService;
	
	public HomeController(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
		
	}
	
	@GetMapping({"", "/"})
    public String home() {
		System.out.println(CLASS_NAME.concat("home()"));
		
		String nextPage = "home";
		
		return nextPage;
		
	}
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
	                     Model model) throws IOException {
		System.out.println(CLASS_NAME.concat("upload()"));
		
		String savedFileName = uploadFileService.upload(file);
		System.out.println(CLASS_NAME.concat("savedFileName: " + savedFileName));
		
		model.addAttribute("savedFileName", savedFileName);
		
	    return "home";
	}

}
