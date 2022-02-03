package cf.towsifkafi.timedwhitelist.Commands;


import cf.towsifkafi.timedwhitelist.Timedwhitelist;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Timedwhitelist plugin = Timedwhitelist.getInstance();
        CommandSender p =  sender;
        if (p.hasPermission("timedwhitelist.reload")) {
            plugin.reloadConfig();
            p.sendMessage("§aTimedWhitelist reloaded!");
        } else {
            p.sendMessage("§cYou don't have permission to do that!");
        }
        return true;
    }
}
