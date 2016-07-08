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
package com.karusmc.commandwork;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidCommandHandler extends CommandCallable {
    
    private String help;
    
    
    public InvalidCommandHandler() {
        super(null);
        help = ChatColor.RED + "Invalid command!";
    }
    
    public InvalidCommandHandler(String help) {
        super(null);
        this.help = help;
    }
    
    
    @Override
    public void call(CommandSender sender, String label, String[] args) {
        sender.sendMessage(help);
    }

    
    @Override
    public boolean conditionsAreValid(CommandSender sender, String[] args) {
        sender.sendMessage(help);
        return false;
    }
    
    
    @Override
    public String getInfo() {
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
