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
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandUtility {
    
    private CommandUtility() {}
    
    
    public static boolean handleSenderIsPlayer(CommandSender sender) {
        boolean senderIsPlayer = checkSenderIsPlayer(sender);
        
        if (!senderIsPlayer) {
            sender.sendMessage("This is a player only command.");
        }
        return senderIsPlayer;
        
    }
    
    public static boolean checkSenderIsPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
    
    
    public static boolean handleSenderHasPermission(CommandSender sender, String permission) {
        boolean senderHasPermission = checkSenderHasPermission(sender, permission);
        
        if(!senderHasPermission) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }
        return senderHasPermission;
        
    }
    
    public static boolean checkSenderHasPermission(CommandSender sender, String permission) {
        return sender.hasPermission(permission);
    }
    
    
    public static boolean handleArgumentLength(CommandSender sender, Command cmd, int min, int length, int max) {
        boolean lengthIsValid = checkArgumentLength(min, length, max);
        
        if(!lengthIsValid) {
            sender.sendMessage(ChatColor.RED + cmd.getUsage());
        }
        return lengthIsValid;
        
    }
    
    public static boolean checkArgumentLength(int min, int length, int max) {
        return (length >= min && length <= max && length >= 0);
    }
    
}
