package alanglang.naree.db.domain;

public class EngBg {
	
	private int eng_bg_seq;
	private String eng_bg_name;
	private String eng_bg_server;
	public int getEng_bg_seq() {
		return eng_bg_seq;
	}
	public void setEng_bg_seq(int eng_bg_seq) {
		this.eng_bg_seq = eng_bg_seq;
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
	@Override
	public String toString() {
		return "EngBg [eng_bg_seq=" + eng_bg_seq + ", eng_bg_name="
				+ eng_bg_name + ", eng_bg_server=" + eng_bg_server + "]";
	}
	

}
