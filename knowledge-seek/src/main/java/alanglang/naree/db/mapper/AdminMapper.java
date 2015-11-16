package alanglang.naree.db.mapper;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Admin;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.StarBg;


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

	public int selectMainBgSeq();

	public int insertMainBg(MainBg mainbg);

	public int selectStarBgSeq();

	public int insertStarBg(StarBg starbg);

	public int selectEngBgSeq();

	public int insertEngBg(EngBg engbg);

	

}

