package me.Geekenex.rradventures.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.items.AllCurrencies;
import me.Geekenex.rradventures.utils.Utils;

public class Currencies implements Listener, CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	public Currencies(Main plugin) {
		this.plugin = plugin;
	}
	
	private AllCurrencies currencies = new AllCurrencies(plugin);
	
	//Gives all the currencies
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
	    
		if(label.equalsIgnoreCase("currencies")) {
			if(sender instanceof Player) {
				//Giving the currencies!
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&a$$$"));
				currencies.giveCopper(player);
				currencies.giveSilver(player);
				currencies.giveGold(player);
				currencies.giveMoney(player);
				return true;
			}
		}
		return false;
	}
	
	
	
}
