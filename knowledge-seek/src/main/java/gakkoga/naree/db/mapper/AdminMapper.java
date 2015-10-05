package gakkoga.naree.db.mapper;

import java.util.List;

import gakkoga.naree.db.domain.Ad;
import gakkoga.naree.db.domain.Admin;
import gakkoga.naree.db.domain.Eng;
import gakkoga.naree.db.domain.Entries;


public interface AdminMapper {

	public Admin searchAdmin(String admin_id);
	
	public String selectEngSeq();

	public int insertEng(Eng eng);

	public String selectAdSeq();

	public int insertAd(Ad ad);

	public List<Ad> searchAdList(Ad ad);

	public List<Entries> selectEntries(String ad_seq);

	public String selectWinSepa(String entry_seq);

	public int updateWinSepa(Entries entries);

}

