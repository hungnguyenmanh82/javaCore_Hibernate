package hung.com.CRUD.select;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;
import hung.com.table.Employee2;

/**
 https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App16_get_Employee2 {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			Employee2 emp = session.get(Employee2.class, new Long(7499)); //id = 7499
			System.out.println("name = " + emp.getEmpName() + ", no = " + emp.getEmpNo()+ ", Job = " + emp.getJob());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
