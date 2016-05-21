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
package com.karusmc.playwork.menus;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public interface MenuUtil {
    
    public default int calDynamicSize(int total, int page) {
        int subtotal = total - (page - 1) * 54;
        if (subtotal <= 0) return 0;
        
        return (int) Math.min(Math.ceil((double) subtotal / 9.0) * 9.0, 54);
    }
    
    public default int calPageTotal(int total, int pageSize) {
        
        if (total <= pageSize) return 1;
        
        return (int) Math.ceil((double) total / pageSize);
        
    }
    
    public default int calFirst(int page, int pageSize) {
        return pageSize * (page - 1);
    }
    
    public default int calLast(int page, int pageSize, int rootSize) {
        return Math.min(page * pageSize, rootSize);   
    }
    
}
