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

import java.util.*;

import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import static com.karusmc.commandwork.CommandUtility.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpSubcommand extends Subcommand  {
    
    private final int SIZE = 3;
    private Collection<Subcommand> commands;
    private HelpParser parser;
    private HelpComponentBuilder builder;
    
    
    private HelpSubcommand() {
        super(null);
    }
    
    public HelpSubcommand(Command command, Collection<Subcommand> commands) {
        super(command);
        this.commands = commands;
        parser = new HelpParser();
        builder = new HelpComponentBuilder();
    }
    
    public HelpSubcommand(Command command, Collection<Subcommand> commands, HelpParser parser, HelpComponentBuilder builder) {
        super(command);
        this.commands = commands;
        this.parser = parser;
        this.builder = builder;
    }

    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        if (!handleSenderHasPermission(sender, command.getPermission()) || !handleArgumentLength(sender, 1, args.length, 3)) {
            return;
        }   
        
        String criteria = parser.getSearchCriteria(args);
        int page = parser.getCurrentPage(args);
        
        List<String> commandUsages = parser.getCommandUsages(commands, parser.getPredicate(sender, criteria));
        
        int totalPages = parser.getTotalPages(commandUsages.size(), SIZE);
        
        if (page > totalPages) {
            sender.sendMessage(ChatColor.RED + "No matches found.");
            return;
        }
        
        printCommandUsages(sender, page, totalPages, criteria, commandUsages);
        
        if (sender instanceof Player) {
            Player player = (Player) sender;
            buildAndSendTextComponents(player, criteria, page, totalPages);
        }
        
    }
    
    private void printCommandUsages(CommandSender sender, int page, int totalPages, String search, List<String> subcommands) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6==== Help: &c" + search
                + " &6=== Page: &c" + page + "/" + totalPages + " &6===="));
        
        sender.sendMessage(subcommands.subList(page * SIZE - SIZE, Math.min(page * SIZE, subcommands.size())).toArray(new String[0]));
    
    }
    
    private void buildAndSendTextComponents(Player player, String criteria, int page, int totalPages) {
        
        TextComponent back = builder.buildBackButton(criteria, page);
        TextComponent next = builder.buildNextButton(criteria, page);

        if (page - 1 < 1) {
            back = builder.getBlankSpace();
        }

        if (page + 1 > totalPages) {
            next = builder.getBlankSpace();
        }

        player.spigot().sendMessage(back, builder.getWhiteSpace(), next);

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
