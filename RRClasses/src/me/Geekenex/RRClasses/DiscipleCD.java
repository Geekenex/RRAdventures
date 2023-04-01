package me.Geekenex.RRClasses;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DiscipleCD implements Listener {

	public static HashMap<UUID, Double> dCD;
	
	public static void setupCooldown() {
		dCD = new HashMap<>();
	}
	
	//setCooldown
	public static void setCooldown(Player player, int seconds) {
		double delay = System.currentTimeMillis() + (seconds * 1000);
		dCD.put(player.getUniqueId(), delay);
		
	}
	
	//getCooldown
	public static int getCooldown(Player player) {
		return Math.toIntExact(Math.round(dCD.get(player.getUniqueId()) - System.currentTimeMillis())/1000);
	}
	
	
	//checkCooldown
	public static boolean checkCooldown(Player player) {
		if(!dCD.containsKey(player.getUniqueId()) || dCD.get(player.getUniqueId()) <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	
	
}
