package blog.csdn.net.mchenys.service.impl;

import blog.csdn.net.mchenys.dao.UserDao;
import blog.csdn.net.mchenys.dao.impl.UserDaoImpl;
import blog.csdn.net.mchenys.domain.User;
import blog.csdn.net.mchenys.service.UserService;
import blog.csdn.net.mchenys.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) throws Exception {
		UserDao dao = new UserDaoImpl();
		dao.add(user);
			
		// 发送验证邮箱
		try {
			MailUtils.sendMail(user.getEmail(),
					"账户注册成功,<a href='http://localhost/storev1/user?act=active&code=" + user.getCode() + "'>点击此激活</a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User active(String code) throws Exception {
		UserDao dao = new UserDaoImpl();
		User user = dao.getByCode(code);
		if(user!=null) {
			//更新状态
			user.setState(1);
			dao.update(user);
		}
		return user;
	}

	@Override
	public User checkUserName(String username) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.getByName(username);
	}

	@Override
	public User checkEmail(String email) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.getByEmail(email);
	}

}
