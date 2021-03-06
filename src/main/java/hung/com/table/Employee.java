package hung.com.table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EMPLOYEE",
uniqueConstraints = { @UniqueConstraint(columnNames = { "EMP_NO" }) })
public class Employee {
	private Long empId;
	private String empNo;

	private String empName;
	private String job;
	private Employee manager;   //ko dung id của manager
	private Date hideDate;
	private Float salary;
	private byte[] image;

	private Department department;  //chỗ này là DEPT_ID của DEPARTMENT table 
//	private Set<Employee> employees = new HashSet<Employee>(0);

	public Employee() {
	}

	public Employee(Long empId, String empName, String job, Employee manager,
			Date hideDate, Float salary, Float comm, Department department) {
		this.empId = empId;
		this.empNo = "E" + this.empId;
		this.empName = empName;
		this.job = job;
		this.manager = manager;
		this.hideDate = hideDate;
		this.salary = salary;
		this.department = department;
	}

	@Id          //id này ko đc tự động gen ở SQL server
	@Column(name = "EMP_ID")
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	@Column(name = "EMP_NO", length = 20, nullable = false)
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Column(name = "EMP_NAME", length = 50, nullable = false)
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "JOB", length = 30, nullable = false)
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	// @ManyToOne  sẽ tạo Foreign key
	//@ManyToOne(fetch = FetchType.EAGER)    // SQL command sẽ lấy luôn Manager khi lấy instance của Employee 
	@ManyToOne(fetch = FetchType.LAZY)      //chỉ tạo SQL command lấy thông tin manager khi gọi hàm getManager()
	@JoinColumn(name = "MNG_ID")           //MNG_ID ở Employee sẽ trỏ tới id của Employee
	public Employee getManager() {   //Employee là table joi
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Column(name = "HIRE_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getHideDate() {
		return hideDate;
	}

	public void setHideDate(Date hideDate) {
		this.hideDate = hideDate;
	}

	@Column(name = "SALARY", nullable = false)
	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	@Column(name = "IMAGE", length = 1111111, nullable = true)
	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	// @ManyToOne tại table này là Many (foreign key), tại  DEPARTMENT table nó là One (primary key)
	// tên Key sẽ đc generate tự động bởi Hibernate
	@ManyToOne(fetch = FetchType.LAZY)                 //chỉ tạo SQL command lấy thông khi gọi hàm getDepartment()
	@JoinColumn(name = "DEPT_ID", nullable = false)   // DEPT_ID là tên trên table Employee (trên Department nó luôn là primary key)
	public Department getDepartment() { //Department class là đại diện cho table DEPARTMENT table
		return department;
	}

	//Department class là đại diện cho table DEPARTMENT table
	public void setDepartment(Department department) {
		this.department = department;
	}

/*	// @oneToMany tại table này là One, tại table TimeKeeper là Many
	// tên Key sẽ đc generate tự động bởi Hibernate
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empId")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}*/

}
