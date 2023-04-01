package me.Geekenex.rradventures.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.items.NVTransporter;
import me.Geekenex.rradventures.utils.Utils;

public class NoobVilleTransporter implements Listener, CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	public NoobVilleTransporter(Main plugin) {
		this.plugin = plugin;
	}

private NVTransporter nvtrans = new NVTransporter(plugin);
	
	//Gives the noobville transporter
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
	    
		if(label.equalsIgnoreCase("nvtrans")) {
			if(sender instanceof Player) {
				//Giving the transporter!
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7Here is the &rNoobville Transporter&7!"));
				nvtrans.giveNVTrans(player);
				return true;
			}
		}
		return false;
	}
	
}
