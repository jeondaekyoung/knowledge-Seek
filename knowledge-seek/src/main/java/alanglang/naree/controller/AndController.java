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
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.SqlEntriesAd;
import alanglang.naree.service.AndService;

@Controller
@RequestMapping("and")
public class AndController {
	
	@Autowired
	private AndService andService;

	/**
	 * 영어학습알람화면
	 * @return
	 */
	@RequestMapping(value = "eng.do")
	public ModelAndView eng(){
		ModelAndView mv = new ModelAndView();
		
		//1. 오늘의 예문과 뜻
		System.out.println("============== 오늘의 예문 찾기==============");
		Eng eng = andService.findTodayEng();
		
		//2.  3일치 예문과 뜻 읽어오기
		List<Eng> engs = andService.findThreeEng();
		
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
	
	@RequestMapping(value = "adEntryResult.do", method=RequestMethod.POST)
	@ResponseBody
	public List<SqlEntriesAd> adEntryResult(Entries entries){
		System.out.println(entries.toString());
		List<SqlEntriesAd> sqlEntriesAds = new ArrayList<SqlEntriesAd>();
		
		sqlEntriesAds = andService.findAdEntryResult(entries);
		
		return sqlEntriesAds;
	}
	
	
	
}
