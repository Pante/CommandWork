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

import com.karusmc.commandwork.CommandCallable;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.PluginDescriptionFile;

import static com.karusmc.commandwork.ConditionValidator.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class AboutCommand extends CommandCallable {
    
    private String description;
    
    private AboutCommand() {
        super(null);
    }
    
    public AboutCommand(Command command, PluginDescriptionFile description) {
        super(command);
        this.description = formatAbout(description);
    }
    
    
    private String formatAbout(PluginDescriptionFile description) {
        StringBuilder buffy = new StringBuilder();
        buffy.append("&6").append(description.getName()).append(" version: &c").append(description.getVersion()).append("\n&6");
        buffy.append(description.getDescription());
        buffy.append("\nAuthor(s): &c").append(description.getAuthors().toString());
        buffy.append("\n&6Source code & development resources: &c").append(description.getWebsite());

        return ChatColor.translateAlternateColorCodes('&', buffy.toString());
    }
    
    
    @Override
    public void call(CommandSender sender, String[] args) {
        sender.sendMessage(description);
    }
    
    
    @Override
    public List<String> getAliases() {
        List<String> aliases = command.getAliases();
        aliases.add("about");
        return aliases;
    }
    
    @Override
    public String getTabCompleteName() {
        return "about";
    }

    @Override
    public boolean conditionsAreValid(CommandSender sender, String[] args) {
        return (handleNoPermission(sender, command.getPermission()) && handleInvalidLength(sender, 1, args.length, 1));
    }
}
