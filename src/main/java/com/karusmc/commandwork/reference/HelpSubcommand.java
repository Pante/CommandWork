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


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;

import static com.karusmc.commandwork.CommandUtility.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpSubcommand extends Subcommand {
    
    private static int SIZE = 3;
    private List<Subcommand> commands;
   
    
    private HelpSubcommand() {
        super(null);
    }
    
    public HelpSubcommand(Command command, List<Subcommand> commands) {
        super(command);
        this.commands = commands;
        
    }

    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        if (!handleSenderHasPermission(sender, command.getPermission()) || !handleArgumentLength(sender, 0, args.length, 2)) {
            return;
        }
        
        int page = parsePageNumber(args);
        
        List<String> filteredCommands = filterCommands(sender, args);
        int totalPages = getTotalPages(filteredCommands);
        
        if (page <= totalPages) {
            
        } else {
            
        }
        
    }
    
    private int parsePageNumber(String[] args) {
        int page = 1;
        
        if (args.length != 0) {
            try {
                page = Integer.parseInt(args[args.length - 1]);
            } catch (NumberFormatException e) {}
        }

        return page;
    }
    
    private List<String> filterCommands(CommandSender sender, String[] args) {
        Predicate<Subcommand> criteria;
        
        if (args.length <= 1 ) {
            criteria = (subcommand) -> sender.hasPermission(subcommand.getPermission());
            
        } else {
            criteria = (subcommand) -> sender.hasPermission(subcommand.getPermission()) && subcommand.getName().startsWith(args[args.length - 2]);
        }
        
        return commands.stream().filter(criteria).limit(page * SIZE).map(Subcommand::getUsage).collect(Collectors.toList());
    }
    
    
    private int getTotalPages(List<String> returnedCommands) {
        
        if (returnedCommands.isEmpty()) {
            return 0;
        } else {
            return (int) Math.max(1, Math.floor(returnedCommands.size() / SIZE));
        }
  
    }
    
    private void printCommands(CommandSender sender, int page, int totalPages, String[] args, List<String> subcommands) {
        String helpTopic;
        if (args.length <= 1) {
            helpTopic = "All";
        } else {
            helpTopic = args[1];
        }
        
        sender.sendMessage(ChatColor.GOLD + "==== Help: " + ChatColor.RED + helpTopic + ChatColor.GOLD 
                    + " === Page: " + ChatColor.RED + page + "/" + totalPages + ChatColor.GOLD + " ====");
        
        sender.sendMessage(subcommands.subList(page * SIZE - SIZE, page * SIZE).toArray(new String[0]));
    }
    
}
