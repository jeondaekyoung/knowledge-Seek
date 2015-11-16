package alanglang.naree.db.domain;

public class StarBg {

	private int star_bg_seq;
	private String star_bg_name;
	private String star_bg_server;
	public int getStar_bg_seq() {
		return star_bg_seq;
	}
	public void setStar_bg_seq(int star_bg_seq) {
		this.star_bg_seq = star_bg_seq;
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
	@Override
	public String toString() {
		return "StarBg [star_bg_seq=" + star_bg_seq + ", star_bg_name="
				+ star_bg_name + ", star_bg_server=" + star_bg_server + "]";
	}
	
}
