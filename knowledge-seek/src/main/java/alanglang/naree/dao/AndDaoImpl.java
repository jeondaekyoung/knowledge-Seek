package alanglang.naree.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import alanglang.naree.db.domain.Ad;
import alanglang.naree.db.domain.Eng;
import alanglang.naree.db.domain.Entries;
import alanglang.naree.db.domain.SqlEntriesAd;
import alanglang.naree.db.mapper.AndMapper;
import alanglang.naree.util.factory.ConnectionFactory;

@Repository
public class AndDaoImpl implements AndDao {

	@Override
	public String searchMaxEngSeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String eng_seq = null;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			eng_seq = andMapper.searchMaxEngSeq();
		} finally {
			sqlSession.close();
		}
		
		return eng_seq;
	}

	@Override
	public Eng findTodayEng() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Eng eng = null;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			eng = andMapper.findTodayEng();
		} finally {
			sqlSession.close();
		}
		
		return eng;
	}
	
	@Override
	public Eng findTodayEngByEngSeq(String eng_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Eng eng = null;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			eng = andMapper.findTodayEngByEngSeq(eng_seq);
		} finally {
			sqlSession.close();
		}
		
		return eng;
	}

	@Override
	public List<Eng> findThreeEngByEngSeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Eng> engs = null;
		try{
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			engs = andMapper.findThreeEngByEngSeq();
		} finally {
			sqlSession.close();
		}
		return engs;
	}
	
	@Override
	public List<Eng> findThreeEng() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Eng> engs = null;
		try{
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			engs = andMapper.findThreeEng();
		} finally {
			sqlSession.close();
		}
		return engs;
	}

	@Override
	public Ad findAd() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Ad ad = null;
		try{
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			ad = andMapper.findAd();
		} finally {
			sqlSession.close();
		}
		return ad;
	}

	@Override
	public int updateCallNum(Ad ad) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			result = andMapper.updateCallNum(ad);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public String selectEntrySeq() {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String entrySeq = null;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			entrySeq = andMapper.selectEntrySeq();
		}
		finally {
			sqlSession.close();
		}
		return entrySeq;
	}

	@Override
	public int insertEntries(Entries entries) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			result = andMapper.insertEntries(entries);
		}
		finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int selectEntries(Entries entries) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int noEntries;
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			noEntries = andMapper.selectEntries(entries);
		}
		finally {
			sqlSession.close();
		}
		return noEntries;
	}

	@Override
	public List<SqlEntriesAd> selectAdEntryResult(Entries entries) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<SqlEntriesAd> sqlEntriesAds = new ArrayList<SqlEntriesAd>();
		try {
			AndMapper andMapper = sqlSession.getMapper(AndMapper.class);
			sqlEntriesAds = andMapper.selectAdEntryResult(entries);
		}
		finally {
			sqlSession.close();
		}
		return sqlEntriesAds;
	}

}
