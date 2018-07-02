package zttc.itat.service;

import java.util.List;

import zttc.itat.dao.IUserDao;
import zttc.itat.model.Pager;
import zttc.itat.model.User;
import zttc.itat.model.UserException;

public class UserService implements IUserService {

	private IUserDao userDao;
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		User u =userDao.loadByUsername(user.getUsername());
		if(u!=null) throw new UserException("添加的用户已存在");
		userDao.add(u);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	@Override
	public Pager<User> find() {
		// TODO Auto-generated method stub
		return userDao.find();
	}


	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User u =userDao.loadByUsername(username);
		if(u==null)throw new UserException("登录用户不存在");
		if(!u.getPassword().equals(password))throw new UserException("用户密码不正确");
		return u;
	}

}
