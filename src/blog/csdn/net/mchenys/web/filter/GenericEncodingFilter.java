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
 * 处理全站乱码问题的filter
 * @author mChenys
 *
 */
@WebFilter(urlPatterns="/*s")
public class GenericEncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//强转
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		System.out.println(httpServletRequest.getRequestURI() + ":GenericEncodingFilter...");
		//统一处理响应编码
		httpServletResponse.setCharacterEncoding("utf-8");
		
		//统一处理请求参数编码,使用增强后的request包装httpServletRequest
		MyHttpServletRequestWrapper req  = new MyHttpServletRequestWrapper(httpServletRequest);
		
		//放行
		chain.doFilter(req, httpServletResponse);
		
		
	}

	@Override
	public void destroy() {
		
	}

}
