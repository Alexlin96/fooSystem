package com.pure.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.filter.OncePerRequestFilter;

import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;


public class SecurityFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 不过滤的uri
		String[] notFilter = new String[] { "login.jsp", "index.jsp",
				"login.do", "logout.do",
		};
		// 请求的uri
		String uri = request.getRequestURI();

		// uri中包含background时才进行过滤

		// 是否过滤
		boolean doFilter = true;
		for (String s : notFilter) {
			if (uri.indexOf(s) != -1) {
				// 如果uri中包含不过滤的uri，则不进行过滤
				doFilter = false;
				break;
			}
		}
		if (doFilter) {
			// 执行过滤
			// 从session中获取登录者实体
			Object obj = request.getSession().getAttribute("user");
			if (null == obj) {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				ReturnData returnData = new ReturnData();
				returnData.setCode(SysCodeMsg.CODE_10002);
				returnData.setMessage(SysCodeMsg.MSG_10002);
				out.print(JSONObject.fromObject(returnData));
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			// 如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}
	}

}
