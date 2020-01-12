package sec03.brd01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	BoardService boardService;
	ArticleVO articleVO;
	
	public void init() throws ServletException{
		boardService = new BoardService();
		articleVO = new ArticleVO();
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
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo(); // 요청명을 가져온다.
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
//			}else if(action.equals("/listArticles.do")) {
//				articlesList = boardService.listArticles();
//				request.setAttribute("articlesList", articlesList);
//				nextPage = "/board01/listArticles.jsp";
			
			}else if(action.equals("/articleForm.do")) {
				
				nextPage = "/board01/articleForm.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				
				articleVO.setParentNO(0);
				articleVO.setUsername("aaa");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				boardService.addArticle(articleVO);
				nextPage = "/board/listArticles.do";
			
			}
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
				
		
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		System.out.println("1");
		File currentDirPath = new File(ARTICLE_IMAGE_REPO); // 글 이미지 저장 폴더에 대해 파일 객체를 생성합니다.		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		System.out.println("2");
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		System.out.println("3");
		try {
			System.out.println("4");
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				System.out.println("5");
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) {
					System.out.println("6");
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("7");
					System.out.println("파라미터이름: " + fileItem.getFieldName());
					System.out.println("파일이름: " + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize() > 0) {
						System.out.println("8");
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							System.out.println("9");
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
						System.out.println("10");
					}//end if
				}//end if
			}//end for
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}
	
	
	
	
	
	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	