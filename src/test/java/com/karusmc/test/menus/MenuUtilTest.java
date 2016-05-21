/*
 * Copyright (C) 2016 PanteLegacy @ karusmc.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karusmc.test.menus;

import com.karusmc.playwork.menus.MenuUtil;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MenuUtilTest implements MenuUtil {
    
    @Test
    public void testCalSize() {
        
        assertEquals(54, calDynamicSize(54, 1));
        assertEquals(54, calDynamicSize(200, 1));
        
        assertEquals(18, calDynamicSize(69, 2));
        
        assertEquals(0, calDynamicSize(10, 2));
        
    }
    
    @Test
    public void testCalPageTotal() {
        
        assertEquals(3, calPageTotal(52, 18));
        
        assertEquals(3, calPageTotal(128, 54));
        
    }
    
    @Test
    public void testCalFirst() {
        
        assertEquals(0, calFirst(1, 54));
        
        assertEquals(45, calFirst(2, 45));
        
    }
    
    @Test
    public void testCalLast() {
        
        assertEquals(100, calLast(2, 54, 100));
        
        assertEquals(50, calLast(1, 50, 100));
        
    }
    
}
