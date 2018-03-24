package team.pepsi.bungeeplugin.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.pepsi.bungeeplugin.Main;
import team.pepsi.bungeeplugin.Misc;

public class BlistCommand extends Command {
    public BlistCommand() {
        super("blist");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender.getName().equals("DaPorkchop_") || !(commandSender instanceof ProxiedPlayer))  {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                commandSender.sendMessage(player.getDisplayName() + player.getName() + "\u00A7r - \u00A73" + player.getServer().getInfo().getName() + "\u00A7r Forge: " + (player.isForgeUser() ? "\u00A7atrue" : "\u00A7cfalse"));
            }
        } else {
            commandSender.sendMessage("\u00A7cREEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        }
    }
}
