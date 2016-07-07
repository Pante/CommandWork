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
import java.util.stream.Collectors;

import org.bukkit.command.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandHandler implements TabExecutor {
    
    private Map<String, Subcommand> commands;
    private List<String> commandNames;
    
    private Subcommand invalidSubcommandHandler;
    
    
    public CommandHandler() {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        invalidSubcommandHandler = new InvalidSubcommandHandler();
    }
    
    public CommandHandler(String errorMessage) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        invalidSubcommandHandler = new InvalidSubcommandHandler(errorMessage);
    }
    
    public CommandHandler(Subcommand invalidSubcommandHandler) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        this.invalidSubcommandHandler = invalidSubcommandHandler;
    }
    
    
    public void register(Subcommand command) {
        command.getAliases().stream().forEach(alias -> commands.put(alias, command));
        commandNames.add(command.getTabCompleteName());
    }
    
    public void unregister(Subcommand command) {
        command.getAliases().stream().forEach(alias -> commands.remove(alias, command));
        commandNames.remove(command.getTabCompleteName());
    }

    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return null;
            
        } else {
            List<String> results = commandNames.stream()
                    .filter(name -> name.startsWith(args[0]))
                    .collect(Collectors.toList());
            
            return results;
        }
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length > 0 && args[args.length - 1].equalsIgnoreCase("?")) {
            String info = searchForCommand(label, args, 1).getInfo(sender);
            sender.sendMessage(info);
            
        } else {
            searchForCommand(label, args, 0).execute(sender, args);
        }
        
        return true;
    }
    
    
    private Subcommand searchForCommand(String label, String[] args, int minLength) {
        String criteria;
        
        if (args.length == minLength) {
            criteria = label;
        } else {
            criteria = args[0];
        }
        
        return commands.getOrDefault(criteria, invalidSubcommandHandler);
    }
    
}
