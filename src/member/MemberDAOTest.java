package member;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberDAOTest {
	private MemberDAO mDao = new MemberDAO();
	
	@Before
	public void beforeTest() {
		mDao = new MemberDAO();
		System.out.println("beforeTest()");
	}
	
	@Test		//아이디, 비밀번호 일치
	public void verifyIdPassword() {
		int result = mDao.verifyIdPassword(100001, "1234");
		assertEquals(MemberDAO.ID_PASSWORD_MATCH, result);
	}

	@After
	public void afterTest() {
		mDao = new MemberDAO();
		System.out.println("afterTest()");
	}
}
