package alanglang.naree.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.EngBg;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.MainBg;
import alanglang.naree.db.domain.SqlEntriesAd;
import alanglang.naree.db.domain.StarBg;
import alanglang.naree.service.AndService;

@Controller
@RequestMapping("and")
public class AndController {
	
	@Autowired
	private AndService andService;

	/**
	 * 영어학습알람화면 jsp
	 * @return
	 */
	@RequestMapping(value = "eng.do")
	public ModelAndView eng(){
		ModelAndView mv = new ModelAndView();
		
		//1. 오늘의 예문과 뜻
		System.out.println("============== 오늘의 예문 찾기==============");
		Eng eng = andService.findTodayEng();
		
		//2.  9일치 예문과 뜻 읽어오기
		List<Eng> engs = andService.findNineEng();
		
		//3. 소리파일 재생
		mv.addObject("eng", eng);
		mv.addObject("engs", engs);
		
		mv.setViewName("and/eng");
		return mv;
	}
	
	/**
	 * 영어학습알람화면 - 다른 날짜 기준
	 * @param eng_seq
	 * @return
	 */
	@RequestMapping(value = "engByEngSeq.do")
	public ModelAndView engByEngSeq(String eng_seq){
		ModelAndView mv = new ModelAndView();
		
		//지난 예문과 뜻
		System.out.println("============= "  + eng_seq + " 예문 찾기 =============");
		Eng eng = andService.findEngByEngSeq(eng_seq);
		
		//2.  eng를 기준으로 전 9일치 예문과 뜻 읽어오기
		List<Eng> engs = andService.findNineEngByEng(eng);
		
		//3. 소리파일 재생
		mv.addObject("eng", eng);
		mv.addObject("engs", engs);
		
		mv.setViewName("and/eng");
		return mv;
	}
	
	/**
	 * 광고알람화면
	 * @return
	 */
	@RequestMapping(value = "ad.do")
	public ModelAndView ad(){
		ModelAndView mv = new ModelAndView();
		
		//광고화면 출력
		//출력해야될 광고들 중에서 가장 출력횟수가 작은 광고를 리턴한다.
		Ad ad = andService.findAd();
		System.out.println("광고알람 : " + ad.toString());
		
		//출력횟수를 카운트한다.
		int result = andService.countCallNum(ad);
		if(result == 1){
			System.out.println("출력횟수 카운트성공");
		}
		
		mv.addObject("ad", ad);
		mv.setViewName("and/ad");
		return mv;
	}
	
	/**
	 * 응모하기
	 * @param entries
	 * @return
	 */
	@RequestMapping(value = "entry.do", method=RequestMethod.POST)
	@ResponseBody
	public String entry(Entries entries){
		System.out.println(entries.toString());
		
		//응모 저장하기
		//1. 응모시퀀스 생성
		String entry_seq = andService.nextEntrySeq();
		System.out.println("생성된 entry_seq : " + entry_seq);
		entries.setEntry_seq(entry_seq);
		
		//2. 응모하기
		int result = andService.entriesRegister(entries);
		String resultStr = null;
		if(result == 1){
			System.out.println("응모자등록에 성공하였습니다.");
			resultStr = "success";
		} else {
			System.out.println("응모한 이벤트입니다.");
			resultStr = "fail";
		}
		
		return resultStr;
	}
	
	/**
	 * 알람 광고시 소리파일을 안드로이드에 알려주기 위한 JSON
	 * (사용안함)
	 * @return
	 */
	@RequestMapping(value = "adSound.do")
	@ResponseBody
	public Ad adSound(){
		System.out.println("알람 광고시 소리파일을 안드로이드에 알려주기 위한 JSON");
		
		Ad ad = andService.findAd();
		
		return ad;
	}
	/**
	 * 알람 광고시 광고정보를 안드로이드에 알려주기 위한 JSON
	 * (위의 adSound()를 adInfo()로 바뀜)
	 * @return
	 */
	@RequestMapping(value = "adInfo.do")
	@ResponseBody
	public Ad adInfo(){
		System.out.println("알람 광고시 광고정보를 안드로이드에 알려주기 위한 JSON");
		
		//출력해야될 광고들 중에서 가장 출력횟수가 작은 광고를 리턴한다.
		Ad ad = andService.findAd();
		System.out.println("광고알람 : " + ad.toString());
		
		//출력횟수를 카운트한다.
		int result = andService.countCallNum(ad);
		if(result == 1){
			System.out.println("출력횟수 카운트성공");
		}
				
		return ad;
	}
	
	
	/**
	 * 알람 영어학습시 소리파일을 안드로이드에 알려주기 위한 JSON
	 * @return
	 */
	@RequestMapping(value = "engSound.do")
	@ResponseBody
	public Eng engSound(){
		System.out.println("알람 영어학습시 소리파일을 안드로이드에 알려주기 위한 JSON");
		
		Eng eng = andService.findTodayEng();
		
		return eng;
	}
	
