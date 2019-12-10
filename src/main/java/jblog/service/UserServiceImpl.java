package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogVo;
import jblog.repository.CategoryVo;
import jblog.repository.UserDao;
import jblog.repository.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BlogService blogService;
	@Override
	public boolean createBlog(String id) {
		UserVo userVo = this.getUser(id);
		BlogVo blogVo = new BlogVo();
		blogVo.setUserNo(userVo.getUserNo());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그 입니다.");
		blogVo.setLogoFile("defaultLogo.jpg");
		blogService.create(blogVo);
		// 디폴트 카테고리 생성
		CategoryVo cateVo = new CategoryVo();
		cateVo.setPostCount(0);
		cateVo.setCateName("미분류");
		cateVo.setUserNo(userVo.getUserNo());
		cateVo.setDescription("기본으로 만들어지는 카테고리 입니다.");
		blogService.createCategory(cateVo);
		return false;
	}
	
	@Override
	public boolean join(UserVo vo) {
		int insertedCount = userDao.insert(vo);
		return 1 == insertedCount;
	}

	@Override
	public UserVo getUser(String id) {
		UserVo vo = userDao.selectUser(id);
		return vo;
	}

	@Override
	public UserVo getUser(String id, String password) {
		UserVo vo = userDao.selectUser(id, password);
		return vo;
	}

}
