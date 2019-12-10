package jblog.repository;

import java.util.List;

public interface BlogDao {
	public int insertBlog(BlogVo vo);						// 블로그 생성
	public BlogVo selectById(String id);					// id로 블로그 찾기
	public int insertCategory(CategoryVo vo);				// 카테고리 생성
	public int updateBlog(BlogVo vo);						// 블로그 기본정보 수정
	public List<PostVo> selectPostList(Long cateNo);		// 카테고리번호로 포스트 리스트 불러오기
	public List<CategoryVo> selectCategoryList(Long userNo);// 유저번호로 카테고리 리스트 불러오기
	public int insertPost(PostVo vo);						// 포스트 생성
	public CategoryVo selectBycateNo(Long cateNo);			// 카테고리 번호로 카테고리 찾기
	public PostVo selectNewestPost(Long userNo);			// 최신 포스트 불러오기
	public int deleteCategory(Long cateNo);					// 카테고리 삭제
	public PostVo selectNewestPostOfCate(Long cateNo);		// 카테고리 내 최신 포스트
	public PostVo selectByPostNo(Long postNo);				// 포스트번호로 포스트 불러오기
	public List<PostVo> selectPostListByUserNo(Long userNo);// 유저번호로 포스트 리스트 불러오기
	public int insertComment(CommentVo vo);					// 코멘트 저장
	public List<CommentVo> selectCommentList(Long postNo);	// 코멘트 리스트 불러오기
	public int deleteComment(Long cmtNo);					// 코멘트 삭제
}
