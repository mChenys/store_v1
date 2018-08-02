package blog.csdn.net.mchenys.dao;

import blog.csdn.net.mchenys.domain.User;

public interface UserDao {

	void add(User user) throws Exception;

	User getByCode(String code) throws Exception;

	void update(User user) throws Exception;

}
