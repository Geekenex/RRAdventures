package me.Geekenex.rradventures.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.CooldownManager;

public class SpiderWand implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public SpiderWand(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Spider Wand
	@EventHandler
	  public void spiderWand(PlayerInteractEvent e){
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		if(e.getItem() != null) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(item.getType().equals(Material.NETHER_BRICK)) {
			if(CooldownManager.checkCooldown(player)) {
			CooldownManager.setCooldown(player,30);
				player.sendMessage(ChatColor.GRAY + "Woosh");
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 1);
				final ItemStack potion = new ItemStack(Material.LINGERING_POTION);
				PotionMeta meta = (PotionMeta) potion.getItemMeta();
				meta.setColor(Color.WHITE);
				meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
				meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0), true);
				potion.setItemMeta(meta);
				Vector vector = new Vector();
				vector.setX(player.getLocation().getDirection().getX());
				vector.setY(player.getLocation().getDirection().getY() + 0.5);
				vector.setZ(player.getLocation().getDirection().getZ());
				vector.multiply(0.65);
				ThrownPotion thrownPotion = (ThrownPotion) player.getWorld().spawnEntity(player.getLocation(), EntityType.SPLASH_POTION);
				thrownPotion.setVelocity(vector);
				thrownPotion.setItem(potion);
				thrownPotion.setBounce(true);
			
				} else {
					player.sendMessage(ChatColor.GRAY + "The Spider Staff is recharching for another " + ChatColor.WHITE + (CooldownManager.getCooldown(player)) + ChatColor.GRAY + " seconds.");
					player.playSound(e.getPlayer().getLocation(), Sound.ENTITY_SPIDER_STEP, 1, 1);
				}
				}
		}
			}	
	      	} 

}
