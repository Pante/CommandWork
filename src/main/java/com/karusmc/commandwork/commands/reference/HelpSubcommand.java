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
package com.karusmc.commandwork.commands.reference;

import com.karusmc.commandwork.commands.CommandManager;
import com.karusmc.commandwork.commands.SubcommandExecutor;
import com.karusmc.commandwork.commands.CommandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Displays information about a command
 */
public class HelpSubcommand implements SubcommandExecutor, CommandUtil {
    
    // Fields
    private Command meta;
    private final int SIZE = 3;
    
    @Override
    public Command getCommand() {
        return meta;
    }
    
    @Override
    public void setCommand(Command meta) {
        this.meta = meta;
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        if (!checkLength(sender, this, args, 1, 3)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        int page = parsePage(args);
        
        Predicate<Entry<String, Command>> conditions;
        if (args.length == 1 || args[1].equals("all")) {
            conditions = (entry) -> sender.hasPermission(entry.getValue().getPermission());
        } else { 
            conditions = (entry) -> entry.getKey().contains(args[1]) && sender.hasPermission(entry.getValue().getPermission());
        }
        
        ArrayList<String> commands = new ArrayList<>(CommandManager.getRegisteredCommands().entrySet().stream()
                .filter(conditions::test).limit(page * SIZE)
                .map(HashMap.Entry::getKey).collect(Collectors.toList()));
        
        printHelpInfo(sender, args, page, commands);
        
    }
    
    
    private int parsePage(String[] arguments) {
        if (arguments.length == 3) {
            try {
                return Integer.parseInt(arguments[2]);
            } catch (NumberFormatException e) {
                return 1;
            }
        } else {
            return 1;
        }
    }
    
    private void printHelpInfo(CommandSender sender, String[] args, int page, ArrayList<String> commands) {
        
        if (commands.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No matches found.");
            return;
        }
        
        int totalPages = (int) Math.max(1, Math.floor(commands.size() / SIZE));
        
        if (page <= 0 || page > totalPages) {
            sender.sendMessage(ChatColor.RED + "Invalid page number!");
            return;
        }
        
        String helpTopic;
        if (args.length == 1) {
            helpTopic = "All";
        } else {
            helpTopic = args[1];
        }
        
        sender.sendMessage(ChatColor.GOLD + "==== Help: " + ChatColor.RED + helpTopic + ChatColor.GOLD 
                    + " === Page: " + ChatColor.RED + page + "/" + totalPages + ChatColor.GOLD + " ====");
        
        IntStream.range(page * SIZE - SIZE, commands.size())
        .forEach(i -> sender.sendMessage(ChatColor.GOLD + CommandManager.getRegisteredCommands().get(commands.get(i)).getUsage()));
        
    }
    
}
