<%-- 
    Document   : CreateTable
    Created on : May 15, 2016, 11:29:20 AM
    Author     : vahan
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

<jsp:include page="header.jsp" />
<h4>Add new to  table ${table.getNameAsString()}</h4>
${error}

<form:form method="post" commandName="newTableForm">
    <table>
        <tr>
            <td>Table Row qualifier:</td>
            <td><input name="raw" type="text" value=""/></td>

        </tr>
        <tr>
            <td colspan="2">ColumnFamilys:</td>                        
        </tr>   
        <c:forEach var="colfamily" items="${table.getColumnFamilies()}">
            <tr>
                <td>${ct:byteAsString(colfamily.getName())}</td>
                <td><textarea name = "${ct:byteAsString(colfamily.getName())}" ></textarea> </td>

            </tr>
        </c:forEach>                                


        <tr>
            <td colspan="3"><input type="submit" value="Add" /></td>
        </tr>        
    </table>
</form:form>
<jsp:include page="footer.jsp" />
