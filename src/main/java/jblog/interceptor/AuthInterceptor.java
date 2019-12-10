package jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jblog.repository.UserVo;



public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVo authUser = null;
		if(session != null) {
			authUser =(UserVo)session.getAttribute("authUser");
		}
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/users/login");
			return false;
		}
		return true;
	}


}
