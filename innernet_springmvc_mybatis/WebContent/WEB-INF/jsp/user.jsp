<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user页面</title>
</head>
<body>
		<div align="center">
		<h1>Enrollment Form</h1>
		<form method="post"  action="role/insert">
			<div>
				<label for="roleId">userId</label>
				<div>
					<input type="text" name="id" />
				</div>
			</div>
			
			<div>
				<label for="roleName">userName</label>
				<div>
					<input type="text" name="userName" />
				</div>
			</div>
			
			<div>
				<label for="roleNote">userNote</label>
				<div>
					<input type="text" name="userNote" />
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