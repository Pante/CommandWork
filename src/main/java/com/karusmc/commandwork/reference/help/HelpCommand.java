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
package com.karusmc.commandwork.reference.help;

import com.karusmc.commandwork.*;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import static com.karusmc.commandwork.ConditionValidator.*;


/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpCommand extends CommandCallable {
    
    private final int SIZE;
    
    private Collection<CommandCallable> commands;
    private HelpParser parser;
    private TextComponentBuilder builder;
    
    
    private HelpCommand() {
        super(null);
        
        SIZE = 0;
    }
    
    public HelpCommand(Command command, Collection<CommandCallable> commands, int size) {
        super(command);
        
        this.commands = commands;
        parser = new HelpParser();
        builder = new TextComponentBuilder(command.getName());
        SIZE = size;
    }
    
    public HelpCommand(Command command, HelpParser parser, TextComponentBuilder builder, Collection<CommandCallable> commands, int size) {
        super(command);
        
        this.commands = commands;
        this.parser = parser;
        this.builder = builder;
        SIZE = size;
    }

    
    @Override
    public void call(CommandSender sender, String[] args) {
        
        String criteria = parser.getCriteria(args);
        int page = parser.getPage(args);
        
        List<String> info = parser.getCommandUsages(commands, parser.getPredicate(sender, criteria));
        
        int totalPages = parser.getTotalPages(info.size(), SIZE);
        
        printInfo(sender, info, criteria, page, totalPages);
        printButtons(sender, criteria, page, totalPages);
        
    }
    
    private void printInfo(CommandSender sender, List<String> info, String search, int page, int totalPages) {
        
        if (page <= totalPages) {
            
            String header = ChatColor.translateAlternateColorCodes('&', "&6==== Help: &c" + search
                + " &6=== Page: &c" + page + "/" + totalPages + " &6====");
            
            sender.sendMessage(header);
            
            int last = Math.min(page * SIZE, info.size());
            sender.sendMessage(info.subList(page * SIZE - SIZE, last).toArray(new String[0]));
            
        } else {
            sender.sendMessage(ChatColor.RED + "No matches found.");
        }
        
    }
    
    private void printButtons(CommandSender sender, String search, int page, int totalPages) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.spigot().sendMessage(builder.getBackButton(search, page - 1), builder.getWhiteSpace(), builder.getNextButton(search, page + 1, totalPages));
        }
    }

    
    @Override
    public boolean conditionsAreValid(CommandSender sender, String[] args) {
        return (handleNoPermission(sender, command.getPermission()) 
                && handleInvalidLength(sender, 0, args.length, 3));
    }
    
    
    @Override
    public List<String> getAliases() {
        List<String> aliases = command.getAliases();
        aliases.add("help");
        return aliases;
    }
    
    
    @Override
    public String getTabCompleteName() {
        return "help";
    }
    
}
