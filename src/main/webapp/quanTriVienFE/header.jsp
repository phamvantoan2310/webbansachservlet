<%@page import="model.quantrivien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.khachhang"%>  

<%String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()	;%>


<!-- Navbar -->
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="<%=url %>/img/Bold Green Neon Ninja Logo.png"
				alt="Bootstrap" height="50">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=url%>/quanTriVienFE/trangchu.jsp">Trang chủ</a></li>
				</ul>
					
					<%
					    Object obj = session.getAttribute("qtv");
					    quantrivien qtv = null;
					    if(obj != null){
					    	qtv = (quantrivien)obj;
					    }
					    if(qtv == null){
					%>    	
					    	<a class="btn btn-primary" style="white-space: nowrap;" href="dangnhapvadangkyquantrivien.jsp">Đăng nhập</a>
					<%
					    }else{	
					    	
					%>    	
					    <ul class="navbar-nav mb-2 mb-lg-0 bg-infor ">
						<li class="nav-item dropdown dropstart"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Tài khoản</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Thông báo</a></li>
								<li><a class="dropdown-item" href="<%=url %>/quanTriVienFE/thongtinquantrivien.jsp">Thông
										tin</a></li>
								<li><a class="dropdown-item" href="<%=url %>/quanTriVienFE/doimatkhau.jsp">Đổi
										mật khẩu</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="<%=url %>/quanTriVienController?controller=dangxuat">Đăng Xuất</a></li>
							</ul></li>
					    </ul>	
					<%				
					    }
					%>
			</div>
		</div>
	</nav>
	<!-- End Navbar -->