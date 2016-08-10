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

import static com.karusmc.commandwork.ConditionValidator.handleNoPermission;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandDispatcher implements TabExecutor {
        
    private final CommandParser parser;
    
    private final Map<String, CommandCallable> commands;
    private final CommandCallable invalidCommandHandler;
    
    
    public CommandDispatcher() {
        commands = new HashMap<>();
        parser = new CommandParser(commands);
        
        invalidCommandHandler = new InvalidCommandHandler();
    }
    
    public CommandDispatcher(CommandParser parser, CommandCallable invalidCommandHandler) {
        commands = new HashMap<>();
        this.parser = parser;
              
        this.invalidCommandHandler = invalidCommandHandler;
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
        
        if (parser.isQuery(args) && handleNoPermission(sender, returned.getPermission())) {
            sender.sendMessage(returned.getInfo());
            
        } else if (returned.conditionsAreValid(sender, args)) {
            returned.call(sender, args);    
        }
        
        return true;
    }
    
}
