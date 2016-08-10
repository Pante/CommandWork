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
package com.karusmc.commandwork.mockobjects;

import java.util.*;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MockBukkitObjectFactory {
    
    public static final String MOCK_NAME = "Mock name";
    public static final String MOCK_DESCRIPTION = "Mock description";
    public static final String MOCK_PERMISSION = "Mock.Permission";
    public static final String MOCK_USAGE = "Mock usage";
    
    public static final String MOCK_ALIAS_1 = "Mock alias 1";
    public static final String MOCK_ALIAS_2 = "Mock alias 2";
    
    
    
    public static CommandSender mockSender(boolean returnValue) {
        
        CommandSender sender = mock(CommandSender.class);
        when(sender.hasPermission(MOCK_PERMISSION)).thenReturn(returnValue);
        
        return sender;
    }
    
    
    public static Player mockPlayer(boolean returnValue) {
        
        Player player = mock(Player.class);
        when(player.hasPermission(MOCK_PERMISSION)).thenReturn(returnValue);
        
        return player;
    }
    
    
    public static Command mockCommand() {
        
        Command command = mock(Command.class);
        
        List<String> aliases = new ArrayList<>();
        aliases.add(MOCK_ALIAS_1);
        aliases.add(MOCK_ALIAS_2);
        
        when(command.getAliases()).thenReturn(aliases);
        
        when(command.getDescription()).thenReturn(MOCK_DESCRIPTION);
        when(command.getName()).thenReturn(MOCK_NAME);
        when(command.getPermission()).thenReturn(MOCK_PERMISSION);
        when(command.getUsage()).thenReturn(MOCK_USAGE);
        
        return command;
    }
    
    
}
