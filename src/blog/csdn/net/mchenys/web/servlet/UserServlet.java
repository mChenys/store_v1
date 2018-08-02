package blog.csdn.net.mchenys.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import blog.csdn.net.mchenys.convert.DateConverter;
import blog.csdn.net.mchenys.domain.User;
import blog.csdn.net.mchenys.service.UserService;
import blog.csdn.net.mchenys.service.impl.UserServiceImpl;
import blog.csdn.net.mchenys.utils.MD5Utils;
import blog.csdn.net.mchenys.utils.UUIDUtils;

/**
 * 用户模块
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳去注册页面
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	
	
	/**
	 * 注册操作
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("sex:"+request.getParameter("sex"));
		System.out.println("name:"+request.getParameter("name"));
		//创建user
		User user = new User();
		
		//BeanUtils处理日期格式的参数需要注册转换器
		ConvertUtils.register(new DateConverter(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());
		
		//加密密码
		user.setPassword(MD5Utils.md5(user.getPassword()));
		//设置uid
		user.setUid(UUIDUtils.getId());
		//设置激活码
		user.setCode(UUIDUtils.getCode());
		
		//注册
		UserService service = new UserServiceImpl();
		service.regist(user);
		
		//注册成功后转发
		request.setAttribute("msg", "注册成功,请打开邮箱激活");
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 激活账户操作
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		
		UserService service = new UserServiceImpl();
		
		User user = service.active(code);
		
		if(user==null) {
			request.setAttribute("msg", "激活失败,请重新激活");
		}else {
			request.setAttribute("msg", "激活成功");
		}
		return "/jsp/msg.jsp";
		
	}
	/**
	 * 检查用户是否已经占用
	 */
	public String checkName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		UserService service = new UserServiceImpl();
		User user = service.checkUserName(username);
		response.getWriter().println(user==null?"true":"false");//用户不存在返回"true",存在返回"false"
		return null;
	}
	
	/**
	 * 检查邮箱是否已经占用
	 */
	public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		UserService service = new UserServiceImpl();
		User user = service.checkEmail(email);
		response.getWriter().println(user==null?"true":"false");//用户不存在返回"true",存在返回"false"
		return null;
	}
	/**
	 * 检查验证码
	 */
	public String checkCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rCode=request.getParameter("code");
		String sCode=(String) request.getSession().getAttribute("sessionCode");
		System.out.println("rCode:"+rCode+" sCode:"+sCode);
		//一次性验证码 用完之后从session中移除
		if(sCode.equalsIgnoreCase(rCode)) {
			request.getSession().removeAttribute("sessionCode");
			response.getWriter().println("true");
		}else {
			response.getWriter().println("false");//验证码正确返回"true",否则返回"false"
		}
		
		return null;
	}
}
