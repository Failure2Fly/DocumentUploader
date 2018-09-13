package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		if (context != null) {
			context.close();
			context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		} else {
			context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		}
	}

	@RequestMapping(value = "/downloadFile/**", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(14);
		path = path.replaceAll("%20", " ");
		File file1 = new File("H:\\DebugDownload1.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write("File attempted to download: " + path);
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			InputStream input = new FileInputStream(path);
			org.apache.commons.io.IOUtils.copy(input, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			File file2 = new File("H:\\DebugDownload2.txt");
			try {
				FileWriter writer = new FileWriter(file2);
				writer.write("File attempted to download: " + path + " with error " + e);
				writer.flush();
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/deleteFile/**", method = RequestMethod.GET)
	public RedirectView deleteFile(HttpSession session, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(12);
		path = path.replaceAll("%20", " ");

		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
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
