package me.Geekenex.rradventures.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.items.NoobVilleKey;
import me.Geekenex.rradventures.utils.Utils;

public class IronKey implements CommandExecutor, Listener {

	private Main plugin;
	public IronKey(Main plugin) {
		this.plugin = plugin;
	}

private NoobVilleKey nvkey = new NoobVilleKey(plugin);
	
	//Gives the iron key
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
	    
		if(label.equalsIgnoreCase("nvkey")) {
			if(sender instanceof Player) {
				//Giving the key!
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7Here is the &rNoobville Key&7!"));
				nvkey.giveNVkey(player);
				return true;
			}
		}
		return false;
	}
	
}
