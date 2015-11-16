package alanglang.naree.service;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Admin;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.StarBg;

/**
 * @author Administrator
 *
 */
public interface AdminService {

	/**
	 * 관리자 로그인하기
	 * 관리자 아이디가 사용중인지, 비밀번호는 맞는지 확인한다.
	 * @param admin_id
	 * @param admin_pw
	 * @return
	 */
	public Admin login(String admin_id, String admin_pw);
	
	/**
	 * 영어시퀀스 생성
	 * @return
	 */
	public String nextEngSeq();

	/**
	 * 영어학습테이블 저장
	 * @param eng
	 */
	public int engRegister(Eng eng);

	/**
	 * 광고시퀀스 생성
	 * @return
	 */
	public String nextAdSeq();

	/**
	 * 광고테이블에 저장
	 * @param ad
	 * @return
	 */
	public int adRegister(Ad ad);

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
	public List<Entries> searchEntries(String ad_seq);

	/**
	 * 응모시퀀스로 당첨여부 검색
	 * @param string
	 * @return
	 */
	public String searchWinSepa(String entry_seq);

	/**
	 * 응모자테이블에서 당첨자 저장하기
	 * @param entries
	 * @return
	 */
	public int registerWinSepa(Entries entries);

	/**
	 * 메인배경 시퀀스 생성
	 * @return
	 */
	public int nextMainBgSeq();

	/**
	 * 메인배경화면 등록하기
	 * @param mainbg
	 * @return 
	 */
	public int registerMainBg(MainBg mainbg);

	/**
	 * 스타배경 시퀀스 생성
	 * @return
	 */
	public int nextStarBgSeq();

	/**
	 * 스타배경화면 등록하기
	 * @param starbg
	 * @return
	 */
	public int registerStarBg(StarBg starbg);

	/**
	 * 영어배경 시퀀스 생성
	 * @return
	 */
	public int nextEngBgSeq();

	/**
	 * 영어배경화면 등록하기
	 * @param engbg
	 * @return
	 */
	public int registerEngBg(EngBg engbg);

}
