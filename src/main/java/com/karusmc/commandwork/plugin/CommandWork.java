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
package com.karusmc.commandwork.plugin;


import com.karusmc.commandwork.*;
import com.karusmc.commandwork.reference.AboutCommand;
import com.karusmc.commandwork.reference.help.HelpCommand;

import org.bukkit.plugin.java.JavaPlugin;

import static com.karusmc.commandwork.CommandManager.register;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandWork extends JavaPlugin {
    
    @Override
    public void onEnable() {
        
        getLogger().warning("Users are highly discouraged from running CommandWork on a production server. Maven-shade CommandWork instead.");
        
        CommandDispatcher dispatcher = new CommandDispatcher();
        
        register(dispatcher, new InvalidCommandHandler(getCommand("commandwork invalid")));
        register(dispatcher, new HelpCommand(getCommand("commandwork help"), CommandManager.REGISTERED_COMMANDS.values(), 2));
        register(dispatcher, new AboutCommand(getCommand("commandwork about"), getDescription()));

        getCommand("commandwork").setExecutor(dispatcher);
    }

    @Override
    public void onDisable() {}
    
}
