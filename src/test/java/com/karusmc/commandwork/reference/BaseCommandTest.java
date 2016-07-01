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

import com.karusmc.commandwork.MockSubcommand;

import org.bukkit.command.CommandSender;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class BaseCommandTest {
    
    public MockSubcommand subcommand;
    public InvalidSubcommand invalidHandler;
    public BaseCommand command;
    public CommandSender sender;
    
    
    @Before
    public void setup() {
        subcommand = new MockSubcommand();
        invalidHandler = new InvalidSubcommand("/dummy");
        command = new BaseCommand(invalidHandler);
        
        sender = mock(CommandSender.class);
    }
    
    
    @Test
    public void registerSubcommand_registers_command() {
        subcommand.logicExecuted = false;
        
        command.registerSubcommand(subcommand);
        
        command.onCommand(null, null, null, new String[] {subcommand.getCommand().getAliases().get(0)});
        assertTrue(subcommand.logicExecuted);
    }
}
