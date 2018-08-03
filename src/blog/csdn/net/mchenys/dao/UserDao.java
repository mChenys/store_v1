package blog.csdn.net.mchenys.dao;

import blog.csdn.net.mchenys.domain.User;

public interface UserDao {

	void add(User user) throws Exception;

	void update(User user) throws Exception;

	User getByColumnAndVlaue(String column,String value) throws Exception;

	User getByNameAndPassword(String username, String password) throws Exception;

}
