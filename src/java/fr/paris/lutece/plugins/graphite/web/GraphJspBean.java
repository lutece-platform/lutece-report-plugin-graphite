/*
 * Copyright (c) 2002-2014, Mairie de Paris
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

import fr.paris.lutece.plugins.graphite.business.Category;
import fr.paris.lutece.plugins.graphite.business.CategoryHome;
import fr.paris.lutece.plugins.graphite.business.Graph;
import fr.paris.lutece.plugins.graphite.business.GraphHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage Graph features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageGraphs.jsp", controllerPath = "jsp/admin/plugins/graphite/", right = "GRAPHITE_MANAGEMENT" )
public class GraphJspBean extends ManageGraphJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_GRAPHS="/admin/plugins/graphite/manage_graphs.html";
    private static final String TEMPLATE_CREATE_GRAPH="/admin/plugins/graphite/create_graph.html";
    private static final String TEMPLATE_MODIFY_GRAPH="/admin/plugins/graphite/modify_graph.html";


    // Parameters
    private static final String PARAMETER_ID_GRAPH="id_graph";


    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_GRAPHS = "graphite.manage_graphs.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_GRAPH = "graphite.modify_graph.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_GRAPH = "graphite.create_graph.pageTitle";

    // Markers
    private static final String MARK_GRAPH_LIST = "graph_list";
    private static final String MARK_CATEGORIES_COMBO = "combo_categories";
    private static final String MARK_GRAPH_CATEGORY = "category";
    private static final String MARK_GRAPH = "graph";

    private static final String JSP_MANAGE_GRAPHS = "jsp/admin/plugins/graphite/ManageGraphs.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_GRAPH = "graphite.message.confirmRemoveGraph";
    private static final String PROPERTY_DEFAULT_LIST_GRAPH_PER_PAGE = "graphite.listGraphs.itemsPerPage";
 
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "graphite.model.entity.graph.attribute.";

    // Views
    private static final String VIEW_MANAGE_GRAPHS = "manageGraphs";
    private static final String VIEW_CREATE_GRAPH = "createGraph";
    private static final String VIEW_MODIFY_GRAPH = "modifyGraph";

    // Actions
    private static final String ACTION_CREATE_GRAPH = "createGraph";
    private static final String ACTION_MODIFY_GRAPH = "modifyGraph";
    private static final String ACTION_REMOVE_GRAPH = "removeGraph";
    private static final String ACTION_CONFIRM_REMOVE_GRAPH = "confirmRemoveGraph";

    // Infos
    private static final String INFO_GRAPH_CREATED = "graphite.info.graph.created";
    private static final String INFO_GRAPH_UPDATED = "graphite.info.graph.updated";
    private static final String INFO_GRAPH_REMOVED = "graphite.info.graph.removed";
    
    // Session variable to store working values
    private Graph _graph;
    
    @View( value = VIEW_MANAGE_GRAPHS, defaultView = true )
    public String getManageGraphs( HttpServletRequest request )
    {
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_GRAPH_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_MANAGE_GRAPHS );
        String strUrl = url.getUrl(  );
        List<Graph> listGraphs = (List<Graph>) GraphHome.getGraphsList(  );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( listGraphs, _nItemsPerPage, strUrl,
                PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = getModel(  );

        model.put( MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_GRAPH_LIST, paginator.getPageItems(  ) );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_GRAPHS, TEMPLATE_MANAGE_GRAPHS, model );
    }

    /**
     * Returns the form to create a graph
     *
     * @param request The Http request
     * @return the html code of the graph form
     */
    @View( VIEW_CREATE_GRAPH )
    public String getCreateGraph( HttpServletRequest request )
    {
        _graph = ( _graph != null ) ? _graph : new Graph(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_GRAPH, _graph );
        model.put( MARK_CATEGORIES_COMBO, getComboCategories());

        return getPage( PROPERTY_PAGE_TITLE_CREATE_GRAPH, TEMPLATE_CREATE_GRAPH, model );
    }

    /**
     * Process the data capture form of a new graph
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_GRAPH )
    public String doCreateGraph( HttpServletRequest request )
    {
        populate( _graph, request );

        // Check constraints
        if ( !validateBean( _graph, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_GRAPH );
        }

        GraphHome.create( _graph );
        _graph = null;
        addInfo( INFO_GRAPH_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_GRAPHS );
    }

    /**
     * Manages the removal form of a graph whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_GRAPH )
    public String getConfirmRemoveGraph( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_GRAPH ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_GRAPH ) );
        url.addParameter( PARAMETER_ID_GRAPH, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_GRAPH,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a graph
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage graphs
     */
    @Action( ACTION_REMOVE_GRAPH )
    public String doRemoveGraph( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_GRAPH ) );
        GraphHome.remove( nId );
        addInfo( INFO_GRAPH_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_GRAPHS );
    }

    /**
     * Returns the form to update info about a graph
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_GRAPH )
    public String getModifyGraph( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_GRAPH ) );

        _graph = GraphHome.findByPrimaryKey( nId );

        Map<String, Object> model = getModel(  );
        model.put( MARK_GRAPH, _graph );
        model.put( MARK_GRAPH_CATEGORY, _graph.getGraphCategory() );
        model.put( MARK_CATEGORIES_COMBO, getComboCategories());

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_GRAPH, TEMPLATE_MODIFY_GRAPH, model );
    }

    /**
     * Process the change form of a graph
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_GRAPH )
    public String doModifyGraph( HttpServletRequest request )
    {
        populate( _graph, request );

        // Check constraints
        if ( !validateBean( _graph, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_GRAPH, PARAMETER_ID_GRAPH, _graph.getIdGraph(  ) );
        }

        GraphHome.update( _graph );
        _graph = null;
        addInfo( INFO_GRAPH_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_GRAPHS );
    }
    
    /**
    * Returns a referenceList of the categories
    * @return a referenceList of the categories
    */
    public static ReferenceList getComboCategories()
    {
        ReferenceList list = new ReferenceList(  );
        List<Category> listCategorys = (List<Category>) CategoryHome.getCategorysList(  );
        
        for ( Category c : listCategorys)
        {
            list.addItem( c.getIdCategory(  ), c.getCategoryTitle(  ) );
        }
        return list;
    }
}
