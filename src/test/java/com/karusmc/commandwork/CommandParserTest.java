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

import com.karusmc.commandwork.mockobjects.MockCommand;

import java.util.*;

import org.bukkit.command.CommandSender;

import org.junit.Test;

import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.mockSender;
import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandParserTest {
    
    private Map<String, CommandCallable> commands;
    private MockCommand command;
    
    private CommandParser parser;
    
    
    public CommandParserTest() {
        commands = new HashMap<>();
        command = new MockCommand();
        parser = new CommandParser(commands);
        
        commands.put("Key", command);
    }
    
    
    @Test
    public void getMatchingNames_ReturnsCommandUsage() {
        CommandSender sender = mockSender(true);
        
        List<String> matchingNames = parser.getMatchingNames(sender, new String[] {"Mock"});
        String returned = matchingNames.get(0);
        
        String expected = command.getTabCompleteName();
        
        assertEquals(expected, returned);
    }
    
    @Test
    public void getMatchingNames_NoPermission_ReturnsEmptyList() {
        CommandSender sender = mockSender(false);
        
        boolean isEmpty = parser.getMatchingNames(sender, new String[] {"Mock"}).isEmpty();
        
        assertTrue(isEmpty);
    }
    
    
    @Test
    public void getMatchingNames_NoMatch_ReturnsEmptyList() {
        CommandSender sender = mockSender(true);
        
        boolean isEmpty = parser.getMatchingNames(sender, new String[] {"Tab Complete"}).isEmpty();
        
        assertTrue(isEmpty);
    }
    
    
    @Test
    public void getCommandOrDefault_NoArguments_ReturnsCommand() {
        CommandCallable returnedCommand = parser.getCommandOrDefault(null, "Key", new String[] {});
        
        assertEquals(command, returnedCommand);
    }
    
    @Test
    public void getCommandOrDefault_Argument_ReturnsCommand() {
        CommandCallable returnedCommand = parser.getCommandOrDefault(null, null, new String[] {"Key"});
        
        assertEquals(command, returnedCommand);
    }
    
    
    @Test
    public void getCommandOrDefault_ReturnsDefault() {
        
        CommandCallable defaultCommand = new MockCommand();
        CommandCallable returnedCommand = parser.getCommandOrDefault(defaultCommand, null, new String[] {});
        
        assertEquals(defaultCommand, returnedCommand);
    }
    
    
    @Test
    public void isQuery_firstArgument_returnsTrue() {
        boolean returned = parser.isQuery(new String[] {"?"});
        
        assertTrue(returned);
    }
    
    @Test
    public void isQuery_secondArgument_returnsTrue() {
        boolean returned = parser.isQuery(new String[] {"Nope", "?"});
        
        assertTrue(returned);
    }
    
    @Test
    public void isQuery_firstArgument_returnsFalse() {
        boolean returned = parser.isQuery(new String[] {"Nope"});
        
        assertFalse(returned);
    }
    
    @Test
    public void isQuery_secondArgument_returnsFalse() {
        boolean returned = parser.isQuery(new String[] {"Nope", "Nope"});
        
        assertFalse(returned);
    }
    
}
