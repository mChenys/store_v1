package blog.csdn.net.mchenys.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.csdn.net.mchenys.domain.Product;
import blog.csdn.net.mchenys.service.ProductService;
import blog.csdn.net.mchenys.service.impl.ProductServiceImpl;

/**
 * 首页
 */
@WebServlet("/index")
public class IndexServlet extends BaseServlet {

	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 去数据库中查询最新商品和热门商品 将他们放入request域中 请求转发
		ProductService service = new ProductServiceImpl();
		List<Product> newpList = service.findNew();
		List<Product> hotpList = service.findHot();

		request.setAttribute("nplist", newpList);
		request.setAttribute("hplist", hotpList);
		return "/jsp/index.jsp";
	}

}
