<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="viewgraph" scope="session" class="fr.paris.lutece.plugins.graphite.web.ViewGraphJspBean" />

<% viewgraph.init( request, viewgraph.RIGHT_VIEWGRAPH ); %>
<%= viewgraph.getViewGraphHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
