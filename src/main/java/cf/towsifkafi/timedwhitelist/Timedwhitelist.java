package cf.towsifkafi.timedwhitelist;

import org.bukkit.Bukkit;
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


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.util.log("&c&lTimedwhitelist has been disabled!");
    }


    public static Timedwhitelist getInstance() {
        return instance;
    }

    public static void setInstance(Timedwhitelist instance) {
        Timedwhitelist.instance = instance;
    };

}
