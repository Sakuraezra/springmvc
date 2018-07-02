package zttc.itat.service;

import java.util.List;

import zttc.itat.model.Pager;
import zttc.itat.model.User;

public interface IUserService {
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public List<User> list();
	public Pager<User> find();
	public User login(String username,String password);
}
