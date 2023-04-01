package me.Geekenex.RRClasses;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class classesGUI implements CommandExecutor {

	
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public classesGUI(Main plugin) {
		this.plugin = plugin;
		
	}
	
	
	
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("class")) {
			//Creating the GUI Inventory	
				Inventory classgui = Bukkit.createInventory(p, 9, "Classes");
				
				//Defining the items
				//AlchemistPotion
				ItemStack alchemist = new ItemStack(Material.POTION);
				ItemMeta almeta = alchemist.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				
				lore.add("");
				lore.add(Utils.chat("&dInflict the rath of chemistry."));
				
				almeta.setLore(lore);
				almeta.setDisplayName(Utils.chat("&5Alchemist"));
				
				alchemist.setItemMeta(almeta);
				
				//TankShield
				ItemStack tank = new ItemStack(Material.SHIELD);
				ItemMeta tankmeta = tank.getItemMeta();
				ArrayList<String> tanklore = new ArrayList<String>();
				
				tanklore.add("");
				tanklore.add(Utils.chat("&7Massive defense."));
				
				tankmeta.setLore(tanklore);
				tankmeta.setDisplayName(Utils.chat("&8Titan"));
				
				tank.setItemMeta(tankmeta);
				
				//RogueFeather
				ItemStack rogue = new ItemStack(Material.FEATHER);
				ItemMeta roguemeta = rogue.getItemMeta();
				ArrayList<String> roguelore = new ArrayList<String>();
				
				roguelore.add("");
				roguelore.add(Utils.chat("&2Breaks the sound barrier."));
				
				roguemeta.setLore(roguelore);
				roguemeta.setDisplayName(Utils.chat("&aRogue"));
				
				rogue.setItemMeta(roguemeta);
				
				//BrawlerAxe
				ItemStack brawler = new ItemStack(Material.IRON_AXE);
				ItemMeta brawlermeta = rogue.getItemMeta();
				ArrayList<String> brawlerlore = new ArrayList<String>();
				
				brawlerlore.add("");
				brawlerlore.add(Utils.chat("&4Slays anything anywhere."));
				
				brawlermeta.setLore(brawlerlore);
				brawlermeta.setDisplayName(Utils.chat("&cBrawler"));
				
				brawler.setItemMeta(brawlermeta);
				
				//DiscipleTotem
				ItemStack disciple = new ItemStack(Material.TOTEM_OF_UNDYING);
				ItemMeta disciplemeta = rogue.getItemMeta();
				ArrayList<String> disciplelore = new ArrayList<String>();
				
				disciplelore.add("");
				disciplelore.add(Utils.chat("&7Prays to the lord."));
				
				disciplemeta.setLore(disciplelore);
				disciplemeta.setDisplayName(Utils.chat("&fDisciple"));
				
				disciple.setItemMeta(disciplemeta);
				
				//Glass Panes
				ItemStack glassfiller = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta gfmeta = glassfiller.getItemMeta();

				gfmeta.setDisplayName(" ");
				
				glassfiller.setItemMeta(gfmeta);

				//Setting up GUI and putting in items
				p.openInventory(classgui);
				classgui.setItem(0, alchemist);
				classgui.setItem(2, tank);
				classgui.setItem(4, rogue);
				classgui.setItem(6, brawler);
				classgui.setItem(8, disciple);
				classgui.setItem(1, glassfiller);
				classgui.setItem(3, glassfiller);
				classgui.setItem(5, glassfiller);
				classgui.setItem(7, glassfiller);
				return true;
			}
			
		}
		
		return false;
	}
	      
	
}
