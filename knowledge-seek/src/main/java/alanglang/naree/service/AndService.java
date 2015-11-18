package alanglang.naree.service;

import java.util.List;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.SqlEntriesAd;
import alanglang.naree.db.domain.StarBg;

public interface AndService {

	/**
	 * 오늘의 영어학습 찾기
	 * @return
	 */
	public Eng findTodayEng();

	/**
	 * 9일치 영어학습 찾기
	 * @return
	 */
	public List<Eng> findNineEng();

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

	/**
	 * 안드로이드폰에서 당첨자 확인하기
	 * @param entries
	 * @return
	 */
	public List<SqlEntriesAd> findAdEntryResult(Entries entries);

	/**
	 * eng_seq로 영어학습 찾기
	 * @param eng_seq
	 * @return
	 */
	public Eng findEngByEngSeq(String eng_seq);

	/**
	 * eng를 기준으로  전 9일치 예문과 뜻 읽어오기
	 * @param eng
	 * @return
	 */
	public List<Eng> findNineEngByEng(Eng eng);

	/**
	 * 메인배경 이미지 찾기
	 * @return
	 */
	public MainBg findMaxMainBg();

	/**
	 * 스타배경 이미지 찾기
	 * @return
	 */
	public StarBg findMaxStarBg();

	/**
	 * 영어배경 이미지 찾기
	 * @return
	 */
	public EngBg findMaxEngBg();

	/**
	 * 3일치 영어학습 찾기(아랫부분)
	 * @return
	 */
	public List<Eng> findThreeEng();

	/**
	 * 3일치 영어학습 찾기(아랫부분) 왼쪽, 오른쪽 이동시
	 * @param eng_seq
	 * @param orien
	 * @return
	 */
	public List<Eng> findThreeEngRe(String eng_seq, String orien);

}
