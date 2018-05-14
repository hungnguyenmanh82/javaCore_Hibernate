package hung.com.vd2;

import java.util.Date;

import org.hibernate.Session;

import hung.com.CRUD.insert.DataUtils;
import hung.com.table.Employee;
import hung.com.vd2.table.Stock;
import hung.com.vd2.table.StockDailyRecord;

public class App_Insert_autoId {
	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");
		Session session = Vd2SessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			Stock stock = new Stock();
			stock.setStockCode("7052");
			stock.setStockName("PADINI");
			session.save(stock);

			StockDailyRecord stockDailyRecords = new StockDailyRecord();
			stockDailyRecords.setPriceOpen(new Float("1.2"));
			stockDailyRecords.setPriceClose(new Float("1.1"));
			stockDailyRecords.setPriceChange(new Float("10.0"));
			stockDailyRecords.setVolume(3000000L);
			stockDailyRecords.setDate(new Date());

			stockDailyRecords.setStock(stock);        
			stock.getStockDailyRecords().add(stockDailyRecords);

			session.save(stockDailyRecords);

			session.getTransaction().commit();
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println("============================= fail to insert");
			e.printStackTrace();
			session.getTransaction().rollback();
		}




	}
}
