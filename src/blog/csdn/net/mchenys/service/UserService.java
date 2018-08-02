package blog.csdn.net.mchenys.service;

import blog.csdn.net.mchenys.domain.User;

public interface UserService {

	void regist(User user) throws Exception;

	User active(String code) throws Exception;

	User checkUserName(String username) throws Exception;

	User checkEmail(String email) throws Exception;

}
