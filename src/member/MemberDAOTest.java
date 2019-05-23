package member;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MemberDAOTest {
	private MemberDAO mDao = new MemberDAO();
	
	@Test
	public void verifyIdPassword() {
		int result = mDao.verifyIdPassword(100001, "1234");
		assertEquals(MemberDAO.ID_PASSWORD_MATCH, result);
	}
	
}
