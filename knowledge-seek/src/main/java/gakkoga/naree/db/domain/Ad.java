package gakkoga.naree.db.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class Ad {

	private String ad_seq;		//년월+A+번호(12)
	private Date start_date;
	private Date end_date;
	private String company_name;
	private String company_tel;
	private String company_addr1;
	private String company_addr2;
	private String ad_name;
	private String ad_sound_name;
	private String ad_image_name;
	private String ad_sound_server;
	private String ad_image_server;
	private MultipartFile ad_sound_file;			//16
	private MultipartFile ad_image_file;			//16
	private String ad_url;
	private String youtube_addr;
	private String ad_gubun;
	private Timestamp input_date;
	private int call_num;
	private String entry_or;
	
	
	public String getAd_seq() {
		return ad_seq;
	}
	public void setAd_seq(String ad_seq) {
		this.ad_seq = ad_seq;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getCompany_addr1() {
		return company_addr1;
	}
	public void setCompany_addr1(String company_addr1) {
		this.company_addr1 = company_addr1;
	}
	public String getCompany_addr2() {
		return company_addr2;
	}
	public void setCompany_addr2(String company_addr2) {
		this.company_addr2 = company_addr2;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAd_sound_name() {
		return ad_sound_name;
	}
	public void setAd_sound_name(String ad_sound_name) {
		this.ad_sound_name = ad_sound_name;
	}
	public String getAd_image_name() {
		return ad_image_name;
	}
	public void setAd_image_name(String ad_image_name) {
		this.ad_image_name = ad_image_name;
	}
	public String getAd_sound_server() {
		return ad_sound_server;
	}
	public void setAd_sound_server(String ad_sound_server) {
		this.ad_sound_server = ad_sound_server;
	}
	public String getAd_image_server() {
		return ad_image_server;
	}
	public void setAd_image_server(String ad_image_server) {
		this.ad_image_server = ad_image_server;
	}
	public MultipartFile getAd_sound_file() {
		return ad_sound_file;
	}
	public void setAd_sound_file(MultipartFile ad_sound_file) {
		this.ad_sound_file = ad_sound_file;
	}
	public MultipartFile getAd_image_file() {
		return ad_image_file;
	}
	public void setAd_image_file(MultipartFile ad_image_file) {
		this.ad_image_file = ad_image_file;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	public String getYoutube_addr() {
		return youtube_addr;
	}
	public void setYoutube_addr(String youtube_addr) {
		this.youtube_addr = youtube_addr;
	}
	public String getAd_gubun() {
		return ad_gubun;
	}
	public void setAd_gubun(String ad_gubun) {
		this.ad_gubun = ad_gubun;
	}
	public Timestamp getInput_date() {
		return input_date;
	}
	public void setInput_date(Timestamp input_date) {
		this.input_date = input_date;
	}
	public int getCall_num() {
		return call_num;
	}
	public void setCall_num(int call_num) {
		this.call_num = call_num;
	}
	public String getEntry_or() {
		return entry_or;
	}
	public void setEntry_or(String entry_or) {
		this.entry_or = entry_or;
	}
	@Override
	public String toString() {
		return "Ad [ad_seq=" + ad_seq + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", company_name=" + company_name
				+ ", company_tel=" + company_tel + ", company_addr1="
				+ company_addr1 + ", company_addr2=" + company_addr2
				+ ", ad_name=" + ad_name + ", ad_sound_name=" + ad_sound_name
				+ ", ad_image_name=" + ad_image_name + ", ad_sound_server="
				+ ad_sound_server + ", ad_image_server=" + ad_image_server
				+ ", ad_sound_file=" + ad_sound_file + ", ad_image_file="
				+ ad_image_file + ", ad_url=" + ad_url + ", youtube_addr="
				+ youtube_addr + ", ad_gubun=" + ad_gubun + ", input_date="
				+ input_date + ", call_num=" + call_num + ", entry_or="
				+ entry_or + "]";
	}
}
