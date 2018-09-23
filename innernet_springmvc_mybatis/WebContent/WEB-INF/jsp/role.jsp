<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>role页面</title>
</head>
<body>
	<div align="center">
		<h1>Enrollment Form</h1>
		<form method="post"  action="role/insert">
			<div>
				<label for="roleId">roleId</label>
				<div>
					<input type="text" name="roleId" />
				</div>
			</div>
			
			<div>
				<label for="roleName">roleName</label>
				<div>
					<input type="text" name="roleName" />
				</div>
			</div>
			
			<div>
				<label for="roleNote">roleNote</label>
				<div>
					<input type="text" name="RoleNote" />
				</div>
			</div>
			
			<div >
			<div >
				<input type="submit" value="添加" >
			</div>
		</div>
			
		</form>
	</div>
	
	
</body>
</html>