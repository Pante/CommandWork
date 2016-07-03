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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.junit.Test;

import static com.karusmc.commandworks.mock.MockBukkitObjectFactory.*;
import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class SubcommandTest  {
    
    private Command command = mockCommand();
    private TestSubcommand testCommand = new TestSubcommand(command);
    
    
    private static class TestSubcommand extends Subcommand {

        public TestSubcommand(Command command) {
            super(command);
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    @Test
    public void getInfo_determinesInvalidPermission() {
        
        CommandSender sender = mockSender(false);
        
        String expected = ChatColor.RED + "You do not have permission to use this command.";    
        String returned = testCommand.getInfo(sender);
        
        assertEquals(expected, returned);
        
    }
    
    
    @Test
    public void getInfo_returnsCommandInfo() {
        
        CommandSender sender = mockSender(true);
        
        StringBuilder buffy = new StringBuilder();

        buffy.append("&6==== Help: &c").append(command.getName()).append("&6 ====");
        buffy.append("\nAliases: &c").append(command.getAliases().toString());
        buffy.append("&6\nDescription: &c").append(command.getDescription());
        buffy.append("&6\nUsage: &c").append(command.getUsage());

        String expected = ChatColor.translateAlternateColorCodes('&', buffy.toString());
        String returned = testCommand.getInfo(sender);
        
        assertEquals(expected, returned);
        
    }
    
}
