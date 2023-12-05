package Controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.tacgiaDAO;
import model.tacgia;

/**
 * Servlet implementation class tacGiaController
 */

public class tacGiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tacGiaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controller = request.getParameter("controller");
		if(controller.equals("themtacgia")) {
			themTacGiaController(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void themTacGiaController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String ten = request.getParameter("tenTacGia");
		String ngaySinh = request.getParameter("ngaySinh");
		
		String baoLoi = "";
		String url = "/tacGiaFE/themtacgia.jsp";
		
		tacgia tg = tacgiaDAO.getInstance().selectByTen(ten);
		if(tg!=null) {
			baoLoi = "Tác giả đã tồn tại";
		}else {
			Date ns = null;
			try {
				ns = Date.valueOf(ngaySinh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			tacgia tg1 = new tacgia(1, ten, ns, null);
			tacgiaDAO.getInstance().insert(tg1);
			baoLoi = "thêm thành công";
		}
		
		request.setAttribute("baoLoi", baoLoi);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
