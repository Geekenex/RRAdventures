package me.Geekenex.rradventures.events.messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.Utils;

public class Chat implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public Chat(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void chatFormat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		e.setFormat(Utils.chat(player.getDisplayName() + "&7: " + e.getMessage()));
	}
	
}
