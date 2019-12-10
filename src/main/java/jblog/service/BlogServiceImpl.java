package jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogDao;
import jblog.repository.BlogVo;
import jblog.repository.CategoryVo;
import jblog.repository.CommentVo;
import jblog.repository.PostVo;

@Service("blogService")
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDao blogDao;

	@Override
	public boolean create(BlogVo vo) {
		int insertedCount = blogDao.insertBlog(vo);
		return 1 == insertedCount;
	}

	@Override
	public BlogVo selectById(String id) {
		BlogVo blogVo = blogDao.selectById(id);
		return blogVo;
	}

	@Override
	public boolean createCategory(CategoryVo vo) {
		int insertedCount = blogDao.insertCategory(vo);
		return 1 == insertedCount;
	}

	@Override
	public boolean modifyBlog(BlogVo vo) {
		int updatedCount = blogDao.updateBlog(vo);
		return 1 == updatedCount;
	}

	@Override
	public List<PostVo> getPostList(Long cateNo) {
		List<PostVo> list = blogDao.selectPostList(cateNo);
		return list;
	}

	@Override
	public List<CategoryVo> getCategoryList(String id) {
		BlogVo blogVo = blogDao.selectById(id);
		List<CategoryVo> list = blogDao.selectCategoryList(blogVo.getUserNo());
		// 카테고리 내 포스트 수 세팅
		for(CategoryVo cateVo : list) {
			int postCount = blogDao.selectPostList(cateVo.getCateNo()).size();
			cateVo.setPostCount(postCount);
		}
		return list;
	}

	@Override
	public boolean writePost(PostVo vo) {
		int insertedCount = blogDao.insertPost(vo);
		return 1 == insertedCount;
	}

	@Override
	public CategoryVo getCategorybyCateNo(Long cateNo) {
		CategoryVo cateVo = blogDao.selectBycateNo(cateNo);
		return cateVo;
	}

	@Override
	public PostVo getNewestPost(String id) {
		BlogVo blogVo = blogDao.selectById(id);
		PostVo postVo = blogDao.selectNewestPost(blogVo.getUserNo());	
		return postVo;
	}

	@Override
	public boolean removeCategory(Long cateNo) {
		int deletedCount = blogDao.deleteCategory(cateNo);
		return 1 == deletedCount;
	}

	@Override
	public PostVo getNewestPostOfCate(Long cateNo) {
		PostVo postVo = blogDao.selectNewestPostOfCate(cateNo);
		return postVo;
	}

	@Override
	public PostVo getPost(Long postNo) {
		PostVo postVo = blogDao.selectByPostNo(postNo);
		return postVo;
	}

	@Override
	public List<PostVo> getPostListAll(String id) {
		BlogVo blogVo = blogDao.selectById(id);
		List<PostVo> list = blogDao.selectPostListByUserNo(blogVo.getUserNo());
		return list;
	}

	@Override
	public boolean writeComment(CommentVo vo) {
		int insertedCount = blogDao.insertComment(vo);
		return 1 == insertedCount;
	}

	@Override
	public List<CommentVo> getCommentList(Long postNo) {
		List<CommentVo> list = blogDao.selectCommentList(postNo);
		return list;
	}

	@Override
	public boolean removeComment(Long cmtNo) {
		int deletedCount = blogDao.deleteComment(cmtNo);
		return 1 == deletedCount;
	}

}
