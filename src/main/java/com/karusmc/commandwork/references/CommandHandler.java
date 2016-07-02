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
package com.karusmc.commandwork.references;

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
    private Subcommand invalidCommandHandler;
    
    
    private CommandHandler() {}
    
    public CommandHandler(Subcommand invalidCommandHandler) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        this.invalidCommandHandler = invalidCommandHandler;
    }
    
    public CommandHandler(String message) {
        commands = new HashMap<>();
        commandNames = new ArrayList<>();
        
        invalidCommandHandler = new InvalidSubcommand(message);
    }
    
    
    public void register(Subcommand command) {
        command.getBukkitCommand().getAliases().stream().forEach(alias -> commands.put(alias, command));
        commandNames.add(command.getBukkitCommand().getAliases().get(0));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return null;
            
        } else {
            String givenName = args[args.length - 1];
            
            List<String> results = commandNames.stream().filter(name -> name.startsWith(givenName)).collect(Collectors.toList());
            return results;
        }
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            handleCommand(sender, label, args);
            
        } else if (args[args.length - 1].equals("?")) {
            handleHelpRequest(sender, label, args);
            
        } else {
            handleCommand(sender, args[args.length - 1], args);
        }
        
        return true;
    }
    
    private void handleHelpRequest(CommandSender sender, String label, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(commands.getOrDefault(label, invalidCommandHandler).getInfo());
            
        } else {
            sender.sendMessage(commands.getOrDefault(args[args.length - 2], invalidCommandHandler).getInfo());
        }
    }
    
    private void handleCommand(CommandSender sender, String name, String args[]) {
        commands.getOrDefault(name, invalidCommandHandler).execute(sender, args);
    }
    
    
}
