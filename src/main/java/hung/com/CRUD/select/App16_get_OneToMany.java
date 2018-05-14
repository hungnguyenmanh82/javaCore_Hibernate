package hung.com.CRUD.select;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Department;
import hung.com.table.Employee;

/**
 https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App16_get_OneToMany {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			//tại đây tạo câu lệnh SELECT vào table Employee thôi (ko có lệnh JOIN)
			Department department = session.get(Department.class, new Integer(10)); //id = 7499
			System.out.println("=============================== start:  ===========================");
			
			//tại đây mới tạo câu lệnh truy vấn SELECT vào Department table (ko có JOIN)
			System.out.println("DepartmentId: " + department.getDeptId() + ", DepartmentName: "+ department.getDeptName());
			System.out.println("--------------------");
			Set<Employee> emps = department.getEmployees();
			for (Employee employee : emps) {
				System.out.println("EmployeeName: " + employee.getEmpName() + ", No: " + employee.getEmpNo());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
