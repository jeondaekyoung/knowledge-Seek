package knowledge_seek.com;

/**
 * Created by sjw on 2015-11-12.
 * 광고
 */
public class Ad {
    private String ad_seq;
    private String ad_sound_server;
    private String ad_image_server;
    private String ad_url;
    private String youtube_addr;
    private String entry_or;

    public String getAd_seq() {
        return ad_seq;
    }

    public void setAd_seq(String ad_seq) {
        this.ad_seq = ad_seq;
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

    public String getEntry_or() {
        return entry_or;
    }

    public void setEntry_or(String entry_or) {
        this.entry_or = entry_or;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "ad_seq='" + ad_seq + '\'' +
                ", ad_sound_server='" + ad_sound_server + '\'' +
                ", ad_image_server='" + ad_image_server + '\'' +
                ", ad_url='" + ad_url + '\'' +
                ", youtube_addr='" + youtube_addr + '\'' +
                ", entry_or='" + entry_or + '\'' +
                '}';
    }
}