	/**
	 * 당첨자 확인 페이지
	 * @return
	 */
	@RequestMapping(value = "adEntry.do")
	public ModelAndView adEntry(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("and/entry");
		return mv;
	}
	
	/**
	 * 이름,전화번호,이메일로 당첨자 확인하기
	 * @param entries
	 * @return
	 */
	@RequestMapping(value = "adEntryResult.do", method=RequestMethod.POST)
	@ResponseBody
	public List<SqlEntriesAd> adEntryResult(Entries entries){
		System.out.println(entries.toString());
		List<SqlEntriesAd> sqlEntriesAds = new ArrayList<SqlEntriesAd>();
		
		sqlEntriesAds = andService.findAdEntryResult(entries);
		
		return sqlEntriesAds;
	}
	
	
	/**
	 * 메인배경 이미지파일을 안드로이드에 알려주기 위한 JSON
	 * @return
	 */
	@RequestMapping(value = "mainbg.do")
	@ResponseBody
	public MainBg mainbg(){
		System.out.println("메인배경 이미지파일을 안드로이드에 알려주기 위한 JSON");
		
		MainBg mainbg = andService.findMaxMainBg();
		return mainbg;
	}
	
	/**
	 * 스타배경 이미지파일을 안드로이드에 알려주기 위한 JSON
	 * @return
	 */
	@RequestMapping(value = "starbg.do")
	@ResponseBody
	public StarBg starbg(){
		System.out.println("스타배경 이미지파일을 안드로이드에 알려주기 위한 JSON");
		
		StarBg starbg = andService.findMaxStarBg();
		return starbg;
	}
	
	/**
	 * 영어배경 이미지파일을 안드로이드에 알려주기 위한 JSON
	 * @return
	 */
	@RequestMapping(value = "engbg.do")
	@ResponseBody
	public EngBg engbg(){
		System.out.println("영어배경 이미지파일을 안드로이드에 알려주기 위한 JSON");
		
		EngBg engbg = andService.findMaxEngBg();
		return engbg;
	}
	
	
	/**
	 * 영어학습알람화면
	 * 윗부분
	 * @return
	 */
	@RequestMapping(value = "engToday.do")
	@ResponseBody
	public Eng engToday(){
		ModelAndView mv = new ModelAndView();
		
		//오늘의 예문과 뜻
		System.out.println("============== 오늘의 예문 찾기==============");
		Eng eng = andService.findTodayEng();
		
		return eng;
	}
	/**
	 * 영어학습알람화면
	 * 아랫부분
	 * @return
	 */
	@RequestMapping(value = "engThree.do")
	@ResponseBody
	public List<Eng> engThree(){
		System.out.println("============== 3일치 예문 찾기==============");
		
		//3개 예문과 뜻 읽어오기
		List<Eng> engs = andService.findThreeEng();
		
		return engs;
	}
	/**
	 * 영어학습알람화면
	 * 아랫부분 (오른쪽, 왼쪽 이동시 변경)
	 * @return
	 */
	@RequestMapping(value = "engThreeRe.do")
	@ResponseBody
	public List<Eng> engThreeRe(String eng_seq, String orien){
		System.out.println("============== 3일치 예문 찾기 왼쪽, 오른쪽 이동시 ==============");
		System.out.println(eng_seq + ", " + orien);
		//3개 예문과 뜻 읽어오기
		List<Eng> engs = andService.findThreeEngRe(eng_seq, orien);
		
		return engs;
	}
	
}
