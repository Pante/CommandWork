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
package com.karusmc.commandwork.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandManager {
    
    private static final Map<String, Command> REGISTERED_COMMANDS = new HashMap<>();
    private static String helpMessage = "Type /<plugin name> help for more information";
    
    public static void register(String fullName, Command command) {
        REGISTERED_COMMANDS.put(fullName, command);
    }
    
    public static void register(Map<String, Command> commands) {
        REGISTERED_COMMANDS.putAll(commands);
    }
    
    
    public static Command getCommand(String fullName) {
        return REGISTERED_COMMANDS.getOrDefault(fullName, new InvalidCommand(fullName));
    }
    
    public static Map<String, Command> getRegisteredCommands() {
        return REGISTERED_COMMANDS;
    }
    
    
    public static String getHelpMessage() {
        return helpMessage;
    }
    
    public static void setHelpMessage(String message) {
        helpMessage = message;
    } 
    
}
