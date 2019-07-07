/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Milk.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import thirtyvirus.fun.FunCommands;
import thirtyvirus.fun.helpers.Utilities;

public class Milk implements CommandExecutor {

    /*
    Function:    onCommand
    Description: process the /milk command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // use as player
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("funCommands.milk")) {

                // use on self
                if(args.length == 0) {
                    Player player2 = (Player) sender;
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect clear "+ player2.getName());
                    player2.sendMessage(FunCommands.prefix + "Potion effects cleared!");
                }
                // use on other player
                else if (player.hasPermission("funCommands.milk.other")) {
                    Player player2 = Utilities.getOnlinePlayer(args[0]);

                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect clear "+ player2.getName());
                    player2.sendMessage(FunCommands.prefix + "Potion effects cleared!");
                }
            }
            else {
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not enough permissions!");
            }
        }

        // use as console
        else {
            Player player2 = Utilities.getOnlinePlayer(args[0]);

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect clear "+ player2.getName());
            player2.sendMessage(FunCommands.prefix + "Potion effects cleared!");
        }

        return true;
    }
}