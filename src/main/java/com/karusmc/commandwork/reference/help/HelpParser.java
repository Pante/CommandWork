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

import com.karusmc.commandwork.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Class which handles the parsing of arguments
 */
public class HelpParser {
    
    /**
     * Gets the search criteria based on the arguments
     * @param args The command arguments
     * @return The search criteria
     */
    public String getCriteria(String[] args) {
        if (args.length == 1 || StringUtils.isNumeric(args[1])) {
            return "All";
        } else {
            return args[1];
        }
    }
    
    /**
     * 
     * @param sender
     * @param criteria
     * @return The predicate
     */
    public Predicate<CommandCallable> getPredicate(CommandSender sender, String criteria) {
        
        if (criteria.equalsIgnoreCase("All")) {
            return (command) -> sender.hasPermission(command.getPermission());
            
        } else {
            return (command) -> sender.hasPermission(command.getPermission()) && command.getName().contains(criteria);
        }
        
    }
    
    
    /**
     * 
     * @param commands A collection of CommandCallables to filter through
     * @param predicate The predicate to filter the collection of commands by
     * @return A list of command usages based on the the specified collection of CommandCallables
     */
    public List<String> getCommandUsages(Collection<CommandCallable> commands, Predicate<CommandCallable> predicate) {
        return commands.stream()
                .filter(predicate)
                .map(subcommand -> ChatColor.GOLD + subcommand.getUsage())
                .collect(Collectors.toList());
    }

    
    /**
     * Return the current page based on the arguments 
     * @param args
     * @return The current page number
     */
    public int getPage(String[] args) {
        int page = 1;
        
        if (args.length != 0 && StringUtils.isNumeric(args[args.length - 1])) {
            int number = Integer.parseInt(args[args.length - 1]);
            if (number > 0) {
                return number;
            }
        }
        
        return page;
    }
    
    
    /**
     * Calculates the total number of pages based on the parameters
     * @param listSize The total number of items
     * @param pageSize The page size
     * @return The total number of pages
     */
    public int getTotalPages(int listSize, int pageSize) {
        if (listSize == 0) {
            return 0;
        } else {
            return (int) Math.max(1, Math.ceil((double) listSize / pageSize));
        }
    }
    
}
