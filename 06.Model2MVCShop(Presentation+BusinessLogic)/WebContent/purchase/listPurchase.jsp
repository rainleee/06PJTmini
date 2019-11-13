<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--	
	List<Purchase> list = (List<Purchase>) request.getAttribute("list");
	
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search) request.getAttribute("search");
	
	//유효성 체크부분. null일경우 CommonUtil의 static method 실행
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	
	//얘가 필요한지 확인해보기
	String menu = request.getParameter("menu");
--%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript">

 function fncGetList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
  	document.detailForm.submit();		
} 

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%--	
		
		for (int i = 0; i < list.size(); i++) {
			Purchase purchase = list.get(i);
			
	--%>
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${i + 1}" />
		<tr class="ct_list_pop">
			<td align="center">
				<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${i}</a>
			</td>
			
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
			</td>
			
			<td></td>
			<td align="left">${purchase.buyer.userName}</td>
			<td></td>
			<td align="left">${purchase.buyer.phone}</td>
			<td></td>
			<td align="left">
				
				<!-- 코드번호가 1번일때, 즉 구매만 완료 -->
				<c:if test="${fn:trim(purchase.tranCode) == '1'}">
				 	판매자가 배송을 준비중입니다.
				</c:if>
				
				<!-- 코드번호 2번, 배송진행중, 제품 수령 후 수령버튼 누르기 -->
				<c:if test="${fn:trim(purchase.tranCode) == '2'}">
				 	배송 진행 중, 물건을 받으면 제품수령을 눌러주세요.
				</c:if>
				
				<!-- 코드번호 3번, 제품 수령후 맘에들면 구매확정 누르기 -->
				<c:if test="${fn:trim(purchase.tranCode) == '3'}">
				 	배송이 완료되었습니다. 구매확정을 눌러주세요.
				</c:if>
				
			</td>
			<td></td>
			<td align="left">
			
			<!-- 코드1, 구매완료 후 배송준비 기다림(admin에서 눌러야됨) -->
			<c:if test="${fn:trim(purchase.tranCode) == '1'}">
			 	구매완료, 판매자 배송준비 중
			</c:if>
			
			<!-- 코드2, 제품 수령 후 수령확정 누르기 -->
			<c:if test="${fn:trim(purchase.tranCode) == '2'}">
			 	배송중 <a href="/updateTranCode.do?tranNo=${purchase.tranNo}&tranCode=3&currentPage=${resultPage.currentPage}">제품수령</a>
			</c:if>
			
			<!-- 코드3, 제품수령 버튼을 누르면 구매확정 누르기 -->
			<c:if test="${fn:trim(purchase.tranCode) == '3'}">
			 	구매확정</a>
			</c:if>
			
			</td>
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
		
	</c:forEach>
	<%--
		}
	--%>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
		
		<!-- page navigation의 반복적인 c&p로 공통모듈로 빼놓음 -->
		<jsp:include page="../common/pageNavigator.jsp"/>
		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>