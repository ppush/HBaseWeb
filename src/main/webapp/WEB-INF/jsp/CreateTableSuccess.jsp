<%-- 
    Document   : CreateTable
    Created on : May 15, 2016, 11:29:20 AM
    Author     : vahan
--%>

<jsp:include page="header.jsp" />
<h4>Table Created</h4>
<span class="blue">${tablename}</span>
<span class="blue">${ColumnFamilys}</span>
<c:forEach var="ColumnFamily" items="${ColumnFamilys}">
    <p>${ColumnFamily}</p>            
</c:forEach>
<jsp:include page="footer.jsp" />
