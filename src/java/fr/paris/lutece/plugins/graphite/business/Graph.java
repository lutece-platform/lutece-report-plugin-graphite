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

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;


/**
 * This is the business class for the object Graph
 */ 
public class Graph
{
	// Variables declarations 
        
	private int _nIdGraph;
        // @NotEmpty( message = "#i18n{graphite.validation.graph.GraphTitle.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        // @Size( max = 50 , message = "#i18n{graphite.validation.graph.GraphTitle.size}" ) 
        @Size( max = 50 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strGraphTitle;
        // @NotEmpty( message = "#i18n{graphite.validation.graph.GraphUrl.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        
	private String _strGraphUrl;
        
	private int _nGraphOrder;
        
	private int _nGraphCategory;
        // @Size( max = 255 , message = "#i18n{graphite.validation.graph.GraphComment.size}" ) 
        @Size( max = 255 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strGraphComment;
	/**
	 * Returns the IdGraph
	 * @return The IdGraph
	 */
	public int getIdGraph()
	{
		return _nIdGraph;
	}

	/**
	 * Sets the IdGraph
	 * @param nIdGraph The IdGraph
	 */ 
	public void setIdGraph( int nIdGraph )
	{
		_nIdGraph = nIdGraph;
	}
	/**
	 * Returns the GraphTitle
	 * @return The GraphTitle
	 */
	public String getGraphTitle()
	{
		return _strGraphTitle;
	}

	/**
	 * Sets the GraphTitle
	 * @param strGraphTitle The GraphTitle
	 */ 
	public void setGraphTitle( String strGraphTitle )
	{
		_strGraphTitle = strGraphTitle;
	}
	/**
	 * Returns the GraphUrl
	 * @return The GraphUrl
	 */
	public String getGraphUrl()
	{
		return _strGraphUrl;
	}

	/**
	 * Sets the GraphUrl
	 * @param strGraphUrl The GraphUrl
	 */ 
	public void setGraphUrl( String strGraphUrl )
	{
		_strGraphUrl = strGraphUrl;
	}
	/**
	 * Returns the GraphOrder
	 * @return The GraphOrder
	 */
	public int getGraphOrder()
	{
		return _nGraphOrder;
	}

	/**
	 * Sets the GraphOrder
	 * @param nGraphOrder The GraphOrder
	 */ 
	public void setGraphOrder( int nGraphOrder )
	{
		_nGraphOrder = nGraphOrder;
	}
	/**
	 * Returns the GraphCategory
	 * @return The GraphCategory
	 */
	public int getGraphCategory()
	{
		return _nGraphCategory;
	}

	/**
	 * Sets the GraphCategory
	 * @param strGraphCategory The GraphCategory
	 */ 
	public void setGraphCategory( int nGraphCategory )
	{
		_nGraphCategory = nGraphCategory;
	}
        
        /**
	 * Returns the GraphCategory title
	 * @return The GraphCategory title
	 */
	public String getGraphCategoryTitle()
	{
		return CategoryHome.findByPrimaryKey( _nGraphCategory ).getCategoryTitle();
	}
        
	/**
	 * Returns the GraphComment
	 * @return The GraphComment
	 */
	public String getGraphComment()
	{
		return _strGraphComment;
	}

	/**
	 * Sets the GraphComment
	 * @param strGraphComment The GraphComment
	 */ 
	public void setGraphComment( String strGraphComment )
	{
		_strGraphComment = strGraphComment;
	}
}