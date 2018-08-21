package hung.com.CRUD.select.HQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;
/**
https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

*/
public class App12_select_where {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();
		
		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			// Tất cả các lệnh hành động với DB thông qua Hibernate
			// đ�?u phải nằm trong 1 giao dịch (Transaction)
			// Bắt đầu giao dịch
			session.getTransaction().begin();

			// Tạo một câu lệnh HQL query object.
			// HQL Có tham số.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e cross join DEPARTMENT d
			// where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;

			// truy vấn theo java Entity class, ko phải theo SQL Table
			// 1 java Entity class chứa dữ liệu của nhiếu SQL table
			//e: là object của Employee Class
			//e.empName: là tên member of Employee Class
			//e.department: chứa dữ liệu ở SQL Department table 
			//e.department.deptNo: vẫn là dữ liệu của java Employee entity class
			// Employee java class ko nhất thiết phải chứa hết các column của Employee SQL table
			String sql = "Select e from " + Employee.class.getName() + " e "
					                          + " where e.department.deptNo= :deptNo "; // ":deptNo" tương tự "?" trên SQL

			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);

			// Các tham số dua vao co the chua special character vì thế dùng cách này an toàn
			query.setParameter("deptNo", "D10");  // ":deptNo" tương tự "?" trên SQL

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList();  //gửi lệnh tới SQL server: synchronous function

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : "
						+ emp.getEmpName());
			}

			// lệnh query đã trả v�? trc khi commit()
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trư�?ng hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

}
