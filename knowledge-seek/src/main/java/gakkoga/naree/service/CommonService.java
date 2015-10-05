package gakkoga.naree.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	
	/**
	 * 파일업로드
	 * @param multipartFile	업로드파일
	 * @param gubun				소리파일? 이미지파일?
	 * @param filename			원본파일이름
	 * @param fileserver			서버에 저장되는 이름
	 */
	public void writeFile(MultipartFile multipartFile, String gubun, String filename, String fileserver);

}
