package knowledge_seek.com;

/**
 * Created by sjw on 2015-11-17.
 */
public class Eng {
    private String eng_seq;
    private String today_date;
    private String eng_sentence;
    private String eng_mean;
    private String eng_sound_server;

    public String getEng_seq() {
        return eng_seq;
    }

    public void setEng_seq(String eng_seq) {
        this.eng_seq = eng_seq;
    }

    public String getToday_date() {
        return today_date;
    }

    public void setToday_date(String today_date) {
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

    public String getEng_sound_server() {
        return eng_sound_server;
    }

    public void setEng_sound_server(String eng_sound_server) {
        this.eng_sound_server = eng_sound_server;
    }

    @Override
    public String toString() {
        return "Eng{" +
                "eng_seq='" + eng_seq + '\'' +
                ", today_date='" + today_date + '\'' +
                ", eng_sentence='" + eng_sentence + '\'' +
                ", eng_mean='" + eng_mean + '\'' +
                ", eng_sound_server='" + eng_sound_server + '\'' +
                '}';
    }
}
