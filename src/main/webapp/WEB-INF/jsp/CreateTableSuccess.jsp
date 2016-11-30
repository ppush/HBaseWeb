<%-- 
    Document   : CreateTable
    Created on : May 15, 2016, 11:29:20 AM
    Author     : vahan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<h4>Table Created</h4>
<p class="blue">Name ${tablename}</p>

<p>ColumnFamilys: </p>            
<c:forEach var="ColumnFamily" items="${ColumnFamilys}">
    <p>${ColumnFamily}</p>            
</c:forEach>    
<jsp:include page="footer.jsp" />
