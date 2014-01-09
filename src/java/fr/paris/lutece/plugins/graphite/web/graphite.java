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

import fr.paris.lutece.plugins.graphite.business.Category;
import fr.paris.lutece.plugins.graphite.business.CategoryHome;
import fr.paris.lutece.plugins.graphite.business.GraphHome;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * This class provides a simple implementation of an XPage
 */
 
@Controller( xpageName = "graphite" , pageTitleProperty = "graphite.pageTitle" , pagePathProperty = "graphite.pagePathLabel" )
public class graphite extends MVCApplication
{
    private static final String TEMPLATE_XPAGE = "/skin/plugins/graphite/graphite.html";
    private static final String VIEW_HOME = "home";
    
    private static final String MARK_CATEGORIES_COMBO = "combo_categories";
    private static final String MARK_CATEGORY = "categorie";
    
    private static final String MARKER_GRAPHS_LIST = "graphs_list";
    private static final String MARKER_ROLE = "role";
    
    private Category _category;
    /**
     * Returns the content of the page graphite.
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_HOME, defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        
        String strIdCategory = request.getParameter( "category" );
        int nId =-1;
        boolean isRole = true;
        List<Category> listCategory = new ArrayList<Category>(  );
        
        if(!StringUtils.isEmpty(strIdCategory))
        {
            nId = Integer.parseInt( strIdCategory );
        }
       else
       {
           //default category
           List<Category> listCategories = getComboCategories();
           if(!CollectionUtils.isEmpty(listCategories))
           {
               nId=listCategories.get(0).getIdCategory();
           }
        }
        _category = CategoryHome.findByPrimaryKey( nId );
        
        if(SecurityService.isAuthenticationEnable())
        {
            isRole = SecurityService.getInstance().isUserInRole(request, _category.getCategoryRole());
            for ( Category c : getComboCategories())
            {
                if(SecurityService.getInstance().isUserInRole(request, c.getCategoryRole()))
                listCategory.add( c );
            }
        }
        else
        {
            for ( Category c : getComboCategories())
            {
                listCategory.add( c );
            }
        }
        
        Map<String, Object> model = getModel(  );
        model.put( MARK_CATEGORY, _category);
        model.put( MARKER_GRAPHS_LIST, GraphHome.getGraphsList(  ) );
        model.put( MARK_CATEGORIES_COMBO, listCategory);
        model.put( MARKER_ROLE, isRole);
        
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ), model );
    }
    
    /**
    * Returns the categories
    * @return the categories
    */
    public List<Category> getComboCategories()
    {
        List<Category> listCategorys = (List<Category>) CategoryHome.getCategorysList(  );
        
        return listCategorys;
    }
}
