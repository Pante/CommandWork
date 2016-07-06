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
package com.karusmc.commandwork.mock;

import com.karusmc.commandwork.Subcommand;


import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MockSubcommand extends Subcommand {
    
    private String message;
    
    public MockSubcommand() {
        super(MockBukkitObjectFactory.mockCommand());
        message = "Mock command executed";
    }
    
    public MockSubcommand(String message) {
        super(MockBukkitObjectFactory.mockCommand());
        this.message = message;
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(message);
    }
    
    
    @Override
    public String getInfo(CommandSender sender) {
        return "Mock information";
    }
        
}
