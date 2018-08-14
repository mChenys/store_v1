package blog.csdn.net.mchenys.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import blog.csdn.net.mchenys.dao.CategoryDao;
import blog.csdn.net.mchenys.domain.Category;
import blog.csdn.net.mchenys.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	public Category getById(String cid)  throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid = ? limit 1" ;
		return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

}
