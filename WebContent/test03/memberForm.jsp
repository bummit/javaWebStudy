<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<fmt:requestEncoding value="utf-8"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 가입창</title>
	</head>
	<body>
		<form method="post" action="${contextPath }/member/addMember.do">
			
			<table align="center">
			
				<tr>
					<td><p align="right">아이디</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td><p align="right">비밀번호</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><p align="right">이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td><p align="right">전화번호</td>
					<td><input type="text" name="phone1">-<input type="text" name="phone2">-<input type="text" name="phone3"></td>
				</tr>
				<tr>
					<td><p align="right">이메일</td>
					<td><input type="text" name="email"></td>
				</tr>		
				<tr>
					<td><p align="right">우편번호</td>
					<td><input type="text" name="zipCode"></td>
				</tr>						
				<tr>
					<td><p align="right">도로명 주소</td>
					<td><input type="text" name="strAddress"></td>
				</tr>
				<tr>
					<td><p align="right">지번 주소</td>
					<td><input type="text" name="address"></td>
				</tr>				
				<tr>
					<td><p align="right">상세 주소</td>
					<td><input type="text" name="detailedAddress"></td>
				</tr>
				<tr>
					<td><p align="right">기타 주소</td>
					<td><input type="text" name="referAddress"></td>
				</tr>
				<tr><td><p>&nbsp;</p></td>
					<td><input type="submit" value="가입하기">
						<input type="reset" value="다시입력">
					</td>				
			</table>
		</form>
	</body>
</html>























