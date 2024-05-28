package com.koreaIT.develog.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreaIT.develog.dao.FileDao;
import com.koreaIT.develog.vo.FileVO;

@Service
public class FileService {
	
	@Value("${file.dir}")
	private String fileDir;

	private FileDao fileDao;

	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public void saveFile(MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			return;
		}

		String orgName = file.getOriginalFilename();

		String uuid = UUID.randomUUID().toString();

		String extension = orgName.substring(orgName.lastIndexOf("."));

		String savedName = uuid + extension;

		String savedPath = fileDir + "/" + savedName;

		fileDao.insertFileInfo(orgName, savedName, savedPath);

		file.transferTo(new File(savedPath));
	}

	public List<FileVO> getFiles() {
		return fileDao.getFiles();
	}

	public FileVO getFileById(int fileId) {
		return fileDao.getFileById(fileId);
	}
}
