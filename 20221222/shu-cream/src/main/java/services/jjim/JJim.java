package services.jjim;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ActionBean;
import beans.JJimBean;
import beans.NoteBean;
import beans.UserBean;

public class JJim {
	private HttpServletRequest request;
	private HttpSession session;

	public JJim(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession(); 
	}
	
	public ActionBean backController(int serviceCode) {
		ActionBean action = null;
		if(serviceCode == 31) {
			action = this.JJimCtl();
		}else if(serviceCode == 32) {
			action = this.JJimOutCtl();			
		}
		return action;
	}
	
	private ActionBean JJimCtl() {
		ActionBean action = new ActionBean();
		String page = "RegistPage.jsp", message = null;
		boolean tran = false, access = true;
		
		JJimBean jjim = new JJimBean();
		ArrayList<JJimBean> jjimList = new ArrayList<JJimBean>();
		jjim.setJjimName(this.request.getParameter("goodsName"));
		jjim.setJjimCount(Integer.parseInt(this.request.getParameter("jjimCount")));
		jjim.setJjimPrice(Integer.parseInt(this.request.getParameter("jjimPrice")));
		jjim.setJjimGoodsState(this.request.getParameter("jjimGoodsState"));
		jjim.setJjimNoteState(this.request.getParameter("jjimNoteState"));
		jjim.setJjimState(this.request.getParameter("JJ1"));
		jjimList.add(jjim);
		
		NoteBean note = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		
		note.setNoteCode(this.request.getParameter("noteCode"));
		note.setJJimList(jjimList);
		noteList.add(note);
		
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		user.setUserId(this.request.getParameter("UserId"));
		user.setNoteList(noteList);
		
		JJimDataAccessObject dao = new JJimDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, false);
		
		if(this.convertToBoolean(dao.insJJimData(connection, user))) {
			tran = true;
			access = false;
			page = "";
			
			userList = dao.selJJimData(connection, user);
			
			session.setAttribute("JjimInfo", user);
		}
		
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, tran);
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action;
	}
	
	private ActionBean JJimOutCtl() {
		ActionBean action = new ActionBean();
		String page = "RegistPage.jsp", message = null;
		boolean tran = false, access = true;
		
		JJimBean jjim = new JJimBean();
		ArrayList<JJimBean> jjimList = new ArrayList<JJimBean>();
		jjim.setJjimName(this.request.getParameter("goodsName"));
		jjim.setJjimCount(Integer.parseInt(this.request.getParameter("jjimCount")));
		jjim.setJjimPrice(Integer.parseInt(this.request.getParameter("jjimPrice")));
		jjim.setJjimGoodsState(this.request.getParameter("jjimGoodsState"));
		jjim.setJjimNoteState(this.request.getParameter("jjimNoteState"));
		jjim.setJjimState(this.request.getParameter("JJ1"));
		jjimList.add(jjim);
		
		NoteBean note = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		
		note.setNoteCode(this.request.getParameter("noteCode"));
		note.setJJimList(jjimList);
		noteList.add(note);
		
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		user.setUserId(this.request.getParameter("UserId"));
		user.setNoteList(noteList);
		
		JJimDataAccessObject dao = new JJimDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, tran);
		
		if(this.convertToBoolean(dao.delJJimData(connection, user))) {
			tran = true;
			access = false;
			page = "";
		}
		
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, tran);
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action;
	}
	private boolean convertToBoolean(int value) {
		return value>0? true:false;
	}
}
