package sec01.ex01;

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
}
