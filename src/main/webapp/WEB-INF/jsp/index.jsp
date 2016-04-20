<%-- 
    Document   : index
    Created on : Apr 20, 2016, 10:55:39 AM
    Author     : vahan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="header.jsp" />

        <h4>Spring 4 Web MVC via Annotations</h4>
        Spring says: <span class="blue">${msg}</span>        
        <p><a class="btn btn-lg btn-success" href="<c:url value="/page2"/>">Go to Page2</a></p>

    <jsp:include page="footer.jsp" />

