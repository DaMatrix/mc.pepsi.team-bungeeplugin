package team.pepsi.bungeeplugin;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Map;

public class PepsiListener implements Listener {
    public static final BaseComponent[]
            HEADER = TextComponent.fromLegacyText("    \u00A7fWelcome to the official \u00A79Team \u00A7cPepsi \u00A7fMinecraft server!    \n\u00A7aMade by \u00A79\u00A7lDaPorkchop_\u00A7r\n\n\u00A7fCheck out the website! \u00A79\u00A7n\u00A7ohttp://pepsi.team\u00A7r\nJoin the \u00A79\u00A7lDiscord \u00A7rserver: \u00A79\u00A7n\u00A7ohttps://discord.gg/h2MNn35"),
            FOOTER = TextComponent.fromLegacyText("");

    @EventHandler
    public void onChat(ChatEvent event) {
        if (!event.isCommand()) {
            event.setCancelled(true);
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            String name = player.getName();
            PlayerAddonData data = Main.addonData.get(name);
            long delay = data.lastMessageSent;
            if (delay + 2000 > System.currentTimeMillis()) {
                if (data.isWarned) {
                    player.disconnect("SPAM");
                    return;
                } else {
                    data.isWarned = true;
                    player.sendMessage("Don't spam!");
                }
            } else {
                data.isWarned = false;
            }
            data.lastMessageSent = System.currentTimeMillis();
            String message = "<\u00A73" + player.getServer().getInfo().getName() + " \u00A78- \u00A7r" + player.getDisplayName() + name + "\u00A7r> ";
            if (event.getMessage().startsWith(">")) {
                message += "\u00A7a";
            }
            message += event.getMessage();
            ProxyServer.getInstance().broadcast(message);
        }
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        Main.addonData.put(event.getPlayer().getName(), new PlayerAddonData());
        ProxiedPlayer player = event.getPlayer();
        player.setTabHeader(HEADER, FOOTER);
        String uuid = player.getUniqueId().toString();
        boolean flag = true;
        NAMECOLORS: if (true) {
            if (Misc.PEPSI_BOY.equals(uuid))    {
                player.setDisplayName("\u00A74");
                flag = false;
                break NAMECOLORS;
            }
            for (String s : Misc.OWNER) {
                if (s.equals(uuid)) {
                    player.setDisplayName("\u00A7a");
                    flag = false;
                    break NAMECOLORS;
                }
            }
            for (String s : Misc.YOUTUBE) {
                if (s.equals(uuid)) {
                    player.setDisplayName("\u00A7c");
                    flag = false;
                    break NAMECOLORS;
                }
            }
            for (String s : Misc.PEPSI_MEMBERS) {
                if (s.equals(uuid)) {
                    player.setDisplayName("\u00A79");
                    flag = false;
                    break NAMECOLORS;
                }
            }
        }
        if (flag)   {
            player.setDisplayName("");
        }
        player.sendMessage("\u00A7cWelcome to the \u00A79Team Pepsi \u00A7cMinecraft server!");
        player.sendMessage("\u00A7aRules:");
        player.sendMessage("\u00A7a  There are NONE. Anything that you CAN do, you are allowed to.");
        player.sendMessage("");
        player.sendMessage("\u00A79Website: \u00A7o\u00A7nhttp://pepsi.team");
        player.sendMessage("\u00A79Discord: \u00A7o\u00A7nhttps://discord.gg/h2MNn35");
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent event) {
        String name = event.getPlayer().getName();
        Main.addonData.remove(name);
        Main.hallwayQueue.remove(event.getPlayer());
        for (Map.Entry<String, PlayerAddonData> entry : Main.addonData.entrySet()) {
            if (name.equals(entry.getValue().lastDM)) {
                entry.getValue().lastDM = null;
            }
        }
    }

    @EventHandler
    public void onSwitch(ServerConnectedEvent event) {
        if (event.getServer().getInfo().getName().equals("hallway")) {
            Main.hallwayQueue.remove(event.getPlayer());
        }
        event.getPlayer().setTabHeader(HEADER, TextComponent.fromLegacyText("\u00A7cCurrently connected to\u00A7f: \u00A79\u00A7o" + event.getServer().getInfo().getName()));
    }

    @EventHandler
    public void onConnect(ServerConnectEvent event) {
        if (event.getTarget().getName().equals("hallway")) {
            ProxiedPlayer player = event.getPlayer();
            if (Main.tryConnectHallway(player))  {
                event.setCancelled(true);
            }
        } else if (event.getTarget().getName().equals("bepis")) {
            ProxiedPlayer player = event.getPlayer();
            if (!Misc.BEPIS_WHITELIST.contains(player.getUniqueId().toString()))   {
                event.setCancelled(true);
                player.sendMessage("\u00A7cYou are not whitelisted on \u00A7lbepis\u00A7r\u00A7c!");
            } else {
                player.sendMessage("\u00A7aConnecting to \u00A79bepis\u00A7a...");
                player.sendMessage("\u00A7aThis may take a while.");
            }
        }
    }

    @EventHandler
    public void onKick(ServerKickEvent event) {
        if (event.getKickedFrom().getName().equals("lobby"))    {
            event.setKickReason("Lobby has gone offline!");
        } else {
            event.setCancelled(true);
            event.setCancelServer(ProxyServer.getInstance().getServerInfo("lobby"));
            event.getPlayer().sendMessage("\u00A7cYou have been disconnected from: \u00A79" + event.getKickedFrom().getName());
            event.getPlayer().sendMessage("\u00A7cReason:");
            event.getPlayer().sendMessage(event.getKickReasonComponent());
        }
    }
}
