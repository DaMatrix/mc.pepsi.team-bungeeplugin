package team.pepsi.bungeeplugin.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.pepsi.bungeeplugin.Main;

public class HallwayCommand extends Command {
    public HallwayCommand() {
        super("hallway");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            if (false && !((ProxiedPlayer) commandSender).isForgeUser())    {
                commandSender.sendMessage("\u00A7cVanilla users cannot use hallway! (Forge-only)");
                commandSender.sendMessage("\u00A7cMake sure you're using the \u00A74\u00A7lhallway modpack\u00A7r\u00A7c, which can be downloaded at \u00A79\u00A7o\u00A7nhttp://pepsi.team/hallway");
                return;
            }

            if (((ProxiedPlayer) commandSender).getServer().getInfo().getName().equals("hallway"))    {
                commandSender.sendMessage("\u00A7cAlready connected to hallway!");
                return;
            }

            if (!Main.tryConnectHallway((ProxiedPlayer) commandSender))   {
                commandSender.sendMessage("\u00A79Transferring to hallway...");
                ((ProxiedPlayer) commandSender).connect(ProxyServer.getInstance().getServerInfo("hallway"),
                        (success, e) -> {
                            if (success) {
                                commandSender.sendMessage("\u00A7aSuccessfully transferred to hallway!");
                            } else {
                                commandSender.sendMessage("\u00A7cError whilst transferring to hallway!");
                            }
                        });
            }
        } else {
            commandSender.sendMessage(new ComponentBuilder("Not a player!").bold(true).color(ChatColor.RED).create());
        }
    }
}
