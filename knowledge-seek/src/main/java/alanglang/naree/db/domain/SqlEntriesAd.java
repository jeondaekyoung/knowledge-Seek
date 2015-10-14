package alanglang.naree.db.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class SqlEntriesAd {

	private String win_sepa;
	private String coupon_num;
	private String coupon_use;
	private Timestamp input_datetime;
	private Date start_date;
	private Date end_date;
	private String ad_name;
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
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	@Override
	public String toString() {
		return "SqlEntriesAd [win_sepa=" + win_sepa + ", coupon_num="
				+ coupon_num + ", coupon_use=" + coupon_use
				+ ", input_datetime=" + input_datetime + ", start_date="
				+ start_date + ", end_date=" + end_date + ", ad_name="
				+ ad_name + "]";
	}
}
