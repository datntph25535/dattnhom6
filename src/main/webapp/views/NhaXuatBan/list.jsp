<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nhà xuất bản</title>
    <style>

    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3 style="text-align: center;">List nhà xuất bản</h3>
    <br>
    <%--    <div class="" style="margin-top: 30px; margin-bottom: 30px">--%>
    <%--        <h1>Tìm kiếm cỡ giầy</h1>--%>
    <%--        <form action="${pageContext.request.contextPath}/CoGiay/search" method="GET">--%>
    <%--            <input style="width: 400px" type="text" id="tenCoGiay" name="tenCoGiay">--%>
    <%--            <button type="submit">Tìm kiếm</button>--%>
    <%--        </form>--%>
    <%--    </div>--%>

<%--    <a href="/MauSac/list">--%>
<%--        <button>Back</button>--%>
<%--    </a>--%>
    <%--    <div class="thongBao">--%>
    <%--        &lt;%&ndash;           Tìm kiếm thành công &ndash;%&gt;--%>
    <%--        <label>${messageDone}</label>--%>
    <%--        &lt;%&ndash;            Không tìm thấy id có mã&ndash;%&gt;--%>
    <%--        <label>${messageFind}</label>--%>
    <%--        &lt;%&ndash;            Không tìm thầy sản phẩm&ndash;%&gt;--%>
    <%--        <label>${messageErFind}</label>--%>
            <label>${message}</label>

    <%--    </div>--%>

    <div><a href="${pageContext.request.contextPath}/NhaXuatBan/create" class="btn btn-primary">Thêm nhà xuất bản</a></div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Tên nhà xuất bản</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Functions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="nxb" items="${listPage}" varStatus="i">
            <tr>
                <th style="padding-top: 40px" scope="row">${i.index+1}</th>
                <td style="padding-top: 55px; text-align: center">${nxb.ten}</td>
                <td style="padding-top: 55px; text-align: center">${nxb.moTa}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/NhaXuatBan/edit/${nxb.id}"><button class="btn btn-primary">Edit</button></a>
                    <a href="${pageContext.request.contextPath}/NhaXuatBan/delete/${nxb.id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination" style="margin-left: 500px">
        <c:forEach begin="1" end="${totalPage}" varStatus="status">
            <li class="page-item">
                <a href="${pageContext.request.contextPath}/NhaXuatBan/list?pageNumber=${status.index}" class="page-link">${status.index}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>