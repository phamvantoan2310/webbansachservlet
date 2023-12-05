<%@page import="model.quantrivien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tác giả</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
	integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
	crossorigin="anonymous"></script>

<style>
.red {
	color: red;
}
</style>
</head>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();

String baoLoi = request.getAttribute("baoLoi") + "";
baoLoi = (baoLoi.equals("null")) ? "" : baoLoi;
%>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- end header -->

<%
	Object obj = session.getAttribute("qtv");
	quantrivien qtv = null;
	if (obj != null) {
		qtv = (quantrivien) obj;
	}
	if (qtv == null) {
%>
	<p>chưa đăng nhập</p>
<%
	} else {
%>


	<p class="red"><%=baoLoi%></p>

	<div class="container">
		<form action="<%=url%>/tacGiaController" method="post">
			<input type="hidden" name="controller" value="themtacgia"
				id="controller" />
			<h1 class="text-center">Nhập thông tin tác giả</h1>
			<div class="input-group mb-4">
				<span class="input-group-text" id="basic-addon1">Tên tác giả</span>
				<input type="text" class="form-control"
					placeholder="nhập tên tác giả" aria-label="Username"
					aria-describedby="basic-addon1" name="tenTacGia">
			</div>
			<div class="mb-3">
				<label for="ngaySinh" class="form-label">Ngày sinh</label> <input
					type="date" class="form-control" id="ngaySinh" name="ngaySinh"
					value="">
			</div>

			<button type="submit" class="btn btn-primary">Thêm tác giả</button>
		</form>
	</div>

<%
	}
%>

	<!-- footer -->
	<%@ include file="footer.jsp"%>
	<!-- end footer -->

</body>
</html>