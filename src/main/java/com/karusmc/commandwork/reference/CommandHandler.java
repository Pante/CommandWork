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

import com.karusmc.commandwork.Subcommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandHandler implements TabExecutor {
    
    private Map<String, Subcommand> commands;
    private List<String> commandNames;
    private Subcommand invalidHandler;
    
    
    public CommandHandler() {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        invalidHandler = new InvalidSubcommandHandler();
    }
    
    public CommandHandler(String message) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        invalidHandler = new InvalidSubcommandHandler(message);
    }
    
    public CommandHandler(Subcommand invalidHandler) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        this.invalidHandler = invalidHandler;
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
            String criteria = args[args.length - 1];
            
            List<String> results = commandNames.stream().filter(name -> name.startsWith(criteria)).collect(Collectors.toList());
            return results;
        }
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length != 0 && args[args.length - 1].equalsIgnoreCase("?")) {
            
            String info = searchForCommand(label, args, 1).getInfo(sender);
            sender.sendMessage(info);
            
        } else {
            searchForCommand(label, args, 0).execute(sender, args);
        }
        
        return true;
    }
    
    
    private Subcommand searchForCommand(String label, String[] args, int minArgumentNeeded) {
        String criteria;
        
        if (args.length == minArgumentNeeded) {
            criteria = label;
        } else {
            criteria = args[args.length - 1 - minArgumentNeeded];
        }
        
        return commands.getOrDefault(criteria, invalidHandler);
    }
    
}
