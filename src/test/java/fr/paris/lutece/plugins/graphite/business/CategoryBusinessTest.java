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

package fr.paris.lutece.plugins.graphite.business;

import fr.paris.lutece.test.LuteceTestCase;


public class CategoryBusinessTest extends LuteceTestCase
{
    private final static int IDCATEGORY1 = 1;
    private final static int IDCATEGORY2 = 2;
    private final static String CATEGORYTITLE1 = "CategoryTitle1";
    private final static String CATEGORYTITLE2 = "CategoryTitle2";
    private final static String CATEGORYROLE1 = "CategoryRole1";
    private final static String CATEGORYROLE2 = "CategoryRole2";
    private final static String CATEGORYWORKGROUP1 = "CategoryWorkgroup1";
    private final static String CATEGORYWORKGROUP2 = "CategoryWorkgroup2";
    private final static int DISPLAYFRONT1 = 1;
    private final static int DISPLAYFRONT2 = 2;
    private final static int DISPLAYBACK1 = 1;
    private final static int DISPLAYBACK2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        Category category = new Category();
        category.setIdCategory( IDCATEGORY1 );
        category.setCategoryTitle( CATEGORYTITLE1 );
        category.setCategoryRole( CATEGORYROLE1 );
        category.setCategoryWorkgroup( CATEGORYWORKGROUP1 );
        category.setDisplayFront( DISPLAYFRONT1 );
        category.setDisplayBack( DISPLAYBACK1 );

        // Create test
        CategoryHome.create( category );
        Category categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory() );
        assertEquals( categoryStored.getIdCategory() , category.getIdCategory() );
        assertEquals( categoryStored.getCategoryTitle() , category.getCategoryTitle() );
        assertEquals( categoryStored.getCategoryRole() , category.getCategoryRole() );
        assertEquals( categoryStored.getWorkgroup() , category.getWorkgroup() );
        assertEquals( categoryStored.getDisplayFront() , category.getDisplayFront() );
        assertEquals( categoryStored.getDisplayBack() , category.getDisplayBack() );
        
        // Update test
        category.setIdCategory( IDCATEGORY2 );
        category.setCategoryTitle( CATEGORYTITLE2 );
        category.setCategoryRole( CATEGORYROLE2 );
        category.setCategoryWorkgroup( CATEGORYWORKGROUP2 );
        category.setDisplayFront( DISPLAYFRONT2 );
        category.setDisplayBack( DISPLAYBACK2 );
        CategoryHome.update( category );
        categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory() );
        assertEquals( categoryStored.getIdCategory() , category.getIdCategory() );
        assertEquals( categoryStored.getCategoryTitle() , category.getCategoryTitle() );
        assertEquals( categoryStored.getCategoryRole() , category.getCategoryRole() );
        assertEquals( categoryStored.getWorkgroup() , category.getWorkgroup() );
        assertEquals( categoryStored.getDisplayFront() , category.getDisplayFront() );
        assertEquals( categoryStored.getDisplayBack() , category.getDisplayBack() );

        // List test
        CategoryHome.getCategorysList();

        // Delete test
        CategoryHome.remove( category.getIdCategory() );
        categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory() );
        assertNull( categoryStored );
        
    }

}