package team.pepsi.bungeeplugin.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.pepsi.bungeeplugin.Main;
import team.pepsi.bungeeplugin.Misc;

public class HallwayQueueSizeCommand extends Command {
    public HallwayQueueSizeCommand() {
        super("hallwayqueuesize");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender.getName().equals("DaPorkchop_") || !(commandSender instanceof ProxiedPlayer))  {
            if (strings.length == 1)    {
                Misc.maxCountHallway = Integer.parseInt(strings[0]);
                commandSender.sendMessage(String.valueOf(Misc.maxCountHallway));
            } else if (strings.length == 0) {
                commandSender.sendMessage("Queue size: " + Main.hallwayQueue.size());
                commandSender.sendMessage("Ingame: " + ProxyServer.getInstance().getServerInfo("hallway").getPlayers().size());
            }
        }
    }
}
