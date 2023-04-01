package me.Geekenex.rradventures.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Geekenex.rradventures.Main;

public class CrazyPowder implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public CrazyPowder(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Speedy Sugar
	@EventHandler
	    public void onInteract (PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		if(e.getItem() == null) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			assert item != null;
			if(item.getType().equals(Material.SUGAR)) {
				if (e.getHand() == EquipmentSlot.HAND) {
				int amount = e.getItem().getAmount();
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2000, 1));
			if(amount > 1) {
				p.getInventory().getItemInMainHand().setAmount(amount-1);
			} else {
				p.getInventory().getItemInMainHand().setAmount(0);
			}
			
			}
		}
		}
	}

	
}
