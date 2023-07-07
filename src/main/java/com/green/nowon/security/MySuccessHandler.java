package com.green.nowon.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache =new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String targetUrl="/";
		//인증이 필요한 url요청시 인증되지 않는 사용자는 로그인페이지로 redirect된다.
		//인증이 정상적으로 완료되면 원래 이동하고자하는 url로 이동해야 하므로 그 정보를 저장해주는 객체
		//security 적용 됐을때
		SavedRequest savedRequest = this.requestCache.getRequest(request, response);
		clearAuthenticationAttributes(request);
		
		if(savedRequest!=null){
				
				String redirectUrl=savedRequest.getRedirectUrl(); //따로 설정 안 하면 default가 "/"이다
				if(!redirectUrl.contains("/login")) {
					getRedirectStrategy().sendRedirect(request, response, targetUrl);
				return;
			}
		}
		
		//Security가 redirect하지 않고 로그인을 직접 클릭했을때 
		//savedRequest가 null이므로 나머지 유형 처리를 위해 HttpSession을 이용해서 처리함.
		//Controller에서 로그인 페이지 이동하는 매핑메서드에서 session을 저장하여야 한다.
		
		HttpSession session=request.getSession();
		String prevPage=(String)session.getAttribute("prevPage");
		if(prevPage !=null) {
			targetUrl=prevPage;
			session.removeAttribute("prevPage");
			this.requestCache.removeRequest(request, response);
		}
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);

	}

	

}
