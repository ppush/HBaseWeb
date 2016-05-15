<%-- 
    Document   : CreateTable
    Created on : May 15, 2016, 11:29:20 AM
    Author     : vahan
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp" />
<h4>Create new table</h4>


<form:form method="post" commandName="newTableForm">
    <table>
        <tr>
            <td>TableName:</td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>
        <tr>
            <td>ColumnFamilys:</td>
            <td><form:textarea path="ColumnFamilys" /></td>
            <td><span class="error"><form:errors path="ColumnFamilys" /></span></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Create" /></td>
        </tr>        
    </table>
</form:form>
<jsp:include page="footer.jsp" />
