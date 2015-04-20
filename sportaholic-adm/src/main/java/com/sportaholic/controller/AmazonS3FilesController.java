package com.sportaholic.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.service.AmazonS3Service;

@Controller
@RequestMapping("/amazon-s3-files")
public class AmazonS3FilesController {

	private AmazonS3Service amazonS3Service;
	
	@Autowired
	public AmazonS3FilesController(AmazonS3Service amazonS3Service) {
		this.amazonS3Service = amazonS3Service;
	}
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("amazon-s3-files/index");
			
			if(request.getParameter("deleted") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Arquivo deletado com sucesso.");
			if(request.getParameter("uploaded") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Arquivo inserido com sucesso.");
			
			return modelAndView;
		} catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		try {			
			this.amazonS3Service.delete(request.getParameter("fileName"));
			return "redirect:/amazon-s3-files?deleted";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile filePart) {
		try {			
			String targetFileName = request.getParameter("targetFileName");
			InputStream fileInputStream = filePart.getInputStream();			
			this.amazonS3Service.upload(targetFileName, fileInputStream);
			return "redirect:/amazon-s3-files?uploaded";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
