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

import org.bukkit.command.*;

import org.junit.Test;

import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.*;
import net.md_5.bungee.api.ChatColor;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandCallableTest {
    
    private static class TestCallable extends CommandCallable {

        public TestCallable(Command command) {
            super(command);
        }

        @Override
        public void call(CommandSender sender, String[] args) {
            throw new UnsupportedOperationException(); 
        }

        @Override
        public boolean conditionsAreValid(CommandSender sender, String[] args) {
            return true;
        }
        
    }
    
    
    @Test
    public void getInfo_ReturnsCommandInfo() { 
        
        Command command = mockCommand();
        TestCallable test = new TestCallable(command);
        
        String expectedInfo = "==== Help: " + command.getName() + " ===="
                + "\nUsage: " + command.getUsage()
                + "\nDescription: " + command.getDescription()
                + "\nAliases: " + command.getAliases().toString();
                
        String returnedInfo = ChatColor.stripColor(test.getInfo());
        
        assertEquals(expectedInfo, returnedInfo);
        
    }
    
}
