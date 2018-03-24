package team.pepsi.bungeeplugin;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import team.pepsi.bungeeplugin.command.*;
import team.pepsi.bungeeplugin.command.message.MsgCommand;
import team.pepsi.bungeeplugin.command.message.ReplyCommand;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static team.pepsi.bungeeplugin.Misc.maxCountHallway;

public class Main extends Plugin {
    public static final Map<String, PlayerAddonData> addonData = new Hashtable<>();
    public static final List<ProxiedPlayer> hallwayQueue = new ArrayList<>();

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new SkyblockCommand());
        getProxy().getPluginManager().registerCommand(this, new BedwarsCommand());
        getProxy().getPluginManager().registerCommand(this, new HallwayCommand());
        getProxy().getPluginManager().registerCommand(this, new BepisCommand());

        getProxy().getPluginManager().registerCommand(this, new MsgCommand());
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand());

        getProxy().getPluginManager().registerCommand(this, new HallwayQueueSizeCommand());

        getProxy().getPluginManager().registerCommand(this, new BlistCommand());

        getProxy().getPluginManager().registerListener(this, new PepsiListener());

        getProxy().getScheduler().schedule(this,
                () -> {
                    //check if queue is empty
                    if (!hallwayQueue.isEmpty())    {
                        List<ProxiedPlayer> list = new ArrayList<>();
                        list.addAll(hallwayQueue);
                        ServerInfo info = getProxy().getServerInfo("hallway");
                        //check if the server has free slots
                        int i = 1;
                        if (info.getPlayers().size()
                                < Misc.maxCountHallway)  {
                            list.get(0).sendMessage("\u00A79\u00A7lConnecting to hallway...");
                            list.get(0).connect(info);
                            list.remove(0);
                        } else {
                            i = 0;
                        }
                        for (; i < list.size(); i++)   {
                            list.get(i).sendMessage("\u00A79\u00A7lPosition in queue: " + (i + 1));
                        }
                    }
                },
                5000, 5000, TimeUnit.MILLISECONDS);
    }

    public static boolean tryConnectHallway(ProxiedPlayer player)    {
        int onlineCount = ProxyServer.getInstance().getServerInfo("hallway").getPlayers().size();
        //check if server has free slots
        //this will return false if the server is overfilled too
        //in the case that pepsi users are online
        //on an otherwise full server
        if (onlineCount >= maxCountHallway) {
            //check if in queue
            if (Main.hallwayQueue.contains(player)) {
                player.sendMessage("\u00A7cAlready in queue for: hallway");
                return true;
            }

            //pepsi users should bypass the queue
            {
                String uuid = player.getUniqueId().toString();
                for (String s : Misc.PEPSI_MEMBERS) {
                    if (s.equals(uuid)) {
                        player.sendMessage("\u00A79Skipping the queue...");
                        return false;
                    }
                }
            }

            //mark as in queue
            Main.hallwayQueue.add(player);
            player.sendMessage("\u00A79\u00A7lYou have been added to the queue.");
            player.sendMessage("\u00A79\u00A7lPosition in queue: " + (Main.hallwayQueue.size() + 1));
            return true;
        } else {
            return false;
        }
    }
}
