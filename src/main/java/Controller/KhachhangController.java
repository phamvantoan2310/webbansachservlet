package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import DAO.khachhangDAO;
import model.khachhang;
import until.guiMail;
import until.maHoaMatKhau;
import until.taoMaXacThuc;

/**
 * Servlet implementation class KhachhangController
 */
public class KhachhangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachhangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controller = request.getParameter("controller");
		if(controller.equals("dangxuat")) {
			dangXuatController(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controller = request.getParameter("controller");
		if(controller.equals("dangnhap")) {
			dangNhapController(request,response);
		}
		
		if(controller.equals("xacthuc")) {
			xacThucController(request, response);
		}
		if(controller.equals("dangky")) {
			dangKyController(request, response);
		}
		if(controller.equals("taomaxacthuc")) {
			taoMaXacThucController(request, response);
		}
		if(controller.equals("doiMatKhau")) {
			doiMatKhauController(request, response);
		}
		if(controller.equals("thaydoithongtin")) {
			thayDoiThongTinController(request, response);
		}
		
	}
	
	
	private void dangNhapController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			
			String url = "";
			String baoLoi = "";
			
			khachhang kh = khachhangDAO.getInstance().selectByTendangnhap(tenDangNhap);
			if(kh == null) {
				baoLoi = "sai ten dang nhap";
				url = "/khachHangFE/dangNhapVaDangKy.jsp";
			}else {
				if(!kh.getMatkhau().equals(maHoaMatKhau.maHoa(matKhau))) {
					baoLoi = "sai mat khau";
					url = "/khachHangFE/dangNhapVaDangKy.jsp";
				}else {
					url = "/khachHangFE/trangchu.jsp";
					HttpSession session = request.getSession();
					session.setAttribute("khachHang", kh);
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
			 
			khachhang kh = khachhangDAO.getInstance().selectByTendangnhap(tenDangNhap);
				
			if(matKhau.equals(matKhauNhapLai)) {
				if(kh != null) {
					baoLoi = "Ten dang nhap da ton tai!";
					url = "/khachHangFE/dangky.jsp";
				}else {
					Date ns = null;
					try {
						ns = Date.valueOf(ngaySinh);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String matkhau = maHoaMatKhau.maHoa(matKhau);
				 
				    khachhang kh1 = new khachhang(1, hoVaTen, ns, diaChiKhachHang, gioiTinh, soDienThoai, email, tenDangNhap, matkhau);
				    
				    khachhangDAO.getInstance().insert(kh1);
				    
				    HttpSession session = request.getSession();
				    session.setAttribute("khachHang", kh1);
				
					url = "/khachHangFE/dangkythanhcong.jsp";
				}
			}else {
				baoLoi = "mat khau khong khop!";
				url = "/khachHangFE/dangky.jsp";
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void taoMaXacThucController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 try {
			 
			 String url = "/khachHangFE/nhapmaxacthuc.jsp";
			 
			 String maxacthuc = taoMaXacThuc.laymaXacThuc();
			 System.out.println(maxacthuc);
			 
			 HttpSession session = request.getSession(false);
			 khachhang kh = (khachhang)session.getAttribute("khachHang");
			 
			 session.setAttribute("maxacthuc", maxacthuc);
			 
			 guiMail.sendEmail(kh.getEmail(), "xác thực tài khoản","đây là mã xác thực của bạn" + maxacthuc + ",mã có hiệu lực trong 5 phút.");
			 
			 long hieulucma = Instant.now().plusSeconds(300).getEpochSecond();
			 session.setAttribute("hieulucma", hieulucma);
			 
			 RequestDispatcher rd = request.getRequestDispatcher(url);
			 rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	
	 private void xacThucController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String baoLoi = "";
			String url = "";
			
			String maXacThucDuocNhap = request.getParameter("maXacThucDuocNhap");
			
			HttpSession session = request.getSession(false);
			String maXacThuc = (String)session.getAttribute("maxacthuc");
			long hieuLucMa = (long)session.getAttribute("hieulucma");
			
			if(hieuLucMa < Instant.now().getEpochSecond()) {
				session.removeAttribute("maxacthuc");
				session.removeAttribute("hieulucma");
				baoLoi = "mã đã hết hiệu lực, vui lòng tạo lại";
				url = "/khachHangFE/nhapmaxacthuc.jsp";
			}else {
				if(maXacThuc.equals(maXacThucDuocNhap)) {
					url = "/khachHangFE/trangchu.jsp";
				}else {
					url = "/khachHangFE/nhapmaxacthuc.jsp";
					baoLoi = "sai mã xác thực, vui lòng nhập lại!";
				}
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
			
			String url = "/khachHangFE/doimatkhau.jsp";
			String baoLoi = "";
			
			HttpSession session = request.getSession(false);
			
			if(matKhauCu.equals(matKhauMoi)) {
				baoLoi = "Mật khẩu mới không được trùng với mật khẩu cũ";
			}else {
				if(!matKhauMoi.equals(matKhauMoiNhapLai)) {
					baoLoi = "Mật khẩu không trùng nhau";
				}else {
					khachhang khachHang1 = (khachhang)session.getAttribute("khachHang");
					if(khachHang1.getMatkhau().equals(maHoaMatKhau.maHoa(matKhauCu))) {
						matKhauMoi = maHoaMatKhau.maHoa(matKhauMoi);
						khachHang1.setMatkhau(matKhauMoi);
						khachhangDAO.getInstance().update(khachHang1);
						baoLoi = "Đổi mật khẩu thành công";
						
						session.removeAttribute("khachHang");
						session.setAttribute("khachHang", khachHang1);
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
  			String url = "/khachHangFE/thongtinkhachhang.jsp";
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
 			khachhang kh = (khachhang)session.getAttribute("khachHang");
 			
 			kh.setTen(hoVaTen);
 			kh.setDiachi(diaChiKhachHang);
 			kh.setEmail(email);
 			kh.setGioitinh(gioiTinh);
 			kh.setTuoi(ns);
 			kh.setSdt(soDienThoai);

 			khachhangDAO.getInstance().update(kh);
 			
 			session.removeAttribute("khachHang");
 			session.setAttribute("khachHang", kh);
 			
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
  			
   			response.sendRedirect(url + "/khachHangFE/dangNhapVaDangKy.jsp");
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
   		
   	}
}
