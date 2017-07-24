package db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class LevelSolutionManager 
{
	private SessionFactory factory;
	
	public LevelSolutionManager() 
	{
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	
	@SuppressWarnings("unchecked")
	public List<LevelSolution> getAllSolutions() 
	{
		List<LevelSolution> solutions = null;
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			solutions = session.createQuery("from Solutions").list();
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return solutions;
	}
	
	public void addSolution(LevelSolution emp) 
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			session.save(emp);
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean SolutionisExist(String LevelName)
		{
			Session session = null;
			Transaction tx = null;

			try
			{
				session = factory.openSession();
				tx = session.beginTransaction();

			    Criteria criteria = session.createCriteria(LevelSolution.class);
			    criteria.add(Restrictions.eq("level_name", LevelName));
			    criteria.setProjection(Projections.rowCount());
			    long count = (Long) criteria.uniqueResult();
			    tx.commit();

			   if(count != 0)
			       return true;

			   else
				   return false;
			}

			catch (HibernateException ex)
			{
				if (tx != null)
					tx.rollback();
				System.out.println(ex.getMessage());
			}

			finally
			{
				if (session != null)
					session.close();
			}
			return true;
		}
}