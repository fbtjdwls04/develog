package com.koreaIT.develog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.FileService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.FileVO;

@Controller
public class UsrHomeController {
	
	private ArticleService articleService;  
	private FileService fileService;
	
	public UsrHomeController(ArticleService articleService,FileService fileService) {
		this.articleService = articleService;
		this.fileService = fileService;
	}
	
	@RequestMapping("/usr/home/main")
	public String home(Model model) {
		
		List<Article> articles = articleService.getAllArticles();
		
		model.addAttribute("articles",articles);
		
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/test")
	public String test() {
		return "/usr/home/test";
	}
	
	@RequestMapping("/usr/home/upload")
	@ResponseBody
	public String uploadFile(MultipartFile file) {

		try {
			fileService.saveFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Util.jsReplace("파일 업로드 성공", "/");
	}

	@RequestMapping("/usr/home/view")
	public String view(Model model) {		

		List<FileVO> files = fileService.getFiles();

		model.addAttribute("files", files);

		return "usr/home/view";
	}

	@RequestMapping("/usr/home/file/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") int id, Model model) throws IOException {

		FileVO fileVo = fileService.getFileById(id);

		return new UrlResource("file:" + fileVo.getSavedPath()); 
	}
}
