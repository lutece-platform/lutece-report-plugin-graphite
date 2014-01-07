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
 * This class provides Data Access methods for Graph objects
 */

public final class GraphDAO implements IGraphDAO
{
	
	// Constants
	private static final String SQL_QUERY_NEW_PK = "SELECT max( id_graph ) FROM graphite_graph";
	private static final String SQL_QUERY_SELECT = "SELECT id_graph, graph_title, graph_url, graph_order, graph_category, graph_comment FROM graphite_graph WHERE id_graph = ?";
	private static final String SQL_QUERY_INSERT = "INSERT INTO graphite_graph ( id_graph, graph_title, graph_url, graph_order, graph_category, graph_comment ) VALUES ( ?, ?, ?, ?, ?, ? ) ";
	private static final String SQL_QUERY_DELETE = "DELETE FROM graphite_graph WHERE id_graph = ? ";
	private static final String SQL_QUERY_UPDATE = "UPDATE graphite_graph SET id_graph = ?, graph_title = ?, graph_url = ?, graph_order = ?, graph_category = ?, graph_comment = ? WHERE id_graph = ?";
	private static final String SQL_QUERY_SELECTALL = "SELECT id_graph, graph_title, graph_url, graph_order, graph_category, graph_comment FROM graphite_graph";


	
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
	public void insert( Graph graph, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
				
		graph.setIdGraph( newPrimaryKey( plugin ) );
				
		daoUtil.setInt( 1, graph.getIdGraph( ) );
		daoUtil.setString( 2, graph.getGraphTitle( ) );
		daoUtil.setString( 3, graph.getGraphUrl( ) );
		daoUtil.setInt( 4, graph.getGraphOrder( ) );
		daoUtil.setString( 5, graph.getGraphCategory( ) );
		daoUtil.setString( 6, graph.getGraphComment( ) );

		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public Graph load( int nKey, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
		daoUtil.setInt( 1 , nKey );
		daoUtil.executeQuery( );

		Graph graph = null;

		if ( daoUtil.next( ) )
		{
			graph = new Graph();
			graph.setIdGraph( daoUtil.getInt(  1 ) );
			graph.setGraphTitle( daoUtil.getString(  2 ) );
			graph.setGraphUrl( daoUtil.getString(  3 ) );
			graph.setGraphOrder( daoUtil.getInt(  4 ) );
			graph.setGraphCategory( daoUtil.getString(  5 ) );
			graph.setGraphComment( daoUtil.getString(  6 ) );
		}

		daoUtil.free( );
		return graph;
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void delete( int nGraphId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
		daoUtil.setInt( 1 , nGraphId );
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void store( Graph graph, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
				
		daoUtil.setInt( 1, graph.getIdGraph( ) );
		daoUtil.setString( 2, graph.getGraphTitle( ) );
		daoUtil.setString( 3, graph.getGraphUrl( ) );
		daoUtil.setInt( 4, graph.getGraphOrder( ) );
		daoUtil.setString( 5, graph.getGraphCategory( ) );
		daoUtil.setString( 6, graph.getGraphComment( ) );
		daoUtil.setInt( 7, graph.getIdGraph( ) );
				
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}



	/**
	 * {@inheritDoc }
	 */
	@Override
	public Collection<Graph> selectGraphsList( Plugin plugin )
	{
		Collection<Graph> graphList = new ArrayList<Graph>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
				Graph graph = new Graph(  );

					graph.setIdGraph( daoUtil.getInt( 1 ) );
					graph.setGraphTitle( daoUtil.getString( 2 ) );
					graph.setGraphUrl( daoUtil.getString( 3 ) );
					graph.setGraphOrder( daoUtil.getInt( 4 ) );
					graph.setGraphCategory( daoUtil.getString( 5 ) );
					graph.setGraphComment( daoUtil.getString( 6 ) );

				graphList.add( graph );
		}

		daoUtil.free( );
		return graphList;
	}

}
