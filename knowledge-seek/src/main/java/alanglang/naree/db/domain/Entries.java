package alanglang.naree.db.domain;

import java.sql.Timestamp;

public class Entries {

	private String entry_seq;   //년+번호
	private String name;
	private String email;
	private String phone;
	private String win_sepa;
	private String coupon_num;
	private String coupon_use;
	private Timestamp input_datetime;
	private String ad_seq;
	
	public String getEntry_seq() {
		return entry_seq;
	}
	public void setEntry_seq(String entry_seq) {
		this.entry_seq = entry_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWin_sepa() {
		return win_sepa;
	}
	public void setWin_sepa(String win_sepa) {
		this.win_sepa = win_sepa;
	}
	public String getCoupon_num() {
		return coupon_num;
	}
	public void setCoupon_num(String coupon_num) {
		this.coupon_num = coupon_num;
	}
	public String getCoupon_use() {
		return coupon_use;
	}
	public void setCoupon_use(String coupon_use) {
		this.coupon_use = coupon_use;
	}
	public Timestamp getInput_datetime() {
		return input_datetime;
	}
	public void setInput_datetime(Timestamp input_datetime) {
		this.input_datetime = input_datetime;
	}
	public String getAd_seq() {
		return ad_seq;
	}
	public void setAd_seq(String ad_seq) {
		this.ad_seq = ad_seq;
	}
	@Override
	public String toString() {
		return "Entry [entry_seq=" + entry_seq + ", name=" + name + ", email="
				+ email + ", phone=" + phone + ", win_sepa=" + win_sepa
				+ ", coupon_num=" + coupon_num + ", coupon_use=" + coupon_use
				+ ", input_datetime=" + input_datetime + ", ad_seq=" + ad_seq
				+ "]";
	}
	
	
}
