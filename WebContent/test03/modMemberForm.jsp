<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 정보 수정창</title>
		<style>
			.cls{
				font-size:40px;
				text-align:center;
			}
		</style>
	</head>
	<body>
		<h1 class="cls1">회원 정보 수정창</h1>
		<form method="post" action="${contextPath }/member/modMember.do?username=${memInfo.username }">
			
			<table align="center">
			
				<tr>
					<td><p align="right">아이디</td>
					<td><input type="text" name="username" value="${memInfo.username }"></td>
				</tr>
				<tr>
					<td><p align="right">비밀번호</td>
					<td><input type="password" name="password" value="${memInfo.password }" ></td>
				</tr>
				<tr>
					<td><p align="right">이름</td>
					<td><input type="text" name="name" value="${memInfo.name}"></td>
				</tr>
				<tr>
					<td><p align="right">전화번호</td>
					<td><input type="text" name="phone1" value="${memInfo.phone1}">-<input type="text" name="phone2" value="${memInfo.phone2}">-<input type="text" name="phone3" value="${memInfo.phone3}"></td>
				</tr>
				<tr>
					<td><p align="right">이메일</td>
					<td><input type="text" name="email" value="${memInfo.email}"></td>
				</tr>		
				<tr>
					<td><p align="right">우편번호</td>
					<td><input type="text" name="zipCode" value="${memInfo.zipCode}"></td>
				</tr>						
				<tr>
					<td><p align="right">도로명 주소</td>
					<td><input type="text" name="strAddress" value="${memInfo.strAddress}"></td>
				</tr>
				<tr>
					<td><p align="right">지번 주소</td>
					<td><input type="text" name="address" value="${memInfo.address}"></td>
				</tr>				
				<tr>
					<td><p align="right">상세 주소</td>
					<td><input type="text" name="detailedAddress" value="${memInfo.detailedAddress}"></td>
				</tr>
				<tr>
					<td><p align="right">기타 주소</td>
					<td><input type="text" name="referAddress" value="${memInfo.referAddress}"></td>
				</tr>
				<tr>
					<td><p align="right">가입일</td>
					<td><input type="text" name="joinDate" value="${memInfo.joinDate}"></td>
				</tr>
				<tr>
					<td colspan ="2">
						<input type="submit" value="수정하기">
						<input type="reset" value="다시입력">
					</td>
				</tr>				
			</table>
		</form>
	</body>
</html>























