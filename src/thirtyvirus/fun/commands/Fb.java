/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Fb.java
Description: /fb command (easy fireball)
 */

package thirtyvirus.fun.commands;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import thirtyvirus.fun.FunCommands;

public class Fb implements CommandExecutor {

    /*
    Function:    onCommand
    Description: execute /fb command
                 /fb SPEED POWER (default: 2.0, 5.0)
    Args:        sender  - the command sender
                 command - the command
                 label   - N/A
                 args    - command arguments
    Returns:     N/A
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!(player.hasPermission("funCommands.fb"))) {
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not enough permissions!");
            return false;
        }

        Double vel = 2.0; Float yel = 5F;
        if (args.length > 0 && Integer.parseInt(args[0]) >= 0) vel = Double.parseDouble(args[0]);
        if (args.length > 1 && Integer.parseInt(args[1]) >= 0) yel = Float.parseFloat(args[1]);

        Fireball thrown = player.launchProjectile(Fireball.class);
        Vector v = player.getEyeLocation().getDirection().multiply(vel);
        thrown.setVelocity(v);
        thrown.setYield(yel);

        thrown.setCustomName("FunFireBall");

        return true;
    }

}	