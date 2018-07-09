package zttc.itat.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zttc.itat.dao.IUserDao;
import zttc.itat.dao.UserDao;
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
	/**
     * 分页查询 
     * @param currentPage 当前页号：现在显示的页数
     * @param pageSize 每页显示的记录条数
     * @return 封闭了分页信息(包括记录集list)的Bean
     * */

    @Override
    public PageBean<User> queryForPage(int currentPage,int pageSize) {
        // TODO Auto-generated method stub
    	PageBean<User> page = new PageBean<User>();        
        //总记录数
        int allRow = userDao.findCount();
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);  
        //分页查询结果集
        
    
        
        List<User> list = userDao.queryForPage(offset, pageSize); 

        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
    }

}
