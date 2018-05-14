package hung.com.CRUD.select.HQL;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;
import hung.com.table.ShortEmpInfo;
/**
https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

*/
public class App14_select_shortEmpInfo {

	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Sử dụng cấu tử của Class ShortEmpInfo
			//e: là object của Employee Class (Hibernate sẽ map nó với Table Employee)
			//e.empName: là tên member of Employee Class
			String sql = "Select new " + ShortEmpInfo.class.getName()
					+ "(e.empId, e.empNo, e.empName)" + " from "
					+ Employee.class.getName() + " e ";

			Query<ShortEmpInfo> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng ShortEmpInfo
			List<ShortEmpInfo> employees = query.getResultList();  //gửi lênh tới SQL server: synchronous function

			for (ShortEmpInfo emp : employees) {
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
