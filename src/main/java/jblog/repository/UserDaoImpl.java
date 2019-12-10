package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.exception.UserDaoException;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(UserVo vo) {
		int insertedCount = 0;
		try{
			insertedCount = sqlSession.insert("user.insert", vo);
		} catch(Exception e) {
			throw new UserDaoException("가입 중 오류 발생", vo);
		}
		return insertedCount;
	}

	@Override
	public UserVo selectUser(String id) {
		UserVo vo = sqlSession.selectOne("user.selectById", id);
		return vo;
	}

	@Override
	public UserVo selectUser(String id, String password) {
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		UserVo user = sqlSession.selectOne("user.selectByIdAndPassword", vo);
		return user;
	}

}
