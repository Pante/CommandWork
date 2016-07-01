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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MockSubcommand extends Subcommand {
    
    public boolean logicExecuted;
    
    
    public MockSubcommand() {
        super(null);
        
        Command mockCommand = mock(Command.class);
        
        List<String> aliases = new ArrayList<>();
        aliases.add("mocked");
        aliases.add("m");
        
        when(mockCommand.getAliases()).thenReturn(aliases);
        
        when(mockCommand.getName()).thenReturn("Mock command");
        when(mockCommand.getDescription()).thenReturn("This is a mocked command");
        when(mockCommand.getUsage()).thenReturn("/mock command [example]");
        
        super.setCommand(mockCommand);
        
        logicExecuted = false;
        
    }
    public MockSubcommand(Command mockCommand) {
        super(mockCommand);
        logicExecuted = false;
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        logicExecuted = true;
    }
    
}
