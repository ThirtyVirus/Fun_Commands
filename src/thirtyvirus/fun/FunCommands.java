/*
Project:     FunCommands for CraftBukkit / Spigot
Author:      Brandon (ThirtyVirus) Calabrese
File:        MainClass.java
Description: Main Class File in Project
 */

package thirtyvirus.fun;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import thirtyvirus.fun.commands.Anvil;
import thirtyvirus.fun.commands.Fb;
import thirtyvirus.fun.commands.FunHelp;
import thirtyvirus.fun.commands.FunReload;
import thirtyvirus.fun.commands.Milk;
import thirtyvirus.fun.commands.Rename;
import thirtyvirus.fun.commands.Smelt;
import thirtyvirus.fun.helpers.Utilities;

public class FunCommands extends JavaPlugin  {

    // config and IO
    private PluginDescriptionFile descFile = getDescription();
    private PluginManager pm = getServer().getPluginManager();
    public FileConfiguration config;
    private Logger logger = getLogger();

    // permissions
    private Permission anvil = new Permission("funCommands.anvil");
    private Permission milk = new Permission("funCommands.milk");
    private Permission milkOther = new Permission("funCommands.milk.other");
    private Permission rename = new Permission("funCommands.rename");
    private Permission smelt = new Permission("funCommands.smelt");
    private Permission fb = new Permission("funCommands.fb");
    private Permission admin = new Permission("funCommands.admin");

    // settings
    public static String prefix = "&6[FUN] &7";

    /*
    Function:    onEnable
    Description: execute actions to be done on enable
    Args:        N/A
    Returns:     N/A
     */
    public void onEnable() {

        // load config.yml (generate one if not there)
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Utilities.loadResource(getPlugin(FunCommands.class), "config.yml");
        }

        config = this.getConfig();
        prefix = config.getString("plugin-prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        registerCommands();
        registerPermissions();

        // posts confirmation in chat
        logger.info(descFile.getName() + " V: " + descFile.getVersion() + " has been enabled");
    }

    /*
    Function:    onDisable
    Description: execute actions to be done on disable
    Args:        N/A
    Returns:     N/A
     */
    public void onDisable() {
        // posts exit message in chat
        logger.info(descFile.getName() + " V: " + descFile.getVersion() + " has been disabled");
    }

    /*
    Function:    registerCommands
    Description: register all plugin commands
    Args:        N/A
    Returns:     N/A
     */
    private void registerCommands() {
        getCommand("funhelp").setExecutor(new FunHelp());
        getCommand("funreload").setExecutor(new FunReload(this));
        getCommand("anvil").setExecutor(new Anvil());;
        getCommand("milk").setExecutor(new Milk());;
        getCommand("rename").setExecutor(new Rename());
        getCommand("smelt").setExecutor(new Smelt());
        getCommand("fb").setExecutor(new Fb());
    }

    /*
    Function:    registerPermissions
    Description: register all plugin permissions
    Args:        N/A
    Returns:     N/A
     */
    private void registerPermissions() {
        pm.addPermission(anvil);
        pm.addPermission(milk);
        pm.addPermission(milkOther);
        pm.addPermission(rename);
        pm.addPermission(smelt);
        pm.addPermission(fb);
        pm.addPermission(admin);
    }

}
