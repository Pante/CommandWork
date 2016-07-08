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

import org.bukkit.command.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandDispatcher implements TabExecutor {
    
    private Map<String, CommandCallable> commands;
    private CommandCallable invalidCommandHandler;
    private CommandParser parser;
    
    
    public CommandDispatcher() {
        commands = new HashMap<>();
        invalidCommandHandler = new InvalidCommandHandler();
        parser = new CommandParser(commands);
    }
    
    public CommandDispatcher(CommandCallable invalidCommandHandler, CommandParser parser) {
        commands = new HashMap<>();
        this.invalidCommandHandler = invalidCommandHandler;
        this.parser = parser;
    }
    
    
    public void register(CommandCallable command) {
        command.getAliases().forEach(alias -> commands.putIfAbsent(alias, command));
    }
    
    public void unregister(CommandCallable command) {
        command.getAliases().forEach(commands::remove);
    }

    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command  command, String label, String[] args) {
        return parser.getMatchingNames(sender, args);
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandCallable returned = parser.getCommandOrDefault(invalidCommandHandler, label, args);
        
        if (returned.conditionsAreValid(sender, args)) {
            returned.call(sender, label, args);    
        }
        
        return true;
    }
    
}
