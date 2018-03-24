package team.pepsi.bungeeplugin.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BedwarsCommand extends Command {
    public BedwarsCommand() {
        super("bedwars");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            if (((ProxiedPlayer) commandSender).getServer().getInfo().getName().equals("bedwars"))    {
                commandSender.sendMessage("\u00A7cAlready connected to bedwars!");
                return;
            }
            commandSender.sendMessage("\u00A79Transferring to hub...");
            ((ProxiedPlayer) commandSender).connect(ProxyServer.getInstance().getServerInfo("bedwars"),
                    (success, e) -> {
                        if (success)    {
                            commandSender.sendMessage("\u00A7aSuccessfully transferred to bedwars!");
                        } else {
                            commandSender.sendMessage("\u00A7cError whilst transferring to bedwars!");
                        }
                    });
        } else {
            commandSender.sendMessage(new ComponentBuilder("Not a player!").bold(true).color(ChatColor.RED).create());
        }
    }
}
