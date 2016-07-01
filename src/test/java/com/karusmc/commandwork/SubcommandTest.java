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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class SubcommandTest {
    
    private Command mockCommand;
    private MockSubcommand mockSubcommand;
    
    @Before
    public void setup() {
        mockCommand = mock(Command.class);
        
        List<String> aliases = new ArrayList<>();
        aliases.add("mocked");
        aliases.add("m");
        
        when(mockCommand.getAliases()).thenReturn(aliases);
        
        when(mockCommand.getName()).thenReturn("Mock command");
        when(mockCommand.getDescription()).thenReturn("This is a mocked command");
        when(mockCommand.getUsage()).thenReturn("/mock command [example]");
        
        mockSubcommand = new MockSubcommand();
    }
    
    @Test
    public void formatInformation_returns_formattedInfo() {
        String expected = ChatColor.GOLD + "==== Help: " + ChatColor.RED + mockCommand.getName() + ChatColor.GOLD + " ===="
                + "\nAliases: " + ChatColor.RED + mockCommand.getAliases().toString() + ChatColor.GOLD
                + "\nDescription: " + ChatColor.RED + mockCommand.getDescription() + ChatColor.GOLD
                + "\nUsage: " + ChatColor.RED + mockCommand.getUsage();
        
        assertEquals("Incorrect formatting", expected, mockSubcommand.formatInformation());
    }
    
    @Test
    public void execute_calls_executeLogic() {
        mockSubcommand.logicExecuted = false;
        
        mockSubcommand.execute(null, null);
        assertTrue("executeLogic was not called", mockSubcommand.logicExecuted);
    }
    
}
