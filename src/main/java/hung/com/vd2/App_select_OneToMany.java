package hung.com.vd2;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hung.com.CRUD.insert.DataUtils;
import hung.com.table.Employee;
import hung.com.vd2.table.Stock;
import hung.com.vd2.table.StockDailyRecord;

public class App_select_OneToMany {
	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");
		Session session = Vd2SessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			// Tạo một câu lệnh HQL query object.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			
			//e: là object của Employee Class (Hibernate sẽ map nó với Table Employee)
			//e.empName: là tên member of Employee Class
			String sql = "Select e from " + Stock.class.getName() + " e " + " where d.deptNo= :deptNo ";

			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);  //chưa gửi tới SQL server

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList(); //gửi lệnh tới SQL server: synchronous function

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			session.getTransaction().commit();
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println("============================= fail to insert");
			e.printStackTrace();
			session.getTransaction().rollback();
		}




	}
}
