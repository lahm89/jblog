package jblog.exception;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadException extends RuntimeException {
	private MultipartFile mFile;
	public FileUploadException() {
		super();
	}
	public FileUploadException(String message, MultipartFile mFile) {
		super(message);
		this.mFile = mFile;
	}
	public MultipartFile getmFile() {
		return mFile;
	}
	public void setmFile(MultipartFile mFile) {
		this.mFile = mFile;
	}
	@Override
	public String toString() {
		return "FileUploadException [mFile=" + mFile + "]";
	}
}
