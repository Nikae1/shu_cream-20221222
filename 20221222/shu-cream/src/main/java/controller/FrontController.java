package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ActionBean;
import services.auth.Auth;
import services.note.Note;
import services.search.SearchCtl;

@WebServlet({"/insNote", "/simpleAll", "/searchUser", "/searchMenu", "/RegNote", "/Join", "/Login", "/Logout" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String jobCode = request.getRequestURI().substring(request.getContextPath().length() + 1);
		ActionBean action = null;
		Auth auth;
		SearchCtl search;
		
		if(jobCode.equals("simpleAll")) {
			search = new SearchCtl(request);
			action = search.backController(31);
		} else if(jobCode.equals("searchUser")) {
			search = new SearchCtl(request);
			action = search.backController(33);
		}
		
		/* action의 전달방식을 결정하며 이동할 페이지를 지정하는 구문 */
		if (action.isRedirect()) {
			response.sendRedirect(action.getPage());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(action.getPage());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");
		String jobCode = request.getRequestURI().substring(request.getContextPath().length() + 1);
		ActionBean action = null;
		Auth auth;
		Note note;
		SearchCtl search;
		
		if (jobCode.equals("Join")) {
			auth = new Auth(request);
			action = auth.backController(1);
		} else if (jobCode.equals("Login")) {
			auth = new Auth(request);
			action = auth.backController(2);
		} else if (jobCode.equals("Logout")) {
			auth = new Auth(request);
			action = auth.backController(3);
		} else if (jobCode.equals("insNote")) {
			note = new Note(request);
			action = note.backcontroller(21);
		} else if(jobCode.equals("searchMenu")) {
			search = new SearchCtl(request);
			action = search.backController(32);
		}

		/* action의 전달방식을 결정하며 이동할 페이지를 지정하는 구문 */
		if (action.isRedirect()) {
			response.sendRedirect(action.getPage());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(action.getPage());
			dispatcher.forward(request, response);

		}

	}

}
