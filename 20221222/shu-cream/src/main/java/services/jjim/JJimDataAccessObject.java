package services.jjim;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.JJimBean;
import beans.NoteBean;
import beans.UserBean;

public class JJimDataAccessObject extends services.DataAccessObject{

	public JJimDataAccessObject() {

	}

	final Connection openConnection() {
		return this.openConnect();
	}

	final Connection openConnection(String userName, String password) {
		return this.openConnect();
	}

	final void closeConnection(Connection connect) {
		this.closeConnect(connect);
	}

	final void modifyTranStatus(Connection connect, boolean tran) {
		this.setTranStatus(connect, tran);
	}

	final void setTransaction(boolean tran, Connection connect) {
		this.setTransactionEnd(tran, connect);
	}
	
	final int insJJimData(Connection connection, UserBean user) {
		int result = 0;
		String dml = "INSERT INTO TRDBA.JJ(JJ_CSID, JJ_NTCODE, JJ_GONAME, JJ_DATE, JJ_COUNT, JJ_PRICE, JJ_GOSTATE, JJ_NTSTATE, JJ_JJSTATE) "
				+ "VALUES(?, ?, ?, DEFAULT, ?, ?, ?, ?, ?)";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			this.ps.setNString(3, user.getNoteList().get(0).getJJimList().get(0).getJjimName());
			this.ps.setInt(4, user.getNoteList().get(0).getJJimList().get(0).getJjimCount());
			this.ps.setInt(5, user.getNoteList().get(0).getJJimList().get(0).getJjimPrice());
			this.ps.setNString(6, user.getNoteList().get(0).getJJimList().get(0).getJjimGoodsState());
			this.ps.setNString(7, user.getNoteList().get(0).getJJimList().get(0).getJjimNoteState());
			this.ps.setNString(8, user.getNoteList().get(0).getJJimList().get(0).getJjimState());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	final ArrayList<UserBean> selJJimData(Connection connection, UserBean user) {
		UserBean userBean = null;
		ArrayList<UserBean> userList = null;
		NoteBean noteBean = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		JJimBean jjimBean = new JJimBean();
		ArrayList<JJimBean> jjimList = new ArrayList<JJimBean>();
		String qur = "SELECT * FROM TRDBA.JJ WHERE JJ_CSID = ? ";
		
		try {
			this.ps = connection.prepareStatement(qur);
			this.ps.setNString(1, user.getUserId());
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				userList = new ArrayList<UserBean>();
				while (this.rs.next()) {
					userBean = new UserBean();
					
					jjimBean.setJjimName(this.rs.getNString("GoodsName"));
					jjimBean.setJjimCount(this.rs.getInt("GoodsCount"));
					jjimBean.setJjimPrice(this.rs.getInt("GoodsPrice"));
					jjimBean.setJjimGoodsState(this.rs.getNString("GoodsState"));
					jjimBean.setJjimNoteState(this.rs.getNString("NoteState"));
					jjimBean.setJjimState(this.rs.getNString("JjimState"));
					jjimList.add(jjimBean);
					
					noteBean.setNoteCode(this.rs.getNString("NoteCode"));
					noteBean.setJJimList(jjimList);
					noteList.add(noteBean);
					
					userBean.setUserId(this.rs.getNString("UserId"));
					userBean.setNoteList(noteList);
					userList.add(userBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	final int delJJimData(Connection connection, UserBean user) {
		int result = 0;
		String dml = "DELECT FROM TRDBA.JJ WHERE JJ_CSID = ? AND JJ_NTCODE = ?";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
