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

import org.bukkit.command.CommandSender;

import org.junit.Test;

import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.mockSender;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidCommandHandlerTest {
    
    private String message = "Mock error message";
    private InvalidCommandHandler handler = new InvalidCommandHandler(message);
    private CommandSender sender = mockSender(true);
    
    
    @Test
    public void call_Calls_CommandSender_sendMessage() {
        handler.call(sender, null);
        
        verify(sender, times(1)).sendMessage(message);
    }
    
    @Test
    public void conditionsAreValid_Calls_CommandSender_sendMessage_ReturnsFalse() {
        boolean returned = handler.conditionsAreValid(sender, null);
        
        verify(sender, times(1)).sendMessage(message);
        assertFalse(returned);
    }
    
}
