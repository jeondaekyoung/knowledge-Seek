package alanglang.naree.service;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;

public interface AndService {

	/**
	 * 오늘의 영어학습 찾기
	 * @return
	 */
	public Eng findTodayEng();

	/**
	 * 3일치 영어학습 찾기
	 * @return
	 */
	public List<Eng> findThreeEng();

	/**
	 * 광고정보 찾기
	 * @return
	 */
	public Ad findAd();

	/**
	 * 광고호출시 횟수카운트
	 * @param ad
	 * @return
	 */
	public int countCallNum(Ad ad);

	/**
	 * 응모시 다음 응모시퀀스 생성
	 * @return
	 */
	public String nextEntrySeq();

	/**
	 * 응모자 등록하기
	 * @param entries
	 * @return
	 */
	public int entriesRegister(Entries entries);

}
