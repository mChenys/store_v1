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
		MailUtils.sendMail(user.getEmail(),
				"账户注册成功,<a href='http://localhost/storev1/user?act=active&code=" + user.getCode() + "'>点击此激活</a>");
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

}
