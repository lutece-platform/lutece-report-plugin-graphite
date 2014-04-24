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
import fr.paris.lutece.portal.business.role.RoleHome;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.service.workgroup.AdminWorkgroupService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Locale;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage Category features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageCategorys.jsp", controllerPath = "jsp/admin/plugins/graphite/", right = "GRAPHITE_MANAGEMENT" )
public class CategoryJspBean extends ManageGraphJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_CATEGORYS="/admin/plugins/graphite/manage_categorys.html";
    private static final String TEMPLATE_CREATE_CATEGORY="/admin/plugins/graphite/create_category.html";
    private static final String TEMPLATE_MODIFY_CATEGORY="/admin/plugins/graphite/modify_category.html";


    // Parameters
    private static final String PARAMETER_ID_CATEGORY="id_category";


    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_CATEGORYS = "graphite.manage_categorys.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_CATEGORY = "graphite.modify_category.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_CATEGORY = "graphite.create_category.pageTitle";

    // Markers
    private static final String MARK_CATEGORY_LIST = "category_list";
    private static final String MARK_ROLES_COMBO = "combo_roles";
    private static final String MARK_WORKGROUPS_COMBO = "combo_workgroups";
    private static final String MARK_CATEGORY = "category";
    private static final String MARK_CATEGORY_ROLE = "role";
    private static final String MARK_CATEGORY_WORKGROUP = "workgroup";
    private static final String MARK_CATEGORY_FRONT = "front";
    private static final String MARK_CATEGORY_BACK = "back";

    private static final String JSP_MANAGE_CATEGORYS = "jsp/admin/plugins/graphite/ManageCategorys.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_CATEGORY = "graphite.message.confirmRemoveCategory";
    private static final String PROPERTY_DEFAULT_LIST_CATEGORY_PER_PAGE = "graphite.listCategorys.itemsPerPage";
 
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "graphite.model.entity.category.attribute.";

    // Views
    private static final String VIEW_MANAGE_CATEGORYS = "manageCategorys";
    private static final String VIEW_CREATE_CATEGORY = "createCategory";
    private static final String VIEW_MODIFY_CATEGORY = "modifyCategory";

    // Actions
    private static final String ACTION_CREATE_CATEGORY = "createCategory";
    private static final String ACTION_MODIFY_CATEGORY = "modifyCategory";
    private static final String ACTION_REMOVE_CATEGORY = "removeCategory";
    private static final String ACTION_CONFIRM_REMOVE_CATEGORY = "confirmRemoveCategory";

    // Infos
    private static final String INFO_CATEGORY_CREATED = "graphite.info.category.created";
    private static final String INFO_CATEGORY_UPDATED = "graphite.info.category.updated";
    private static final String INFO_CATEGORY_REMOVED = "graphite.info.category.removed";
    
    // Session variable to store working values
    private Category _category;
    
    
    @View( value = VIEW_MANAGE_CATEGORYS, defaultView = true )
    public String getManageCategorys( HttpServletRequest request )
    {
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_CATEGORY_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_MANAGE_CATEGORYS );
        String strUrl = url.getUrl(  );
        List<Category> listCategorys = (List<Category>) CategoryHome.getCategorysList(  );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( listCategorys, _nItemsPerPage, strUrl,
                PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = getModel(  );

        model.put( MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_CATEGORY_LIST, paginator.getPageItems(  ) );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_CATEGORYS, TEMPLATE_MANAGE_CATEGORYS, model );
    }

    /**
     * Returns the form to create a category
     *
     * @param request The Http request
     * @return the html code of the category form
     */
    @View( VIEW_CREATE_CATEGORY )
    public String getCreateCategory( HttpServletRequest request )
    {
        _category = ( _category != null ) ? _category : new Category(  );
        //AdminUser adminUser = getUser( );
        //Locale locale = getLocale( );

        Map<String, Object> model = getModel(  );
        model.put( MARK_CATEGORY, _category );
        model.put( MARK_ROLES_COMBO, getComboRoles());
        model.put( MARK_WORKGROUPS_COMBO, getComboWorkGroups());
        
        return getPage( PROPERTY_PAGE_TITLE_CREATE_CATEGORY, TEMPLATE_CREATE_CATEGORY, model );
    }

    /**
     * Process the data capture form of a new category
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_CATEGORY )
    public String doCreateCategory( HttpServletRequest request )
    {
        populate( _category, request );

        // Check constraints
        if ( !validateBean( _category, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_CATEGORY );
        }

        CategoryHome.create( _category );
        _category = null;
        addInfo( INFO_CATEGORY_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CATEGORYS );
    }

    /**
     * Manages the removal form of a category whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_CATEGORY )
    public String getConfirmRemoveCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_CATEGORY ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_CATEGORY ) );
        url.addParameter( PARAMETER_ID_CATEGORY, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_CATEGORY,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a category
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage categorys
     */
    @Action( ACTION_REMOVE_CATEGORY )
    public String doRemoveCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_CATEGORY ) );
        
        for(Graph g : GraphHome.getGraphsList())
        {
            if(g.getGraphCategory() == CategoryHome.findByPrimaryKey( nId ).getIdCategory())
            {
                GraphHome.remove(g.getIdGraph());
            }
        }
        CategoryHome.remove( nId );
        
        addInfo( INFO_CATEGORY_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CATEGORYS );
    }

    /**
     * Returns the form to update info about a category
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_CATEGORY )
    public String getModifyCategory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_CATEGORY ) );

        _category = CategoryHome.findByPrimaryKey( nId );

        Map<String, Object> model = getModel(  );
        model.put( MARK_CATEGORY, _category );
        model.put( MARK_ROLES_COMBO, getComboRoles());
        model.put( MARK_WORKGROUPS_COMBO, getComboWorkGroups());
        model.put( MARK_CATEGORY_ROLE, _category.getCategoryRole() );
        model.put( MARK_CATEGORY_WORKGROUP, _category.getWorkgroup() );
        model.put( MARK_CATEGORY_FRONT, _category.getDisplayFront() );
        model.put( MARK_CATEGORY_BACK, _category.getDisplayBack() );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_CATEGORY, TEMPLATE_MODIFY_CATEGORY, model );
    }

    /**
     * Process the change form of a category
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_CATEGORY )
    public String doModifyCategory( HttpServletRequest request )
    {
        populate( _category, request );

        // Check constraints
        if ( !validateBean( _category, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_CATEGORY, PARAMETER_ID_CATEGORY, _category.getIdCategory(  ) );
        }
        
        for(Graph g : GraphHome.getGraphsList())
        {
            if(g.getGraphCategory() == _category.getIdCategory())
            {
                GraphHome.update(g);
            }
        }
        CategoryHome.update( _category );
        _category = null;
        addInfo( INFO_CATEGORY_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_CATEGORYS );
    }
    
    /**
    * Returns the roles
    * @return The roles
    */
    public ReferenceList getComboRoles()
    {
        return RoleHome.getRolesList();
    }
    
    /**
    * Returns the WorkGroups
    * @return The WorkGroups
    */
    public ReferenceList getComboWorkGroups()
    {
        AdminUser adminUser = getUser( );
        Locale locale = getLocale( );
        return AdminWorkgroupService.getUserWorkgroups( adminUser, locale );
    }
}
