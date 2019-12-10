package jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jblog.exception.FileUploadException;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/upload";
	
	public String store(MultipartFile mFile) {
		// 실제 클라이언트에서의 파일 이름 -> Rename 필요
		String savedFilename = "";
		String originalFilename = mFile.getOriginalFilename();
		
		// 파일명 변경 -확장자 분리
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		// 파일명 생성(시스템 날짜를 기준으로 생성)
		Calendar cal = Calendar.getInstance();
		savedFilename = String.valueOf(cal.getTimeInMillis()) + extName;
		try {
			writeFile(mFile, savedFilename);
		} catch(Exception e) {
			String message= "File 업로드 실패![파일명:"+ savedFilename +"]";
			throw new FileUploadException(message, mFile);
			// 익셉션의 전환 : 커스텀 익셉션 만들어 처리할 것을 권장
		}
		return savedFilename;
	}
	
	// 실제 저장을 위한 메서드
	private void writeFile(MultipartFile mFile, String saveFilename) throws IOException {
		byte[] fileData = mFile.getBytes();
		
		// 저장을 위해 FileOutputStream을 생성
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		fos.write(fileData);
		fos.close();
	}
}
