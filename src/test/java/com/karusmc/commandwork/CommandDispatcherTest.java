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

import java.lang.reflect.Field;
import java.util.*;

import org.bukkit.command.CommandSender;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandDispatcherTest {
    
    private CommandDispatcher dispatcher;
    private Map<String, CommandCallable> commands;
    
    private CommandParser parser;
    private MockCommand command;
    private CommandSender sender;
    
    
    public CommandDispatcherTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        
        command = new MockCommand();
        parser = mock(CommandParser.class);
        
        dispatcher = new CommandDispatcher(parser, command);
        
        Field field = CommandDispatcher.class.getDeclaredField("commands");
        field.setAccessible(true);
        commands = (Map<String, CommandCallable>) field.get(dispatcher);
        
        sender = mockSender(true);
    }
    
    
    @Test
    public void register_PutsCommandInMap() {
        dispatcher.register(command);
        
        boolean containsCommand = commands.containsValue(command);
        assertTrue(containsCommand);
    }
    
    
    @Test
    public void unregister_RemovesCommandFromMap() {
        command.getAliases().forEach(alias -> commands.put(alias, command));
        dispatcher.unregister(command);
      
        boolean containsCommand = commands.containsValue(command);
        assertFalse(containsCommand);
    }
    
    
    @Test
    public void onTabComplete_Calls_Parser_getMatchingNames() {
        dispatcher.onTabComplete(null, null, null, null);
        
        verify(parser, times(1)).getMatchingNames(null, null);
    }
    
    
    @Test
    public void onCommand_Calls_CommandSender_sendMessage() {
        
        when(parser.getCommandOrDefault(command, null, null)).thenReturn(command);
        when(parser.isQuery(null)).thenReturn(true);
        
        sender = mockSender(true);
        
        dispatcher.onCommand(sender, null, null, null);
        
        verify(sender, times(1)).sendMessage(command.getInfo());
    }
    
    
    @Test
    public void onCommand_Calls_CommandCallable_Call() {
        
        CommandCallable dummy = mock(CommandCallable.class);
        
        when(parser.getCommandOrDefault(command, null, null)).thenReturn(dummy);
        when(parser.isQuery(null)).thenReturn(false);
        
        when(dummy.conditionsAreValid(sender, null)).thenReturn(true);
        
        dispatcher.onCommand(sender, null, null, null);
        
        verify(dummy, times(1)).call(sender, null);
    }
    
    
}
