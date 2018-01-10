# CommandWork - Just another Command Library

<b> This framework has been superseeded by <a href = "https://github.com/Pante/karus-commons"/>Karus-Commons</a> and is deprecated.</b>


CommandWork is a plugin.yml based Command Library that aims to make subcommands less of a hassle for developers to create and maintain, with its primary goals being simplicity and ease of use. At roughly 20KB, the library is fairly light.

To include it in a plugin, add the jar to your library folder, paste the following in the pom.xml and maven shade the library.
```xml
  <dependency>
    <groupId>com.karusmc</groupId>
    <artifactId>CommandWork</artifactId>
    <version>2.0</version>
    <scope>compile</scope>
    <type>jar</type>
  </dependency>
```
<br>
----------------------------------------
Consider the following commands:
```
/panda feed
```
and 
```
/panda hug
```

All too often what will happen is a developer writing the following:
```java
public boolean onCommand(CommandSeder sender, Command command, String label, String[] args) {
  if (args[0].equalsIgnoreCase("feed")) {
    // Do something here
  } else if (arg[0].equalsIgnoreCase("hug")) {
    // Do something else here
  }
  return true;
}
```
This often leads to a couple of issues:
- How do you check if the sender has permission to use the subcommands?
- How do you handle tab completion?
- How do you handle aliases?
- Lastly but most importantly, how do you prevent the code from devolving into a steaming pile of mess as more subcommands are added?

CommandWork addresses the following issues by seperating them into multiple concerns, parsing, condition checking, command delegation and the subcommand. As the different concerns are decoupled, long term maintainece is easier overall. Tab Completion, Aliases, condition checking and command delegation is handled by the CommandDispatcher and the parsing by CommandParser. The tab completion name and aliases are handled by specifiying the aliases in the plugin.yml. By default the first alias will be used as its tab completion name. 
<br> <br>
The above code can be refactored into the following:

Plugin.yml:
```yml
commands:
  panda:
    aliases: [panda, pan, p]
    
  "panda feed":
    aliases: [feed, f]
    permission: panda.feed
    
  "panda hug":
    aliases: [hug, h]
    permission: panda.hug
```
    
Subcommands:
```java
public class FeedCommand extends CommandCallable{
  
  public void call(CommandSender sender, String[] label) {
    // Do something here
  }
  
}

public class HugCommand extends CommandCallable{
  
  public void call(CommandSender sender, String[] label) {
    // Do something else here
  }
  
}
```

Registering the CommandCallables with the CommandDispatcher:
```java
CommandDispatcher dispatcher = new CommandDispatcher(); //Dispatcer is instaniated with the default CommandParser
dispatcher.register(new FeedCommand(getCommand("Panda feed")));
dispatcher.register(new HugCommand(getCommand("Panda hug")));
getCommand("Panda").setExecutor(dispatcher);
```

<b> NOTE:
To register a behaviour for the when there is no arguments, in this case, panda, simply register it as the following: </b>
```java
CommandDispatcher dispatcher = new CommandDispatcher(); //Dispatcer is instaniated with the default CommandParser
dispatcher.register(new PandaCommand(getCommand("Panda")));
getCommand("Panda").setExecutor(dispatcher);
```
<br>
#Requirements
- Java 8 and above
- Spigot 1.10 and above
<br>

#Documentation
A copy of the Javodocs can be found [here](https://pante.github.io/CommandWork/).

#Licensing
Copyright (C) 2016 PanteLegacy @ karusmc.com <br>
This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
