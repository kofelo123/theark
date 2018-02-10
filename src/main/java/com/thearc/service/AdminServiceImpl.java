package com.thearc.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;
import com.thearc.persistence.AdminDAO;
import com.thearc.util.EncryptUtil;

@Service
public class AdminServiceImpl implements AdminService {
	

	@Inject
	private EncryptUtil encrypt;
	
	@Inject
	private AdminDAO dao;

	@Override
	public List<UserVO> listuser() throws Exception {
		// TODO Auto-generated method stub
		return dao.listuser();
	}

	@Override
	public UserVO adminlogin(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		return dao.adminlogin(user);
	}

	@Override
	public void authmodify(UserVO user) throws Exception {
		dao.authmodify(user);
	}

	@Override
	public void userDrop(UserVO user) throws Exception {
		dao.userDrop(user);
	}

	@Override
	public List<String> dayBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.dayBoard();
	}

	@Override
	public List<String> dayReply() throws Exception {
		// TODO Auto-generated method stub
		return dao.dayReply();
	}

	@Override
	public List<String> cateBoardview() throws Exception {
		// TODO Auto-generated method stub
		return dao.cateBoardview();
	}

	@Override
	public List<String> weekcateBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.weekcateBoard();
	}

	@Override
	public List<String> weekReplyCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.weekReplyCount();
	}

	@Override
	public void boardDrop(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.boardDrop(board);
	}

}
