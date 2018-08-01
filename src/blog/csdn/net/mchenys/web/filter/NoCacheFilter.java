package blog.csdn.net.mchenys.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 禁止缓存filter
 * @author mChenys
 *
 */
@WebFilter(urlPatterns="*.jsp")
public class NoCacheFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 强转request和response对象
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		System.out.println(httpServletRequest.getRequestURI()+":NoCacheFilter...");
		// 这三个头 设置 浏览器 不要 缓存
		httpServletResponse.setHeader("cache-control", "no-cache");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0); // 设置 为 0 就表示 1970 0 点0 分0 秒

		// 放行
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

	public void destroy() {

	}

}