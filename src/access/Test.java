package access;

/*
 * 2020069046 정준호
 * JDBC 프로그래밍 테스트
 */

import java.sql.*;

public class Test {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/madang",
					"jeongjunho", "Icjh8954!");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1)
						+ " " + rs.getString(2)
						+ " " + rs.getString(3));
			}
			// 여기까진 실습 내용
			System.out.println("-------------------------");
			ResultSetMetaData metaData = rs.getMetaData();
			System.out.println("Table description...");
			for (int i = 1; i < metaData.getColumnCount(); i++) {
				System.out.println(metaData.getColumnName(i)+"\t"+metaData.getColumnTypeName(i));
			}
			System.out.println("-------------------------");
			// 데이터 삽입
			System.out.println("Insert...");
			stmt.execute("INSERT INTO Book (bookid, bookname, publisher) VALUE(21, '소프트웨어 공학 이론과 실재', '한빛아카데미')");
			// 삽입 후 출력
			rs=stmt.executeQuery("SELECT * FROM Book");
			while (rs.next()) {
				System.out.println(rs.getInt(1)
						+ " " + rs.getString(2)
						+ " " + rs.getString(3));
			}
			System.out.println("-------------------------");
			// 데이터 삭제
			System.out.println("Delete...");
			stmt.executeUpdate("DELETE FROM Book WHERE bookname='모두를 위한 클라우드 컴퓨팅'");
			// 삭제 후 출력
			rs=stmt.executeQuery("SELECT * FROM Book");
			while (rs.next()) {
				System.out.println(rs.getInt(1)
						+ " " + rs.getString(2)
						+ " " + rs.getString(3));
			}
			System.out.println("-------------------------");
			// 데이터 검색
			System.out.println("Search...");
			rs=stmt.executeQuery("SELECT * FROM Book WHERE bookid < 10");
			while (rs.next()) {
				System.out.println(rs.getInt(1)
						+ " " + rs.getString(2)
						+ " " + rs.getString(3));
			}
			
			con.close();
		} catch(Exception e) { System.out.println(e); }

	}

}
