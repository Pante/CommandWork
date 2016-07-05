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
package com.karusmc.commandworks;

import com.karusmc.commandwork.Subcommand;
import com.karusmc.commandwork.InvalidSubcommandHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import org.junit.Test;

import static com.karusmc.commandworks.mock.MockBukkitObjectFactory.mockSender;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class InvalidSubcommandHandlerTest {
    
    private Subcommand invalidSubcommandHandler = new InvalidSubcommandHandler();
    private CommandSender sender = mockSender(true);
    
    
    @Test
    public void executes_callsSendMessage() {
        invalidSubcommandHandler.execute(sender, new String[] {});
        verify(sender, times(1)).sendMessage(ChatColor.RED + "Invalid command.");
    }
    
    
    @Test
    public void getInfo_callsSendMessage() {
        invalidSubcommandHandler.execute(sender, new String[] {});
        verify(sender, times(1)).sendMessage(ChatColor.RED + "Invalid command.");
    }
    
    @Test
    public void getAliases_returnsEmptyList() {
        boolean expected = invalidSubcommandHandler.getAliases().isEmpty();
        assertTrue(expected);
    }
    
}
