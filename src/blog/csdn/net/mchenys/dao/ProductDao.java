package blog.csdn.net.mchenys.dao;

import java.util.List;

import blog.csdn.net.mchenys.domain.Product;

public interface ProductDao {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getById(String id) throws Exception;

	String getCidById(String id) throws Exception;
}
