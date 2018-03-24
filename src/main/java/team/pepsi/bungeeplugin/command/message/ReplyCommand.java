package team.pepsi.bungeeplugin.command.message;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.pepsi.bungeeplugin.Main;
import team.pepsi.bungeeplugin.PlayerAddonData;

public class ReplyCommand extends Command {
    public ReplyCommand() {
        super("reply", null, "r");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (strings.length < 1) {
                commandSender.sendMessage("\u00A7cUsage: \u00A7o/r <message>");
                return;
            }
            PlayerAddonData senderData = Main.addonData.get(player.getName());
            if (senderData.lastDM == null)    {
                commandSender.sendMessage("\u00A7cNo DM active!");
                return;
            }
            ProxiedPlayer to = ProxyServer.getInstance().getPlayer(senderData.lastDM);
            String message = "";
            for (int i = 0; i < strings.length; i++)    {
                message += strings[i] + " ";
            }

            to.sendMessage("\u00A77[\u00A7d" + player.getName() + " \u00A77-> \u00A7dme\u00A77] \u00A7d" + message);
            player.sendMessage("\u00A77[\u00A7dme \u00A77-> \u00A7d" + to.getName() + "\u00A77] \u00A7d" + message);
            Main.addonData.get(to.getName()).lastDM = player.getName();
            senderData.lastDM = to.getName();
        } else {
            commandSender.sendMessage(new ComponentBuilder("Not a player!").bold(true).color(ChatColor.RED).create());
        }
    }
}
