package hung.com.CRUD.select.criteriaJPA;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;

/**
 https://www.boraji.com/hibernate-5-criteria-query-example

 */
public class App17_select_WHERE {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();
			//==================================================
			// Create CriteriaBuilder
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			//================== SELECT * FROM employee WHERE EMP_ID=1;
			Root<Employee> root = query.from(Employee.class);
			query.select(root).where(builder.equal(root.get("empId"), 7499));
			Query<Employee> q=session.createQuery(query);

			//=============================================
			Employee employee=q.getSingleResult();

			System.out.println(employee.getEmpName());


			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
