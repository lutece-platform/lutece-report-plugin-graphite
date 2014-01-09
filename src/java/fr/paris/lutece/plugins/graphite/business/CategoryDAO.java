/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *	 and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *	 and the following disclaimer in the documentation and/or other materials
 *	 provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *	 contributors may be used to endorse or promote products derived from
 *	 this software without specific prior written permission.
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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class provides Data Access methods for Category objects
 */

public final class CategoryDAO implements ICategoryDAO
{
	
	// Constants
	private static final String SQL_QUERY_NEW_PK = "SELECT max( id_category ) FROM graphite_category";
	private static final String SQL_QUERY_SELECT = "SELECT id_category, category_title, category_role, category_workgroup, display_front, display_back FROM graphite_category WHERE id_category = ?";
	private static final String SQL_QUERY_INSERT = "INSERT INTO graphite_category ( id_category, category_title, category_role, category_workgroup, display_front, display_back ) VALUES ( ?, ?, ?, ?, ?, ? ) ";
	private static final String SQL_QUERY_DELETE = "DELETE FROM graphite_category WHERE id_category = ? ";
	private static final String SQL_QUERY_UPDATE = "UPDATE graphite_category SET id_category = ?, category_title = ?, category_role = ?, category_workgroup = ?, display_front = ?, display_back = ? WHERE id_category = ?";
	private static final String SQL_QUERY_SELECTALL = "SELECT id_category, category_title, category_role, category_workgroup, display_front, display_back FROM graphite_category";


	
	/**
	 * Generates a new primary key
	 * @param plugin The Plugin
	 * @return The new primary key
	 */
	public int newPrimaryKey( Plugin plugin)
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
		daoUtil.executeQuery( );

		int nKey = 1;

		if( daoUtil.next( ) )
		{
			nKey = daoUtil.getInt( 1 ) + 1;
		}

		daoUtil.free();

		return nKey;
	}




	/**
	 * {@inheritDoc }
	 */
	@Override
	public void insert( Category category, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
				
		category.setIdCategory( newPrimaryKey( plugin ) );
				
		daoUtil.setInt( 1, category.getIdCategory( ) );
		daoUtil.setString( 2, category.getCategoryTitle( ) );
		daoUtil.setString( 3, category.getCategoryRole( ) );
		daoUtil.setString( 4, category.getWorkgroup( ) );
		daoUtil.setInt( 5, category.getDisplayFront( ) );
		daoUtil.setInt( 6, category.getDisplayBack( ) );

		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public Category load( int nKey, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
		daoUtil.setInt( 1 , nKey );
		daoUtil.executeQuery( );

		Category category = null;

		if ( daoUtil.next( ) )
		{
			category = new Category();
			category.setIdCategory( daoUtil.getInt(  1 ) );
			category.setCategoryTitle( daoUtil.getString(  2 ) );
			category.setCategoryRole( daoUtil.getString(  3 ) );
			category.setCategoryWorkgroup( daoUtil.getString(  4 ) );
			category.setDisplayFront( daoUtil.getInt(  5 ) );
			category.setDisplayBack( daoUtil.getInt(  6 ) );
		}

		daoUtil.free( );
		return category;
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void delete( int nCategoryId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
		daoUtil.setInt( 1 , nCategoryId );
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void store( Category category, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
				
		daoUtil.setInt( 1, category.getIdCategory( ) );
		daoUtil.setString( 2, category.getCategoryTitle( ) );
		daoUtil.setString( 3, category.getCategoryRole( ) );
		daoUtil.setString( 4, category.getWorkgroup( ) );
		daoUtil.setInt( 5, category.getDisplayFront( ) );
		daoUtil.setInt( 6, category.getDisplayBack( ) );
		daoUtil.setInt( 7, category.getIdCategory( ) );
				
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}



	/**
	 * {@inheritDoc }
	 */
	@Override
	public Collection<Category> selectCategorysList( Plugin plugin )
	{
		Collection<Category> categoryList = new ArrayList<Category>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
				Category category = new Category(  );

					category.setIdCategory( daoUtil.getInt( 1 ) );
					category.setCategoryTitle( daoUtil.getString( 2 ) );
					category.setCategoryRole( daoUtil.getString( 3 ) );
					category.setCategoryWorkgroup( daoUtil.getString( 4 ) );
					category.setDisplayFront( daoUtil.getInt( 5 ) );
					category.setDisplayBack( daoUtil.getInt( 6 ) );

				categoryList.add( category );
		}

		daoUtil.free( );
		return categoryList;
	}

}
