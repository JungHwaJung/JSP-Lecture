package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BbsDAO {
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "javapass";
    private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
    private Connection conn;
	
    public BbsDAO() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");	
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
	
    public void createBbsTable() {
    	String query = "create table if not exists bbs (" + 
    			"  id int unsigned not null auto_increment," + 
    			"  memberId int unsigned not null," + 
    			"  title varchar(50) not null," + 
    			"  date datetime not null default current_timestamp," + 
    			"  content varchar(400)," + 
    			"  primary key(id)," + 
    			"  foreign key(memberId) references member(id)" + 
    			") default charset=utf8;";
    	PreparedStatement pStmt = null;
    	try {
			pStmt = conn.prepareStatement(query);
			
			pStmt.execute();
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
    
    public int getCount() {
		String query = "select count(*) from bbs;";
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
    
	public BbsDTO selectOne(int id) {
		String query = "select * from bbs where id=?;";
		PreparedStatement pStmt = null;
		BbsDTO bDto = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				bDto.setId(rs.getInt(1));
				bDto.setMemberId(rs.getInt(2));
				bDto.setTitle(rs.getString(3));
				bDto.setDate(rs.getString(4));
				bDto.setContent(rs.getString(5));
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
		return bDto;
	}
	
	public BbsMember read(int id) {
		String query = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " +
				"inner join member on bbs.memberId=member.id where bbs.id=?;";
		PreparedStatement pStmt = null;
		BbsMember bDto = new BbsMember();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				bDto.setId(rs.getInt(1));
				bDto.setTitle(rs.getString(2));
				bDto.setName(rs.getString(3));
				bDto.setDate(rs.getString(4).substring(0, 16));
				bDto.setContent(rs.getString(5));
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
		return bDto;
	}
	
	public List<BbsMember> selectJoinAll(int page) {
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc;";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<BbsMember> bmList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4).substring(0, 16));
				bmList.add(bmDto);
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
		return bmList;
	}
	 
    public List<BbsMember> selectAll() {
		String sql = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " +
				"inner join member on bbs.memberId=member.id;";
		PreparedStatement pStmt= null;
		List<BbsMember> memberList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				BbsMember members = new BbsMember();
				members.setId(rs.getInt(1));		
				members.setTitle(rs.getString(2));
				members.setName(rs.getString(3));
				members.setDate(rs.getString(4));
				members.setContent(rs.getString(5));
				memberList.add(members);
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
		return memberList;
	}
    
    public List<BbsMember> selectCondition(String query) {
		PreparedStatement pStmt= null;
		List<BbsMember> memberList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				BbsMember members = new BbsMember();
				members.setId(rs.getInt(1));		
				members.setName(rs.getString(2));
				members.setTitle(rs.getString(3));
				members.setContent(rs.getString(4));
				members.setDate(rs.getString(5).substring(0, 16));
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
	
	public void updateBbs(BbsMember bDto) {
		PreparedStatement pStmt = null;
		//String date = getCurrentDBTime();
		String query = "update bbs set title=?, date=now(), content=? where id=?;";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, bDto.getTitle());
			//pStmt.setString(2, date);
			pStmt.setString(2, bDto.getContent());
			pStmt.setInt(3, bDto.getId());
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
	
	public void insertBbs(BbsDTO dto) {
		PreparedStatement pStmt = null;
		String query = "insert into bbs (memberId, title, content) values(?, ?, ?);";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, dto.getMemberId());
			pStmt.setString(2, dto.getTitle());
			pStmt.setString(3, dto.getContent());		
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
	
	public void deleteBbs(int id) {
		String query = "delete from bbs where id=?;";
		PreparedStatement pStmt = null;
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
	
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception se1) { }
	}
}