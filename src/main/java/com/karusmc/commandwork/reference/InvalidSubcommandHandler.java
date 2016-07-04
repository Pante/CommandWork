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

import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidSubcommandHandler extends Subcommand {
    
    private String help;
    
    
    public InvalidSubcommandHandler() {
        super(null);
        help = ChatColor.RED + "Invalid command.";
    }
    
    public InvalidSubcommandHandler(String help) {
        super(null);
        this.help = help;
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(help);
    }
    
    @Override
    public String getInfo(CommandSender sender) {
        return help;
    }
    
    
    @Override
    public String getName() {
        return help;
    }
    
    @Override
    public String getDescription() {
        return help;
    }
    
    @Override
    public String getUsage() {
        return help;
    }
    
    @Override
    public String getPermission() {
        return "Invalid.Permission.";
    }
    
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }
    
    
    @Override
    public String getTabCompleteName() {
        return help;
    }
    
}
