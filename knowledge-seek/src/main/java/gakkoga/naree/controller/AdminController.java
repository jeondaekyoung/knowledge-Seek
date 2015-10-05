package gakkoga.naree.controller;

import gakkoga.naree.db.domain.Ad;
import gakkoga.naree.db.domain.Admin;
import gakkoga.naree.db.domain.Eng;
import gakkoga.naree.db.domain.Entries;
import gakkoga.naree.service.AdminService;
import gakkoga.naree.service.CommonService;
import gakkoga.naree.util.exception.GakkoGaWebException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CommonService commonService;
	
	
	/**
	 * 알람앱(가꼬가(GakkoGa)) 서버인덱스가기
	 * @return
	 */
	@RequestMapping(value = "index.do")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("admin/login");
		return mv;
	}
	
	/**
	 * 로그인
	 * @param admin
	 * @param session
	 */
	@RequestMapping(value = "login.do", method=RequestMethod.POST)
	public ModelAndView login(Admin admin, HttpSession session){
		ModelAndView mv = new ModelAndView();
	
		Admin resultAdmin = adminService.login(admin.getAdmin_id(), admin.getAdmin_pw());
		session.setAttribute("adminName", resultAdmin.getAdmin_name());
		
		mv.setViewName("admin/index");
		return mv;
	}
	
	/**
	 * 광고 등록
	 * @param ad
	 * @return
	 */
	@RequestMapping(value = "adRegister.do", method=RequestMethod.POST)
	public ModelAndView adRegister(Ad ad){
		System.out.println("광고 등록 하기");
		System.out.println(ad.toString());
		ModelAndView mv = new ModelAndView();
		
		//1. 광고시퀀스 생성
		String ad_seq = adminService.nextAdSeq();
		System.out.println(ad_seq);
		ad.setAd_seq(ad_seq);
		
		if(ad.getAd_gubun().equals("I")){
			//2. 광고소리파일 저장
			MultipartFile adSoundFile = ad.getAd_sound_file();
			if(adSoundFile != null){
				String fileName = adSoundFile.getOriginalFilename();
				//업로드 파일이름
				ad.setAd_sound_name(fileName);
				String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
				//서버에 저장된 파일이름
				ad.setAd_sound_server(new StringBuilder(ad_seq).insert(6, "AS").append(fileNameExt).toString());
				
				//서버에 저장
				commonService.writeFile(adSoundFile, "sound", ad.getAd_sound_name(), ad.getAd_sound_server());
			}
			
			//3. 광고이미지파일 저장
			MultipartFile adImageFile = ad.getAd_image_file();
			if(adImageFile != null){
				String fileName = adImageFile.getOriginalFilename();
				//업로드 파일이름
				ad.setAd_image_name(fileName);
				String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
				//서버에 저장된 파일이름
				ad.setAd_image_server(new StringBuilder(ad_seq).insert(6, "AI").append(fileNameExt).toString());
				
				//서버에 저장
				commonService.writeFile(adImageFile, "image", ad.getAd_image_name(), ad.getAd_image_server());
			}
		}

		//빈문자열 체크
		if(ad.getAd_url().length() == 0){
			ad.setAd_url(null);
		}
		if(ad.getYoutube_addr().length() == 0){
			ad.setYoutube_addr(null);
		}
		
		//4. 테이블 저장
		System.out.println(ad.toString());
		int result = adminService.adRegister(ad);
		if(result == 1){
			System.out.println("광고등록에 성공하였습니다.");
		} else {
			System.out.println("광고등록에 실패하였습니다.");
		}
		
		mv.setViewName("admin/ad_reg");
		return mv;
	}
	
	/**
	 * 영어학습등록
	 * @param eng
	 * @return
	 */
	@RequestMapping(value = "engRegister.do", method=RequestMethod.POST)
	public ModelAndView engRegister(Eng eng){
		System.out.println("영어 등록 하기");
		System.out.println(eng.toString());
		ModelAndView mv = new ModelAndView();
		
		//1. 영어시퀀스 생성
		String eng_seq = adminService.nextEngSeq();
		System.out.println("생성된 eng_seq : " + eng_seq);
		eng.setEng_seq(eng_seq);
		
		//2. 영어소리파일 저장
		MultipartFile engSoundFile = eng.getEng_sound_file();
		if(engSoundFile != null){
			String fileName = engSoundFile.getOriginalFilename();
			//업로드 파일이름
			eng.setEng_sound_name(fileName);
			String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
			//서버에 저장된 파일이름
			eng.setEng_sound_server(new StringBuilder(eng_seq).insert(6, "ES").append(fileNameExt).toString());
			
			//서버에 저장
			commonService.writeFile(engSoundFile, "sound", eng.getEng_sound_name(), eng.getEng_sound_server());
			System.out.println("서버에 소리파일을 저장하였습니다.");
		}
		
		//3. 영어이미지파일 저장
		/*MultipartFile engImageFile = eng.getEng_image_file();
		if(engImageFile != null){
			String fileName = engImageFile.getOriginalFilename();
			//업로드 파일이름
			eng.setEng_image_name(fileName);
			String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
			//서버에 저장된 파일이름
			eng.setEng_image_server(new StringBuilder(eng_seq).insert(6, "EI").append(fileNameExt).toString());
			
			//서버에 저장
			commonService.writeFile(engImageFile, "image", eng.getEng_image_name() , eng.getEng_image_server());
		}*/
		
		
		//4. 테이블 저장
		System.out.println(eng.toString());
		int result = adminService.engRegister(eng);
		if(result == 1){
			System.out.println("영어학습등록에 성공하였습니다.");
		} else {
			System.out.println("영어학습등록에 실패하였습니다.");
		}
		
		mv.setViewName("admin/eng_reg");
		return mv;
	}
	
	/**
	 * 광고목록 검색
	 * @param ad_name
	 * @param company_name
	 * @param start_date
	 * @param end_date
	 * @return
	 */
	@RequestMapping(value = "searchAdList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Ad> searchAdList(Ad ad){
		System.out.println(ad.getAd_name() + ", " + ad.getCompany_name() + ", " + ad.getStart_date() + ", " + ad.getEnd_date());
		List<Ad> adList = new ArrayList<Ad>();
		
		adList = adminService.searchAdList(ad);
		return adList;
	}
	
	/**
	 * 광고이벤트에 응모자 보여주기
	 * @param ad_seq
	 * @return
	 */
	@RequestMapping(value = "popEntries.do")
	public ModelAndView popEntries(String ad_seq){
		System.out.println("ad_seq : " + ad_seq);
		ModelAndView mv = new ModelAndView();
		
		List<Entries> entriesList = adminService.searchEntries(ad_seq);
		
		mv.addObject("entriesList", entriesList);
		mv.setViewName("admin/pop_entries");
		return mv;
		}
	
	/**
	 * (응모식)이벤트에서 당첨자 저장하기
	 * @param entry_seqsY
	 * @param entry_seqsN
	 * @param coupon_numsY
	 * @return
	 */
	@RequestMapping(value = "sendWinner.do", method=RequestMethod.POST)
	@ResponseBody
	public String sendWinner(@RequestParam(value="entry_seqsY[]") String[] entry_seqsY
									, @RequestParam(value="entry_seqsN[]") String[] entry_seqsN
									, @RequestParam(value="coupon_numsY[]") String[] coupon_numsY){
		System.out.println(entry_seqsY.length + ", " + entry_seqsN.length + ", " + coupon_numsY.length);
		
		//응모자중에서 당첨자저장하기(비당첨자도 저장)
		
		//다시 저장할 수 없도록한다.
		//System.out.println(entry_seqsY[0]);
		String win_sepa = adminService.searchWinSepa(entry_seqsY[0]);
		System.out.println("win_sepa : " + win_sepa);
		if(win_sepa != null){
			return "finish event";
		}
		
		//당첨자등록
		Entries entries;
		for(int i=0; i<entry_seqsY.length; i++){
			//System.out.println(entry_seqsY[i] );
			//entry_seq, win_sepa, coupon_num,
			entries = new Entries();
			entries.setEntry_seq(entry_seqsY[i]);
			entries.setWin_sepa("Y");
			if(coupon_numsY[i].length() == 0){
				entries.setCoupon_num(null);
			} else {
				entries.setCoupon_num(coupon_numsY[i]);
			}
			//System.out.println(entries.toString());
			int result = adminService.registerWinSepa(entries);
		}
		
		//비당첨자 등록
		for(int i=0; i<entry_seqsN.length; i++){
			//System.out.println(entry_seqsN[i] );
			entries = new Entries();
			entries.setEntry_seq(entry_seqsN[i]);
			entries.setWin_sepa("N");
			int result = adminService.registerWinSepa(entries);
		}
				
		return "success";
	}
	
	
	/**
	 * 파일 업로드(서버에 파일을 저장한다.)
	 * @param multipartFile
	 */
/*	private void writeFile(MultipartFile multipartFile){
		OutputStream out = null;
		try {
			out = new FileOutputStream("C:\\fileupload\\image\\" + multipartFile.getOriginalFilename());
			BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
			byte[] buffer = new byte[8106];
			int read;
			while((read = bis.read(buffer)) > 0){
				out.write(buffer, 0, read);
			}
		} catch (IOException e){
			throw new GakkoGaWebException("파일 업로드에 실패하였습니다");
		} finally {
			IOUtils.closeQuietly(out);
		}
	}*/
	
}
