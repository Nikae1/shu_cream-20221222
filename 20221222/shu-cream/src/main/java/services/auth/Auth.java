package services.auth;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ActionBean;
import beans.LoginBean;
import beans.UserBean;

public class Auth {
	private HttpServletRequest request;
	private HttpSession session;

	public Auth(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession(); 
		this.session.setMaxInactiveInterval(300);
	}
	
	public ActionBean backController(int serviceCode) {
		ActionBean action = null;
		if(serviceCode == 1) {
			action = this.JoinCtl();
		}else if(serviceCode == 2) {
			action = this.LoginCtl();
		}else if(serviceCode == 3) {
			action = this.LogoutCtl();
		}
		return action;
	}
	/*회원가입 하는 메소드*/
	private ActionBean JoinCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean(); 
		String page = "index.jsp", message = null;
		boolean forward = false, tran = false;
		/*사용자가 입력한 값을 bean에 저장*/
		user.setUserId(this.request.getParameter("userId"));
		user.setUserName(this.request.getParameter("userName"));
		user.setUserPw(this.request.getParameter("userPw"));
		user.setUserPhone(this.request.getParameter("userPhone"));
		user.setUserAddr(this.request.getParameter("userAddr"));
		//System.out.println(this.request.getParameter("userId"));
		/*DAO 연결*/
		AuthDataAccessObject dao = new AuthDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, false);
		
		/*사용자의 입력 정보와 기존의 회원 정보 중 일치하는 id가 있으면 회원가입 실패*/
		if(!this.convertToBoolean(dao.selUser(connection, user))) {
			//System.out.println(2);
			tran = this.convertToBoolean(dao.insUser(connection, user));
			//System.out.println(tran);
				
		}else {
			message = "사용중인 계정";
		}
		
		/*DAO 종료*/
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, true);
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(forward);
			
		return action;
	}
	
	/*로그인을 하기 위한 메소드*/
	private ActionBean LoginCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		ArrayList<LoginBean> login = new ArrayList<LoginBean>();
		LoginBean log = new LoginBean();
		String page = "index.jsp", message = null;
		boolean forward = false, tran = false;
		
		
		log.setLogType("1");
		login.add(log);
		user.setUserId(this.request.getParameter("userId"));
		user.setUserPw(this.request.getParameter("userPw"));
		user.setLogInfo(login);
		
		AuthDataAccessObject dao = new AuthDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, false);
		
		
		if(this.convertToBoolean(dao.isId(connection, user))) {
			if(this.convertToBoolean(dao.selLogin(connection, user))) {
				tran = this.convertToBoolean(dao.insAccess(connection, user));
				//로그인 후 메인화면 만들어지면 교체
				page = "RegistPage.jsp";
				session.setAttribute("UserBean", user);

			}else {
				page = "Login.jsp";
				message = "회원 정보가 존재하지 않습니다.";
				forward = true;
			}
		}else {
			page = "Login.jsp";
			message = "존재하지 않는 ID 입니다.";
			forward = true;
		}
		
		/*DAO 종료*/
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, true);
		dao.closeConnection(connection);
		
		
		action.setPage(page);
		action.setRedirect(forward);
		return action;
	}
	
	/*로그아웃*/
	private ActionBean LogoutCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		ArrayList<LoginBean> login = new ArrayList<LoginBean>();
		LoginBean log = new LoginBean();
		String page = "index.jsp", message = null;
		boolean forward = false, tran = false;
		
		System.out.println(((UserBean)session.getAttribute("UserBean")).getUserId());
		
		log.setLogType("-1");
		login.add(log);
		
		user.setUserId(((UserBean)session.getAttribute("UserBean")).getUserId());
		user.setUserPw(((UserBean)session.getAttribute("UserBean")).getUserPw());
		user.setLogInfo(login);
		
		/*DAO 연결*/
		AuthDataAccessObject dao = new AuthDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, false);
		if(this.convertToBoolean(dao.insAccess(connection, user))) {
			page = "index.jsp";
			tran = true;
		}
		
		/*DAO 종료*/
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, true);
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(true);
		return action;
	}
	
	private boolean convertToBoolean(int value) {
		return value>0? true:false;
	}

}
