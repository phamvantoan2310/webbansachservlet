package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.Year;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.sachDAO;
import DAO.tacgiaDAO;
import model.sach;
import model.tacgia;

/**
 * Servlet implementation class sachController
 */
public class sachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controller = request.getParameter("controller");
		if(controller.equals("themsach")) {
			themSachController(request, response);
		}
		if(controller.equals("chonsach")) {
			chonSachController(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void themSachController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String baoLoi = "";
		String url = "";
		
		String tenSach = request.getParameter("tenSach");
		String tacGia = request.getParameter("tacGia");
		String theLoai = request.getParameter("theLoai");
		String nhaXuatBan = request.getParameter("nhaXuatBan");
		String namXuatBan = request.getParameter("namXuatBan");
		String gia = request.getParameter("gia");
		
		
		tacgia tg = tacgiaDAO.getInstance().selectByTen(tacGia);
		if(tg.equals(null)) {
			url = "/quanTriVienFE/themsach.jsp";
			baoLoi ="không có tác giả tương ứng, vui lòng thêm tác giả mới";
		}else {
			float Gia = Float.parseFloat(gia);
			int nxb = Integer.valueOf(namXuatBan);
			Year namxuatban = Year.of(nxb);
			
			sach s = new sach(1, tenSach, theLoai, namxuatban, nhaXuatBan, Gia, null, tg);
			sachDAO.getInstance().insert(s);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("sach", s);
			
			
			url = "/sachFE/themanhsach.jsp";
			baoLoi ="thêm sách thành công";
		}
		
		
		
		
		request.setAttribute("baoLoi", baoLoi);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
private void chonSachController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		String url = "/sachFE/chonsachdesua.jsp";
		
		List<sach> arr = sachDAO.getInstance().selectAll();
		
		
		request.setAttribute("arr", arr);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
