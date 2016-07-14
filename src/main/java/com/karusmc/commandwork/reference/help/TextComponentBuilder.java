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

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Class which handles the building of text components used in HelpCommand
 */
public class TextComponentBuilder {
    
    private String command;
    
    private TextComponent backButton;
    private TextComponent nextButton;
    
    private TextComponent whitespace;
    private TextComponent blankspace;
    
    /**
     * 
     * @param command The underlying spigot command name of the instance of HelpCommand
     */
    public TextComponentBuilder(String command) {
        this.command = command;
        
        backButton = new TextComponent("<< Back");
        backButton.setColor(ChatColor.GOLD);
        
        nextButton = new TextComponent("Next >>");
        nextButton.setColor(ChatColor.GOLD);
        
        whitespace = new TextComponent("                           ");
        blankspace = new TextComponent("       ");
    }
    
    /**
     * Builds the back button based on the specified parameters
     * @param search The search criteria
     * @param page The current page number
     * @return The back button
     */
    public TextComponent getBackButton(String search, int page) {
        
        if (page >= 1) {
            ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command + " " + search + " " + page);
            backButton.setClickEvent(event);

            return backButton;
            
        } else {
            return blankspace;
        }
        
    }
    
    /**
     * Builds the next button based on the specified parameters
     * @param search The search criteria
     * @param page The current page number
     * @param totalPages The total number of pages
     * @return The next button
     */
    public TextComponent getNextButton(String search, int page, int totalPages) {
        
        if (page <= totalPages) {
            ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command + " " + search + " " + page);
            nextButton.setClickEvent(event);

            return nextButton;
            
        } else {
            return blankspace;
        }
        
    }
    
    
    public TextComponent getWhiteSpace() {
        return whitespace;
    }
    
    
}
