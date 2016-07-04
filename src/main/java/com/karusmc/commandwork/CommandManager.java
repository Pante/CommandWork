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

import com.karusmc.commandwork.reference.CommandHandler;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandManager {
    
    public static final Map<String, Subcommand> REGISTERED_COMMANDS = new HashMap<>();
    
    
    public static void register(CommandHandler handler, Subcommand command) {
        REGISTERED_COMMANDS.put(command.getName(), command);
        handler.register(command);
    }
    
    
    public static void unregister(CommandHandler handler, Subcommand command) {
        REGISTERED_COMMANDS.remove(command.getName());
        handler.unregister(command);
    }
    
}
