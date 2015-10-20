package alanglang.naree.dao;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.SqlEntriesAd;

public interface AndDao {

	/**
	 * 가장최근 영어학습시퀀스(eng_seq)찾기
	 * @return
	 */
	public String searchMaxEngSeq();

	/**
	 * 오늘날짜로 영어학습찾기
	 * @param eng_seq
	 * @return
	 */
	public Eng findTodayEngByToday();
	
	/**
	 * eng시퀀스로 영어학습찾기
	 * @param eng_seq
	 * @return
	 */
	public Eng findEngByEngSeq(String eng_seq);

	/**
	 * 마지막 영어학습 3일치 찾기
	 * @return
	 */
	public List<Eng> findThreeEngByEngSeq();
	
	/**
	 * 최근 영어학습 9일치 찾기
	 * @return
	 */
	public List<Eng> selectNineEng();

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

	/**
	 * 안드로이드폰에서 당첨자 확인하기
	 * @param entries
	 * @return
	 */
	public List<SqlEntriesAd> selectAdEntryResult(Entries entries);

	/**
	 * 알람시 오늘의 영어학습찾기
	 * @return
	 */
	public Eng selectTodayEng();

	/**
	 * eng_seq로 영어학습찾기
	 * @param eng_seq
	 * @return
	 */
	public Eng selectEngByEngSeq(String eng_seq);

	/**
	 * eng를 기준으로  전 9일치 예문과 뜻 읽어오기
	 * @param eng
	 * @return
	 */
	public List<Eng> selectNineEngByEng(Eng eng);

}
