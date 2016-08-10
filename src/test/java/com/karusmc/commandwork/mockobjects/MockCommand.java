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
package com.karusmc.commandwork.mockobjects;

import com.karusmc.commandwork.CommandCallable;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MockCommand extends CommandCallable {
    
    private String message;
    private boolean isValid;
    
    
    public MockCommand() {
        super(MockBukkitObjectFactory.mockCommand());
        
        isValid = true;
        message = "Mock command executed";
    }
    
    public MockCommand(String message) {
        super(MockBukkitObjectFactory.mockCommand());
        
        isValid = true;
        this.message = message;
    }
    
    
    @Override
    public void call(CommandSender sender, String[] args) {
        sender.sendMessage(message);
    }
    
    
    @Override
    public String getInfo() {
        return "Mock information";
    }

    @Override
    public boolean conditionsAreValid(CommandSender sender, String[] args) {
        return isValid;
    }
    
    public void setValid(boolean valid) {
        isValid = valid;
    }
    
    
    @Override
    public String getTabCompleteName() {
        return "Mock Tab Complete Name";
    }
        
}
