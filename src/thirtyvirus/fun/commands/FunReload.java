/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        FunHelp.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import thirtyvirus.fun.FunCommands;

public class FunReload implements CommandExecutor {

    private FunCommands main = null;
    public FunReload(FunCommands main) {
        this.main = main;
    }

    /*
    Function:    onCommand
    Description: process the /funreload command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (!(player.hasPermission("FunCommands.admin"))) {
                sender.sendMessage(FunCommands.prefix + ChatColor.RED + "You don't have proper permissions!");
                return false;
            }
        }

        main.reloadConfig();
        main.config = main.getConfig();
        FunCommands.prefix = main.config.getString("plugin-prefix");
        FunCommands.prefix = ChatColor.translateAlternateColorCodes('&', FunCommands.prefix);
        sender.sendMessage(FunCommands.prefix + "FunCommands config reloaded");

        return true;
    }
}