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
package com.karusmc.commandwork.reference.help;

import java.lang.reflect.Field;

import net.md_5.bungee.api.chat.TextComponent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class TextComponentBuilderTest {
    
    private TextComponentBuilder builder;
    
    private TextComponent backButton;
    private TextComponent nextButton;
    
    private TextComponent blankspace;
    
    
    public TextComponentBuilderTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        builder = new TextComponentBuilder();
        
        Field back = TextComponentBuilder.class.getDeclaredField("backButton");
        Field next = TextComponentBuilder.class.getDeclaredField("nextButton");
        
        Field blank = TextComponentBuilder.class.getDeclaredField("blankspace");
        
        back.setAccessible(true);
        next.setAccessible(true);
        
        blank.setAccessible(true);
        
        backButton = (TextComponent) back.get(builder);
        nextButton = (TextComponent) next.get(builder);
        
        blankspace = (TextComponent) blank.get(builder);
    }

    
    @Test
    public void getBackButton_pageMoreThanZero_ReturnsBackButton() {
        TextComponent returned = builder.getBackButton("whatever", 1);
        assertEquals(backButton, returned);
    }
    
    @Test
    public void getBackButton_pageLessThanOne_ReturnsBlankSpace() {
        TextComponent returned = builder.getBackButton("whatever", 0);
        assertEquals(blankspace, returned);
    }
    
    
    @Test
    public void getNextButton_pageLessOrEqualToTotalPages_ReturnNextButton() {
        TextComponent returned = builder.getNextButton("whatever", 3, 3);
        assertEquals(nextButton, returned);
    }
    
    @Test
    public void getNextButton_pageMoreThanTotalPages_ReturnsBlankSpace() {
        TextComponent returned = builder.getNextButton("whatever", 4, 3);
        assertEquals(blankspace, returned);
    }
    
}
