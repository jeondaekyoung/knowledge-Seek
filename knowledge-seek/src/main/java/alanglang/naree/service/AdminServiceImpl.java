package alanglang.naree.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alanglang.naree.dao.AdminDao;
import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Admin;
import alanglang.naree.db.domain.Bg;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.util.exception.AlanglangWebException;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin login(String admin_id, String admin_pw) {
		System.out.println("AdminServiceImpl.login()");
		Admin admin = adminDao.searchAdmin(admin_id);
		
		if(admin == null){
			throw new AlanglangWebException("존재하지 않는 아이디입니다.");
		}
		
		if(admin.getAdmin_pw().equals(admin_pw)){
			return admin;
		} else {
			throw new AlanglangWebException("잘못된 패스워드입니다.");
		}
	}

	@Override
	public String nextEngSeq() {
		String curEngSeq = adminDao.selectEngSeq();
		String nextEngSeq = null;
		//System.out.println("eng_seq : " + curEngSeq);
		
		//현재 날짜 계산
		Calendar cal = Calendar.getInstance();
		int nYear = cal.get(Calendar.YEAR);
		int nMonth = cal.get(Calendar.MONTH) + 1;
		String nowYear = String.valueOf(nYear);
		String nowMonth = String.valueOf(nMonth);
		System.out.println("현재 날짜는 " + nowYear + "년 " + nowMonth + "월입니다.");
		
		if(nowMonth.length() == 1){
			nowMonth = "0".concat(nowMonth);
		}
		
		
		if(curEngSeq == null){
			nextEngSeq = "".concat(nowYear).concat(nowMonth).concat(String.format("%04d", 1));
		} else {
			String curYear = curEngSeq.substring(0,4);
			String curMonth = curEngSeq.substring(4, 6);
			String curNum = curEngSeq.substring(6);
			int number = Integer.parseInt(curNum);
			
			if(curYear.equals(nowYear)){
				if(curMonth.equals(nowMonth)){
					nextEngSeq = curYear.concat(curMonth).concat(String.format("%04d", number +1));
				} else {
					nextEngSeq = curYear.concat(nowMonth).concat(String.format("%04d", 1));
				}
			} else {
				//년도 변경(월도 변경됨)
				nextEngSeq = nowYear.concat(nowMonth).concat(String.format("%04d", 1));
			}
		}
		
		return nextEngSeq;
	}

	@Override
	public int engRegister(Eng eng) {
		
		return adminDao.insertEng(eng);
	}

	@Override
	public String nextAdSeq() {
		String curAdSeq = adminDao.selectAdSeq();
		String nextAdSeq = null;
		System.out.println("ad_seq : " + curAdSeq);
		
		//현재 날짜 계산
		Calendar cal = Calendar.getInstance();
		int nYear = cal.get(Calendar.YEAR);
		int nMonth = cal.get(Calendar.MONTH) + 1;
		String nowYear = String.valueOf(nYear);
		String nowMonth = String.valueOf(nMonth);
		System.out.println("현재 날짜는 " + nowYear + "년 " + nowMonth + "월입니다.");
		
		if(nowMonth.length() == 1){
			nowMonth = "0".concat(nowMonth);
		}
		
		if(curAdSeq == null){
			nextAdSeq = "".concat(nowYear).concat(nowMonth).concat(String.format("%04d", 1));
		} else {
			String curYear = curAdSeq.substring(0, 4);
			String curMonth = curAdSeq.substring(4, 6);
			String curNum = curAdSeq.substring(6);
			int number = Integer.parseInt(curNum);
			
			if(curYear.equals(nowYear)){
				if(curMonth.equals(nowMonth)){
					nextAdSeq = curYear.concat(curMonth).concat(String.format("%04d", number + 1));
				} else {
					nextAdSeq = curYear.concat(nowMonth).concat(String.format("%04d", 1));
				}
			} else {
				//년도 변경(월도 변경됨)
				nextAdSeq = nowYear.concat(nowMonth).concat(String.format("%04d", 1));
			}
		}
		
		return nextAdSeq;
	}

	@Override
	public int adRegister(Ad ad) {
		
		return adminDao.insertAd(ad);
	}

	@Override
	public List<Ad> searchAdList(Ad ad) {
		
		return adminDao.searchAdList(ad);
	}

	@Override
	public List<Entries> searchEntries(String ad_seq) {
		
		return adminDao.selectEntries(ad_seq);
	}

	@Override
	public String searchWinSepa(String entry_seq) {
		
		return adminDao.selectWinSepa(entry_seq);
	}

	@Override
	public int registerWinSepa(Entries entries) {
		
		return adminDao.updateWinSepa(entries);
	}

	@Override
	public int bgRegister(Bg bg) {
		
		return adminDao.insertBg(bg);
	}



}
