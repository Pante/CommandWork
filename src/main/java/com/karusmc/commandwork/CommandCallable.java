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
import org.bukkit.command.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public abstract class CommandCallable {
    
    protected Command command;
    
    
    private CommandCallable() {}
    
    public CommandCallable(Command command) {
        this.command = command;
    }
            
            
    public abstract void call(CommandSender sender, String label, String[] args);
    
    public abstract boolean conditionsAreValid(CommandSender sender, String[] args);
    
    
    public String getInfo() {
        StringBuilder buffy = new StringBuilder();

        buffy.append("&6==== Help: &c").append(getName()).append("&6 ====");
        buffy.append("&6\nUsage: &c").append(getUsage());
        buffy.append("&6\nDescription: &c").append(getDescription());
        buffy.append("\nAliases: &c").append(getAliases().toString());

        return ChatColor.translateAlternateColorCodes('&', buffy.toString());
    }
    
    
    public String getName() {
        return command.getName();
    }
    
    public String getDescription() {
        return command.getDescription();
    }
    
    public String getUsage() {
        return command.getUsage();
    }
    
    public String getPermission() {
        return command.getPermission();
    }
    
    public String getTabCompleteName() {
        return command.getAliases().get(0);
    }
    
    
    public List<String> getAliases() {
        return command.getAliases();
    }
    
}
