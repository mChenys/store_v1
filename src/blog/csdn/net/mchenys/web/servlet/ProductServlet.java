package blog.csdn.net.mchenys.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.csdn.net.mchenys.domain.Product;
import blog.csdn.net.mchenys.service.ProductService;
import blog.csdn.net.mchenys.service.impl.ProductServiceImpl;

/**
 * 商品模块
 */
@WebServlet("/product")
public class ProductServlet extends BaseServlet {

	public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("pid");
		ProductService service = new ProductServiceImpl();
		Product product = service.getById(id);
		request.setAttribute("p", product);
		return "/jsp/product_info.jsp";
	}

}
