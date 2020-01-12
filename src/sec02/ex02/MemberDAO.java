package sec02.ex02;

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
				MemberVO memInfo = new MemberVO();
				memInfo.setUsername(rs.getString("username"));
				memInfo.setPassword(rs.getString("password"));
				memInfo.setName(rs.getString("name"));
				memInfo.setPhone1(rs.getString("phone1"));
				memInfo.setPhone2(rs.getString("phone2"));
				memInfo.setPhone3(rs.getString("phone3"));
				memInfo.setEmail(rs.getString("email"));
				memInfo.setZipCode(rs.getString("zipcode"));
				memInfo.setStrAddress(rs.getString("str_address"));
				memInfo.setAddress(rs.getString("address"));
				memInfo.setDetailedAddress(rs.getString("detailed_address"));
				memInfo.setReferAddress(rs.getString("referaddress"));
				memInfo.setJoinDate(rs.getDate("joinDate"));
				membersList.add(memInfo);
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

	public void addMember(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into mymember values(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipCode());
			pstmt.setString(9, vo.getStrAddress());
			pstmt.setString(10, vo.getAddress());
			pstmt.setString(11, vo.getDetailedAddress());
			pstmt.setString(12, vo.getReferAddress());
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}
	}

	public MemberVO findMember(String username) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO memInfo = null;

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement("select * from mymember where username = ?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				memInfo = new MemberVO();
				memInfo.setUsername(rs.getString("username"));
				memInfo.setPassword(rs.getString("password"));
				memInfo.setName(rs.getString("name"));
				memInfo.setPhone1(rs.getString("phone1"));
				memInfo.setPhone2(rs.getString("phone2"));
				memInfo.setPhone3(rs.getString("phone3"));
				memInfo.setEmail(rs.getString("email"));
				memInfo.setZipCode(rs.getString("zipcode"));
				memInfo.setStrAddress(rs.getString("str_address"));
				memInfo.setAddress(rs.getString("address"));
				memInfo.setDetailedAddress(rs.getString("detailed_address"));
				memInfo.setReferAddress(rs.getString("referaddress"));
				memInfo.setJoinDate(rs.getDate("joinDate"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException sqle) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}
		return memInfo;
	}

	public void modMember(MemberVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement("update mymember set password=?, phone1=?, phone2=?, phone3=?, email=?, zipcode=?, str_address=?, address=?, detailed_address=?, referaddress=? where username = ? ");
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipCode());
			pstmt.setString(7, vo.getStrAddress());
			pstmt.setString(8, vo.getAddress());
			pstmt.setString(9, vo.getDetailedAddress());
			pstmt.setString(10, vo.getReferAddress());
			pstmt.setString(11, vo.getUsername());
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}
	}

	public void delMember(String username) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement("delete from MyMember where username = ?");
			pstmt.setString(1, username);
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}
		}

	}



	//패스워드 가져오는 메서드
	public String getPass(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String PasswordInDB = "";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement("select password from mymember where username = ?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				PasswordInDB = rs.getString("password");
			}

		}catch(Exception e) {
			if(rs != null) try {rs.close();}catch(SQLException sqle) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null) try {conn.close();}catch(SQLException sqle) {}

		}

		return PasswordInDB;
	}


}


















