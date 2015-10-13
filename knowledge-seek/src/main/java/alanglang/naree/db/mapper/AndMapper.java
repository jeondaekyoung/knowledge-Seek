package alanglang.naree.db.mapper;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;

public interface AndMapper {

	public String searchMaxEngSeq();

	public Eng findTodayEng();
	
	public Eng findTodayEngByEngSeq(String eng_seq);

	public List<Eng> findThreeEngByEngSeq();
	
	public List<Eng> findThreeEng();

	public Ad findAd();

	public int updateCallNum(Ad ad);

	public String selectEntrySeq();

	public int insertEntries(Entries entries);

	public int selectEntries(Entries entries);

}
