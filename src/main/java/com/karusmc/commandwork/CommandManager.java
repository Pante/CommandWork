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

import com.karusmc.commandwork.references.CommandHandler;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.Command;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandManager {
    
    public static final Map<String, Command> REGISTERED_COMMANDS = new HashMap<>();
    
    public static void register(CommandHandler handler, Subcommand command) {
        Command bukkitCommand = command.getBukkitCommand();
        REGISTERED_COMMANDS.put(bukkitCommand.getName(), bukkitCommand);
        handler.register(command);
    }
    
    public static void register(Subcommand command ){
        Command bukkitCommand = command.getBukkitCommand();
        REGISTERED_COMMANDS.put(bukkitCommand.getName(), bukkitCommand);
    }
    
}
