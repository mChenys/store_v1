package blog.csdn.net.mchenys.dao;

import java.util.List;

import blog.csdn.net.mchenys.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;
	Category getById(String cid)  throws Exception;

}
