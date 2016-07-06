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

import com.karusmc.commandwork.reference.AboutSubcommand;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.command.CommandSender;

import org.junit.*;

import static com.karusmc.commandwork.mock.MockBukkitObjectFactory.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class AboutSubcommandTest {
    
    private AboutSubcommand command;
    private PluginDescriptionFile description;
    
    @Before
    public void setup() {
        description = mock(PluginDescriptionFile.class);
        
        when(description.getName()).thenReturn("Mock name");
        when(description.getVersion()).thenReturn("Mock version");
        when(description.getDescription()).thenReturn("Mock description");
        when(description.getAuthors().toString()).thenReturn("Mock author");
        when(description.getWebsite()).thenReturn("Mock website");
        
        command = new AboutSubcommand(mockCommand(), description);
    }
    
    //@Test TODO: Find a way to mock PluginDescriptionFile which is final
    public void execute_printsMessage() {
        CommandSender sender = mockSender(true);
        
        command.execute(sender, new String[] {"About"});

        String expected = ChatColor.translateAlternateColorCodes('&',
                "&6" + description.getName() + " version: &c" + description.getVersion() + "\n"
                + "&6" + description.getDescription() + "\n"
                + "Author(s): &c" + description.getAuthors().toString() + "\n"
                + "&6Source code & development resources: &c" + description.getWebsite());

        
        verify(sender, times(1)).sendMessage(expected);
        
    }
    
}
