package alanglang.naree.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alanglang.naree.dao.AndDao;
import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.SqlEntriesAd;


@Service
public class AndServiceImpl implements AndService {
	
	@Autowired
	private AndDao andDao;

	@Override
	public Eng findTodayEng() {
		//오늘의 예문이 없을 수도 있고 미리 입력했을 수도 있다.
		//오늘의 예문 읽어오기 - 오늘 날짜를 기준으로 최근 것을 읽어오자
		return andDao.selectTodayEng();
	}

	@Override
	public List<Eng> findNineEng() {
		
		return andDao.selectNineEng();
	}

	@Override
	public Ad findAd() {

		return andDao.findAd();
	}

	@Override
	public int countCallNum(Ad ad) {
		int call_num = ad.getCall_num();
		ad.setCall_num(call_num + 1);
		
		return andDao.updateCallNum(ad);
	}

	@Override
	public String nextEntrySeq() {
		String curEntrySeq = andDao.selectEntrySeq();
		String nextEntrySeq = null;
		System.out.println("entry_seq : " + curEntrySeq);
		
		//현재 날짜 계산
		Calendar cal = Calendar.getInstance();
		int nYear = cal.get(Calendar.YEAR);
		int nMonth = cal.get(Calendar.MONTH) + 1;
		int nDate = cal.get(Calendar.DATE);
		String nowYear = String.valueOf(nYear);
		String nowMonth = String.valueOf(nMonth);
		String nowDate = String.valueOf(nDate);
		
		if(nowMonth.length() == 1){
			nowMonth = "0".concat(nowMonth);
		}
		if(nowDate.length() == 1){
			nowDate = "0".concat(nowDate);
		}
		
		//System.out.println("현재 날짜는 " + nowYear + "년 " + nowMonth + "월 " + nowDate + "일입니다.");
		
		if(curEntrySeq == null){
			nextEntrySeq = "".concat(nowYear).concat(nowMonth).concat(nowDate).concat(String.format("%08d", 1));
		} else {
			String curYear = curEntrySeq.substring(0, 4);
			String curMonth = curEntrySeq.substring(4, 6);
			String curDate = curEntrySeq.substring(6, 8);
			String curNum = curEntrySeq.substring(8);
			int number = Integer.parseInt(curNum);
			
			System.out.println(curYear + curMonth + curDate + ", " + number);
			
			if(curYear.equals(nowYear)){
				if(curMonth.equals(nowMonth)){
					if(curDate.equals(nowDate)){
						System.out.println("다 같다");
						nextEntrySeq = curYear.concat(curMonth).concat(curDate).concat(String.format("%08d", number + 1));
					} else {
						nextEntrySeq = curYear.concat(curMonth).concat(nowDate).concat(String.format("%08d",1));
					}
				} else {
					nextEntrySeq = curYear.concat(nowMonth).concat(nowDate).concat(String.format("%08d", 1));
				}
			} else {
				nextEntrySeq = nowYear.concat(nowMonth).concat(nowDate).concat(String.format("%08d", 1));
			}
		}
		
		return nextEntrySeq;
	}

	@Override
	public int entriesRegister(Entries entries) {
		//같은 이벤트에 또 응모하지 못하도록한다.
		int noEntries = andDao.selectEntries(entries); 
		System.out.println("응모한 횟수 : " + noEntries);
		if(noEntries == 0){
			return andDao.insertEntries(entries);
		} else {
			return 0;
		}
	}

	@Override
	public List<SqlEntriesAd> findAdEntryResult(Entries entries) {
		
		return andDao.selectAdEntryResult(entries);
	}

	@Override
	public Eng findEngByEngSeq(String eng_seq) {
		
		return andDao.selectEngByEngSeq(eng_seq);
	}

	@Override
	public List<Eng> findNineEngByEng(Eng eng) {
		
		return andDao.selectNineEngByEng(eng);
	}

}
