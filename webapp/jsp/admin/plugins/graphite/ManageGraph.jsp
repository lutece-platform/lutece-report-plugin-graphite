<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="managegraph" scope="session" class="fr.paris.lutece.plugins.graphite.web.ManageGraphJspBean" />

<% managegraph.init( request, managegraph.RIGHT_MANAGEGRAPH ); %>
<%= managegraph.getManageGraphHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
