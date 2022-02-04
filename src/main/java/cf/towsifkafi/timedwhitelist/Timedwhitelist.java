package cf.towsifkafi.timedwhitelist;

import cf.towsifkafi.timedwhitelist.Commands.Reload;
import cf.towsifkafi.timedwhitelist.Commands.ResetPlayer;
import cf.towsifkafi.timedwhitelist.Events.OnJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timedwhitelist extends JavaPlugin {

    private static Timedwhitelist instance;
    public Utils util;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.util = new Utils(this);
        this.util.log("&c&lTimedwhitelist has been enabled!");

        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new OnJoinEvent(), this);
        getCommand("twreload").setExecutor(new Reload());
        getCommand("twreset").setExecutor(new ResetPlayer());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.util.log("&c&lTimedwhitelist has been disabled!");
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    public static String getPrefix() {
        return getInstance().getConfig().getString("prefix");
    }


    public static Timedwhitelist getInstance() {
        return instance;
    }

    public static void setInstance(Timedwhitelist instance) {
        Timedwhitelist.instance = instance;
    };

}
