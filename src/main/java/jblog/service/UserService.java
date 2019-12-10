package jblog.service;

import jblog.repository.UserVo;

public interface UserService {
	public boolean join(UserVo vo);
	public boolean createBlog(String id);
	public UserVo getUser(String id);
	public UserVo getUser(String id, String password);
}
