package me.Geekenex.RRClasses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.RRClasses.Abilities.AbilityList;

public class InventoryGUI implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public InventoryGUI(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	AbilityList abilities = new AbilityList();
	
	CustomItem shopGuiItem = new CustomItem(Material.SLIME_BALL, "Shop", ChatColor.DARK_GREEN, "Click to view the shop.", ChatColor.GREEN);
	CustomItem skillTreeGuiItem = new CustomItem(Material.EXPERIENCE_BOTTLE, "Skill Tree", ChatColor.DARK_AQUA, "Click to view the skill tree.", ChatColor.AQUA);
	CustomItem abilityGuiItem = new CustomItem(Material.NETHER_STAR, "Ability Menu", ChatColor.DARK_PURPLE, "Click to manage abilities.", ChatColor.LIGHT_PURPLE);
	
	CustomItem abilitySlot1 = new CustomItem(Material.PAPER, "Ability Slot 1", ChatColor.GRAY, "Slot 1 for an ability.", ChatColor.DARK_GRAY);
	CustomItem abilitySlot2 = new CustomItem(Material.PAPER, "Ability Slot 2", ChatColor.GRAY, "Slot 2 for an ability.", ChatColor.DARK_GRAY);
	CustomItem abilitySlot3 = new CustomItem(Material.PAPER, "Ability Slot 3", ChatColor.GRAY, "Slot 3 for an ability.", ChatColor.DARK_GRAY);
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().contains(abilityGuiItem.getItem())) return;
		p.getInventory().setItem(9, shopGuiItem.getItem());
		p.getInventory().setItem(10, skillTreeGuiItem.getItem());
		p.getInventory().setItem(11, abilityGuiItem.getItem());
		
		p.getInventory().setItem(6, abilitySlot1.getItem());
		p.getInventory().setItem(7, abilitySlot2.getItem());
		p.getInventory().setItem(8, abilitySlot3.getItem());
		
	}
	
	
	public void abilityGUI(Player p) {
		//Creating the GUI Inventory	
		Inventory abilityGUI = Bukkit.createInventory(p, 9, "Abilities");
		
		//Defining the abilities
		
		CustomItem i = new CustomItem(Material.ACACIA_BOAT, "test", ChatColor.BLUE, "boat", ChatColor.DARK_RED);
		
		//Glass Panes
		ItemStack glassfiller = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta gfmeta = glassfiller.getItemMeta();

		gfmeta.setDisplayName(" ");
		
		glassfiller.setItemMeta(gfmeta);

		//Setting up GUI and putting in items
		p.openInventory(abilityGUI);
		abilityGUI.setItem(0, i.getItem());
		abilityGUI.setItem(1, glassfiller);
		abilityGUI.setItem(3, glassfiller);
		abilityGUI.setItem(5, glassfiller);
		abilityGUI.setItem(7, glassfiller);
	}
	
	public void shopGUI(Player p) {
		//Creating the GUI Inventory	
		Inventory shopGUI = Bukkit.createInventory(p, 27, "Shop");
		
		//Glass Panes
		ItemStack glassfiller = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta gfmeta = glassfiller.getItemMeta();
		gfmeta.setDisplayName(" ");
		glassfiller.setItemMeta(gfmeta);

		//Setting up GUI and putting in items
		p.openInventory(shopGUI);
		shopGUI.setItem(0, abilities.getAbility("fireball").getItem());
		for(int x = 1; x < 27; x+=2) {
		shopGUI.setItem(x, glassfiller);
		}
	}
	
	
	
	@EventHandler
	public void playerClickAbilityGUI(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem != null && (clickedItem.isSimilar(shopGuiItem.getItem()) || clickedItem.isSimilar(skillTreeGuiItem.getItem()) || clickedItem.isSimilar(abilityGuiItem.getItem()))) {
        	p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
        	e.setCancelled(true);
        	//Opens GUI
        	if(clickedItem.isSimilar(abilityGuiItem.getItem()))
        		abilityGUI(p);
        	if(clickedItem.isSimilar(shopGuiItem.getItem()))
        		shopGUI(p);
        }
        if(e.getView().getTitle().equalsIgnoreCase("abilities") || e.getView().getTitle().equals("Shop"))
        	e.setCancelled(true);
        
        if (clickedItem != null && (clickedItem.isSimilar(abilitySlot1.getItem()) || clickedItem.isSimilar(abilitySlot2.getItem()) || clickedItem.isSimilar(abilitySlot3.getItem()))) 
        	e.setCancelled(true);
	
	}
	
	@EventHandler
	public void playerCloseAbilityGUI(InventoryCloseEvent e) {
	    if (e.getInventory().getHolder() instanceof Player) {
	        Player p = (Player) e.getInventory().getHolder();
	        
	        // Check if the inventory is the abilityGUI
	        if (e.getView().getTitle().equals("Abilities") || e.getView().getTitle().equals("Shop")) {
	            // Remove the abilityGuiItem from the cursor
	            p.setItemOnCursor(null);
	            
	            // Update the player's inventory to remove the item visually
	            p.updateInventory();
	        }
	    }
	}
	
	@EventHandler
	public void dropAbilityGUI(PlayerDropItemEvent e) {
		ItemStack i = e.getItemDrop().getItemStack();
		if(i.isSimilar(abilitySlot1.getItem()) || i.isSimilar(abilitySlot2.getItem()) || i.isSimilar(abilitySlot3.getItem())) {
			e.setCancelled(true);
		}
		if((i.isSimilar(shopGuiItem.getItem()) || i.isSimilar(skillTreeGuiItem.getItem()) || i.isSimilar(abilityGuiItem.getItem()))) {
			e.setCancelled(true);
		}
	}
	
	
	
}
