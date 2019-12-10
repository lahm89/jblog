package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("blogDao")
public class BlogDaoImpl implements BlogDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertBlog(BlogVo vo) {
		int insertedCount = sqlSession.insert("blog.insert", vo);
		return insertedCount;
	}

	@Override
	public BlogVo selectById(String id) {
		BlogVo blogVo = sqlSession.selectOne("blog.selectById", id);
		return blogVo;
	}

	@Override
	public int insertCategory(CategoryVo vo) {
		int insertedCount = sqlSession.insert("category.insert", vo);
		return insertedCount;
	}

	@Override
	public int updateBlog(BlogVo vo) {
		int updatedCount = sqlSession.update("blog.update", vo);
		return updatedCount;
	}

	@Override
	public List<PostVo> selectPostList(Long cateNo) {
		List<PostVo> list = sqlSession.selectList("post.selectAll", cateNo);
		return list;
	}

	@Override
	public List<CategoryVo> selectCategoryList(Long userNo) {
		List<CategoryVo> list = sqlSession.selectList("category.selectAll", userNo);
		return list;
	}

	@Override
	public int insertPost(PostVo vo) {
		int insertedCount = sqlSession.insert("post.insert", vo);
		return insertedCount;
	}

	@Override
	public CategoryVo selectBycateNo(Long cateNo) {
		CategoryVo cateVo = sqlSession.selectOne("category.selectByCateNo", cateNo);
		return cateVo;
	}

	@Override
	public PostVo selectNewestPost(Long userNo) {
		PostVo postVo = sqlSession.selectOne("post.selectNewest", userNo);
		return postVo;
	}

	@Override
	public int deleteCategory(Long cateNo) {
		int deletedCount = sqlSession.delete("category.delete", cateNo);
		return deletedCount;
	}

	@Override
	public PostVo selectNewestPostOfCate(Long cateNo) {
		PostVo postVo = sqlSession.selectOne("post.selectNewsetOfCate", cateNo);
		return postVo;
	}

	@Override
	public PostVo selectByPostNo(Long postNo) {
		PostVo postVo = sqlSession.selectOne("post.selectByPostNo", postNo);
		return postVo;
	}

	@Override
	public List<PostVo> selectPostListByUserNo(Long userNo) {
		List<PostVo> list = sqlSession.selectList("post.selectAllByUserNo", userNo);
		return list;
	}

	@Override
	public int insertComment(CommentVo vo) {
		int insertedCount = sqlSession.insert("comment.insert", vo);
		return insertedCount;
	}

	@Override
	public List<CommentVo> selectCommentList(Long postNo) {
		List<CommentVo> list = sqlSession.selectList("comment.select", postNo);
		return list;
	}

	@Override
	public int deleteComment(Long cmtNo) {
		int deletedCount = sqlSession.delete("comment.delete", cmtNo);
		return deletedCount;
	}
}
