package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.fdmgroup.documentuploader.dao.DocumentDao;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Document;

@Controller
public class DocumentController {

	@RequestMapping(value = "/downloadFile/**", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(14);
		path = path.replaceAll("%20", " ");
		DocumentDao documentDao = (DocumentDao) DispatchController.getContext().getBean("DocumentDao");
		Document document = documentDao.read(path);
		File file1 = new File("H:\\fileDownload.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write(document.toString());
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			File file = new File(path);
			InputStream input = new FileInputStream(file);
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + document.getName());
			response.setHeader("Content-Length", String.valueOf(file.length()));
			org.springframework.util.FileCopyUtils.copy(input, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			
		}
	}

	@RequestMapping(value = "/deleteFile/**", method = RequestMethod.GET)
	public RedirectView deleteFile(HttpSession session, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(12);
		path = path.replaceAll("%20", " ");

		DocumentDao documentDao = (DocumentDao) DispatchController.getContext().getBean("DocumentDao");
		Document document = documentDao.read(path);
		File file2 = new File("H:\\DebugDelete.txt");
		try {
			FileWriter writer = new FileWriter(file2);
			writer.write("File attempted to delete: " + document);
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		documentDao.delete(document);
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		return new RedirectView("/DocumentUploader/repositoryHome/" + account.getBusinessAccountId());

	}

}
