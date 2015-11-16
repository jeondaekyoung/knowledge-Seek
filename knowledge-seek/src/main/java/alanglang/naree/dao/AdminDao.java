package alanglang.naree.dao;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Admin;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.StarBg;


public interface AdminDao {

	
	/**
	 * 관리자 아이디로 관리자 검색하기
	 * @param admin_id
	 * @return
	 */
	public Admin searchAdmin(String admin_id);

	/**
	 * 영어학습테이블에서 최근 영어시퀀스 찾기
	 * @return
	 */
	public String selectEngSeq();

	/**
	 * 영어학습정보 삽입하기
	 * @param eng
	 * @return
	 */
	public int insertEng(Eng eng);

	/**
	 * 광고테이블에서 최근 광고시퀀스 찾기
	 * @return
	 */
	public String selectAdSeq();

	/**
	 * 광고정보 삽입하기
	 * @param ad
	 * @return
	 */
	public int insertAd(Ad ad);

	/**
	 * 광고목록 검색
	 * @param ad
	 * @return
	 */
	public List<Ad> searchAdList(Ad ad);

	/**
	 * 이벤트에 응모자들 검색
	 * @param ad_seq
	 * @return
	 */
	public List<Entries> selectEntries(String ad_seq);

	/**
	 * 응모자테이블에서 응모시퀀스로 당첨여부검색
	 * @param entry_seq
	 * @return
	 */
	public String selectWinSepa(String entry_seq);

	/**
	 * 응모자테이블에서 당첨자 등록하기
	 * @param entries
	 * @return
	 */
	public int updateWinSepa(Entries entries);

	/**
	 * 메인배경 시퀀스 생성
	 * @return
	 */
	public int selectMainBgSeq();

	/**
	 * 메인배경 등록하기
	 * @param mainbg
	 * @return
	 */
	public int insertMainBg(MainBg mainbg);

	/**
	 * 스타배경 시퀀스 생성
	 * @return
	 */
	public int selectStarBgSeq();

	/**
	 * 스타배경 등록하기
	 * @param starbg
	 * @return
	 */
	public int insertStarBg(StarBg starbg);

	/**
	 * 영어배경 시퀀스 생성
	 * @return
	 */
	public int selectEngBgSeq();

	/**
	 * 영어배경 등록하기
	 * @param engbg
	 * @return
	 */
	public int insertEngBg(EngBg engbg);
	
}
