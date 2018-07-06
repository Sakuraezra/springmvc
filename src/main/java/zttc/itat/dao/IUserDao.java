package zttc.itat.dao;

import java.util.List;

import zttc.itat.model.User;

public interface IUserDao {
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public List<User> load(int id);
	public List<User> list();
	public List<User> findPage(int begin, int pageSize);
	public User loadByUsername(String username);
	public int findCount();
}
