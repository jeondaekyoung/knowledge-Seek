package alanglang.naree.db.domain;

import org.springframework.web.multipart.MultipartFile;

public class Bg {
	
	private int bg_seq;
	private String main_bg_name;
	private String main_bg_server;
	private String star_bg_name;
	private String star_bg_server;
	private String eng_bg_name;
	private String eng_bg_server;
	private MultipartFile main_bg_file;
	private MultipartFile star_bg_file;
	private MultipartFile eng_bg_file;
	public int getBg_seq() {
		return bg_seq;
	}
	public void setBg_seq(int bg_seq) {
		this.bg_seq = bg_seq;
	}
	public String getMain_bg_name() {
		return main_bg_name;
	}
	public void setMain_bg_name(String main_bg_name) {
		this.main_bg_name = main_bg_name;
	}
	public String getMain_bg_server() {
		return main_bg_server;
	}
	public void setMain_bg_server(String main_bg_server) {
		this.main_bg_server = main_bg_server;
	}
	public String getStar_bg_name() {
		return star_bg_name;
	}
	public void setStar_bg_name(String star_bg_name) {
		this.star_bg_name = star_bg_name;
	}
	public String getStar_bg_server() {
		return star_bg_server;
	}
	public void setStar_bg_server(String star_bg_server) {
		this.star_bg_server = star_bg_server;
	}
	public String getEng_bg_name() {
		return eng_bg_name;
	}
	public void setEng_bg_name(String eng_bg_name) {
		this.eng_bg_name = eng_bg_name;
	}
	public String getEng_bg_server() {
		return eng_bg_server;
	}
	public void setEng_bg_server(String eng_bg_server) {
		this.eng_bg_server = eng_bg_server;
	}
	public MultipartFile getMain_bg_file() {
		return main_bg_file;
	}
	public void setMain_bg_file(MultipartFile main_bg_file) {
		this.main_bg_file = main_bg_file;
	}
	public MultipartFile getStar_bg_file() {
		return star_bg_file;
	}
	public void setStar_bg_file(MultipartFile star_bg_file) {
		this.star_bg_file = star_bg_file;
	}
	public MultipartFile getEng_bg_file() {
		return eng_bg_file;
	}
	public void setEng_bg_file(MultipartFile eng_bg_file) {
		this.eng_bg_file = eng_bg_file;
	}
	@Override
	public String toString() {
		return "Bg [bg_seq=" + bg_seq + ", main_bg_name=" + main_bg_name
				+ ", main_bg_server=" + main_bg_server + ", star_bg_name="
				+ star_bg_name + ", star_bg_server=" + star_bg_server
				+ ", eng_bg_name=" + eng_bg_name + ", eng_bg_server="
				+ eng_bg_server + ", main_bg_file=" + main_bg_file
				+ ", star_bg_file=" + star_bg_file + ", eng_bg_file="
				+ eng_bg_file + "]";
	}
	

}
