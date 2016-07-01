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
import org.junit.*;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import static com.karusmc.commandwork.CommandUtility.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandUtilityTest {
    
    @Test
    public void handleSenderIsPlayer_calls_sendMessage() {
        CommandSender sender = mock(CommandSender.class);
        
        assertFalse(handleSenderIsPlayer(sender));
        verify(sender, times(1)).sendMessage("This is a player only command.");
    }
    
    @Test
    public void checkSenderIsPlayer_returns_validBoolean() {
        Player player = mock(Player.class);
        
        CommandSender notPlayer = mock(CommandSender.class);
        
        assertTrue(checkSenderIsPlayer(player));
        assertFalse(checkSenderIsPlayer(notPlayer));
    }
    
    
    @Test
    public void handleSenderHasPermission_calls_sendMessage() {
        CommandSender sender = mock(CommandSender.class);
        
        when(sender.hasPermission("dummy.permission.fail")).thenReturn(false);
        
        assertFalse(handleSenderHasPermission(sender, "dummy.permission.fail"));
        verify(sender, times(1)).sendMessage(ChatColor.RED + "You do not have permission to use this command.");
    }
    
    @Test
    public void checkSenderHasPermission_returns_validBoolean() {
        CommandSender sender = mock(CommandSender.class);
        
        when(sender.hasPermission("dummy.permission.fail")).thenReturn(false);
        when(sender.hasPermission("dummy.permission.pass")).thenReturn(true);
        
        assertTrue(checkSenderHasPermission(sender, "dummy.permission.pass"));
        assertFalse(checkSenderHasPermission(sender, "dummy.permission.fail"));
    }
    
    
    @Test
    public void handleArgumentLength_calls_sendMessage() {
        CommandSender sender = mock(CommandSender.class);
        
        Command command = mock(Command.class);
        when(command.getUsage()).thenReturn("Dummy usage");
        
        assertFalse(handleArgumentLength(sender, command, 1, 3, 2));
        verify(sender, times(1)).sendMessage(ChatColor.RED + "Dummy usage");
    }
    
    @Test
    public void checkArgumentLength_returns_validBoolean() {
        assertTrue("Incorrect constrains", checkArgumentLength(1, 2, 3));
        
        assertTrue("Lower constrain", checkArgumentLength(1, 1, 3));
        assertTrue("Upper constrain", checkArgumentLength(1, 3, 3));
        
        assertFalse("Upper constrain not detected", checkArgumentLength(1, 4, 3));
        assertFalse("Argument length not detected", checkArgumentLength(-5, -3, 5));
    }
    
}
