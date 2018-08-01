/*package blog.csdn.net.mchenys.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.csdn.net.web.domain.User;
import blog.csdn.net.web.service.UserService;
import blog.csdn.net.web.util.CookieUtils;
import blog.csdn.net.web.util.StringUtils;

*//**
 * 处理自动登录的Filter,当用户登录的时候勾选了自动登录,用户退出登录后,下次访问,会判断cookie中保存的用户名密码完成自动登录
 * 
 * @author mChenys
 *
 *//*
public class AutoLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 强转
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		
		System.out.println(httpServletRequest.getRequestURI() + ":AutoLoginFilter...");
		// 获取session域中保存的user
		User user =null;
		if(null !=session.getAttribute("user")&& 
				(user= (User) session.getAttribute("user")) ==null) {
			
			// 获取为null表示用户已经退出了登录
			// 避免刚刚退出登录的时候重定向到首页又被拦截了,所以从header中获取标记判断
			
			boolean isFromLogout = false;
			if(null !=session.getAttribute("isLogout")) {
				isFromLogout = (boolean) session.getAttribute("isLogout");
			}
			
			if (!isFromLogout) {
				// 判断用户访问的页面是否需要自动登录,这里除了访问登录和退出的请求都需要完成自动登录
				// 否则访问登录的永远都是上次勾选自动登录的账户,访问退出登录永远都退不了
				String path = httpServletRequest.getRequestURI();
				if (!path.contains("/login") && !path.contains("/logout")) {
					// 获取cookie中保存的账号和密码
					Cookie loginCookie = CookieUtils.getCookieByName("autoLogin", httpServletRequest.getCookies());
					if (null != loginCookie) {
						// 切割用户名和密码
						String username = loginCookie.getValue().split("#")[0];
						String password = loginCookie.getValue().split("#")[1];
						// 用户名需要解码
						username = URLDecoder.decode(username, "utf-8");
						// 尝试登录
						try {
							user = new UserService().login(username, password);
							System.out.println(username + ":自动登录成功...");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (null != user) {
							// 自动登录成功,保存到session域中,避免重复自动登录
							session.setAttribute("user", user);

						}
					}
				}
			}

		}
		// 重置下标记
		session.setAttribute("isLogout", false);
		// 发现
		chain.doFilter(httpServletRequest, response);

	}

	@Override
	public void destroy() {

	}

}
*/