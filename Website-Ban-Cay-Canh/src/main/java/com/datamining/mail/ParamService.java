package com.datamining.mail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext app;
	
	public String getString(String name, String defaultValue) {
		String result = request.getParameter(name);
		return result != null ? result : defaultValue;
	}
	
	public int getInt(String name, int defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Integer.parseInt(result) : defaultValue;
	}
	
	public double getDouble(String name, double defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Double.parseDouble(result) : defaultValue;
	}
	
	public boolean getBoolean(String name, boolean defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Boolean.parseBoolean(result) : defaultValue;
	}
	
	public Date getDate(String name, String pattern) {
		String value = request.getParameter(name);
		if (!value.matches(pattern)) {
			return Date.valueOf(value);
		}
		return null;
	}

	public File createFloder(String nameFolder) {
		File uploadRootDir = new File(app.getRealPath(nameFolder));
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		return uploadRootDir;
	}

	public String saveImage(MultipartFile attach,String path) throws IOException {
		File uploadRootDir= createFloder(path);
		String filename = attach.getOriginalFilename();
		File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(attach.getBytes());
		stream.close();
		return filename;
	}
}
