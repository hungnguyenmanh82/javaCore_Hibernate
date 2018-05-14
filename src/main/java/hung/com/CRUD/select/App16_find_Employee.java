package hung.com.CRUD.select;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;

/**
 https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App16_find_Employee {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			//tại đây tạo câu lệnh SELECT vào table Employee thôi (ko có lệnh JOIN)
			Employee emp = session.find(Employee.class, new Long(7499)); //id = 7499
			System.out.println("===============================start");
			System.out.println("name = " + emp.getEmpName() + ", no = " + emp.getEmpNo()+ ", Job = " + emp.getJob());
			
			//tại đây mới tạo câu lệnh truy vấn vào SELECT vào table Employee (ko có JOIN)
			System.out.println("nameManage = " + emp.getManager().getEmpName() + ", no = " +  emp.getManager().getEmpNo()+ ", Job = " +  emp.getManager().getJob());
			
			//tại đây mới tạo câu lệnh truy vấn SELECT vào Department table (ko có JOIN)
			System.out.println("Department: " + emp.getDepartment().getDeptId() + ", DepartmentName: "+ emp.getDepartment().getDeptName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
