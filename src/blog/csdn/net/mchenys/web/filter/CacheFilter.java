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
 * 控制静态资源缓存时间的filter
 * @author mChenys
 *
 */
@WebFilter(urlPatterns= {"*.png","*.jpg","*.bmp","*.gif","*.css"})
public class CacheFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//System.out.println("CacheFilter start 缓存静态资源");
		
		// 强制 类型 转换 
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		// 设置缓存多长时间就可以了 .......
		httpServletResponse.setHeader("cache-control", "max-age=3600");
		
		// 为了 避免 这里的 数字的乘积 过大溢出 , 所以 最好 将 这个时间 值 后 加上 一个 L, 声明 为 一个 long 值 .单位毫秒
		httpServletResponse.setDateHeader("expires", System.currentTimeMillis()+1000L*3600);
		
		// 放行
		chain.doFilter(httpServletRequest, httpServletResponse);
		
		
		//System.out.println("CacheFilter end 缓存静态资源");
	}
	public void destroy() {
	}
}