/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        Utilities.java
Description: collection of utility functions for use by the plugin
 */

package thirtyvirus.fun.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteStreams;

import thirtyvirus.fun.FunCommands;

public class Utilities {

    /*
    Function:    loadResource
    Description: loads file from JAR with comments
    Args:        plugin   - the plugin
                 resource - the resource
    Returns:     renamed ItemStack
     */
    public static File loadResource(Plugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResource(resource);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    /*
    Function:    nameItem
    Description: renames given item
    Args:        item - the item to rename
                 name - the name
    Returns:     renamed ItemStack
     */
    public static ItemStack nameItem(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        //lots of options for changing item meta data
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    /*
    Function:    nameItem
    Description: creates item that is renamed given material and name
    Args:        item - the material
                 name - the name
    Returns:     named ItemStack
     */
    public static ItemStack nameItem(Material item, String name){
        return nameItem(new ItemStack(item), name);
    }

    /*
    Function:    getSmelted
    Description: return the result of a given item being smelted
    Args:        toSmelt - the item
    Returns:     smelted item (or original item if not smeltable)
     */
    public static ItemStack getSmelted(ItemStack toSmelt) {
        ItemStack result = toSmelt;

        Iterator<Recipe> iter = Bukkit.recipeIterator();
        while (iter.hasNext()) {
            Recipe recipe = iter.next();
            if (!(recipe instanceof FurnaceRecipe)) continue;
            if (((FurnaceRecipe) recipe).getInput().getType() != toSmelt.getType()) continue;
            result = recipe.getResult();
            break;
        }

        return result;
    }

    /*
    Function:    getOnlinePlayer
    Description: return the player of a given name if they are online
    Args:        name - the name of the player
    Returns:     the found player, or null if not found
     */
    public static Player getOnlinePlayer(String name) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    /*
    Function:    openAnvil
    Description: allow player to open an anvil inventory
    Args:        player - the player
    Returns:     none
     */
    public static void openAnvil(Player player) {
        //TODO
        player.sendMessage(FunCommands.prefix + ChatColor.RED + "Not implimented yet, coming soon!");
    }

}
