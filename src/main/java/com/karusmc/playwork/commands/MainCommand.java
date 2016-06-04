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
package com.karusmc.playwork.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MainCommand implements CommandExecutor {
    
    // Fields
    private static final HashMap<String, Command> REGISTERED_COMMANDS = new HashMap<>();
    private static String helpMessage;
    
    private final HashMap<String, SubcommandExecutor> SUBCOMMANDS;
    
    
    public MainCommand() {
        SUBCOMMANDS = new HashMap<>();
    }
    
    
    public void registerSubcommand(SubcommandExecutor executor, Command command) {
        
        REGISTERED_COMMANDS.put(command.getName(), command);
 
        executor.setCommand(command);
        command.getAliases().stream().forEach(alias -> SUBCOMMANDS.put(alias, executor));
        
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length == 0 || !SUBCOMMANDS.containsKey(args[0])) {
            sender.sendMessage(helpMessage); 
        } else if (args[args.length - 1].equals("?")) {
            sender.sendMessage(getCommandInfo(args[0]));        
        } else {
            SUBCOMMANDS.get(args[0]).execute(sender, args);
        }
        
        return true;
        
    }
    
    private String[] getCommandInfo(String command) {
        
        Command meta = SUBCOMMANDS.get(command).getCommand();
        
        return new String[]{
            ChatColor.GOLD + "==== Help: " + ChatColor.RED + command + ChatColor.GOLD + " ====",
            ChatColor.GOLD + "\nAliases: " + ChatColor.RED + meta.getAliases().toString(),
            ChatColor.GOLD + "\nDescription: " + ChatColor.RED + meta.getDescription(),
            ChatColor.GOLD + "\nUsage: " + ChatColor.RED + meta.getUsage()
        };

    }
    
    
    // <------ Getter & Setter methods ------>
    
    public static HashMap<String, Command> getRegisteredCommands() {
        return REGISTERED_COMMANDS;
    }
    
    public static String getHelpMessage() {
        return helpMessage;
    }
    
    public static void setHelpMessage(String message) {
        helpMessage = message;
    }
     
}
