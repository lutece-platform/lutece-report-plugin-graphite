<jsp:useBean id="managegraphGraph" scope="session" class="fr.paris.lutece.plugins.graphite.web.GraphJspBean" />
<% String strContent = managegraphGraph.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
