package hung.com.CRUD.select.criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;

/**
 https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App17_select_like {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();
			//===================================================================================================
			//Criteria là cách khác để dùng lệnh Select
			Criteria cr = session.createCriteria(Employee.class); //truy vấn table Employee
/*			cr.add(Restrictions.eq("salary", new Float(1000))); //salary là 1 field trong Employee class
			// To get records having salary more than 2000
			cr.add(Restrictions.gt("salary", 2000));

			// To get records having salary less than 2000
			cr.add(Restrictions.lt("salary", 2000));*/

			// To get records having fistName starting with zara
			cr.add(Restrictions.like("empName", "SM%"));  //empName = SMITH

/*			// Case sensitive form of the above restriction.
			cr.add(Restrictions.ilike("firstName", "zara%"));

			// To get records having salary in between 1000 and 2000
			cr.add(Restrictions.between("salary", 1000, 2000));

			// To check if the given property is null
			cr.add(Restrictions.isNull("salary"));

			// To check if the given property is not null
			cr.add(Restrictions.isNotNull("salary"));

			// To check if the given property is empty
			cr.add(Restrictions.isEmpty("salary"));

			// To check if the given property is not empty
			cr.add(Restrictions.isNotEmpty("salary"));*/

			
			//=============================================================================
			//chuyển đổi thành câu lệnh truy vấn HQL ở đây
			List<Employee> employees = cr.list();  //kết quả truy vấn trả về

			for (Employee emp : employees) {
				System.out.println("name = " + emp.getEmpName() + ", no = " + emp.getEmpNo()+ ", Job = " + emp.getJob() + ", Salary = " + emp.getSalary());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
