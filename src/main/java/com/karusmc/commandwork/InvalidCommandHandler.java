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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Special case object for when a CommandCallable is invalid.
 */
public class InvalidCommandHandler extends CommandCallable {
    
    private String help;
    
    
    /**
     * Creates an instance of InvalidComandHandler with default configuration.
     */
    public InvalidCommandHandler() {
        super(null);
        help = ChatColor.RED + "Invalid command.";
    }
    
    /**
     * Create an instance of InvalidCommandHandler with the specified help message.
     * @param help Help message to display when InvalidCommandHandler's call method is invoked
     */
    public InvalidCommandHandler(String help) {
        super(null);
        this.help = help;
    }
    
    /**
     * Creating an instance of InvalidCommandHandler with the specified spigot command.
     * @param command A spigot command to be wrapped
     */
    public InvalidCommandHandler(Command command) {
        super(command);
        this.help = ChatColor.RED + "Invalid command.";
    }
    
    
    @Override
    public void call(CommandSender sender, String[] args) {
        sender.sendMessage(help);
    }

    /**
     * Checks for and handles invalid conditions
     * @param sender
     * @param args
     * @return Always returns false
     */
    @Override
    public boolean conditionsAreValid(CommandSender sender, String[] args) {
        sender.sendMessage(help);
        return false;
    }
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getInfo() {
        return help;
    }
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getName() {
        return help;
    }
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getDescription() {
        return help;
    }
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getUsage() {
        return help;
    }
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getPermission() {
        return "Invalid.Permission.";
    }
    
    /**
     * 
     * @return Returns an empty list
     */
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }
    
    
    /**
     * 
     * @return The help message specified in the constructor or the default
     */
    @Override
    public String getTabCompleteName() {
        return help;
    }
    
}
