package zttc.itat.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zttc.itat.dao.IUserDao;
import zttc.itat.model.PageBean;
import zttc.itat.model.User;
import zttc.itat.model.UserException;


@Transactional
@Service("userService")
public class UserService implements IUserService {

	
	private IUserDao userDao;
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		User u =userDao.loadByUsername(user.getUsername());
		if(u!=null)	{
			throw new UserException("添加的用户已存在");			
		}
		userDao.add(user);
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

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> load(int id) {
		// TODO Auto-generated method stub
		return (List<User>) userDao.load(id);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}



	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User u =(User) userDao.loadByUsername(username);
		if(u==null)throw new UserException("登录用户不存在");
		if(!u.getPassword().equals(password))throw new UserException("用户密码不正确");
		return u;
	}

	

	public PageBean listPage(Integer currentPage) {
		//创建PageBean 对象
		PageBean pageBean = new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);		
		//每页显示记录数
		int pageSize = 3;
		int totalpage = 0;
		//总页数    ==  总记录数/每页记录数
		if(totalCount%pageSize==0)
		{
			totalpage = totalCount/pageSize;
		}else{
			totalpage=totalCount/pageSize+1;
		}	
		pageBean.setTotalPage(totalpage);
		//开视位置
		int begin=(currentPage-1)*pageSize;
		//每页记录list
		List<User> list = userDao.findPage(begin,pageSize);
		pageBean.setList(list);
				
		return pageBean;
		
		
	}

}
