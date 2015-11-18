package alanglang.naree.db.mapper;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.SqlEntriesAd;
import alanglang.naree.db.domain.StarBg;

public interface AndMapper {

	public String searchMaxEngSeq();

	public Eng findTodayEngByToday();
	
	public Eng findEngByEngSeq(String eng_seq);

	public List<Eng> findThreeEngByEngSeq();
	
	public List<Eng> selectNineEng();

	public Ad findAd();

	public int updateCallNum(Ad ad);

	public String selectEntrySeq();

	public int insertEntries(Entries entries);

	public int selectEntries(Entries entries);

	public List<SqlEntriesAd> selectAdEntryResult(Entries entries);

	public Eng selectTodayEng();

	public Eng selectEngByEngSeq(String eng_seq);

	public List<Eng> selectNineEngByEng(Eng eng);

	public MainBg selectMaxMainBg();

	public StarBg selectMaxStarBg();

	public EngBg selectMaxEngBg();

	public List<Eng> selectThreeEng();

	public List<Eng> selectThreeEngReLeft(String eng_seq);

	public List<Eng> selectThreeEngReRight(String eng_seq);

	public List<Eng> selectThreeEngReRightMax();

	public List<Eng> selectThreeEngReLeftMax();

}
