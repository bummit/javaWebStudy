package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO;

	
	public void init() throws ServletException {
		memberDAO = MemberDAO.getInstance();
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		List<MemberVO> membersList = memberDAO.listMembers();
//		request.setAttribute("membersList", membersList);
//		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMember.jsp");
//		dispatch.forward(request, response);
		
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo(); //URL에서 요청명을 가져온다.
		System.out.println("action: " + action);
		
		if(action == null || action.equals("/listMember.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMember.jsp"; // test02 폴더의 listMember.jsp로 포워딩
			
		}else if(action.equals("/addMember.do")){
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String email = request.getParameter("email");
			String zipCode = request.getParameter("zipCode");
			String strAddress = request.getParameter("strAddress");
			String address = request.getParameter("address");
			String detailedAddress = request.getParameter("detailedAddress");
			String referAddress = request.getParameter("referAddress");
			MemberVO membervo = new MemberVO(username, password, name, email, phone1, phone2, phone3, zipCode, strAddress, address, detailedAddress, referAddress);
			memberDAO.addMember(membervo);
			System.out.println(membervo.toString());
			nextPage = "/member/listMember.do"; // 회원 등록 후 다시 회원 목록을 출력.
			
		}else if(action.equals("/memberForm.do")) { // 회원가입 창 출력
			nextPage = "/test02/memberForm.jsp";
			
		}else {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMember.jsp";
		}
		System.out.println("nextPage:" + nextPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
