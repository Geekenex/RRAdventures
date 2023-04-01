package me.Geekenex.RRClasses;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class GuiUse implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public GuiUse(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
    private void inventoryClick(InventoryClickEvent e) {
		
         if (!(e.getView().getTitle().toString().equalsIgnoreCase("Classes"))) return;
          if(e.getCurrentItem() == null) return;
          if(e.getCurrentItem().getItemMeta() == null) return;
          if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
          //AlchemistButton
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&5Alchemist"))) {
              Player p = (Player) e.getWhoClicked();
        	  e.setCancelled(true);
        	  Bukkit.dispatchCommand(p, "alchemist");
                    p.closeInventory();

                 p.playSound(p.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1.0f, 1.0f);
                 
                }
          //TitanButton
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&8Titan"))) {
              Player p = (Player) e.getWhoClicked();
        	  e.setCancelled(true);
        	  Bukkit.dispatchCommand(p, "tank");
                    p.closeInventory();

                 p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                 
                }
          //RogueButton
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&aRogue"))) {
              Player p = (Player) e.getWhoClicked();
        	  e.setCancelled(true);
        	  Bukkit.dispatchCommand(p, "rogue");
                    p.closeInventory();

                 p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.0f);
                 
                }
          //BrawlerButton
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cBrawler"))) {
              Player p = (Player) e.getWhoClicked();
        	  e.setCancelled(true);
        	  Bukkit.dispatchCommand(p, "brawler");
                    p.closeInventory();

                 p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1.0f, 1.0f);
                 
                }
          //DiscipleButton
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&fDisciple"))) {
              Player p = (Player) e.getWhoClicked();
        	  e.setCancelled(true);
        	  Bukkit.dispatchCommand(p, "disciple");
                    p.closeInventory();

                 p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                 
                }
          //GlassPanes
          if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
        	  e.setCancelled(true);
                 
                }
          
         
     }
	
	
}
