package hung.com.CRUD.detached;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hung.com.CRUD.insert.DataUtils;
import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;

public class App41_detached_evict {
	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		try {
			session.getTransaction().begin();

			// Đây là một đối tượng có tình trạng Persistent.
			emp = DataUtils.findEmployee(session, "E7499");

			// ==> true
			System.out.println("- emp Persistent? " + session.contains(emp)); //kiểm tra emp có phải là persitent state ko

			// Sử dụng evict(Object) để đuổi đối tượng Persistent
			// ra khỏi quản lý của Hibernate.
			session.evict(emp);

			// Lúc này 'emp' đang có trạng thái Detached.
			// ==> false
			System.out.println("- emp Persistent? " + session.contains(emp)); //kiểm tra emp có phải là persitent state ko

			// Tất cả các thay đổi trên 'emp' sẽ không được update
			// nếu không đưa 'emp' trở lại trạng thái Persistent.
			emp.setEmpNo("NEW");

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
