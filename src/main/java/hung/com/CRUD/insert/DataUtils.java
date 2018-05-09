package hung.com.CRUD.insert;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hung.com.table.Department;
import hung.com.table.Employee;

public class DataUtils {

	public static Department findDepartment(Session session, String deptNo) {
		String sql = "Select d from " + Department.class.getName() + " d "//
				+ " Where d.deptNo = :deptNo";
		
		//lệnh này chưa gửi tới SQL server
		Query<Department> query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		
		// lệnh query đã trả v�? trc khi commit()
		return query.getSingleResult();   //gửi lệnh tới SQL server: synchronous function
	}

	public static Long getMaxEmpId(Session session) {
		String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
		Query<Number> query = session.createQuery(sql); //lệnh này chưa gửi tới SQL server
		Number value = query.getSingleResult(); //gửi lệnh tới SQL server: synchronous function
		if (value == null) {
			return 0L;
		}
		return value.longValue();
	}

	public static Employee findEmployee(Session session, String empNo) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " Where e.empNo = :empNo";
		Query<Employee> query = session.createQuery(sql); //lệnh này chưa gửi tới SQL server
		query.setParameter("empNo", empNo);
		return query.getSingleResult();  //gửi lệnh tới SQL server: synchronous function
	}

}