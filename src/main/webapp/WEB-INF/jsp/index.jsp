<%-- 
    Document   : index
    Created on : Apr 20, 2016, 10:55:39 AM
    Author     : vahan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

<jsp:include page="header.jsp" />

<h4>Spring 4 Web MVC via Annotations</h4>
<div class="row">
    <div class="col-lg-2">
        <c:forEach var="table" items="${tables}">
            <p><a href="?tablename=${table.getNameAsString()}">${table.getNameAsString()}</a></p>
        </c:forEach>
    </div>    
    <div class="col-lg-8">
        <h2>${tablename}</h2>                 
        <c:forEach var="value" items="${values}">                   
            <p>RAW ID as toString = ${value.getRow()}</p>            
            <p>RAW ID as IntStr    = ${ct:bytesToIntStr(value.getRow())}</p>
            <p>RAW ID as HEX    = ${ct:bytesToHex(value.getRow())}</p>
            <p>RAW ID as string = ${ct:byteAsString(value.getRow())}</p>

        </c:forEach>                        
    </div>        
</div>    
<jsp:include page="footer.jsp" />

