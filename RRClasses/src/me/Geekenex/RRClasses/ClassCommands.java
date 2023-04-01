package me.Geekenex.RRClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ClassCommands implements CommandExecutor {


	private Main plugin;
	public ClassCommands(Main plugin) {
		this.plugin = plugin;
	}
	
	
	//Sets the player's class
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		UUID puid = player.getUniqueId();
	    int effectlvl = Main.classlevel.get(puid);
		
		if(label.equalsIgnoreCase("alchemist")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You are now an &5Alchemist&7!"));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, effectlvl, false, false));
				Main.classtype.remove(puid);
				Main.classtype.put(puid, "alchemist");
				return true;
			}
		}
		if(label.equalsIgnoreCase("tank")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You are now a &8Titan&7!"));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100000, effectlvl, false, false));
				if(effectlvl < 4) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 0, false, false));
				}
				if(effectlvl == 4) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 1, false, false));
				}
				Main.classtype.remove(puid);
				Main.classtype.put(puid, "tank");
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("rogue")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You are now a &aRogue&7!"));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, Math.round(effectlvl / 2), false, false));
				if(effectlvl > 0) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 0, false, false));
				}
				if(effectlvl > 2) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 1, false, false));
					}
				Main.classtype.remove(puid);
				Main.classtype.put(puid, "rogue");
				ItemStack rf = new ItemStack(Material.FEATHER);
			    ItemMeta rfmeta = rf.getItemMeta();
			    String rflore = ChatColor.DARK_GREEN + "Right-Click to dash forwards.";
			    List<String> loreList = new ArrayList<String>();
			    loreList.add(rflore);
			    rfmeta.setLore(loreList);
			    rfmeta.setDisplayName(ChatColor.GREEN + "Rogue Dash");
			    rf.setItemMeta(rfmeta);
				if(player.getInventory().contains(rf)) return true;
				player.getInventory().setItem(8, rf);
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("brawler")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You are now a &4Brawler&7!"));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, Math.round(effectlvl / 2), false, false));
				if(effectlvl > 0) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100000, 0, false, false));
					}
					if(effectlvl > 2) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 0, false, false));
						}
				Main.classtype.remove(puid);
				Main.classtype.put(puid, "brawler");
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("disciple")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You are now a &rDisciple&7!"));
				Main.classtype.remove(puid);
				Main.classtype.put(puid, "disciple");
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("clearclass")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You have cleared your class!"));
				Main.classtype.remove(puid);
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("clearlevels")) {
			if(sender instanceof Player) {
				for(PotionEffect effect:player.getActivePotionEffects()){
					player.removePotionEffect(effect.getType());
				}
				player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7You have cleared your class levels!"));
				Main.classlevel.replace(puid, 0);
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("levelget")) {
			if(sender instanceof Player) {
				player.sendMessage("Level: " + effectlvl);
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("skilltokens")) {
			if(sender instanceof Player) {
					ItemStack skilltoken = new ItemStack(Material.MAGMA_CREAM, 10);
				    ItemMeta itemMeta = skilltoken.getItemMeta();
				 
				    String ikeyLore = ChatColor.YELLOW + "Sharpens your class skills.";
				    List<String> loreList = new ArrayList<String>();
				    loreList.add(ikeyLore);
				    itemMeta.setLore(loreList);
				 
				    itemMeta.setDisplayName(ChatColor.GOLD + "Skill Token");
				    skilltoken.setItemMeta(itemMeta);
					
				player.getInventory().addItem(skilltoken);
				return true;
			}
		}
		
		
		return false;
	}
	
	
}
