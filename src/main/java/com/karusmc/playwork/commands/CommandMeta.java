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

import java.util.List;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Information about the command.
 */
public class CommandMeta {
    
    // Fields
    private List<String> aliases;
    
    private String permission;
    private String desc;
    private String usage;
    
    public CommandMeta() {};
    
    public CommandMeta(List<String> aliases, String permission, String desc, String usage) {
        this.aliases = aliases;
        this.permission = permission;
        this.desc = desc;
        this.usage = usage;
    }
    
    // <------ Getter & Setter methods ------>
    public List<String> getAliases() {
        return aliases;
    }
    
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
    
    
    public String getPermission() {
        return permission;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
    
}
