package team.pepsi.bungeeplugin.command.message;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.pepsi.bungeeplugin.Main;

import java.util.regex.Pattern;

public class MsgCommand extends Command {
    public MsgCommand() {
        super("msg", null, "w", "whisper", "message", "tell");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (strings.length < 2) {
                commandSender.sendMessage("\u00A7cUsage: \u00A7o/msg <player> <message>");
                return;
            }
            ProxiedPlayer to = ProxyServer.getInstance().getPlayer(strings[0]);
            if (to == null) {
                commandSender.sendMessage("\u00A7cCould not find player: \u00A7o" + strings[0] + "\u00A7r\u00A7c!");
                return;
            }
            String message = "";
            for (int i = 1; i < strings.length; i++)    {
                message += strings[i] + " ";
            }

            to.sendMessage("\u00A77[\u00A7d" + player.getName() + " \u00A77-> \u00A7dme\u00A77] \u00A7d" + message);
            player.sendMessage("\u00A77[\u00A7dme \u00A77-> \u00A7d" + to.getName() + "\u00A77] \u00A7d" + message);
            Main.addonData.get(to.getName()).lastDM = player.getName();
            Main.addonData.get(player.getName()).lastDM = to.getName();
        } else {
            commandSender.sendMessage(new ComponentBuilder("Not a player!").bold(true).color(ChatColor.RED).create());
        }
    }
}
