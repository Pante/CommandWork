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
 * Utility classes which contains methods to check for and handle invalid conditions
 */
public class ConditionValidator {
    
    /**
     * Message to display when the sender is not a player
     */
    public static String notPlayerMessage = ChatColor.RED + "This is a player only command.";
    /**
     * Message to display when the sender does not have the necessary permission
     */
    public static String noPermissionMessage = ChatColor.RED + "You do not have permission to use this command.";
    /**
     * Message to display when the arguments length specified is invalid
     */
    public static String invalidLengthMessage = ChatColor.RED + "Invalid number of arguments.";
    
    
    /**
     * Checks for and handles the sender not being a player
     * @param sender An instance of a spigot ComandSender sender
     * @return true if the sender is a player
     */
    public static boolean handleNotPlayer(CommandSender sender) {
        
        if (sender instanceof Player) {
            return true;
            
        } else {
            sender.sendMessage(notPlayerMessage);
            return false;
        }
        
    }
   
    
    /**
     * Checks for and handles the sender not having the necessary permission
     * @param sender An instance of a spigot CommandSender sender
     * @param permission The permission to test for
     * @return Returns true if the sender has the specified permission
     */
    public static boolean handleNoPermission(CommandSender sender, String permission) {
        
        if(sender.hasPermission(permission)) {
            return true;
            
        } else {
            sender.sendMessage(noPermissionMessage);
            return false;
        }
        
    }
    
    
    /**
     * Checks for and handles the argument length being out of the specified bounds
     * @param sender
     * @param min Minimum length inclusive
     * @param length The length of the arguments
     * @param max Maximum length inclusive
     * @return Returns true if the length of the arguments is within the specified minimum and maximum length
     */
    public static boolean handleInvalidLength(CommandSender sender, int min, int length, int max) {
        
        if(length >= min && length <= max) {
            return true;
            
        } else {
            sender.sendMessage(invalidLengthMessage);
            return false;
        }
        
    }
    
}
