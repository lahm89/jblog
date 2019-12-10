package jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jblog.exception.UserDaoException;
import jblog.repository.UserVo;
import jblog.service.UserService;



@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BlogController blogController;
	
	// 가입 폼
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(HttpSession session) {
		UserVo authUser = null;
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authUser");
		}
		if(authUser != null) {
			return "redirect:/";
		}
		return "users/joinform";
	}
	
	// 가입 진행
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "users/joinform";
		}
		boolean isSuccess = false;
		try {
			isSuccess = userService.join(vo);
			userService.createBlog(vo.getId());
		} catch(UserDaoException e) {
			System.err.println("UserDao 오류:" + e.getMessage());
			System.err.println("UserVo 정보:" + e.getVo());
		}
		if(isSuccess) {
			return "redirect:/users/joinsuccess";
		} else {
			return "redirect:/users/join";
		}
	}
	
	// 가입 성공
	@RequestMapping("/joinsuccess")
	public String joinSuccess(HttpSession session) {
		UserVo authUser = null;
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authUser");
		}
		if(authUser != null) {
			return "redirect:/";
		}
		return "users/joinsuccess";
	}
	
	// 로그인 폼
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("redirectURI", referer);
		return "users/loginform";
	}
	
	// 로그인 진행
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(@RequestParam(value="id", required=false) String id,
			@RequestParam(value="password", required=false) String password, HttpSession session, Model model) {
		if(id.length() == 0|| password.length() == 0) {
			model.addAttribute("errorMessage", "아이디/패스워드를 입력해 주세요");
			return "users/loginform";
		}
		UserVo authUser = userService.getUser(id, password);
		if(authUser == null) {
			// 로그인실패
			model.addAttribute("errorMessage", "아이디/패스워드를 확인해 주세요");
			return "users/loginform";
		} else {
			session.setAttribute("authUser", authUser);
			
			return "redirect:" + session.getAttribute("redirectURI");
		}
	}
	
	// 로그아웃 진행
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutAction(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return "redirect:" + request.getHeader("referer");
	}
	
	// 이메일 체크
	@ResponseBody
	@RequestMapping("/checkId")
	public Object checkId(@RequestParam String id) {
		UserVo vo = userService.getUser(id);
		boolean isExists = vo != null;
		Map<String, Object> map = new HashMap<>();
		map.put("result","success");
		map.put("exists", isExists);
		return map;
	}
}
