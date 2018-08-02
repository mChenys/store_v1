package blog.csdn.net.mchenys.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理全站乱码问题的filter
 * 
 * @author mChenys
 *
 */
@WebFilter(urlPatterns = "/*")
public class GenericEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("GenericEncodingFilter start 决解编码问题");
		
		// 强转
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 统一处理响应编码
		httpServletResponse.setCharacterEncoding("utf-8");

		// 统一处理请求参数编码,使用增强后的request包装httpServletRequest
		MyHttpServletRequestWrapper req = new MyHttpServletRequestWrapper(httpServletRequest);

		// 放行
		chain.doFilter(req, httpServletResponse);

		System.out.println("GenericEncodingFilter end 决解编码问题");
	}

	@Override
	public void destroy() {

	}

}

/**
 * HttpServletRequest增强类
 * 
 * @author mChenys
 *
 */
class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		if(name==null || name.trim().length()==0){
			return null;
		}
		String[] values = this.getParameterValues(name);
		if(values==null || values.length==0){
			return null;
		}
		
		return values[0];
	}

	@Override
	public String[] getParameterValues(String name) {
		if(name==null || name.trim().length()==0){
			return null;
		}
		Map<String, String[]> map = this.getParameterMap();
		if(map==null || map.size()==0){
			return null;
		}
		return map.get(name);
	}

	private boolean hasEncode;// 避免重复编码

	/**
	 * 上面2个方法最终是调用这个方法,所有统一在这个方法处理请求参数乱码问题
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		
		String method = request.getMethod();
		try {
			if (method.equalsIgnoreCase("post")) {
				// post请求,直接设置这句话即可
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} else if (method.equalsIgnoreCase("get")) {
				Map<String,String[]> map = request.getParameterMap();
				// 通过遍历参数名获取说有的参数值
				if (!hasEncode) {
					hasEncode = true;
					for (String name : map.keySet()) {
						String[] values = map.get(name);
						for (int i = 0; i < values.length; i++) {
							// 在这里处理编码
							values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8").trim();
						}
					}
				}
				return map;
			}
		} catch (Exception e) {
		}
		return super.getParameterMap();
	}
}
