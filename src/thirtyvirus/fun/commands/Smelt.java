/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Smelt.java
Description: Rename held item
 */

package thirtyvirus.fun.commands;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import thirtyvirus.fun.FunCommands;
import thirtyvirus.fun.helpers.Utilities;

public class Smelt implements CommandExecutor {

    /*
    Function:    onCommand
    Description: process the /smelt command
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!(player.hasPermission("funCommands.smelt"))) {
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not enough permissions!");
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        int amount = item.getAmount();
        ItemStack item2 = Utilities.getSmelted(item.clone());
        if (!(item.isSimilar(item2))) {
            item2.setAmount(amount);
            player.giveExp(amount);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            player.getInventory().setItemInMainHand(item2);
        }
        else {
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            player.sendMessage(FunCommands.prefix + ChatColor.RED + "Item is not smeltable!");
        }

        return true;
    }


}	