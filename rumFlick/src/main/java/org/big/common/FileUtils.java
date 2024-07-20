package org.big.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.big.dto.ContentsFileDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUtils {

	public List<ContentsFileDTO> parseFileInfo(String contentsCode, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<ContentsFileDTO> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "/rumFlick/src/main/resources/static/images/contentimg/" + current.format(format);
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
							}
						}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					ContentsFileDTO contentsFile = new ContentsFileDTO();
					contentsFile.setContentsCode(contentsCode);
					contentsFile.setFileSize(multipartFile.getSize());
					contentsFile.setOriginalFileName(multipartFile.getOriginalFilename());
					contentsFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(contentsFile);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}
