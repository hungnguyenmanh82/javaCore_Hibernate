package hung.com.CRUD.select;


import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Department;
import hung.com.table.Employee;
import hung.com.table.ShortEmpInfo;
/**
https://o7planning.org/vi/10201/huong-dan-lap-trinh-java-hibernate-cho-nguoi-moi-bat-dau

*/
public class App15_select_where {


	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();
            //==================================================
			Department dept = getDepartment(session, "D10");  //gửi lệnh tới SQL server: synchronous function
			Set<Employee> emps = dept.getEmployees();

			System.out.println("Dept Name: " + dept.getDeptName());
			for (Employee emp : emps) {
				System.out.println(" Emp name: " + emp.getEmpName());
			}
			
			//==================================================
			Employee emp = getEmployee(session, 7839L);  //gửi lệnh tới SQL server: synchronous function
			System.out.println("Emp Name: " + emp.getEmpName());

			// lệnh query đã trả v�? trc khi commit()
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public static Department getDepartment(Session session, String deptNo) {
		String sql = "Select d from " + Department.class.getName() + " d "//
				+ " where d.deptNo= :deptNo ";
		Query<Department> query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return (Department) query.getSingleResult();
	}

	public static Employee getEmployee(Session session, Long empId) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " where e.empId= :empId ";
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("empId", empId);
		return (Employee) query.getSingleResult();
	}

}
