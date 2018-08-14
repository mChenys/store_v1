package blog.csdn.net.mchenys.service;

import java.util.List;

import blog.csdn.net.mchenys.domain.Product;

public interface ProductService {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getById(String id) throws Exception;
}
