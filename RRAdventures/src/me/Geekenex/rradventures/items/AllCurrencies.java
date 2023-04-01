package me.Geekenex.rradventures.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.rradventures.Main;

public class AllCurrencies implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public AllCurrencies(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	public void giveCopper(Player player) {
		ItemStack coppercoin = new ItemStack(Material.BRICK, 64);
	    ItemMeta itemMeta = coppercoin.getItemMeta();
	    itemMeta.setDisplayName(ChatColor.GOLD + "Copper Coin");
	    coppercoin.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(coppercoin);
	}
	public void giveSilver(Player player) {
		ItemStack silvercoin = new ItemStack(Material.IRON_NUGGET, 64);
	    ItemMeta itemMeta = silvercoin.getItemMeta();
	    itemMeta.setDisplayName(ChatColor.GRAY + "Silver Coin");
	    silvercoin.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(silvercoin);
	}
	public void giveGold(Player player) {
		ItemStack goldcoin = new ItemStack(Material.GOLD_NUGGET, 64);
	    ItemMeta itemMeta = goldcoin.getItemMeta();
	    itemMeta.setDisplayName(ChatColor.YELLOW + "Gold Coin");
	    goldcoin.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(goldcoin);
	}
	public void giveMoney(Player player) {
		ItemStack moneybill = new ItemStack(Material.EMERALD, 64);
	    ItemMeta itemMeta = moneybill.getItemMeta();
	    itemMeta.setDisplayName(ChatColor.DARK_GREEN + "Cash");
	    moneybill.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(moneybill);
	}
	
	
}
