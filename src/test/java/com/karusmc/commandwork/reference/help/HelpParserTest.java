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
package com.karusmc.commandwork.reference.help;

import com.karusmc.commandwork.CommandCallable;
import com.karusmc.commandwork.mockobjects.MockCommand;

import java.util.*;
import java.util.function.Predicate;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import org.junit.Test;

import static com.karusmc.commandwork.mockobjects.MockBukkitObjectFactory.*;
import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpParserTest {
    
    private HelpParser parser = new HelpParser();
    private MockCommand command = new MockCommand();
    
    
    @Test
    public void getCriteria_OneArgument_ReturnsAll() {
        String returned = parser.getCriteria(new String[] {"Work"});
        assertEquals("All", returned);
    }
    
    @Test
    public void getCriteria_IsNumber_ReturnsAll() {
        String returned = parser.getCriteria(new String[] {"Work", "1"});
        assertEquals("All", returned);
    }
    
    
    @Test
    public void getCriteria_TwoArguments_ReturnsSecondArgument() {
        String returned = parser.getCriteria(new String[] {"Work", "Expected"});
        assertEquals("Expected", returned);
    }
    
    
    @Test
    public void getPredicate_All_ReturnsNoNameCheckPredicate_ReturnsTrue() {
        CommandSender sender = mockSender(true);
        
        Predicate<CommandCallable> predicate = parser.getPredicate(sender, "all");
        boolean returned = predicate.test(command);
        assertTrue(returned);
    }
    
    @Test
    public void getPredicate_All_ReturnsNoNameCheckPredicate_ReturnFalse() {
        CommandSender sender = mockSender(false);
        
        Predicate<CommandCallable> predicate = parser.getPredicate(sender, "all");
        boolean returned = predicate.test(command);
        assertFalse(returned);
    }
    
    
    @Test
    public void getPredicate_Argument_ReturnsNameCheckPredicate_ReturnsTrue() {
        CommandSender sender = mockSender(true);
        
        Predicate<CommandCallable> predicate = parser.getPredicate(sender, MOCK_NAME);
        boolean returned = predicate.test(command);
        assertTrue(returned);
    }
    
    @Test
    public void getPredicate_Argument_ReturnsNameCheckPredicate_ReturnsFalse() {
        CommandSender sender = mockSender(true);
        
        Predicate<CommandCallable> predicate = parser.getPredicate(sender, "Invalid name");
        boolean returned = predicate.test(command);
        assertFalse(returned);
    }
    
    
    @Test
    public void getPredicate_HasPermission_Argument_ReturnsNameCheckPredicate_ReturnsFalse() {
        CommandSender sender = mockSender(false);
        
        Predicate<CommandCallable> predicate = parser.getPredicate(sender, MOCK_NAME);
        boolean returned = predicate.test(command);
        assertFalse(returned);
    }
    
    
    @Test
    public void getCommandUsages_ReturnsUsage() {
        Predicate<CommandCallable> test = CommandCallable -> true;
        
        Collection<CommandCallable> commands = new ArrayList<>();
        commands.add(command);
        
        String returned = parser.getCommandUsages(commands, test, 3).get(0);
        assertEquals(ChatColor.GOLD + command.getUsage(), returned);
    }
    
    
    @Test
    public void getPage_NoArguments_Return1() {
        int page = parser.getPage(new String[] {});
        assertEquals(1, page);
    }
    
    @Test
    public void getPage_StringArgument_Return1() {
        int page = parser.getPage(new String[] {"string"});
        assertEquals(1, page);
    }
    
    @Test
    public void getPage_NegativeNumber_Return1() {
        int page = parser.getPage(new String[] {"- 1"});
        assertEquals(1, page);
    }
    
    
    @Test
    public void getTotalPages_ZeroList_ReturnZero() {
        int returned = parser.getTotalPages(0, 100);
        assertEquals(0, returned);
    }
    
    @Test
    public void getTotalPages_ListSmallerThanPage_Returns1() {
        int returned = parser.getTotalPages(3, 10);
        assertEquals(1, returned);
    }
    
    @Test
    public void getTotalPages_ListBiggerThanPage_Returns2() {
        int returned = parser.getTotalPages(4, 3);
        assertEquals(2, returned);
    }
    
}
