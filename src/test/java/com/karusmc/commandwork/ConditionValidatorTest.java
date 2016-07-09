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

import static com.karusmc.commandwork.ConditionValidator.*;
import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class ConditionValidatorTest {

    private CommandSender hasPermSender = mockSender(true);    
    private CommandSender noPermSender = mockSender(false);
    
    @Test
    public void handleNotPlayer_notCalls_CommandSender_sendMessage_returnsTrue() {
        boolean returned = handleNotPlayer(mockPlayer(false));
        
        verify(hasPermSender, times(0)).sendMessage(notPlayerMessage);
        assertTrue(returned);
    }
    
    @Test
    public void handleNotPlayer_calls_CommandSender_sendMessage_returnFalse() {
        boolean returned = handleNotPlayer(hasPermSender);
        
        verify(hasPermSender, times(1)).sendMessage(notPlayerMessage);
        assertFalse(returned);
    }
    
    
    @Test
    public void handleNoPermission_noCalls_CommandSender_sendMessage_returnsTrue() {
        boolean returned = handleNoPermission(hasPermSender, MOCK_PERMISSION);
        
        verify(hasPermSender, times(0)).sendMessage(noPermissionMessage);
        assertTrue(returned);
    }
    
    @Test
    public void handleNoPermission_Calls_CommandSender_sendMessage_returnsFalse() {
        boolean returned = handleNoPermission(noPermSender, MOCK_PERMISSION);
        
        verify(noPermSender, times(1)).sendMessage(noPermissionMessage);
        assertFalse(returned);
    }
    
    
    @Test
    public void handleInvalidLength_noCalls_CommandSender_sendMessage_returnsTrue() {
        boolean returned = handleInvalidLength(hasPermSender, 0, 3, 3);
        
        verify(hasPermSender, times(0)).sendMessage(invalidLengthMessage);
        assertTrue(returned);
    }
    
    @Test
    public void handleInvalidLength_calls_CommandSender_sendMessage_returnsFalse() {
        boolean returnedLower = handleInvalidLength(hasPermSender, 2, 1, 3);
        boolean returnedUpper = handleInvalidLength(hasPermSender, 2, 4, 3);
        
        verify(hasPermSender, times(2)).sendMessage(invalidLengthMessage);
        assertFalse(returnedLower);
        assertFalse(returnedUpper);
    }
    
}
