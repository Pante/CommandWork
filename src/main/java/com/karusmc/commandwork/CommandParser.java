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
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Class that handles the parsing of arguments to determine the CommandCallable that should be called
 */
public class CommandParser {
    
    private Map<String, CommandCallable> commands;
    
    
    private CommandParser() {}
   
    public CommandParser(Map<String, CommandCallable> commands) {
        this.commands = commands;
    }
    
    /**
     * Parses and returns a list of names based on the sender's permission and the arguments
     * @param sender An instance of a spigot CommandSender
     * @param args The command arguments
     * @return A list of names
     */
    public List<String> getMatchingNames(CommandSender sender, String[] args) {
        if (args.length != 0) {
            Predicate<CommandCallable> criteria = command -> sender.hasPermission(command.getPermission()) && command.getTabCompleteName().startsWith(args[0]);
            
            List<String> names = commands.values().stream().filter(criteria).map(CommandCallable::getTabCompleteName).collect(Collectors.toList());
            return names;
            
        } else {
            return null;
        }
    }
    
    /**
     * Gets a command based on the specified label and arguments or defaults to the specified CommandCallable.
     * @param defaultHandler Default CommandCallable which is returned if a command can not be found
     * @param label
     * @param args
     * @return CommandCallable or the Default CommandCallble if a CommandCallble cannot be found
     */
    public CommandCallable getCommandOrDefault(CommandCallable defaultHandler, String label, String[] args) {
        String criteria = label;
        
        if (args.length != 0 && commands.containsKey(args[0])) {
            criteria = args[0];
        }
        
        return commands.getOrDefault(criteria, defaultHandler);
    }
    
    /**
     * Parses the specified arguments to check if it is a query.
     * @param args Arguments to parse
     * @return true if the arguments is a query
     */
    public boolean isQuery(String[] args) {
        boolean firstArgument = args.length > 0 && args[0].equalsIgnoreCase("?");
        boolean secondArgument = args.length > 1 && args[1].equalsIgnoreCase("?");
        
        return (firstArgument || secondArgument);
    }
    
}
