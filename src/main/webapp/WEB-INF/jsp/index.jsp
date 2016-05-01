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
            <ul>
                <c:forEach var="families" items="${table.getFamilies()}">
                    <li>${ct:byteAsString(families.getName())}</li>
                </c:forEach>     
            </ul>
        </c:forEach>
    </div>    
    <div class="col-lg-8">
        <h2>${tablename}</h2>                 
        <ul>
            <c:forEach var="value" items="${values}">                   
                <li> RAW ID
                    <p>As toString = ${value.getRow()}</p>            
                    <p>As IntStr    = ${ct:bytesToIntStr(value.getRow())}</p>
                    <p>As HEX    = ${ct:bytesToHex(value.getRow())}</p>
                    <p>As string = ${ct:byteAsString(value.getRow())}</p>
                    <ul>
                        <c:forEach var="cell" items="${value.rawCells()}">
                            <li>
                            <p>${ct:byteAsString(cell.getFamily())} : ${ct:byteAsString(cell.getQualifier())} =  ${ct:byteAsString(cell.getValue())}</p>
                            </li>
                        </c:forEach>                        
                    </ul>
                </li>
            </c:forEach>                        
        </ul>    
    </div>        
</div>    
<jsp:include page="footer.jsp" />

