package jblog.repository;

public interface UserDao {
	public int insert(UserVo vo);	// 가입 INSERT
	public UserVo selectUser(String id);	// id 중복체크
	public UserVo selectUser(String id, String password);	// 로그인 SELECT
}
