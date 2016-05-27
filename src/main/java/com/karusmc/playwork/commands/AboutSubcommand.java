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
package com.karusmc.playwork.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Displays plugin information.
 */
public class AboutSubcommand implements Subcommand, CommandUtil {
    
    private PluginDescriptionFile pluginDescription;
    private Command meta;
    private String url;
    
    private AboutSubcommand() {};
    
    public AboutSubcommand(PluginDescriptionFile description, String url) {
        this.pluginDescription = description;
        this.url = url;
    }
    
    
    public PluginDescriptionFile getPluginDescription() {
        return pluginDescription;
    }
    
    public void setPlugin(PluginDescriptionFile description) {
        this.pluginDescription = description;
    }
    
    @Override
    public Command getMeta() {
        return meta;
    }
    
    @Override
    public void setMeta(Command properties) {
        this.meta = properties;
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        // Inheritied from CommandUtil
        if (!checkLength(sender, this, args, 1, 1)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        sender.sendMessage(ChatColor.GOLD 
                + pluginDescription.getName() + " version: " + ChatColor.RED + pluginDescription.getVersion() + "\n"
                + ChatColor.GOLD + pluginDescription.getDescription() + "\n"
                + ChatColor.GOLD + "Author(s): " + ChatColor.RED + pluginDescription.getAuthors().toString()
                + ChatColor.GOLD + "Source code & development resources: " + ChatColor.RED + url + " \n"
                + ChatColor.GOLD +  MainCommand.getHelpMessage());
        
    }
    
}
