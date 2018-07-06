package zttc.itat.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zttc.itat.model.User;

@Repository("userDao")
@Transactional

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
			//hibernateTemplate.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
			this.getHibernateTemplate().update(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
			User user = (User) this.load(id);
			this.getHibernateTemplate().delete(user);
	}

	@Override
	public List<User> load(int id) {
		// TODO Auto-generated method stub
			return (List<User>) this.getHibernateTemplate().load(User.class,id);
	}

	@Override
	public List<User> list() {
	//	userlist = (List<User>) hibernateTemplate.find("from User");
		userlist = (List<User>) this.getHibernateTemplate().find("from User");
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
*/	
	
	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return (User)this.getSessionFactory().getCurrentSession().createQuery("from User where username=?").setParameter(0,username).uniqueResult();
	//	return  (User) hibernateTemplate.find("from User where username = ? " ,username);
	}

	
	@Override
	public int findCount() {
		
		List<Object> list =(List<Object>)hibernateTemplate.find("select count(*) from User");
		if(list!=null && list.size()!=0)
		{
		Object obj=list.get(0);
		Long logj=(Long)obj;
		int count = logj.intValue();
		return count;
		}
		return 0;
	}

	
	
	public List<User> findPage(int begin, int pageSize) {
		//使用离线对象		
		DetachedCriteria criteria =DetachedCriteria.forClass(User.class);		
		List<User> list =(List<User>) hibernateTemplate.findByCriteria(criteria,begin,pageSize);
		return list;
	}

	
	
}
