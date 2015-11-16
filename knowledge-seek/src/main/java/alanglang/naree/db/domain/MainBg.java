package alanglang.naree.db.domain;

public class MainBg {

	private int main_bg_seq;
	private String main_bg_name;
	private String main_bg_server;
	public int getMain_bg_seq() {
		return main_bg_seq;
	}
	public void setMain_bg_seq(int main_bg_seq) {
		this.main_bg_seq = main_bg_seq;
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
	@Override
	public String toString() {
		return "MainBg [main_bg_seq=" + main_bg_seq + ", main_bg_name="
				+ main_bg_name + ", main_bg_server=" + main_bg_server + "]";
	}
	
}
