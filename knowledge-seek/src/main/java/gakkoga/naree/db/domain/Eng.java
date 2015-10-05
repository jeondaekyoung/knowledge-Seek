package gakkoga.naree.db.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class Eng {

	private String eng_seq;  //년월+E+번호(12)
	private Date today_date;
	private String eng_sentence;
	private String eng_mean;
	private String eng_sound_name;		//파일이름
	private String eng_image_name;
	private String eng_sound_server;
	private String eng_image_server;
	private MultipartFile eng_sound_file;		//16
	private MultipartFile eng_image_file;		//16
	private Timestamp input_date;
	
	
	public String getEng_seq() {
		return eng_seq;
	}
	public void setEng_seq(String eng_seq) {
		this.eng_seq = eng_seq;
	}
	public Date getToday_date() {
		return today_date;
	}
	public void setToday_date(Date today_date) {
		this.today_date = today_date;
	}
	public String getEng_sentence() {
		return eng_sentence;
	}
	public void setEng_sentence(String eng_sentence) {
		this.eng_sentence = eng_sentence;
	}
	public String getEng_mean() {
		return eng_mean;
	}
	public void setEng_mean(String eng_mean) {
		this.eng_mean = eng_mean;
	}
	public String getEng_sound_name() {
		return eng_sound_name;
	}
	public void setEng_sound_name(String eng_sound_name) {
		this.eng_sound_name = eng_sound_name;
	}
	public String getEng_image_name() {
		return eng_image_name;
	}
	public void setEng_image_name(String eng_image_name) {
		this.eng_image_name = eng_image_name;
	}
	public String getEng_sound_server() {
		return eng_sound_server;
	}
	public void setEng_sound_server(String eng_sound_server) {
		this.eng_sound_server = eng_sound_server;
	}
	public String getEng_image_server() {
		return eng_image_server;
	}
	public void setEng_image_server(String eng_image_server) {
		this.eng_image_server = eng_image_server;
	}
	public MultipartFile getEng_sound_file() {
		return eng_sound_file;
	}
	public void setEng_sound_file(MultipartFile eng_sound_file) {
		this.eng_sound_file = eng_sound_file;
	}
	public MultipartFile getEng_image_file() {
		return eng_image_file;
	}
	public void setEng_image_file(MultipartFile eng_image_file) {
		this.eng_image_file = eng_image_file;
	}
	public Timestamp getInput_date() {
		return input_date;
	}
	public void setInput_date(Timestamp input_date) {
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "Eng [eng_seq=" + eng_seq + ", today_date=" + today_date
				+ ", eng_sentence=" + eng_sentence + ", eng_mean=" + eng_mean
				+ ", eng_sound_name=" + eng_sound_name + ", eng_image_name="
				+ eng_image_name + ", eng_sound_server=" + eng_sound_server
				+ ", eng_image_server=" + eng_image_server
				+ ", eng_sound_file=" + eng_sound_file + ", eng_image_file="
				+ eng_image_file + ", input_date=" + input_date + "]";
	}
}
