package services.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ActionBean;
import beans.CategoriesBean;
import beans.NoteBean;
import beans.UserBean;
import services.note.NoteDataAccessObject;

public class SearchCtl {
	private HttpServletRequest request;
	private HttpSession session;
	
	public SearchCtl(HttpServletRequest request) {
		this.request = request;
		session = this.request.getSession();
	}
	
	public ActionBean backController(int serviceCode) {
		ActionBean action = null;
		
		if(serviceCode == 31) {
			action = this.simpleAllCtl();
		}else if(serviceCode == 32) {
			action = this.simpleChoCtl();
		}else if(serviceCode == 33) {
			action = this.searchUserCtl();
		}else if(serviceCode == 26) {
			action = this.searchUserCtl();
		}
		
		
		return action;
	}
	
	/* 사용자가 검색기능을 사용하여 게시물을 검색한 경우 사용하는 메소드 */
	private ActionBean simpleChoCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		String page = "RegistPage.jsp", message = null;
		boolean access = true;
		
		user.setUserSearch(this.request.getParameter("UserSearch"));
		
		SearchDataAccessObject dao = new SearchDataAccessObject();
		Connection connection = dao.openConnection();
		
		if(this.session.getAttribute("UserBean") != null) {
			ArrayList<UserBean> userList = new ArrayList<UserBean>();
			userList = dao.simpleChoice(connection, user);
			System.out.println("1");
			session.setAttribute("simpleCho", userList);
			access = false;
			page ="SecondPage.jsp"; 
		}else {
			
			access = true;
			page ="index.jsp"; 
			
			message = "게시물이 존재하지 않습니다...";
			
			try {
				page += ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
		}
			
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action;
	}
	
	
	/* 게시판에 요약한 게시물의 정보를 보여줄 메소드 */
	private ActionBean simpleAllCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		String page = "RegistPage.jsp", message = null;
		boolean access = true;
		
		SearchDataAccessObject dao = new SearchDataAccessObject();
		Connection connection = dao.openConnection();
		
		if(this.session.getAttribute("UserBean") != null) {
			ArrayList<UserBean> userList = new ArrayList<UserBean>();
			
			userList = dao.simpleAll(connection, user);
			session.setAttribute("simpleAll", userList);
			access = false;
			page ="RegistPage.jsp"; 
			
		}else {
			
			access = true;
			page ="index.jsp"; 
			
			message = "게시물이 존재하지 않습니다...";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
		}
			
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action;
	}
	
	
	/* 삽니다 // 팝니다 둘 중 하나를 선택하여 검색하는 메소드 */
	private ActionBean searchMenuCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		CategoriesBean cate = null;
		NoteBean note = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		String page = "RegistPage.jsp", message = null;
		boolean access = true;
		
		cate = new CategoriesBean();
		cateList = new ArrayList<CategoriesBean>();
		cate.setCg_Name(this.request.getParameter("CGNAME"));
		cateList.add(cate);
		note = new NoteBean();
		noteList = new ArrayList<NoteBean>();
		note.setCateList(cateList);
		noteList.add(note);
		user.setNoteList(noteList);
		
		SearchDataAccessObject dao = new SearchDataAccessObject();
		Connection connection = dao.openConnection();
		
		if(this.session.getAttribute("UserBean") != null) {
			ArrayList<UserBean> userList = new ArrayList<UserBean>();
			userList = dao.simpleMenu(connection, user);
			session.setAttribute("simpleCho", userList);
			access = false;
			page ="RegistPage.jsp"; 
		
		}else {
			
			access = true;
			page ="index.jsp"; 
			
			message = "게시물이 존재하지 않습니다...";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
		}
			
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action; 
	}
	
	
	/* 특정한 유저의 게시물만 검색하는  */
	private ActionBean searchUserCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		String page = "RegistPage.jsp", message = null;
		boolean access = true;
		
		user.setUserId(this.request.getParameter("UserId"));
		
		SearchDataAccessObject dao = new SearchDataAccessObject();
		Connection connection = dao.openConnection();
		
		if(this.session.getAttribute("UserBean") != null) {
			ArrayList<UserBean> userList = new ArrayList<UserBean>();
			userList = dao.simpleUser(connection, user);
			session.setAttribute("simpleUser", userList);
			access = false;
			page ="RegistPage.jsp"; 
		
		}else {
			
			access = true;
			page ="index.jsp"; 
			
			message = "해당 유저의 게시물이 존재하지 않습니다...";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
		}
			
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		
		return action;
	}
	
}
