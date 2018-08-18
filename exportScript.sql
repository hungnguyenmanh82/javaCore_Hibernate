String sql = "INSERT INTO "+ tableName+ " (CustomerName,ContactName,Country) VALUES " +
		"(?, ?, ?);";
pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//for dynamic SQL statement

pstmt.setString(1, "Kien beo");  //id = ? 1st
pstmt.setString(2, "Contact 22"); //first name = ? 2nd
pstmt.setString(3, "HongKong"); //last name = ? 3rd

//mỗi lần insert ko thành công ID sẽ tự động tăng lên 1
int numberUpdate = pstmt.executeUpdate();
System.out.println("numberUpdate = " + numberUpdate);

ResultSet rs = pstmt.getGeneratedKeys();
if (rs.next()){
	System.out.println("id = " + rs.getLong(1));
}
