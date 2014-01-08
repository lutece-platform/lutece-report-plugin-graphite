/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

 
package fr.paris.lutece.plugins.graphite.web;

import fr.paris.lutece.plugins.graphite.business.GraphHome;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;



/**
 * This class provides the user interface to view thegraphs
 */
@Controller( controllerJsp = "ViewGraphs.jsp", controllerPath = "jsp/admin/plugins/graphite/", right = "GRAPHITE_VIEW" )
public class ViewJspBean extends ViewGraphJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants
    private static final String TEMPLATE_PAGE = "/admin/plugins/graphite/graphite.html";
    private static final String PROPERTY_PAGE_TITLE_VIEW_GRAPHS = "graphite.view_graphs.pageTitle";
    private static final String MARKER_GRAPHS_LIST = "graphs_list";
    private static final String VIEW_HOME = "home";
 
    @View( value = VIEW_HOME , defaultView = true )
    public String viewHome( HttpServletRequest request )
    { 
        Map<String, Object> model = getModel();
        model.put( MARKER_GRAPHS_LIST, GraphHome.getGraphsList(  ) );

        return getPage( PROPERTY_PAGE_TITLE_VIEW_GRAPHS, TEMPLATE_PAGE, model );
    }
}
