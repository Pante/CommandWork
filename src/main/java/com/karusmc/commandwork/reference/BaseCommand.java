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

import com.karusmc.commandwork.ParentCommand;
import com.karusmc.commandwork.Subcommand;

import java.util.*;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class BaseCommand implements ParentCommand, TabExecutor {
    
    private Map<String, Subcommand> subcommands;
    private List<String> names;
    
    private Subcommand invalidSubcommand;
    
    
    private BaseCommand() {}
    
    public BaseCommand(Subcommand invalidSubcommand) {
        subcommands = new HashMap<>();
        names = new ArrayList<>();
        
        this.invalidSubcommand = invalidSubcommand;
    }
    
    
    @Override
    public void registerSubcommand(Subcommand subcommand) {
        subcommand.getCommand().getAliases().stream().forEach(alias -> subcommands.put(alias, subcommand));
        names.add(subcommand.getCommand().getAliases().get(0));
    }

    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> possibleNames = null;
        
        if (args.length >= 1) {
            possibleNames = names.stream().filter(name -> name.startsWith(args[1])).collect(Collectors.toList());
        }
        return possibleNames;
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length <= 0) {
            subcommands.getOrDefault(label, invalidSubcommand).execute(sender, args);
            
        } else if (args[args.length - 1].equalsIgnoreCase("?")) {
            sendHelpInformation(sender, label, args);
            
        } else {
            subcommands.getOrDefault(args[args.length - 1], invalidSubcommand).execute(sender, args);
        }
        
        return true;
    }
    
    private void sendHelpInformation(CommandSender sender, String label, String[] args) {
        String subcommand = label;
        
        if (args.length >= 2) {
            subcommand = args[args.length - 2];
        }
        sender.sendMessage(subcommands.getOrDefault(subcommand, invalidSubcommand).formatInformation());
    }
 
}
