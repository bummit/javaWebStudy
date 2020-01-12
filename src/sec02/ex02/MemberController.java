package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec02.ex02.*;

/**
 * Servlet implementation class MemberController
 */
//@WebServlet("*.do")
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO;

	
	public void init() throws ServletException {
		memberDAO = MemberDAO.getInstance();
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo(); //URL에서 요청명을 가져온다.
		System.out.println("action: " + action);
		System.out.println("nextPage: " + nextPage);
		
		if(action == null || action.equals("/listMember.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMember.jsp"; // test03 폴더의 listMember.jsp로 포워딩
			
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
			nextPage = "/test03/memberForm.jsp";
		
		}else if(action.equals("/modMemberForm.do")) {
			String username = request.getParameter("username");
			MemberVO memInfo = memberDAO.findMember(username);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/test03/modMemberForm.jsp";
			
		}else if(action.equals("/modMember.do")){
			
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
			System.out.println(membervo.toString());
			memberDAO.modMember(membervo);
			request.setAttribute("msg", "modified");
			
			nextPage = "/member/listMembers.do";
			
		}else if(action.equals("/delMember.do")) {
			
			String username = request.getParameter("username");
			memberDAO.delMember(username);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
			
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMember.jsp";
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
