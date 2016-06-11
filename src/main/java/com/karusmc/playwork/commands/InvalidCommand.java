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

import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidCommand extends Command {
    
    private String invalidName;
    
    private InvalidCommand() {
        super("Invalid Command");
    }
    
    
    protected InvalidCommand(String invalidCommand) {
        super("Invalid Command");
        this.invalidName = invalidCommand;
    }
    

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        commandSender.sendMessage(ChatColor.RED + "Invalid command: " + invalidName);
        return true;
    }
    
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }
    
    @Override
    public String getDescription() {
        return ("This is an invalid command!");
    }
    
    @Override
    public String getLabel() {
        return "Invalid command: " + invalidName;
    }
    
    @Override
    public String getName() {
        return invalidName;
    }
    
    @Override
    public boolean setName(String invalidName) {
        this.invalidName = invalidName;
        return true;
    }
    
}
