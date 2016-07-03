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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandUtility {
    
    private CommandUtility() {}
    
    
    public static boolean checkSenderIsPlayer(CommandSender sender) {
        boolean senderIsPlayer = sender instanceof Player;
        
        if (!senderIsPlayer) {
            sender.sendMessage("This is a player only command.");
        }
        return senderIsPlayer;
        
    }
   
    
    public static boolean checkSenderHasPermission(CommandSender sender, String permission) {
        boolean senderHasPermission = sender.hasPermission(permission);
        
        if(!senderHasPermission) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }
        
        return senderHasPermission;
        
    }

    
    
    public static boolean checkArgumentLength(CommandSender sender, Subcommand command, int min, int length, int max) {
        boolean lengthIsValid = length >= min && length <= max;
        
        if(!lengthIsValid) {
            sender.sendMessage(ChatColor.RED + command.getUsage());
        }
        
        return lengthIsValid;
        
    }

}
