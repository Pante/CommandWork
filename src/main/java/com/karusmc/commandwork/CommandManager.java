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

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Class contains a map of all the current CommandCallables registered.
 */
public class CommandManager {
    
    /**
     * Contains all the registered commands
     */
    public static final Map<String, CommandCallable> REGISTERED_COMMANDS = new HashMap<>();
    
    /**
     * Method that registers the CommandCallable with the CommandDispatcher and this class.
     * @param dispatcher The dispatcher to be register the CommandCallable to
     * @param command The command to register
     */
    public static void register(CommandDispatcher dispatcher, CommandCallable command) {
        REGISTERED_COMMANDS.put(command.getName(), command);
        dispatcher.register(command);
    }
    
    /**
     * Method that unregisters the CommandCallable from the CommandDispatcher and this class
     * @param dispatcher The dispatcher to unregister the CommandCallable from
     * @param command The command to unregister
     */
    public static void unregister(CommandDispatcher dispatcher, CommandCallable command) {
        REGISTERED_COMMANDS.remove(command.getName());
        dispatcher.unregister(command);
    }
    
}
