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
package fr.paris.lutece.plugins.graphite.business;

import fr.paris.lutece.portal.service.workgroup.AdminWorkgroupResource;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;


/**
 * This is the business class for the object Category
 */ 
public class Category implements AdminWorkgroupResource
{
	// Variables declarations 
        private static final String INFO_CATEGORY_DISPLAY_YES = "#i18n{graphite.info.yes}";
        private static final String INFO_CATEGORY_DISPLAY_NO = "#i18n{graphite.info.no}";
        
	private int _nIdCategory;
        // @NotEmpty( message = "#i18n{graphite.validation.category.CategoryTitle.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        // @Size( max = 50 , message = "#i18n{graphite.validation.category.CategoryTitle.size}" ) 
        @Size( max = 50 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strCategoryTitle;
        // @NotEmpty( message = "#i18n{graphite.validation.category.CategoryRole.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        // @Size( max = 255 , message = "#i18n{graphite.validation.category.CategoryRole.size}" ) 
        @Size( max = 255 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strCategoryRole;
        // @NotEmpty( message = "#i18n{graphite.validation.category.CategoryWorkgroup.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        // @Size( max = 255 , message = "#i18n{graphite.validation.category.CategoryWorkgroup.size}" ) 
        @Size( max = 255 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strCategoryWorkgroup;
        
	private int _nDisplayFront;
        
	private int _nDisplayBack;
	/**
	 * Returns the IdCategory
	 * @return The IdCategory
	 */
	public int getIdCategory()
	{
		return _nIdCategory;
	}

	/**
	 * Sets the IdCategory
	 * @param nIdCategory The IdCategory
	 */ 
	public void setIdCategory( int nIdCategory )
	{
		_nIdCategory = nIdCategory;
	}
	/**
	 * Returns the CategoryTitle
	 * @return The CategoryTitle
	 */
	public String getCategoryTitle()
	{
		return _strCategoryTitle;
	}

	/**
	 * Sets the CategoryTitle
	 * @param strCategoryTitle The CategoryTitle
	 */ 
	public void setCategoryTitle( String strCategoryTitle )
	{
		_strCategoryTitle = strCategoryTitle;
	}
	/**
	 * Returns the CategoryRole
	 * @return The CategoryRole
	 */
	public String getCategoryRole()
	{
		return _strCategoryRole;
	}
 
	/**
	 * Sets the CategoryRole
	 * @param strCategoryRole The CategoryRole
	 */ 
	public void setCategoryRole( String strCategoryRole )
	{
		_strCategoryRole = strCategoryRole;
	}
        
	/**
	 * Returns the CategoryWorkgroup
	 * @return The CategoryWorkgroup
	 */
	public String getWorkgroup()
	{
		return _strCategoryWorkgroup;
	}

	/**
	 * Sets the CategoryWorkgroup
	 * @param strCategoryWorkgroup The CategoryWorkgroup
	 */ 
	public void setCategoryWorkgroup( String strCategoryWorkgroup )
	{
		_strCategoryWorkgroup = strCategoryWorkgroup;
	}
	/**
	 * Returns the DisplayFront
	 * @return The DisplayFront
	 */
	public int getDisplayFront()
	{
		return _nDisplayFront;
	}
        
        /**
	 * Returns the DisplayFront String
	 * @return The DisplayFront String
	 */
	public String getDisplayFrontString()
	{
            String displayFront = "";
            
            if ( _nDisplayFront == 1 )
            {
                displayFront = INFO_CATEGORY_DISPLAY_YES;
            }
            else
            {
                displayFront = INFO_CATEGORY_DISPLAY_NO;
            }
            return displayFront;
	}

	/**
	 * Sets the DisplayFront
	 * @param nDisplayFront The DisplayFront
	 */ 
	public void setDisplayFront( int nDisplayFront )
	{
		_nDisplayFront = nDisplayFront;
	}
	/**
	 * Returns the DisplayBack
	 * @return The DisplayBack
	 */
	public int getDisplayBack()
	{
		return _nDisplayBack;
	}
        
        /**
	 * Returns the DisplayBack String
	 * @return The DisplayBack String
	 */
	public String getDisplayBackString()
	{
            String displayBack = "";
            
            if ( _nDisplayBack == 1 )
            {
                displayBack = INFO_CATEGORY_DISPLAY_YES;
            }
            else
            {
                displayBack = INFO_CATEGORY_DISPLAY_NO;
            }
            return displayBack;
	}

	/**
	 * Sets the DisplayBack
	 * @param nDisplayBack The DisplayBack
	 */ 
	public void setDisplayBack( int nDisplayBack )
	{
		_nDisplayBack = nDisplayBack;
	}
        
        /**
	 * Returns the graphs
	 * @return The graphs
	 */
        public List<Graph> getGraphs()
        {
            List<Graph> list = new ArrayList<Graph>(  );
            List<Graph> listGraphs = (List<Graph>) GraphHome.getGraphsList(  );

            for ( Graph g : listGraphs )
            {
                if ( g.getGraphCategory() == _nIdCategory )
                {
                    list.add( g );
                }
            }
            return list;
        }
}