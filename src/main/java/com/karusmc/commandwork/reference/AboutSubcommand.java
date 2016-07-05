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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import static com.karusmc.commandwork.CommandUtility.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class AboutSubcommand extends Subcommand {
    
    private String information;
    
    private AboutSubcommand() {
        super(null);
    }
    
    public AboutSubcommand(Command command, PluginDescriptionFile description) {
        super(command);
        
        StringBuilder buffy = new StringBuilder();
        buffy.append("&6").append(description.getName()).append(" version: &c").append(description.getVersion()).append("\n&6");
        buffy.append(description.getDescription());
        buffy.append("\nAuthor(s): &c").append(description.getAuthors().toString());
        buffy.append("\n&6Source code & development resources: &c").append(description.getWebsite());

        information = ChatColor.translateAlternateColorCodes('&', buffy.toString());
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (handleSenderHasPermission(sender, command.getPermission()) && handleArgumentLength(sender, 1, args.length, 1)) {
            sender.sendMessage(information);
        }
    }
    
}
