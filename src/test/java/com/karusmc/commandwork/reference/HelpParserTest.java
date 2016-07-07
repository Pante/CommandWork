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

import java.util.function.Predicate;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import org.junit.*;

import com.karusmc.commandwork.mock.*;

import static com.karusmc.commandwork.mock.MockBukkitObjectFactory.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HelpParserTest {
    
    private HelpParser parser;
    private MockSubcommand command;
    
    @Before
    public void setup() {
        parser = new HelpParser();
        command = new MockSubcommand();
    }
    
    
    @Test
    public void getSeachCriteria_EmptyArray_returnsAll() {
        String returned = parser.getSearchCriteria(new String[] {"help"});
        assertEquals("All", returned);
    }
    
    @Test
    public void getSearchCriteria_Number_returnsAll() {
        String returned = parser.getSearchCriteria(new String[] {"help", "1"});
        assertEquals("All", returned);
    }
    
    @Test
    public void getSearchCriteria_All_returnsArg() {
        String returned = parser.getSearchCriteria(new String[] {"help", "test"});
        assertEquals("test", returned);
    }
    
    
    @Test
    public void getPredicate_All_returns_true() {
        Predicate<Subcommand> test = parser.getPredicate(mockSender(true), "All");
        boolean returned = test.test(command);
        
        assertTrue(returned);
    }
    
    @Test
    public void getPredicate_All_returns_false() {
        Predicate<Subcommand> test = parser.getPredicate(mockSender(false), "All");
        boolean returned = test.test(command);
        
        assertFalse(returned);
    }
    
    
    @Test
    public void getPredicate_Mock_returns_true() {
        Predicate<Subcommand> test = parser.getPredicate(mockSender(true), "Mock");
        boolean returned = test.test(command);
        
        assertTrue(returned);
    }
    
    @Test
    public void getPredicate_Mock_returns_false() {
        Predicate<Subcommand> test = parser.getPredicate(mockSender(false), "Mock");
        boolean returned = test.test(command);
        
        assertFalse(returned);
    }
    
    @Test
    public void getPredicate_InvalidName_returns_false() {
        Predicate<Subcommand> test = parser.getPredicate(mockSender(true), "InvalidName");
        boolean returned = test.test(command);
        
        assertFalse(returned);
    }
    
    @Test
    public void getCommandUsages_returns_filteredList() {

        Predicate<Subcommand> test = subcommand -> subcommand.getUsage().equalsIgnoreCase("Mock usage 1");
        
        Collection<Subcommand> commands = new ArrayList<>();
        
        Subcommand command1 = mock(Subcommand.class);
        Subcommand command2 = mock(Subcommand.class);
        
        when(command1.getUsage()).thenReturn("Mock usage 1");
        when(command2.getUsage()).thenReturn("Mock usage 2");
        
        commands.add(command1);
        commands.add(command2);
        
        List<String> commandUsages = parser.getCommandUsages(commands, test);
       
        
        boolean returnedCommand1 = commandUsages.contains(ChatColor.GOLD + command1.getUsage());
        boolean returnedCommand2 = commandUsages.contains(ChatColor.GOLD + command2.getUsage());
        
        assertTrue(returnedCommand1);
        assertFalse(returnedCommand2);
        
    }
    
    
    @Test
    public void getCurrentPage_EmptyArray_returns1() {
        int currentPage = parser.getCurrentPage(new String[] {});
        assertEquals(1, currentPage);
    }
    
    @Test
    public void getCurrentPage_String_returns1() {
        int currentPage = parser.getCurrentPage(new String[] {"help", "Invalid"});
        assertEquals(1, currentPage);
    }
    
    @Test
    public void getCurrentPage_Number_returnsNumber() {
        int currentPage = parser.getCurrentPage(new String[] {"help", "5"});
        assertEquals(5, currentPage);
    }
    
    
    @Test
    public void getTotalPages_0_returns0() {
        int totalPages = parser.getTotalPages(0, 3);
        assertEquals(0, totalPages);
    }
    
    @Test
    public void getTotalPages_2_returns_1() {
        int totalPages = parser.getTotalPages(2, 3);
        assertEquals(1, totalPages);
    }
    
    @Test
    public void getTotalPages_7_returns_3() {
        int totalPages = parser.getTotalPages(7, 3);
        assertEquals(3, totalPages);
    }
 
}
