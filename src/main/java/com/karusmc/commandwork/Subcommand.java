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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public abstract class Subcommand {
    
    protected Command command;
    
    
    private Subcommand() {}
    
    protected Subcommand(Command command) {
        this.command = command;
    }
    
    
    public abstract void execute(CommandSender sender, String[] args);
    
    
    public String getInfo() {
        StringBuilder buffy = new StringBuilder();
        
        buffy.append("&6==== Help: &c").append(command.getName()).append("&6 ====");
        buffy.append("\nAliases: &c").append(command.getAliases().toString());
        buffy.append("&6\nDescription: &c").append(command.getDescription());
        buffy.append("&6\nUsage: &c").append(command.getUsage());
        
        String info = ChatColor.translateAlternateColorCodes('&', buffy.toString());
        
        return info;
    }
    
    
    public Command getBukkitCommand() {
        return command;
    }
    
}
