/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        FunHelp.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import thirtyvirus.fun.FunCommands;

public class FunHelp implements CommandExecutor {

    /*
    Function:    onCommand
    Description: process the /funhelp command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(FunCommands.prefix + "/funhelp - List all commands");
        sender.sendMessage(FunCommands.prefix + "/anvil   - opens anvil inventory");
        sender.sendMessage(FunCommands.prefix + "/milk    - remove potion effects from player");
        sender.sendMessage(FunCommands.prefix + "/rename  - renames item in hand w/ color!");
        sender.sendMessage(FunCommands.prefix + "/smelt   - smelt item in your hand");
        sender.sendMessage(FunCommands.prefix + "/fb      - easy fireball command");

        return true;
    }
}