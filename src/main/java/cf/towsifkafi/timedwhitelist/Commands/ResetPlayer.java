package cf.towsifkafi.timedwhitelist.Commands;

import cf.towsifkafi.timedwhitelist.Timedwhitelist;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class ResetPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Timedwhitelist plugin = Timedwhitelist.getInstance();
        CommandSender p =  sender;
        if(args.length == 0) {
            p.sendMessage(plugin.getPrefix() + "§c/twreset <player>");
        } else {
            if (p.hasPermission("timedwhitelist.reset")) {

                String playerName = args[0];
                File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("TimedWhitelist").getDataFolder(), File.separator + "PlayerDatabase");
                File f = new File(userdata, File.separator + playerName + ".yml");

                if(f.exists()) {
                    f.delete();
                    p.sendMessage("§aPlayer " + playerName + "'s time limit was reset!");
                } else {
                    p.sendMessage("§cPlayer " + playerName + " does not exist!");
                }

            } else {
                p.sendMessage("§cYou don't have permission to do that!");
            }
        }

        return true;
    }
}
