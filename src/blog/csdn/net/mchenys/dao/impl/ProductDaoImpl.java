package blog.csdn.net.mchenys.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.xml.internal.bind.v2.util.DataSourceSource;

import blog.csdn.net.mchenys.dao.ProductDao;
import blog.csdn.net.mchenys.domain.Product;
import blog.csdn.net.mchenys.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot =1 order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public Product getById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid =? limit 1";
		return qr.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	@Override
	public String getCidById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=? limit 1";
		return (String) qr.query(sql, new ColumnListHandler("cid"), pid).get(0);
	}

	@Override
	public List<Product> findByCidLimit(int index, Integer pageSize, String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?,?";
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid, index, pageSize);
	}

	@Override
	public Integer findCountByCid(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long count = (Long) qr.query(sql, new ScalarHandler(), cid);
		return count.intValue();
	}

}
