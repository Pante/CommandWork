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
package com.karusmc.commandwork.reference;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpComponentBuilder {
    
    private TextComponent backButton;
    private TextComponent nextButton;
    private TextComponent whitespace;
    private TextComponent blankspace;
    
    public HelpComponentBuilder() {
        
        backButton = new TextComponent("<< Back");
        backButton.setColor(ChatColor.GOLD);
        backButton.setBold(true);
        
        nextButton = new TextComponent("Next >>");
        nextButton.setColor(ChatColor.GOLD);
        nextButton.setBold(true);
        
        whitespace = new TextComponent("                           ");
        blankspace = new TextComponent("       ");
    }
    
    public TextComponent buildBackButton(String search, int currentPage) {
        ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help " + search + " " + (currentPage - 1));
        backButton.setClickEvent(event);
        
        return backButton;
    }
    
    public TextComponent buildNextButton(String search, int currentPage) {
        ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help " + search + " " + (currentPage + 1));
        nextButton.setClickEvent(event);
        
        return nextButton;
    }
    
    public TextComponent getWhiteSpace() {
        return whitespace;
    }
    
    public TextComponent getBlankSpace() {
        return blankspace;
    }
    
}
