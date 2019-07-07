/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Anvil.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirtyvirus.fun.FunCommands;
import thirtyvirus.fun.helpers.Utilities;


public class Anvil implements CommandExecutor {

    /*
    Function:    onCommand
    Description: process the /anvil command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!(player.hasPermission("funCommands.anvil"))) {
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not enough permissions!");
            return false;
        }

        player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1);
        Utilities.openAnvil(player);

        return true;
    }
}