package hung.com.createTable;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

/**
https://o7planning.org/vi/11223/tao-ra-he-thong-bang-tu-cac-class-entity-trong-hibernate 
 */
public class App5_createTable {
	
	
	/**
	 Ví dụ này ko can thiệp vào Database.
	 Nó chỉ tạo ra file "exportScript.sql" để người dùng dùng nó import để tạo bảng trên MySQL,Oracle, SQL server.
	 nếu tạo table cho MySQL thì phải dùng file config cho MySQL.
	 
	 */

	public static void main(String[] args) {
		// Sử dụng MySQL
		String configFileName = "hibernate-mysql.cfg.xml";       //tạo SQL script cho MySQL
//		String configFileName = "hibernate-oracle.cfg.xml";      //tạo SQL script cho Oracle
//		String configFileName = "hibernate-sqlserver.cfg.xml";   //tạo SQL script cho SQL server

		// Tạo đối tượng ServiceRegistry từ hibernate-xxx.cfg.xml
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();

		// Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		
		//=======================================================================
		SchemaExport export = getSchemaExport();

		System.out.println("Drop Database...");
		//========================================================
		dropDataBase(export, metadata);   //tạo các lệnh liên quan tới drop Database

		System.out.println("Create Database...");

		//=======================================================
		createDataBase(export, metadata); //tạo các lệnh liên quan tới create table
	}
	
	public static final String SCRIPT_FILE = "exportScript.sql";

	private static SchemaExport getSchemaExport() {

		SchemaExport export = new SchemaExport();
		// Script file.
		File outputFile = new File(SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();

		System.out.println("Export file: " + outputFilePath);

		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);

		// Không ngừng nếu có lỗi
		export.setHaltOnError(false);
		//
		return export;
	}

	public static void dropDataBase(SchemaExport export, Metadata metadata) {

		// TargetType.DATABASE - Thực thi lệnh vào Databse
		// TargetType.SCRIPT - Ghi ra file Script.
		// TargetType.STDOUT - Ghi thông tin Log ra màn hình Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

		export.drop(targetTypes, metadata);
	}

	public static void createDataBase(SchemaExport export, Metadata metadata) {

		// TargetType.DATABASE - Thực thi lệnh vào Databse
		// TargetType.SCRIPT - Ghi ra file Script.
		// TargetType.STDOUT - Ghi thông tin Log ra màn hình Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

		SchemaExport.Action action = SchemaExport.Action.CREATE;
		//
		export.execute(targetTypes, action, metadata);

		System.out.println("Export OK");

	}

}
