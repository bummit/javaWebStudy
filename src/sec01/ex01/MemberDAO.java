package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
public class MemberDAO {
	
	private static MemberDAO instance = null;
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();				
			}
		}
		return instance;
			
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			InitialContext init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch(Exception e ) {
			System.err.println("커넥션 풀 실패");
		}
		
		return conn;
	}
	
	
	public List listMembers(){
		List membersList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String query = "select * from mymember order by joinDate desc";
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO getMember = new MemberVO();
				getMember.setUsername(rs.getString("username"));
				getMember.setPassword(rs.getString("password"));
				getMember.setName(rs.getString("name"));
				getMember.setPhone1(rs.getString("phone1"));
				getMember.setPhone2(rs.getString("phone2"));
				getMember.setPhone3(rs.getString("phone3"));
				getMember.setEmail(rs.getString("email"));
				getMember.setZipCode(rs.getString("zipcode"));
				getMember.setStrAddress(rs.getString("str_address"));
				getMember.setAddress(rs.getString("address"));
				getMember.setDetailedAddress(rs.getString("detailed_address"));
				getMember.setReferAddress(rs.getString("referaddress"));
				getMember.setJoinDate(rs.getDate("joinDate"));
				membersList.add(getMember);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException sqle) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}
		
		return membersList;	
	}
	
	public void addMember(MemberVO membervo) {
		System.out.println("수행된거니");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into mymember values(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");
			pstmt.setString(1, membervo.getUsername());
			pstmt.setString(2, membervo.getPassword());
			pstmt.setString(3, membervo.getName());
			pstmt.setString(4, membervo.getPhone1());
			pstmt.setString(5, membervo.getPhone2());
			pstmt.setString(6, membervo.getPhone3());
			pstmt.setString(7, membervo.getEmail());
			pstmt.setString(8, membervo.getZipCode());
			pstmt.setString(9, membervo.getStrAddress());
			pstmt.setString(10, membervo.getAddress());
			pstmt.setString(11, membervo.getDetailedAddress());
			pstmt.setString(12, membervo.getReferAddress());
			pstmt.executeUpdate();
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}
	}
	
	
}


















