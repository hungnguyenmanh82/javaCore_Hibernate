package hung.com.CRUD.insert;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Department;
import hung.com.table.Employee;

/**
https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

*/
public class App21_persistent_insert {

	public static void main(String[] args) {

		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();
		Department department = null;
		Employee emp = null;
		try {
			session.getTransaction().begin();
			
			//=================================================
			Long maxEmpId = DataUtils.getMaxEmpId(session);
			Long empId = maxEmpId + 1;

			//====================================================
			// Phòng ban với mã số D10.
			// Nó là đối tượng chịu sự quản lý của session
			// Và được g�?i là đối tượng persistent.
			department = DataUtils.findDepartment(session, "D10");

			//=====================================================
			// Tạo mới đối tượng Employee
			// �?ối tượng này chưa chịu sự quản lý của session.
			// Nó được coi là đối tượng Transient.
			emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpNo("E" + empId);
			emp.setEmpName("Name " + empId);
			emp.setJob("Coder");
			emp.setSalary(1000f);
			emp.setManager(null);
			emp.setHideDate(new Date());
			emp.setDepartment(department);


			//==================================================
			// Sử dụng persist(..)        
			// Lúc này 'emp' đã chịu sự quản lý của session.
			// nó có trạng thái persistent.
			// Chưa có hành động gì với DB tại đây.
			session.persist(emp);  //= INSERT in sql

			session.getTransaction().commit(); //phải commit() xong thì lệnh này mới đc thực hiện ở server (đã test với breakpoint)
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// Sau khi session bị đóng lại (commit, rollback, close)
		// �?ối tượng 'emp', 'dept' trở thành đối tượng Detached.
		// Nó không còn trong sự quản lý của session nữa.

		System.out.println("Emp No: " + emp.getEmpNo());

	}

}