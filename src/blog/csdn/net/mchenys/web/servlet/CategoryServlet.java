package blog.csdn.net.mchenys.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.csdn.net.mchenys.domain.Category;
import blog.csdn.net.mchenys.service.CategoryService;
import blog.csdn.net.mchenys.service.impl.CategoryServiceImpl;
import blog.csdn.net.mchenys.utils.JsonUtil;
import net.sf.json.JSONArray;

/**
 * 导航栏目模块
 */
@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
	//查询所有分类
	public  String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		CategoryService service = new CategoryServiceImpl();
		List<Category> list = service.getCategorys();
		if(null !=list) {
			response.getWriter().println(JsonUtil.list2json(list));
		}
		return null;
	}

}
