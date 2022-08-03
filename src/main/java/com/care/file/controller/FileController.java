package com.care.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.service.FileService;

@Controller
public class FileController {
	@Autowired FileService fs;
	
	@GetMapping("form")
	public String form() {
		return "uploadForm";
	}
	
	@PostMapping("upload")
	//public String upload(@RequestParam String id, @RequestParam String name, @RequestParam MultipartFile f)
	public String upload(MultipartHttpServletRequest mul){
		fs.fileProcess(mul);
		return "redirect:form";
	}
	
	@GetMapping("views")
	public String views(Model model) {
		fs.getData(model);
		return "result";
	}
	
	@GetMapping("download")
	public void download(String file,HttpServletResponse response) throws Exception {
		System.out.println("file : "+file);
		response.addHeader("Content-disposition", "attachment; filename="+file); //파일을 다운로드형식으로 응답
		File f = new File(FileService.IMAGE_REPO+"/"+file); //파일경로를 파일객체로 만듦
		FileInputStream in = new FileInputStream(f); // InputStream으로 경로에 있는 파일을 읽어옮
		FileCopyUtils.copy(in, response.getOutputStream()); // OutputStream으로 파일을 출력
		in.close();
	}
	
	@GetMapping("delete")
	public String delete(String file,String id) {
		fs.delete(file, id);
		return "redirect:views";
	}
	
}
