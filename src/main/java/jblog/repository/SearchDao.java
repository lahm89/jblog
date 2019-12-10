package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<BlogVo> selectByTitle(String keyword) {
		List<BlogVo> list = sqlSession.selectList("blog.selectByTitle", keyword);
		return list;				
	}
	
	public List<BlogVo> selectById(String keyword) {
		List<BlogVo> list = sqlSession.selectList("blog.selectByKeyword", keyword);
		return list;				
	}
}
