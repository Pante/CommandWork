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
package com.karusmc.commandworks.references;

import com.karusmc.commandwork.Subcommand;
import com.karusmc.commandwork.reference.CommandHandler;

import com.karusmc.commandworks.mock.MockSubcommand;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import org.junit.*;

import static com.karusmc.commandworks.mock.MockBukkitObjectFactory.mockSender;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandHandlerTest {
    
    private CommandHandler handler;
    private MockSubcommand command;
    private CommandSender sender;
    
    private Map<String, Subcommand> commands;
    private List<String> commandNames;
    
    
    @Before
    public void setup() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        handler = new CommandHandler();
        command = new MockSubcommand();
        sender = mockSender(true);
        
        Field commandsField = CommandHandler.class.getDeclaredField("commands");
        Field commandNamesField = CommandHandler.class.getDeclaredField("commandNames");
        
        commandsField.setAccessible(true);
        commandNamesField.setAccessible(true);
        
        commands = (Map<String, Subcommand>) commandsField.get(handler);
        commandNames = (List<String>) commandNamesField.get(handler);
    }
    
    
    @Test
    public void register_registersCommand() {
        handler.register(command);
        
        boolean commandsExpected = commands.containsKey(command.getAliases().get(0)) && commands.containsKey(command.getAliases().get(1));
        boolean commandNamesExpected = commandNames.contains(command.getTabCompleteName());
        
        assertTrue(commandsExpected);
        assertTrue(commandNamesExpected);
        
    }
    
    
    @Test
    public void unregister_removesCommand() {
        
        commands.put(command.getAliases().get(0), command);
        commandNames.add(command.getTabCompleteName());
        
        handler.unregister(command);
        
        boolean commandsExpected = commands.containsKey(command.getAliases().get(0));
        boolean commandNamesExpected = commandNames.contains(command.getTabCompleteName());
        
        assertFalse(commandsExpected);
        assertFalse(commandNamesExpected);
        
    }
    
    
    @Test
    public void onTabComplete_returnsNull() {
        
        List<String> tabCompleteNames = handler.onTabComplete(sender, null, null, new String[] {});
        
        assertNull(tabCompleteNames);
        
    }
    
    
    @Test
    public void onTabComplete_returnsName() {
        
        String expected = command.getAliases().get(0);
        commandNames.add(expected);
        
        String returned = handler.onTabComplete(sender, null, null, new String[] {"M"}).get(0);
        
        
        assertEquals(expected, returned);
        
    }
    
    
    @Test
    public void onCommand_returnsLabelInfo() {
        Subcommand mock1 = mock(Subcommand.class);
        Subcommand mock2 = mock(Subcommand.class);
        
        when(mock1.getInfo(sender)).thenReturn("Mock 1 info");
        when(mock2.getInfo(sender)).thenReturn("Mock 2 info");
        
        commands.put("Mock1", mock1);
        commands.put("Mock2", mock2);
        
        handler.onCommand(sender, null, "Mock1", new String[] {"?"});
        
        verify(sender, times(1)).sendMessage("Mock 1 info");
        
    }
    
    @Test
    public void onCommand_returnsArgumentInfo() {
        Subcommand mock1 = mock(Subcommand.class);
        Subcommand mock2 = mock(Subcommand.class);
        
        when(mock1.getInfo(sender)).thenReturn("Mock 1 info");
        when(mock2.getInfo(sender)).thenReturn("Mock 2 info");
        
        commands.put("Mock1", mock1);
        commands.put("Mock2", mock2);
        
        handler.onCommand(sender, null, "Mock1", new String[] {"Mock2", "?"});
        
        verify(sender, times(1)).sendMessage("Mock 2 info");
        
    }
    
    @Test
    public void onCommand_executesLabel() {
        Subcommand mock1 = mock(Subcommand.class);
        Subcommand mock2 = mock(Subcommand.class);
        
        commands.put("Mock1", mock1);
        commands.put("Mock2", mock2);
        
        handler.onCommand(sender, null, "Mock1", new String[] {});
        
        verify(mock1, times(1)).execute(sender, new String[] {});
        
    }
    
    @Test
    public void onCommand_executesArgument() {
        Subcommand mock1 = mock(Subcommand.class);
        Subcommand mock2 = mock(Subcommand.class);
        
        commands.put("Mock1", mock1);
        commands.put("Mock2", mock2);
        
        String[] args = new String[] {"Mock2"};
        
        handler.onCommand(sender, null, "Mock1", args);
        
        verify(mock2, times(1)).execute(sender, args);
    }
  
}
