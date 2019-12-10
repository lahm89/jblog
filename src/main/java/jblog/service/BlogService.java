package jblog.service;

import java.util.List;

import jblog.repository.BlogVo;
import jblog.repository.CategoryVo;
import jblog.repository.CommentVo;
import jblog.repository.PostVo;

public interface BlogService {
	public boolean create(BlogVo vo);
	public boolean createCategory(CategoryVo vo);
	public BlogVo selectById(String id);
	public boolean modifyBlog(BlogVo vo);
	public List<PostVo> getPostList(Long cateNo);
	public List<CategoryVo> getCategoryList(String id);
	public boolean writePost(PostVo vo);
	public CategoryVo getCategorybyCateNo(Long cateNo);
	public PostVo getNewestPost(String id);
	public boolean removeCategory(Long cateNo);
	public PostVo getNewestPostOfCate(Long cateNo);
	public PostVo getPost(Long postNo);
	public List<PostVo> getPostListAll(String id);
	public boolean writeComment(CommentVo vo);
	public List<CommentVo> getCommentList(Long postNo);
	public boolean removeComment(Long cmtNo);
}
