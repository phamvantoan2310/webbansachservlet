package Controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAO.quantrivienDao;

import model.quantrivien;
import until.maHoaMatKhau;

/**
 * Servlet implementation class quanTriVienController
 */
public class quanTriVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quanTriVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controller = request.getParameter("controller");
		if(controller.equals("dangnhap")) {
			dangNhapController(request,response);
		}
		if(controller.equals("dangky")) {
			dangKyController(request, response);
		}
		if(controller.equals("doiMatKhau")) {
			doiMatKhauController(request, response);
		}
		if(controller.equals("thaydoithongtin")) {
			thayDoiThongTinController(request, response);
		}
		if(controller.equals("dangxuat")) {
			dangXuatController(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
    private void dangNhapController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			
			String url = "";
			String baoLoi = "";
			
			quantrivien qtv =quantrivienDao.getInstance().selectByTendangnhap(tenDangNhap);
			if(qtv == null) {
				baoLoi = "sai ten dang nhap";
				url = "/quanTriVienFE/dangnhapvadangkyquantrivien.jsp";
			}else {
				if(!qtv.getMatkhau().equals(maHoaMatKhau.maHoa(matKhau))) {
					baoLoi = "sai mat khau";
					url = "/quanTriVienFE/dangnhapvadangkyquantrivien.jsp";
				}else {
					url = "/quanTriVienFE/trangchu.jsp";
					HttpSession session = request.getSession();
					session.setAttribute("qtv", qtv);
				}
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
    private void dangKyController (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
		    String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String soDienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			
			String baoLoi = "";
			String url = "";
			 
			quantrivien qtv =quantrivienDao.getInstance().selectByTendangnhap(tenDangNhap);
				
			if(matKhau.equals(matKhauNhapLai)) {
				if(qtv != null) {
					baoLoi = "Ten dang nhap da ton tai!";
					url = "/quanTriVienFE/dangkyquantrivien.jsp";
				}else {
					Date ns = null;
					try {
						ns = Date.valueOf(ngaySinh);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String matkhau = maHoaMatKhau.maHoa(matKhau);
				 
				    quantrivien qtv1 = new quantrivien(1, hoVaTen, diaChiKhachHang, gioiTinh, ns, soDienThoai, email, tenDangNhap, matkhau);
				    
				    quantrivienDao.getInstance().insert(qtv1);
				    
				    HttpSession session = request.getSession();
				    session.setAttribute("qtv", qtv1);
				
					url = "/quanTriVienFE/trangchu.jsp";
				}
			}else {
				baoLoi = "mat khau khong khop!";
				url = "/quanTriVienFE/dangkyquantrivien.jsp";
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private void doiMatKhauController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
 		try {
			String matKhauCu = request.getParameter("matKhauCu");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");
			
			String url = "/quanTriVienFE/doimatkhau.jsp";
			String baoLoi = "";
			
			HttpSession session = request.getSession(false);
			
			if(matKhauCu.equals(matKhauMoi)) {
				baoLoi = "Mật khẩu mới không được trùng với mật khẩu cũ";
			}else {
				if(!matKhauMoi.equals(matKhauMoiNhapLai)) {
					baoLoi = "Mật khẩu không trùng nhau";
				}else {
					quantrivien qtv1 = (quantrivien)session.getAttribute("qtv");
					if(qtv1.getMatkhau().equals(maHoaMatKhau.maHoa(matKhauCu))) {
						matKhauMoi = maHoaMatKhau.maHoa(matKhauMoi);
						qtv1.setMatkhau(matKhauMoi);
						quantrivienDao.getInstance().update(qtv1);
						baoLoi = "Đổi mật khẩu thành công";
						
						session.removeAttribute("qtv");
						session.setAttribute("qtv", qtv1);
					}else {
						baoLoi = "Sai mật khẩu";
					}
				}
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 	}
    
    private void thayDoiThongTinController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
  		try {
  			String url = "/quanTriVienFE/thongtinquantrivien.jsp";
 			String baoLoi = "đổi thông tin thành công";
 			
  			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String soDienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			
			Date ns = null;
 			try {
				ns = Date.valueOf(ngaySinh);
			} catch (Exception e) {
				e.printStackTrace();
			}
 			
 			HttpSession session = request.getSession(false);
 			quantrivien qtv = (quantrivien)session.getAttribute("qtv");
 			
 			qtv.setTen(hoVaTen);
 			qtv.setDiachi(diaChiKhachHang);
 			qtv.setEmail(email);
 			qtv.setGioitinh(gioiTinh);
 			qtv.setNgaysinh(ns);
 			qtv.setSdt(soDienThoai);

 			quantrivienDao.getInstance().update(qtv);
 			
 			session.removeAttribute("qtv");
 			session.setAttribute("qtv", qtv);
 			
 			request.setAttribute("baoLoi", baoLoi);
 			RequestDispatcher rd = request.getRequestDispatcher(url);
 			rd.forward(request, response);
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
  		
  	}
    
    private void dangXuatController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   		try {
   			HttpSession session = request.getSession(false);
   			if(session!=null) {
   				session.invalidate();
   			}
   			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()	;
  			
   			response.sendRedirect(url + "/quanTriVienFE/dangnhapvadangkyquantrivien.jsp");
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
   		
   	}

}
