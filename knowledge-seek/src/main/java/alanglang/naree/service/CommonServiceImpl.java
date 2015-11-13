package alanglang.naree.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import alanglang.naree.util.exception.AlanglangWebException;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public void writeFile(MultipartFile multipartFile, String gubun, String filename, String fileserver) {
		System.out.println(filename + "(" + fileserver + ") 저장하기");
		String tempPath = null;
		String serverPath = null;
		if(System.getProperty("os.name").contains("Windows")){
			System.out.println("현재 운영체제 : " + System.getProperty("os.name"));
			
			tempPath = "C:\\alanglang\\workspace\\knowledge-seek\\src\\main\\webapp\\fileupload\\temp\\" + filename;
			if(gubun.equals("sound")){
				serverPath = "C:\\alanglang\\workspace\\knowledge-seek\\src\\main\\webapp\\fileupload\\sound\\" + fileserver;
			} else if(gubun.equals("image")){
				serverPath = "C:\\alanglang\\workspace\\knowledge-seek\\src\\main\\webapp\\fileupload\\image\\" + fileserver;
			} else if(gubun.equals("main") || gubun.equals("star") || gubun.equals("eng")){
				serverPath = "C:\\alanglang\\workspace\\knowledge-seek\\src\\main\\webapp\\fileupload\\bg\\" + fileserver;
			} 
			
			
		} else {
			System.out.println("현재 운영체제 : " + System.getProperty("os.name"));
			
			tempPath = "/usr/local/server/tomcat/webapps/ROOT/fileupload/temp/" + filename;
			if(gubun.equals("sound")){
				serverPath = "/usr/local/server/tomcat/webapps/ROOT/fileupload/sound/" + fileserver;
			} else if(gubun.equals("image")){
				serverPath = "/usr/local/server/tomcat/webapps/ROOT/fileupload/image/" + fileserver;
			} else if(gubun.equals("main") || gubun.equals("star") || gubun.equals("eng")){
				serverPath = "/usr/local/server/tomcat/webapps/ROOT/fileupload/bg/" + fileserver;
			} 
			
		}
		System.out.println("파일경로 : " + serverPath);
		
		OutputStream out = null;
		try{
			out = new FileOutputStream(tempPath);
			BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
			byte[] buffer = multipartFile.getBytes(); 
			int read;
			while((read = bis.read(buffer)) > 0){
				out.write(buffer, 0, read);
			}
			
			File file1 = new File(serverPath);
			multipartFile.transferTo(file1);
			
		} catch (IOException e) {
			throw new AlanglangWebException("파일 업로드에 실패하였습니다.");
		} finally {
			IOUtils.closeQuietly(out);
			
		}

		//원본파일 삭제
		File file2 = new File(tempPath);
		if(file2.exists()){
			//System.out.println("삭제중");
			boolean deleteFlag = file2.delete();
			if(deleteFlag){
				//System.out.println("파일삭제 성공함");
			} else {
				//System.out.println("파일삭제 실패함");
			}
		} else {
			//System.out.println("파일이 존재하지 않습니다.");
		}
		
	}

}
