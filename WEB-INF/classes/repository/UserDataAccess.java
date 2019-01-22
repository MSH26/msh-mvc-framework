package repository;

import mvc.model.UserModel;
import orm.OrmModelConfiguration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserDataAccess{
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	
	public UserDataAccess(){
		sessionFactoryObj = OrmModelConfiguration.buildSessionFactory();
	}
	
	
	
	public Boolean insert(UserModel user) {
		try{
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();
			sessionObj.save(user);
			//sessionObj.clear();
			sessionObj.getTransaction().commit();
			return true;
		}
		catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			return false;
		}  finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	
	public UserModel getByUserName(String userName) {
		try {	
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();
            Query query =sessionObj.createQuery("from UserModel where username =:u").setParameter("u", userName);
            UserModel user = (UserModel)(query).uniqueResult();
			sessionObj.getTransaction().commit();
			return user;
		}
		catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			return null;
		}
		finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	public Boolean update(UserModel user, String userName) {
		try {	
			sessionObj = sessionFactoryObj.getCurrentSession();
			sessionObj.beginTransaction();
			
			Query query =sessionObj.createQuery("from UserModel where username =:u").setParameter("u", userName);
            UserModel userToUpdate = (UserModel)(query).uniqueResult();
			
			userToUpdate = user;
			
			sessionObj.clear();
			sessionObj.update(userToUpdate);
			sessionObj.flush();
			// sessionObj.createQuery("update UserModel set password=:pass").setParameter("pass", userName).executeUpdate();
			sessionObj.getTransaction().commit();
			
			System.out.println("Done!");
			return true;
		}
		catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			return false;
		}  finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	public Boolean delete(int userId) {
		try {
			sessionObj = sessionFactoryObj.getCurrentSession();
			sessionObj.beginTransaction();
			UserModel userObj = sessionObj.get(UserModel.class, userId);
			sessionObj.delete(userObj);
			sessionObj.getTransaction().commit();
			return true;
		}
		catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			return false;
		}  finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}