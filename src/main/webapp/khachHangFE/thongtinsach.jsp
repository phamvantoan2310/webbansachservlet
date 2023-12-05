<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>thong tin sach</title>
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
</head>

<%String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()	;%>

<body>

<!-- header -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- end header -->

<div class="container">
   <form action="">
       <div class="text-center"><h1>Thông tin sách</h1></div>
       <hr/>
       <div class="text-center">
				<figure class="figure">
					<img src="<%=url %>/img/Bold Green Neon Ninja Logo.png" class="figure-img img-fluid rounded" alt="...">
					<figcaption class="figure-caption">A caption for the
						above image.</figcaption>
				</figure>
	   </div>
			
	   <table class="table">
  <thead>
    <tr>
      <th scope="col" class="col-2">#</th>
      <th scope="col">Thông tin</th>
     
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Tên sách</th>
      <td>Mark</td>
      
    </tr>
    <tr>
      <th scope="row">Tác giả</th>
      <td>Jacob</td>
      
    </tr>
    <tr>
      <th scope="row">Thể loại</th>
      <td>Jacob</td>
      
    </tr>
    <tr>
      <th scope="row">Năm xuất bản</th>
      <td colspan="2">Larry the Bird</td>
    </tr>
    <tr>
      <th scope="row">Nhà xuất bản</th>
      <td>Jacob</td>
    </tr>
    <tr>
      <th scope="row">Giá</th>
      <td>Jacob</td>
      
    </tr>
  </tbody>
</table>	
			
   </form>
</div>

<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- end footer -->

</body>
</html>