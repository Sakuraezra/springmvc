package zttc.itat.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import zttc.itat.model.Pager;
import zttc.itat.model.SystemContext;
import zttc.itat.model.User;

@Repository("userDao")
public class UserDao extends HibernateDaoSupport implements IUserDao {
	private HibernateTemplate hibernateTemplate;
	List<User> userlist = null;
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
			this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
			this.getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
			this.getHibernateTemplate().update(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
			User user = this.load(id);
			this.getHibernateTemplate().delete(user);
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
			return (User) this.getHibernateTemplate().load(User.class,id);
	}

	@Override
	public List<User> list() {
		userlist = (List<User>) hibernateTemplate.find("from User");
		System.out.println(userlist.toString());
		return userlist;
	}
/*
	@SuppressWarnings("unchecked")
	@Override
	public Pager<User> find() {
		// TODO Auto-generated method stub
		int size = SystemContext.getSize();
		int offset = SystemContext.getOffset();
		Query query = (Query) this.getSession().createQuery("from User");
		query.setFirstResult(offset).setMaxResults(size);
		List<User> datas = query.list();
		Pager<User> us = new Pager<User>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (long) this.getSession().createQuery("select count(*) From User").uniqueResult();
		us.setTotal(total);	
		return us;
	}
	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return (User) this.getSession().createQuery("from User where username=?").setParameter(0,username).uniqueResult();
	}
*/

	@Override
	public Pager<User> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
