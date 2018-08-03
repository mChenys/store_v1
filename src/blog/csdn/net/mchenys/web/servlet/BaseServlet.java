package blog.csdn.net.mchenys.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基类
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 获取请求参数中的act操作,每一个act的值对应 一个Servlet的方法
			String action = request.getParameter("act");
			if (action == null) {
				action = "index";// 让其执行默认的方法,即跳去首页
			}
			System.out.println(this.getClass().getSimpleName()+"执行方法:"+action);
			// 通过反射执行对应的方法
			Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);

			// 方法执行后返回需要转发的路径
			String route = (String) method.invoke(this, request, response);
			
			//判断是需要转发请求
			if (null != route) {
				System.out.println("转发页面:"+route);
				request.getRequestDispatcher(route).forward(request, response);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
	//默认的方法
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//empty 由子类去实现跳去首页的方法
		return null;
	}
}
