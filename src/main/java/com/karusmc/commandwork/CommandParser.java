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
 */
public class CommandParser {
    
    private Map<String, CommandCallable> commands;
    
    
    private CommandParser() {}
   
    public CommandParser(Map<String, CommandCallable> commands) {
        this.commands = commands;
    }
    
    
    public List<String> getMatchingNames(CommandSender sender, String[] args) {
        if (args.length != 0) {
            Predicate<CommandCallable> criteria = command -> sender.hasPermission(command.getPermission()) && command.getTabCompleteName().startsWith(args[0]);
            
            List<String> names = commands.values().stream().filter(criteria).map(CommandCallable::getTabCompleteName).collect(Collectors.toList());
            return names;
            
        } else {
            return null;
        }
    }
    
    
    public CommandCallable getCommandOrDefault(CommandCallable defaultHandler, String label, String[] args) {
        String criteria = label;
        
        if (args.length != 0) {
            criteria = args[0];
        }
        
        return commands.getOrDefault(criteria, defaultHandler);
    }
    
    
    public boolean isQuery(String[] args) {
        boolean firstArgument = args.length > 0 && args[0].equalsIgnoreCase("?");
        boolean secondArgument = args.length > 1 && args[1].equalsIgnoreCase("?");
        
        return (firstArgument || secondArgument);
    }
    
}
