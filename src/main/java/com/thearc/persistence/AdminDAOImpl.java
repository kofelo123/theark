package com.thearc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.thearc.mapper.AdminMapper";

	@Override
	public List<UserVO> listuser() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listuser");
	}
	

	@Override
	public UserVO adminlogin(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".adminlogin",user);
	}
	@Override
	public void authmodify(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		if(user.getAuthority().equals("user")){
			user.setAuthority("supporter");
		}else if(user.getAuthority().equals("supporter")){
			user.setAuthority("ban");
		}else{
			user.setAuthority("user");
		}
		 session.update(namespace + ".authmodify",user);
	}


	@Override
	public void userDrop(UserVO user) throws Exception {//fk때문에 자식테이블의 컬럼부터 지우고 부모테이블인 tbl_user 지워줘야한다.
		// TODO Auto-generated method stub
		session.delete(namespace + ".userDropCheck",user);
		session.delete(namespace + ".userDropMessage",user);
		session.delete(namespace + ".userDropUser",user);
	}


	@Override
	public List<String> dayBoard() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".dayBoard");
	}


	@Override
	public List<String> dayReply() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".dayReply");
	}


	@Override
	public List<String> cateBoardview() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".cateBoardview");
	}


	@Override
	public List<String> weekcateBoard() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".weekcateBoard");
	}


	@Override
	public List<String> weekReplyCount() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".weekReplyCount");
	}


	@Override
	public void boardDrop(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace +".boardDrop1",board);
		session.delete(namespace +".boardDrop2",board);///자식테이블(댓글,첨부 선삭제 후 board삭제)
		session.delete(namespace +".boardDrop3",board);
	}
	
	
	
	
	
}
