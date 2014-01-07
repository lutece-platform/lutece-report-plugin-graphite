<jsp:useBean id="viewgraphGraph" scope="session" class="fr.paris.lutece.plugins.graphite.web.ViewJspBean" />
<% String strContent = viewgraphGraph.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
