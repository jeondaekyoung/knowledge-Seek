package gakkoga.naree.db.mapper;

import java.util.List;

import gakkoga.naree.db.domain.Ad;
import gakkoga.naree.db.domain.Eng;
import gakkoga.naree.db.domain.Entries;

public interface AndMapper {

	public String searchMaxEngSeq();

	public Eng findTodayEng(String eng_seq);

	public List<Eng> findThreeEng();

	public Ad findAd();

	public int updateCallNum(Ad ad);

	public String selectEntrySeq();

	public int insertEntries(Entries entries);

	public int selectEntries(Entries entries);
}
