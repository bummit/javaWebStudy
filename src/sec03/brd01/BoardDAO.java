package sec03.brd01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	private static BoardDAO instance = null;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
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

	
	
	public List<ArticleVO> selectAllArticles() {
	List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			String query = "select level, articleNO, parentNO, title, content, username, writeDate from board start with parentNO = 0 connect by prior articleNO = parentNO order siblings by articleNO desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setLevel(rs.getInt("level"));
				article.setArticleNO(rs.getInt("articleNO"));
				article.setParentNO(rs.getInt("parentNO"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setUsername(rs.getString("username"));
				article.setWriteDate(rs.getDate("writeDate"));
				articlesList.add(article);
			}
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try{ if(rs != null) rs.close(); }catch(SQLException e) {}
			try{ if(pstmt != null) pstmt.close(); }catch(SQLException e) {}
			try{ if(conn != null) conn.close(); }catch(SQLException e) {}
		}
		
		return articlesList;
	}
			
	public int getNewArticleNO() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = -1;
		try {
			String query = "select max(articleNO) from board";	
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1) + 1;
			}
						
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try{ if(rs != null) rs.close(); }catch(SQLException e) {}
			try{ if(pstmt != null) pstmt.close(); }catch(SQLException e) {}
			try{ if(conn != null) conn.close(); }catch(SQLException e) {}
		}
		return result;
	}
	
	public void insertNewArticle(ArticleVO article) {
		
		System.out.println(article.toString());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			String query = "insert into board(articleNO, parentNO, title, content, imageFilename, username) values(?,?,?,?,?,?)";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, getNewArticleNO());
			pstmt.setInt(2, article.getParentNO());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getImageFileName());
			pstmt.setString(6, article.getUsername());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try{ if(pstmt != null) pstmt.close(); }catch(SQLException e) {}
			try{ if(conn != null) conn.close(); }catch(SQLException e) {}
		}
	}
		
}





















