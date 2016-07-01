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
package com.karusmc.commandwork.reference;

import com.karusmc.commandwork.Subcommand;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidSubcommandTest {
    
    public Subcommand testCommand = new InvalidSubcommand("/dummy");
    public CommandSender sender = mock(CommandSender.class);
    public String expected = ChatColor.RED + "Invalid command! Type /dummy for  a list of commands";
    
    @Test
    public void execute_calls_sendMessage() {
        
        testCommand.execute(sender, new String[] {});
        
        verify(sender, times(1)).sendMessage(expected);
    }
    
    @Test
    public void formatInformation_returns_string() {
        String returned = testCommand.formatInformation();
        
        assertEquals("formatInformation formatted incorrectly", expected, returned);
    }
    
}
