package hung.com.CRUD.delete;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hung.com.CRUD.insert.DataUtils;
import hung.com.sessionFactory.MySessionFactory;
import hung.com.table.Employee;
import hung.com.table.Timekeeper;

/**
trường hợp Delete thì dùng SQL native sẽ tối ưu hơn.
Nếu dùng với Hibernate thì phải thêm 1 thao tác Find để add Object vào Persistent State.
Sau khi add vào Persistent state thì mới dùng lệnh session.delete() đc => ko tối ưu
 */
public class App3_delete {


	public static void main(String[] args) {
		SessionFactory factory = MySessionFactory.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = new Employee();
		try {
			session.getTransaction().begin();
			emp.setEmpNo("E7369");
			session.delete(emp);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

}
