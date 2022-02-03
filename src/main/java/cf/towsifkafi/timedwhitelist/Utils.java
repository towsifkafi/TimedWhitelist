package cf.towsifkafi.timedwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {

    Timedwhitelist plugin;
    String prefix;

    public Utils(Timedwhitelist plugin) {
        this.plugin = plugin;
        this.prefix = plugin.getConfig().getString("prefix");
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + message));
    }



}

    //Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',MPREFIX + " &aPixelEdge Essentials Started!"));
