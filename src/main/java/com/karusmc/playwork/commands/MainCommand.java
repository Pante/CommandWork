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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MainCommand implements CommandExecutor {
    
    // Fields
    /** Contains commands by their fully registered names */
    public static final HashMap<String, Subcommand> COMMANDS = new HashMap<>();
    private final HashMap<String, Subcommand> SUBCOMMANDS = new HashMap<>();
    
    private static JavaPlugin plugin;
    private static String helpCommand;
    
    
    /** Registers the method with the main command instance
     * 
     * @param plugin
     * @param fullCommmandName The full command name written in the plugin.yml
     * @param subcommand The subcommand to register.
     */
    public void registerSubcommand(String fullCommmandName, Subcommand subcommand) {
        
        COMMANDS.put(fullCommmandName, subcommand);
        
        subcommand.getMeta().setAliases((List<String>) plugin.getDescription().getCommands().get(fullCommmandName).get("aliases"));
        subcommand.getMeta().setPermission((String) plugin.getDescription().getCommands().get(fullCommmandName).get("permission"));
        subcommand.getMeta().setDesc((String) plugin.getDescription().getCommands().get(fullCommmandName).get("description"));
        subcommand.getMeta().setUsage((String) plugin.getDescription().getCommands().get(fullCommmandName).get("usage"));
        
        new ArrayList<>((List<String>) plugin.getDescription().getCommands().get(fullCommmandName).get("aliases"))
            .stream().forEach(alias -> {
                SUBCOMMANDS.put(alias, subcommand);
            });
        
    }
    
    
    @Override
    
    // Implementation of method from CommandExecutor
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length == 0 || SUBCOMMANDS.get(args[0]) == null) {
            sender.sendMessage(ChatColor.RED + "Invalid argument. Type " + helpCommand + " for a list of commands."); 
        } else if (args[args.length - 1].equals("?")) {
            
            CommandMeta meta = SUBCOMMANDS.get(args[0]).getMeta();
            ArrayList<String> messages = new ArrayList<>();
            
            messages.add(ChatColor.GOLD + "==== Help: " + ChatColor.RED + args[0] + ChatColor.GOLD + " ====");
            
            messages.add(ChatColor.GOLD + "\nAliases: "  + ChatColor.RED + meta.getAliases().toString());
            
            messages.add(ChatColor.GOLD + "\nDescription: " + ChatColor.RED + meta.getDesc());
            
            messages.add(ChatColor.GOLD + "\nUsage: " + ChatColor.RED + meta.getUsage());
            
            sender.sendMessage(messages.toArray(new String[messages.size()]));
            
        } else {
            SUBCOMMANDS.get(args[0]).execute(sender, args);
        }
        
        return true;
        
    }
    
    
    // <------ Getter and Setter methods ------>
    
    public static JavaPlugin getPlugin() {
        return plugin;
    }
    
    public static void setPlugin(JavaPlugin plugin) {
        MainCommand.plugin = plugin;
    }
     
    
    public static String getHelpCommand() {
        return helpCommand;
    }
    
    public static void setHelpCommand(String command) {
        helpCommand = command;
    }
    
}
