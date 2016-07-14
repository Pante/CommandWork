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
package com.karusmc.commandwork;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.command.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Abstract class that serves as a base class which all commands should extend. Used by @See com.karusmc.commandwork.CommandDispatcher .
 */
public abstract class CommandCallable {
    
    /**
     * Wrapped instance of a spigot command.
     */
    protected Command command;
    
    
    private CommandCallable() {}
    
    /**
     * @param command A spigot command to be wrapped
     */
    public CommandCallable(Command command) {
        this.command = command;
    }
    
    /**
     * Execute the main logic of this command. Called by CommandDispatcher
     * @param sender An instance of a spigot CommandSender
     * @param args The command arguments
     */
    public abstract void call(CommandSender sender, String[] args);
    
    /**
     * Used to check if the conditions to execute this command are valid. Called by CommandDispatcher
     * @param sender An instance of a spigot CommandSender
     * @param args
     * @return 
     */
    public abstract boolean conditionsAreValid(CommandSender sender, String[] args);
    
    /**
     * Returns formatted information about this command.
     * @return The formatted information about this command.
     */
    public String getInfo() {
        StringBuilder buffy = new StringBuilder();

        buffy.append("&6==== Help: &c").append(getName()).append("&6 ====");
        buffy.append("&6\nUsage: &c").append(getUsage());
        buffy.append("&6\nDescription: &c").append(getDescription());
        buffy.append("\nAliases: &c").append(getAliases().toString());

        return ChatColor.translateAlternateColorCodes('&', buffy.toString());
    }
    
    /**
     * 
     * @return Returns the underlying spigot command name
     */
    public String getName() {
        return command.getName();
    }
    
    /**
     * 
     * @return Returns the underlying spigot command description
     */
    public String getDescription() {
        return command.getDescription();
    }
    
    /**
     * 
     * @return Returns the underlying spigot command usage
     */
    public String getUsage() {
        return command.getUsage();
    }
    
    /**
     * 
     * @return Returns the underlying spigot command permission
     */
    public String getPermission() {
        return command.getPermission();
    }
    
    /**
     * 
     * @return Returns the tab complete name to be used to complete a command
     */
    public String getTabCompleteName() {
        return command.getAliases().get(0);
    }
    
    /**
     * 
     * @return Returns the underlying spigot command aliases
     */
    public List<String> getAliases() {
        return command.getAliases();
    }
    
}
