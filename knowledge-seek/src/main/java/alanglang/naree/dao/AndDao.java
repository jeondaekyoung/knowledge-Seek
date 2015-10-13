package alanglang.naree.dao;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;

public interface AndDao {

	/**
	 * 가장최근 영어학습시퀀스(eng_seq)찾기
	 * @return
	 */
	public String searchMaxEngSeq();

	/**
	 * 영어시퀀스로 영어학습찾기
	 * @param eng_seq
	 * @return
	 */
	public Eng findTodayEng(String eng_seq);

	/**
	 * 최근 영어학습 3일치 찾기
	 * @return
	 */
	public List<Eng> findThreeEng();

	/**
	 * 광고화면에 출력할 광고정보 찾기
	 * @return
	 */
	public Ad findAd();

	/**
	 * 광고호출후 광고호출횟수 카운트
	 * @param ad
	 * @return
	 */
	public int updateCallNum(Ad ad);

	/**
	 * 가장최근 응모시퀀스(entry_seq)찾기
	 * @return
	 */
	public String selectEntrySeq();

	/**
	 * 응모자테이블에 삽입하기
	 * @param entries
	 * @return
	 */
	public int insertEntries(Entries entries);

	/**
	 * 같은 이벤트에 응모한 적이 있는지 확인하기
	 * @param entries
	 * @return
	 */
	public int selectEntries(Entries entries);

}
