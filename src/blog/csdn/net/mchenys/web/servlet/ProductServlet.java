package blog.csdn.net.mchenys.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.csdn.net.mchenys.domain.PageBean;
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

	public String getByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		Integer pageSize = 12;
		String cid = request.getParameter("cid");
		
		PageBean bean = new ProductServiceImpl().getByPage(pageNo,pageSize,cid);
		request.setAttribute("pageBean", bean);
		return "/jsp/product_list.jsp";
	}
}
