package jblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.exception.FileUploadException;
import jblog.repository.BlogVo;
import jblog.repository.CategoryVo;
import jblog.repository.CommentVo;
import jblog.repository.PostVo;
import jblog.repository.UserVo;
import jblog.service.BlogService;
import jblog.service.FileUploadService;


@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	FileUploadService fileUploadService;
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	// blog 메인 페이지
	@RequestMapping(value="/{id}")
	public String blogMain(@PathVariable String id, Model model, 
			@RequestParam(value="cateNo", required=false, defaultValue = "0") Long cateNo,
			@RequestParam(value="postNo",required=false, defaultValue = "0") Long postNo) {
		System.out.println("[postNo]:"+postNo +", [cateNo]:" + cateNo);
		BlogVo blogVo = blogService.selectById(id);
		List<CategoryVo> cateList = blogService.getCategoryList(id);
		List<PostVo> postList;
		PostVo postVo;
		String cateName = "전체";
		if(cateNo == 0 && postNo == 0) {
			// 모든 포스트 목록 불러오기
			postList = blogService.getPostListAll(id);
			postVo = blogService.getNewestPost(id);
		} else if(cateNo != 0 && postNo == 0) {
			postList = blogService.getPostList(cateNo);
			postVo = blogService.getNewestPostOfCate(cateNo);
			cateName = blogService.getCategorybyCateNo(cateNo).getCateName();
		} else if(cateNo == 0 && postNo != 0) {
			postList = blogService.getPostListAll(id);
			postVo = blogService.getPost(postNo);
		} else {
			postList = blogService.getPostList(cateNo);
			postVo = blogService.getPost(postNo);
			cateName = blogService.getCategorybyCateNo(cateNo).getCateName();
		}
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("postVo", postVo);
		model.addAttribute("postList", postList);
		model.addAttribute("cateList", cateList);
		model.addAttribute("categoryName", cateName);
		model.addAttribute("categoryNo",cateNo);
		
		return "blog/blogmain";
	}
	
	// comment 출력하기
	@RequestMapping("/getcomment")
	public String getComment(@RequestParam Long postNo, Model model) {
		List<CommentVo> commentList = blogService.getCommentList(postNo);
		model.addAttribute("postNo", postNo);
		model.addAttribute("commentList", commentList);
		return "blog/comment";
	}
	// comment 등록하기
	@RequestMapping(value="/writecomment", method=RequestMethod.POST)
	public String writeComment(@ModelAttribute CommentVo cmtVo, HttpServletRequest request) {
		String referer = request.getHeader("referer");
		// TODO : 댓글내용 NULL 체크
		if(cmtVo.getCmtContent().isEmpty()) {
			return "redirect:" + referer;
		}
		boolean isSuccess = blogService.writeComment(cmtVo);
		if(!isSuccess) {
			logger.debug("[코멘트 등록 실패]");
		}
		return "redirect:" + referer;
	}
	// comment 삭제하기
	@RequestMapping(value="/deletecomment/{cmtNo}")
	public String deleteComment(@PathVariable Long cmtNo, HttpServletRequest request) {
		boolean isSuccess = blogService.removeComment(cmtNo);
		if(!isSuccess) {
			logger.debug("[코멘트 삭제 실패]");
		}
		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}
	
	// blog 설정 기본
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		BlogVo blogVo = blogService.selectById(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/adminbasic";
	}
	// blog 기본 설정 변경하기
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.POST)
	public String adminBasicAction(@ModelAttribute BlogVo blogVo, @PathVariable String id,@RequestParam("uploadFile") MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				String logoFile = fileUploadService.store(file);
				blogVo.setLogoFile(logoFile);
			}catch(FileUploadException e) {
				logger.debug(e.getMessage() + e.toString());
			}
		}
		// TODO : 블로그 제목 널체크 -> validator로 변경해보기
		if(blogVo.getBlogTitle() == null) {
			// 블로그 제목 null일 경우 페이지로 돌려보내기
			return "redirect:/{id}/admin/basic";
		}
		boolean isSuccess = blogService.modifyBlog(blogVo);
		if(!isSuccess) {
			logger.debug("[블로그 설정 변경 실패]");
		}
		return "redirect:/{id}/admin/basic";
	}
	
	// blog 카테고리 설정 기본
	@RequestMapping(value="/{id}/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable String id, Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		BlogVo blogVo = blogService.selectById(id);
		List<CategoryVo> cateList = blogService.getCategoryList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateList", cateList);
		return "blog/admincategory";
	}
	// blog 카테고리 추가 액션
	@RequestMapping(value="/{id}/admin/category", method=RequestMethod.POST)
	public String adminCategoryAction(@PathVariable String id, @ModelAttribute CategoryVo cateVo) {
		BlogVo blogVo = blogService.selectById(id);
		cateVo.setUserNo(blogVo.getUserNo());
		cateVo.setPostCount(0);
		// TODO : 카테고리 제목 널체크
		if(cateVo.getCateName().isEmpty()) {
			return "redirect:/{id}/admin/category";
		}
		boolean isSuccess = blogService.createCategory(cateVo);
		if(!isSuccess) {
			logger.debug("[카테고리 추가 실패]");
		}
		return "redirect:/{id}/admin/category";
	}
	// blog 카테고리 삭제 액션
	@RequestMapping("/{id}/admin/category/{cateNo}")
	public String removeCategory(@PathVariable String id,@PathVariable Long cateNo) {
		boolean isSuccess = blogService.removeCategory(cateNo);
		if(!isSuccess) {
			logger.debug("[카테고리 삭제 실패]");
		}
		return "redirect:/{id}/admin/category";
	}
	
	// post 작성 폼
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.GET)
	public String postForm(@PathVariable String id, Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		BlogVo blogVo = blogService.selectById(id);
		List<CategoryVo> cateList = blogService.getCategoryList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateList", cateList);
		return "blog/postform";
	}
	// post 추가 액션
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.POST)
	public String postAction(@PathVariable String id, @ModelAttribute PostVo postVo) {
		String content = postVo.getPostContent().replace("\n", "<br/>");
		postVo.setPostContent(content);
		// TODO : post 제목 널체크
		if(postVo.getPostTitle().isEmpty()) {
			return "redirect:/{id}/admin/write";
		}
		boolean isSuccess = blogService.writePost(postVo);
		if(!isSuccess) {
			logger.debug("[포스트 등록 실패]");
		}
		return "redirect:/{id}";
	}
}
