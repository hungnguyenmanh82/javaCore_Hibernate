package hung.com.CRUD.update;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hung.com.CRUD.insert.DataUtils;
import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Department;
import hung.com.table.Employee;

/**
https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

 */
public class App31_flush_update {

	public static void main(String[] args) {

		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();
		Department department = null;

		try {
			session.getTransaction().begin();

			System.out.println("- Finding Department deptNo = D10...");
			// đây là một đối tượng có trạng thái Persistent.
			department = DataUtils.findDepartment(session, "D10");

			System.out.println("- First change Location");
			// Thay đổi gì đó trên đối tượng Persistent.
			department.setLocation("Chicago " + System.currentTimeMillis());

			System.out.println("- Location = " + department.getLocation());

			System.out.println("- Calling flush...");


			//============================================
			// Sử dụng session.flush() để chủ động đẩy các thay đổi xuống DB.
			// Có tác dụng trên tất cả các đối tượng Persistent có thay đổi.
			//tuy nhiên trên SQL phải ch�? commit() thì mới apply thay đổi thực sự ở server
			session.flush();   

			System.out.println("- Flush OK");

			System.out.println("- Second change Location");
			// Thay đổi gì đó trên đối tượng Persistent.
			// Hình thành câu lệnh update, sẽ được thực thi
			// sau khi session đóng lại (commit).

			department.setLocation("Chicago " + System.currentTimeMillis());

			// In ra Location.
			System.out.println("- Location = " + department.getLocation());

			System.out.println("- Calling commit...");

			//============================================================================
			// chưa commit thì lệnh session.flush(); chưa gửi tới SQL server
			// các lệnh khác đ�?u gửi tới Server và đợi trả v�? trc khi commit()
			session.getTransaction().commit();

			System.out.println("- Commit OK");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// Tạo lại session sau khi nó đã bị đóng trước đó
		// (Do commit hoặc rollback)

		session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();

			System.out.println("- Finding Department deptNo = D10...");

			// Query lại Department D10.

			department = DataUtils.findDepartment(session, "D10");

			// In ra thông tin Location.

			System.out.println("- D10 Location = " + department.getLocation());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}