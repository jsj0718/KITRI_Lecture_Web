package kr.re.kitri.memo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.re.kitri.memo.dao.MemoDAO;
import kr.re.kitri.memo.vo.MemoVO;

@WebServlet("/memo-regist")
public class MemoRegistController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");		// 필터 사용을 통해 UTF-8로 설정 가능
		
		String mdate = request.getParameter("mdate");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemoVO mvo = new MemoVO();
		mvo.setId(id);
		mvo.setMdate(Date.valueOf(mdate));	// 데이트 타입으로 변환 후 값 설정
		mvo.setTitle(title);
		mvo.setMemo(memo);
		
		MemoDAO mdao = new MemoDAO();
		int result = mdao.memoRegist(mvo);
		
		boolean code = false;
		
		if (result > 0) {
			// 성공
			code = true;
		} else {
			// 실패
			code = false;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(code);
//		response.sendRedirect(request.getContextPath() + "/index");
	}

}
