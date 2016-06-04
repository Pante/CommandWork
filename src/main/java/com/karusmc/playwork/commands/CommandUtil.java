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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public interface CommandUtil {
    
    public default boolean checkSender(CommandSender sender, String permission) {

        if (sender.hasPermission(permission)) {
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return false;
        }

    }
   
    public default boolean checkPlayer(CommandSender sender, String permission) {
       
        if (!(sender instanceof Player)) {
            sender.sendMessage("This is a player only command.");
            return false;
        }
        
        if (sender.hasPermission(permission)) {
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return false;
        }
        
    }
    
    public default boolean checkLength(CommandSender sender, SubcommandExecutor cmd, String[] args, int min, int max) {
        if (args.length < min && args.length > max) {
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + cmd.getCommand().getUsage());
            return false;
        }
    }
    
}
