<jsp:useBean id="managegraphCategory" scope="session" class="fr.paris.lutece.plugins.graphite.web.CategoryJspBean" />
<% String strContent = managegraphCategory.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
