<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import="java.util.*, sec01.ex01.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>회원 정보 출력창</title>
       	<!-- <link rel="stylesheet" href="style.css" />  -->
        <style>
			.cls1{
			    font-size: 40px;
			    text-align: center;
			}
			
			.cls2{
			    font-size: 20px;
			    text-align: center;
			}
			
			table {
				align-content: center;
			    border: 1px;
			}
			
			.tr1 { 
			    align-self: center;
			    background-color: lightgreen;
			}
			
			.tr2 {
			    align-self: center;
			}
			
			td {
			    width: 7%;
			    text-align: center;
			}
        </style>
    </head>
    <body>
        <p class="cls1">회원정보</p>
        <table>
            <tr class="tr1">
                <td><b>아이디</b></td>
                <td><b>비밀번호</b></td>
                <td><b>이름</b></td>
                <td><b>폰번호</b></td>
                <td><b>이메일</b></td>
                <td><b>우편번호</b></td>
                <td><b>도로명 주소</b></td>
                <td><b>기존 주소</b></td>
                <td><b>상세 주소</b></td>
                <td><b>기타 주소</b></td>
                <td><b>가입일</b></td>
                
            </tr>
            <c:choose>
                <c:when test="${membersList == null}">
	            	<tr>
	            		<td colspan=10>
	                		<b>등록된 회원이 없습니다.</b>
	                	</td>
	                </tr>
              	</c:when>
                <c:when test="${membersList != null }">
                	<c:forEach var="mem" items="${membersList }">
	               		<tr class="tr2">
	               			<td>${mem.username }</td>
	               			<td>${mem.password }</td>
	               			<td>${mem.name }</td>
	               			<td>${mem.phone1 }- ${mem.phone2 }- ${mem.phone3 }</td>
	               			<td>${mem.email }</td>
	               			<td>${mem.zipCode }</td>
	               			<td>${mem.strAddress }</td>
	               			<td>${mem.detailedAddress }</td>
	               			<td>${mem.referAddress }</td>
	               			<td>${mem.joinDate }
	               		</tr>
               		</c:forEach>
                </c:when>
            </c:choose>
        </table>
        <a href="#">
        	<p class="cls2">회원 가입하기</p>
        </a>
    </body>
</html>