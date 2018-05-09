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
public class App13_select_showSome {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		// Hibernate auto close this session based on thread context (don't care about it)
		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Query một vài cột.
			// Việc lấy dữ liệu trong trư�?ng hợp này sẽ phức tạp hơn.
			//e: là object của Employee Class (Hibernate sẽ map nó với Table Employee)
			//e.empName: là tên member of Employee Class
			String sql = "Select e.empId, e.empNo, e.empName from "
					+ Employee.class.getName() + " e ";

			Query<Object[]> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng Object[]

			List<Object[]> datas = query.getResultList();  //gửi lệnh tới SQL server: synchronous function

			for (Object[] emp : datas) {
				System.out.println("Emp Id: " + emp[0]); //e.empId
				System.out.println(" Emp No: " + emp[1]); //e.empNo
				System.out.println(" Emp Name: " + emp[2]); //e.empName
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
