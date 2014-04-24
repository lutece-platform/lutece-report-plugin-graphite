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

import fr.paris.lutece.test.LuteceTestCase;


public class GraphBusinessTest extends LuteceTestCase
{
    private final static int IDGRAPH1 = 1;
    private final static int IDGRAPH2 = 2;
    private final static String GRAPHTITLE1 = "GraphTitle1";
    private final static String GRAPHTITLE2 = "GraphTitle2";
    private final static String GRAPHURL1 = "GraphUrl1";
    private final static String GRAPHURL2 = "GraphUrl2";
    private final static int GRAPHORDER1 = 1;
    private final static int GRAPHORDER2 = 2;
    private final static int GRAPHCATEGORY1 = 1;
    private final static int GRAPHCATEGORY2 = 2;
    private final static String GRAPHCOMMENT1 = "GraphComment1";
    private final static String GRAPHCOMMENT2 = "GraphComment2";

    public void testBusiness(  )
    {
        // Initialize an object
        Graph graph = new Graph();
        graph.setIdGraph( IDGRAPH1 );
        graph.setGraphTitle( GRAPHTITLE1 );
        graph.setGraphUrl( GRAPHURL1 );
        graph.setGraphOrder( GRAPHORDER1 );
        graph.setGraphCategory( GRAPHCATEGORY1 );
        graph.setGraphComment( GRAPHCOMMENT1 );

        // Create test
        GraphHome.create( graph );
        Graph graphStored = GraphHome.findByPrimaryKey( graph.getIdGraph() );
        assertEquals( graphStored.getIdGraph() , graph.getIdGraph() );
        assertEquals( graphStored.getGraphTitle() , graph.getGraphTitle() );
        assertEquals( graphStored.getGraphUrl() , graph.getGraphUrl() );
        assertEquals( graphStored.getGraphOrder() , graph.getGraphOrder() );
        assertEquals( graphStored.getGraphCategory() , graph.getGraphCategory() );
        assertEquals( graphStored.getGraphComment() , graph.getGraphComment() );

        // Update test
        graph.setIdGraph( IDGRAPH2 );
        graph.setGraphTitle( GRAPHTITLE2 );
        graph.setGraphUrl( GRAPHURL2 );
        graph.setGraphOrder( GRAPHORDER2 );
        graph.setGraphCategory( GRAPHCATEGORY2 );
        graph.setGraphComment( GRAPHCOMMENT2 );
        GraphHome.update( graph );
        graphStored = GraphHome.findByPrimaryKey( graph.getIdGraph() );
        assertEquals( graphStored.getIdGraph() , graph.getIdGraph() );
        assertEquals( graphStored.getGraphTitle() , graph.getGraphTitle() );
        assertEquals( graphStored.getGraphUrl() , graph.getGraphUrl() );
        assertEquals( graphStored.getGraphOrder() , graph.getGraphOrder() );
        assertEquals( graphStored.getGraphCategory() , graph.getGraphCategory() );
        assertEquals( graphStored.getGraphComment() , graph.getGraphComment() );

        // List test
        GraphHome.getGraphsList();

        // Delete test
        GraphHome.remove( graph.getIdGraph() );
        graphStored = GraphHome.findByPrimaryKey( graph.getIdGraph() );
        assertNull( graphStored );
        
    }

}