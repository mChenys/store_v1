package blog.csdn.net.mchenys.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import blog.csdn.net.mchenys.dao.UserDao;
import blog.csdn.net.mchenys.domain.User;
import blog.csdn.net.mchenys.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * 添加用户
	 */
	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
	}

	/**
	 * 根据激活码查找用户
	 */
	@Override
	public User getByCode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where code = ? limit 1";
		return qr.query(sql, new BeanHandler<User>(User.class),code);
	}

	/**
	 * 更新用户
	 */
	@Override
	public void update(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,state=?,code=null where uid=?";
		qr.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),
				user.getBirthday(),user.getState(),user.getUid());
	}

	@Override
	public User getByName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select * from user where username=? limit 1";
		return qr.query(sql, new BeanHandler<User>(User.class),username);
	}

	@Override
	public User getByEmail(String email) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select * from user where email=? limit 1";
		return qr.query(sql, new BeanHandler<User>(User.class),email);
	}

}
