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

import com.karusmc.commandwork.CommandUtility;
import com.karusmc.commandwork.mock.MockSubcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import org.junit.Test;

import static com.karusmc.commandwork.mock.MockBukkitObjectFactory.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandUtilityTest {
    
    
    @Test
    public void handleSenderIsPlayer_returnsFalse() {
        CommandSender sender = mockSender(true);
        
        boolean returned = CommandUtility.handleSenderIsPlayer(sender);
        
        verify(sender, times(1)).sendMessage(CommandUtility.notPlayerMessage);
       
        assertFalse(returned);
    }
    
        
    @Test
    public void handleSenderIsPlayer_returnsTrue() {
        CommandSender sender = mockPlayer(true);
        
        boolean returned = CommandUtility.handleSenderIsPlayer(sender);
        
        verify(sender, times(0)).sendMessage(CommandUtility.notPlayerMessage);
        
        assertTrue(returned);
    }
    
        
        
    @Test
    public void handleSenderHasPermission_callsSendMessage() {
        CommandSender sender = mockSender(false);
        
        CommandUtility.handleSenderHasPermission(sender, MOCK_PERMISSION);
        
        verify(sender, times(1)).sendMessage(CommandUtility.noPermissionMessage);
        
    }
    
        
    @Test
    public void handleSenderHasPermission_returnsTrue() {
        CommandSender sender = mockSender(true);
        
        boolean returned = CommandUtility.handleSenderHasPermission(sender, MOCK_PERMISSION);
        
        verify(sender, times(0)).sendMessage(CommandUtility.noPermissionMessage);
        
        assertTrue(returned);
    }
    
    
        
    @Test
    public void checkArgumentLength_returnTrue() {
        
        CommandSender sender = mockSender(true);
        
        boolean returnedLower = CommandUtility.handleArgumentLength(sender, 1, 1, 3);
        boolean returnedUpper = CommandUtility.handleArgumentLength(sender, 1, 3, 3);
        
        verify(sender, times(0)).sendMessage(CommandUtility.invalidArgumentLengthMessage);
        
        assertTrue(returnedLower);
        assertTrue(returnedUpper);
        
    }
    
    
    @Test
    public void checkArgumentLength_returnsFalse() {
        
        CommandSender sender = mockSender(true);

        boolean returnedLower = CommandUtility.handleArgumentLength(sender, 1, 0, 3);
        boolean returnedUpper = CommandUtility.handleArgumentLength(sender, 1, 4, 3);
        
        verify(sender, times(2)).sendMessage(CommandUtility.invalidArgumentLengthMessage);
        
        assertFalse(returnedLower);
        assertFalse(returnedUpper);
        
    }
    
}
