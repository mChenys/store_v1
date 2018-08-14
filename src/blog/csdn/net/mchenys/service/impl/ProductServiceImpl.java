package blog.csdn.net.mchenys.service.impl;

import java.util.List;

import blog.csdn.net.mchenys.dao.ProductDao;
import blog.csdn.net.mchenys.dao.impl.CategoryDaoImpl;
import blog.csdn.net.mchenys.dao.impl.ProductDaoImpl;
import blog.csdn.net.mchenys.domain.PageBean;
import blog.csdn.net.mchenys.domain.Product;
import blog.csdn.net.mchenys.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findNew() throws Exception {

		return new ProductDaoImpl().findNew();
	}

	@Override
	public List<Product> findHot() throws Exception {
		return new ProductDaoImpl().findHot();
	}

	@Override
	public Product getById(String id) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		Product p = dao.getById(id);
		String cid = dao.getCidById(id);
		p.setCategory(new CategoryDaoImpl().getById(cid));
		return p;
	}

	public PageBean getByPage(Integer pageNo, Integer pageSize, String cid) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = dao.findByCidLimit((pageNo - 1) * pageSize, pageSize, cid);
		Integer total = dao.findCountByCid(cid);
		return new PageBean<Product>(list, pageNo, pageSize, total);
	}

}
