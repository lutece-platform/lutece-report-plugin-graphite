
<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../PortletAdminHeader.jsp" />

<jsp:useBean id="GraphitePortlet" scope="session" class="fr.paris.lutece.plugins.graphite.web.portlet.GraphitePortletJspBean" />

<% GraphitePortlet.init( request, GraphitePortlet.RIGHT_MANAGE_ADMIN_SITE); %>
<%= GraphitePortlet.getCreate ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>


