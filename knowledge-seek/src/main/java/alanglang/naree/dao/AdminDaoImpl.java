package alanglang.naree.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Admin;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.mapper.AdminMapper;
import alanglang.naree.util.factory.ConnectionFactory;


@Repository
public class AdminDaoImpl implements AdminDao {

	public Admin searchAdmin(String admin_id){
		System.out.println("AdminDaoImpl.searchAdmin()");
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Admin admin = null;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			admin = adminMapper.searchAdmin(admin_id);
		} finally {
			sqlSession.close();
		}
		return admin;
	}

	@Override
	public String selectEngSeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String engSeq = null;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			engSeq = adminMapper.selectEngSeq();
		}
		finally {
			sqlSession.close();
		}
		return engSeq;
	}

	@Override
	public int insertEng(Eng eng) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.insertEng(eng);
		}
		finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public String selectAdSeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String adSeq = null;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			adSeq = adminMapper.selectAdSeq();
		}
		finally {
			sqlSession.close();
		}
		return adSeq;
	}

	@Override
	public int insertAd(Ad ad) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.insertAd(ad);
		}
		finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public List<Ad> searchAdList(Ad ad) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Ad> adList = new ArrayList<Ad>();
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			adList = adminMapper.searchAdList(ad);
		}
		finally {
			sqlSession.close();
		}
		return adList;
	}

	@Override
	public List<Entries> selectEntries(String ad_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Entries> entriesList = new ArrayList<Entries>();
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			entriesList = adminMapper.selectEntries(ad_seq);
		}
		finally {
			sqlSession.close();
		}
		return entriesList;
	}

	@Override
	public String selectWinSepa(String entry_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String result_entry_seq = null;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result_entry_seq = adminMapper.selectWinSepa(entry_seq);
		}
		finally {
			sqlSession.close();
		}
		return result_entry_seq;
	}

	@Override
	public int updateWinSepa(Entries entries) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.updateWinSepa(entries);
		}
		finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
