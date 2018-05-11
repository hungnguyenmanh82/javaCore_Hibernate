package hung.com.nativeSql;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;

/**
 https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App61_nativeSql2 {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {

			session.getTransaction().begin();

	         String sql = "SELECT * FROM EMPLOYEE";
	         
			 SQLQuery query = session.createSQLQuery(sql);
	         query.addEntity(Employee.class);
	         List<Employee> employees = query.list();

	         for (Employee employee : employees) {
				System.out.print("{id:" + employee.getEmpId());
				System.out.print(", name:" + employee.getEmpName());
				System.out.print(", No:" + employee.getEmpNo());
				System.out.print(", job: " + employee.getJob());
				System.out.println(", salary:" + employee.getSalary() + "}");
			}

			// lệnh query đã trả về trc khi commit()
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
