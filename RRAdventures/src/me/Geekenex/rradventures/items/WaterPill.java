package me.Geekenex.rradventures.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Geekenex.rradventures.Main;

public class WaterPill implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public WaterPill(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Water Pill
	@EventHandler
	    public void waterPill(PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		if(e.getItem() == null) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(item.getType().equals(Material.PRISMARINE_CRYSTALS)) {
				int amount = e.getItem().getAmount();
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1200, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1200, 1));
			if(amount > 1) {
				p.getInventory().getItemInMainHand().setAmount(amount-1);
			} else {
				p.getInventory().getItemInMainHand().setAmount(0);
			}
			
			}
		}
	}
	
	
	
}
