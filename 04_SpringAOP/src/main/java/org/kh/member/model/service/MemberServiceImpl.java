package org.kh.member.model.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.kh.member.common.Log4jAdvice;
import org.kh.member.common.LogAdvice;
import org.kh.member.model.dao.MemberDAOImpl;
import org.kh.member.model.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	

	@Resource(name = "memberDAO")
	private MemberDAOImpl memberDAO;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //root-context.xml
	
	
	
	@Override
	public Member selectOneMember(Member vo) {
		System.out.println("비즈니스 로직 호출");
//		Connection conn = getConnection();
		Member m = memberDAO.selectOneMember(jdbcTemplate,vo);
		
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return m;
	}

	public int updateMember(Member vo) {
		System.out.println("비즈니스 로직 호출");
		int result = memberDAO.updateMember(jdbcTemplate,vo);
		
		return result;
	}

	public int insertMember(Member m) {
		System.out.println("비즈니스 로직 호출");
		int result = memberDAO.insertMember(jdbcTemplate,m);
		
		return result;
	}

	public int deleteMember(String userId) {
		System.out.println("비즈니스 로직 호출");
		int result = memberDAO.deleteMember(jdbcTemplate,userId);
		return result;
	}

	public ArrayList<Member> memberList() {
		System.out.println("비즈니스 로직 호출");
		ArrayList<Member>list = memberDAO.memberList(jdbcTemplate);

		return list;
	}
	
//	public Connection getConnection() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","spring","spring");
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conn;
//	}

}
