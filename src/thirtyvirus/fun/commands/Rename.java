/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Rename.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import thirtyvirus.fun.FunCommands;
import thirtyvirus.fun.helpers.Utilities;

public class Rename implements CommandExecutor {

    /*
    Function:    onCommand
    Description: process the /rename command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!(player.hasPermission("funCommands.rename"))) {
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not enough permissions!");
            return false;
        }

        // verify that the player has an item in their hand
        if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) return false;

        // test if item has color in name and doesnt have ~ symbol,
        // usually means plugin inventory item that shouldn't be renamed

        // prevent breaking imprtant Plugin Items
        String oldName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

        if (oldName.length() >= 3) {
            if (ChatColor.stripColor(oldName) != oldName && oldName.charAt(2) != '~') {
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                player.sendMessage(FunCommands.prefix + ChatColor.RED + "This item already has special formatting! Are you trying to break a plugin item? NO");
                return false;
            }
        }

        String newName = ChatColor.GRAY + "~";
        for(String s : args) {
            newName = newName + s + " ";
        }
        newName = newName.substring(0, newName.length() - 1);

        // account for color codes
        newName = ChatColor.translateAlternateColorCodes('&', newName);

        player.getInventory().setItemInMainHand(Utilities.nameItem(player.getInventory().getItemInMainHand(), newName));

        return true;
    }
}