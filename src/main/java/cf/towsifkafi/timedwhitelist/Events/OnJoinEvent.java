package cf.towsifkafi.timedwhitelist.Events;

import cf.towsifkafi.timedwhitelist.Timedwhitelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class OnJoinEvent implements Listener {
    public static Timedwhitelist plugin;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        plugin = Timedwhitelist.getInstance();
        String playerName = event.getPlayer().getName();
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("TimedWhitelist").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + playerName + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        //When the player file is created for the first time...
        if (!f.exists()) {
            try {

                playerData.createSection("settings");
                long unixTime = System.currentTimeMillis() / 1000L;
                playerData.set("settings.firstjoin", unixTime);
                playerData.set("settings.lastjoin", unixTime);
                long time = plugin.getConfig().getInt("whitelist-time");
                playerData.set("settings.whitelisttime", time);
                playerData.set("settings.lastdate", unixTime+time);
                playerData.set("settings.addDefaultTime", true);


                playerData.save(f);
            } catch (IOException exception) {

                exception.printStackTrace();
            }
        } else {

            try {

                long unixTime = System.currentTimeMillis() / 1000L;
                playerData.set("settings.lastjoin", unixTime);

                long time = plugin.getConfig().getInt("whitelist-time");
                if(playerData.getBoolean("settings.addDefaultTime")) {
                    playerData.set("settings.lastdate", playerData.getInt("settings.firstjoin")+time);
                } else {
                    int configTime = playerData.getInt("settings.whitelisttime");
                    playerData.set("settings.lastdate", playerData.getInt("settings.firstjoin")+configTime);
                };

                if(unixTime > playerData.getInt("settings.lastdate")) {

                    ConfigurationSection kickConfig = plugin.getConfig().getConfigurationSection("kick");

                    event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getConfigurationSection("kick").getString("kick-message")));

                }

                playerData.save(f);
            } catch (IOException exception) {

                exception.printStackTrace();
            }

        }

    }

}
