package services.note;

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

public class Note {
	private HttpServletRequest request;
	private HttpSession session;

	public Note(HttpServletRequest request) {
		this.request = request;
		session = this.request.getSession();
	}

	public ActionBean backcontroller(int serviceCode) {
		ActionBean action = null;

		if (serviceCode == 21) {
			action = this.NoteInsCtl();
		} else if (serviceCode == 22) {
			action = this.NoteSelCtl();
		}
		return action;
	}

	/* 아이디를 사용하여 게시물을 검색하여 가지고 오기위한 Ctl */
	private ActionBean NoteSelCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		String page = "RegistPage.jsp", message = null;
		boolean access = true;

		user.setUserId(this.request.getParameter("UserId"));

		NoteDataAccessObject dao = new NoteDataAccessObject();
		Connection connection = dao.openConnection();

		if (this.request.getParameter("UserId") != null) {

			userList = dao.selNote(connection, user);
			session.setAttribute("Note", userList);

			access = false;
			page = "RegistPage.jsp";

		} else {

			access = true;
			page = "index.jsp";

			message = "입력하신 아이디의 게시물이 존재하지 않습니다..";

			try {
				message = ("?message=") + URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		dao.closeConnection(connection);

		action.setPage(page);
		action.setRedirect(access);

		return action;
	}

	/* note 테이블에 data를 insert하기위한 메소드 */
	private ActionBean NoteInsCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = null;
		NoteBean note = new NoteBean();
		String page, message = null;
		boolean tran = false, access = true;

		note.setConntents(this.request.getParameter("Conntents"));
		note.setNoteCode(this.request.getParameter("GoodsCode"));
		note.setGoodsName(this.request.getParameter("GoodsName"));
		note.setGoodsPrice(Integer.parseInt(this.request.getParameter("GoodsPrice")));
		note.setGoodsQuantity(Integer.parseInt(this.request.getParameter("GoodsQuantity")));
		note.setGoodsState(this.request.getParameter("GoodsState")); // 상품상태
		note.setNoteDate(this.request.getParameter("NoteDate"));
		note.setNoteIng(this.request.getParameter("NoteIng")); // 거래 중 , 거래 완료 등
		note.setNoteState(this.request.getParameter("NoteState")); // 삽니다, 팝니다
		note.setTranMethod(this.request.getParameter("TranMethod")); // 직거래, 택배거래
		note.setTranPlace(this.request.getParameter("TranPlace"));
		note.setNoteImage(this.request.getParameter("NoteImage"));

		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		noteList.add(note);
		user.setNoteList(noteList);
		user.setUserId(this.request.getParameter("userId"));

		NoteDataAccessObject dao = new NoteDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, tran);
		userList = dao.getMaxNoteCode(connection, user);
		if (userList.get(0).getNoteList().get(0).getNoteCode() != null) {
			if (this.convertToBoolean(dao.insNote(connection, user))) {
				userList = new ArrayList<UserBean>();

				/* 입력한 내용을 client로 보내어 보여주기위한 */
				userList = dao.selNote(connection, user);
				tran = true;
				access = false;
				page = "RegistPage.jsp";

				request.setAttribute("Note", userList);

			} else {
				page = "RegistPage.jsp";
				message = "게시판의 양식과 다릅니다.";

				try {
					message = ("?message=") + URLEncoder.encode(message, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, tran);
		dao.closeConnection(connection);

		action.setPage(page);
		action.setRedirect(access);

		return action;
	}

	private boolean convertToBoolean(int value) {
		return value > 0 ? true : false;
	}

}