package blog.csdn.net.mchenys.service;

import java.util.List;

import blog.csdn.net.mchenys.domain.Category;

public interface CategoryService {

	List<Category> getCategorys() throws Exception;

}
