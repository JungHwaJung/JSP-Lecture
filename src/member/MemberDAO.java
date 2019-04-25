package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class MemberDAO {
	public static final int ID_PASSWORD_MATCH = 1;
	public static final int ID_DOES_NOT_EXIST = 2;
	public static final int PASSWORD_IS_WRONG = 3;
	public static final int DATABASE_ERROR = -1;
	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";

	public MemberDAO() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
            ex.printStackTrace();
        } 
	}
	
	public MemberDTO selectOne(String query) {
		PreparedStatement pStmt= null;
		MemberDTO members = new MemberDTO();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				members.setId(rs.getInt(1));	
				members.setPassword(rs.getString(2));
				members.setName(rs.getString(3));
				members.setBirthday(rs.getString(4));
				members.setAddress(rs.getString(5));
			}
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	    }
		return members;
	}
	
	public int getCount() {
		String query = "select count(*) from member;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return count;
	}
	
	public List<MemberDTO> selectJoinAll(int page) {
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select member.id, member.name, member.birthday, member.address from member;";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select member.id, member.name, member.birthday, member.address from member " + 
					"order by member.id asc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<MemberDTO> mList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				MemberDTO mDto = new MemberDTO();
				mDto.setId(rs.getInt(1));
				mDto.setName(rs.getString(2));
				mDto.setBirthday(rs.getString(3));
				mDto.setAddress(rs.getString(4));
				mList.add(mDto);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return mList;
	}
	
	public void insertMember(MemberDTO members) {
		String query = "insert into member(password, name, birthday, address, hashed) values (?, ?, ?, ?, ?);";
		PreparedStatement pStmt= null;
	
		try {
			String hashedpassword = BCrypt.hashpw(members.getPassword(), BCrypt.gensalt());
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, "*");
			pStmt.setString(2, members.getName());
			pStmt.setString(3, members.getBirthday());
			pStmt.setString(4, members.getAddress());
			pStmt.setString(5, hashedpassword);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	   }
	}
	
	public void initPassword() {
	    List<MemberDTO> mList = selectAll();
	    	for (MemberDTO member: mList) {
	    		int id = member.getId();
	    		String plainPassword = member.getPassword();
	    		String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	    		updatePassword(id, hashedPassword);
	    	}
	    }
	
	public void updatePassword(int id, String hashed) {
		String query = "update member set hashed=? where id=?;";
		PreparedStatement pStmt= null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, hashed);
			pStmt.setInt(2, id);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	    }
	}
	
	
	public void updateMember(MemberDTO members) {
		String query = "update member set password=?, name=?, birthday=?, address=? where id=?;";
		PreparedStatement pStmt= null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, members.getPassword());
			pStmt.setString(2, members.getName());
			pStmt.setString(3, members.getBirthday());
			pStmt.setString(4, members.getAddress());
			pStmt.setInt(5, members.getId());
			
			pStmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	    }
	}
	
	public void deleteMember(int id) {
		String query = "delete from member where id=?;";
		PreparedStatement pStmt= null;
	
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	   }
	}

	public List<MemberDTO> selectCondition(String query) {
		PreparedStatement pStmt= null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				MemberDTO members = new MemberDTO();
				members.setId(rs.getInt(1));		
				members.setPassword(rs.getString(2));
				members.setName(rs.getString(3));
				members.setBirthday(rs.getString(4));
				members.setAddress(rs.getString(5));
				memberList.add(members);
			}
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	    }
		return memberList;
	}
	
	public MemberDTO searchById(int memberId) {
	    	String sql = "select * from member where id=" + memberId + ";";
	    	MemberDTO mDto = selectOne(sql);
	    	return mDto;
	}
	
	public MemberDTO resentId() {
		String sql = "select * from member order by id desc limit 1;";
    	MemberDTO mDto = selectOne(sql);
    	return mDto;
	}
	
	public List<MemberDTO> selectAll() {
		String sql = "select * from member";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	public int verifyIdPassword(int id, String password) {
		System.out.println("verifyIdPassword() : " + id + ", " + password);
		String query = "select hashed from member where id=?;";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String hashedPassword = "";
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			
			while (rs.next()) {
				hashedPassword = rs.getString(1);
				if (BCrypt.checkpw(password, hashedPassword))
					return ID_PASSWORD_MATCH;
				else
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	if (pStmt != null && !pStmt.isClosed())
	        		pStmt.close();
	        } catch (SQLException se) {
                se.printStackTrace();
            }
	    }
		return DATABASE_ERROR;
	
	}
	
	public void close() {
		try {
			if ( conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException E) {
			E.printStackTrace();
		}
	}
}
