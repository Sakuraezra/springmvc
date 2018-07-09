package zttc.itat.service;

import java.util.List;

import zttc.itat.model.PageBean;
import zttc.itat.model.User;

public interface IUserService {
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public List<User> load(int id);
	public List<User> list();	
	public User login(String username,String password);
	public PageBean<User> queryForPage(int currentPage, int pageSize);
}
