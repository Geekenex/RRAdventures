package me.Geekenex.RRClasses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

import me.Geekenex.RRClasses.Abilities.Ability;
import me.Geekenex.RRClasses.Abilities.AbilityList;
import me.Geekenex.RRClasses.Classes.PlayerClass;
import me.Geekenex.RRClasses.SkillTree.Skill;
import me.Geekenex.RRClasses.SkillTree.SkillTree;

public class InventoryGUI implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public InventoryGUI(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	private HashMap<UUID, Ability> selectedAbilities = new HashMap<>();
	
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
		
		int inventorySize = 18;
		int abilityCount = 0;
		
	    // Creating the GUI Inventory with the appropriate size
	    Inventory abilityGUI = Bukkit.createInventory(p, inventorySize, "Abilities");
		//Loops through the ability hashmap, and adds every ability a player has to the gui
	    Set<Ability> playerAbilities = Main.abilities.get(p.getUniqueId());
	    if (playerAbilities != null) {
	        for (Ability ability : playerAbilities) {
	            ItemStack abilityItem = ability.getItem();
	            abilityGUI.addItem(abilityItem);
	            abilityCount++;
	        }
	    }
		
		//Glass Panes
		ItemStack glassfiller = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta gfmeta = glassfiller.getItemMeta();
		gfmeta.setDisplayName(" ");
		glassfiller.setItemMeta(gfmeta);

		//Filling rest of ability slots with glass panes
		for(int i = abilityCount; i < inventorySize; i++) {
		abilityGUI.setItem(i, glassfiller);
		}
		
		p.openInventory(abilityGUI);
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
		
		shopGUI.setItem(0,abilities.getAbility("fireball").getShopItem());
		shopGUI.setItem(2,abilities.getAbility("healpool").getShopItem());
		shopGUI.setItem(4,abilities.getAbility("repulsion").getShopItem());
		
		for(int x = 1; x < 27; x+=2) {
		shopGUI.setItem(x, glassfiller);
		}
		
		p.openInventory(shopGUI);
	}
	

	public void openSkillTree(Player p) {
	    PlayerClass playerClass = Main.classtype.get(p.getUniqueId());

	    SkillTree playerSkillTree = Main.skilltrees.get(playerClass.getClassName());

	    // Create a new inventory for the skill tree GUI
	    Inventory skillTreeGui = Bukkit.createInventory(p, 54,"Skill Tree");

	    
	    CustomItem skillglass = new CustomItem(Material.GRAY_STAINED_GLASS_PANE, "", ChatColor.BLACK, "", ChatColor.BLACK);
	    CustomItem glass = new CustomItem(Material.BLACK_STAINED_GLASS_PANE, "", ChatColor.BLACK, "", ChatColor.BLACK);
        
	    // Loop through the skills in the player's skill tree and create ItemStacks for them
	    for (Skill skill : playerSkillTree.getAllSkills()) {
	        Set<Skill> playerSkills = Main.skills.get(p.getUniqueId());

	        if (playerSkills == null) {
	            if (skill.getPrerequisite() == null) {
	                skill.createItem(false);
	            } else {
	                skillTreeGui.setItem(skill.getGuiSlot(), skillglass.getItem());
	                continue;
	            }
	        } else {
	            if (playerSkills.contains(skill)) {
	                skill.createItem(true);
	            } else if (skill.getPrerequisite() == null || playerSkills.contains(skill.getPrerequisite())) {
	                skill.createItem(false);
	            } else {
	                skillTreeGui.setItem(skill.getGuiSlot(), skillglass.getItem());
	                continue;
	            }
	        }

	        // Add the skill item to the skill tree GUI at the appropriate position based on its tier
	        skillTreeGui.setItem(skill.getGuiSlot(), skill.getCustomItem().getItem());
	    }
	    
	    // Fill the remaining slots with gray glass panes
	    for (int i = 0; i < skillTreeGui.getSize(); i++) {
	        if (skillTreeGui.getItem(i) == null) {
	            skillTreeGui.setItem(i, glass.getItem());
	        }
	    }
	    // Open the skill tree GUI for the player
	    p.openInventory(skillTreeGui);
	}

	
	//Returns amount of emeralds in player's inventory
	public int getMoney(Player player) {
	    int money = 0;
	    // Get the player's inventory
	    Inventory inventory = player.getInventory();

	    // Iterate through the inventory
	    for (ItemStack item : inventory.getContents()) {
	        // Check if the item is an emerald
	        if (item != null && item.getType() == Material.EMERALD) {
	        	
	            // Add the number of emeralds in the current ItemStack to the total count
	        	money += item.getAmount();
	        }
	    }

	    return money;
	}
	
	//Removes the given money from player's inventory
	public void removeMoney(Player p, int amountToRemove) {
	    Inventory inventory = p.getInventory();

	    // Iterate through the inventory
	    for (ItemStack item : inventory.getContents()) {
	        // Check if the item is an emerald
	        if (item != null && item.getType() == Material.EMERALD) {
	            int itemAmount = item.getAmount();

	            if (itemAmount <= amountToRemove) {
	                // Remove the entire ItemStack if it has less or equal emeralds than the amount to remove
	                inventory.removeItem(item);
	                amountToRemove -= itemAmount;
	            } else {
	                // Remove only the required number of emeralds from the current ItemStack
	                item.setAmount(itemAmount - amountToRemove);
	                amountToRemove = 0;
	            }
	        }
	        // Stop the loop when all the required emeralds have been removed
	        if (amountToRemove == 0) {
	            break;
	        }
	    }
	}
	
	public void purchaseAbility(Player p, Ability a) {
    		//Player has enough money
    		if(getMoney(p) >= a.getShopCost()) {
    			Set<Ability> playerAbilities = Main.abilities.get(p.getUniqueId());
    	        if (playerAbilities == null) {
    	            playerAbilities = new HashSet<>();
    	            Main.abilities.put(p.getUniqueId(), playerAbilities);
    	        }
    	        playerAbilities.add(a);
    			removeMoney(p, a.getShopCost());
    			p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
    		}
    		else { //Too broke
    			p.sendMessage(ChatColor.RED + "Not enough money!");
    			p.playSound(p, Sound.BLOCK_CHAIN_FALL, 1, 1);
    		}
    	}
	
	public void purchaseSkill(Player p, Skill s) {
		//Player has enough XP levels
		int skillLevels = s.getRequiredXPLevel();
		if(p.getLevel() >= skillLevels) {
			Set<Skill> playerSkills = Main.skills.get(p.getUniqueId());
			Set<Ability> playerAbilities = Main.abilities.get(p.getUniqueId());
	        if (playerSkills == null) {
	        	playerSkills = new HashSet<>();
	            Main.skills.put(p.getUniqueId(), playerSkills);
	        }
	        if (playerAbilities == null) {
	            playerAbilities = new HashSet<>();
	            Main.abilities.put(p.getUniqueId(), playerAbilities);
	        }
	        if(s.getPrerequisite() == null || playerSkills.contains(s.getPrerequisite())) {
	        playerSkills.add(s);
	        if(s.isAbility()) {
	        	playerAbilities.add(s.getAbility());
	        }
	        //Main.abilities.get(p.getUniqueId()).add(s.getAbility());
	        s.setUnlocked();
			p.giveExpLevels(-skillLevels);
			p.playSound(p, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
		} else {
			p.sendMessage(ChatColor.RED + "Prerequisite not met!");
			p.playSound(p, Sound.BLOCK_NOTE_BLOCK_SNARE, 1, 1);
		}
		}
		else { //Too broke
			p.sendMessage(ChatColor.RED + "Not enough EXP!");
			p.playSound(p, Sound.BLOCK_NOTE_BLOCK_SNARE, 1, 1);
		}
	}
	
	public boolean isAbilityItem(ItemStack item) {
	    for (Ability ability : AbilityList.abilities.values()) {
	        if (ability.getItem().isSimilar(item)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public int scanForItemStack(Player p, ItemStack abilityItem) {
	    for (int i = 0; i < 9; i++) {
	        ItemStack item = p.getInventory().getItem(i);
	        if (item != null && item.isSimilar(abilityItem)) {
	            return i; //Index of item found
	        }
	    }
	    return -1; //Not found
	}
	
	
	@EventHandler
	public void playerClickAbilityGUI(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack clickedItem = e.getCurrentItem();
		
		if(isAbilityItem(clickedItem))
			e.setCancelled(true);
		
        if (clickedItem != null && (clickedItem.isSimilar(shopGuiItem.getItem()) || clickedItem.isSimilar(skillTreeGuiItem.getItem()) || clickedItem.isSimilar(abilityGuiItem.getItem()))) {
        	p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
        	e.setCancelled(true);
        	//Opens GUI
        	if(clickedItem.isSimilar(abilityGuiItem.getItem()))
        		abilityGUI(p);
        	if(clickedItem.isSimilar(shopGuiItem.getItem()))
        		shopGUI(p);
        	if(clickedItem.isSimilar(skillTreeGuiItem.getItem()))
        		openSkillTree(p);
        }
        //Player interacts with the shop items
        if(e.getView().getTitle().equalsIgnoreCase("shop")) {
        	e.setCancelled(true);
        	
        	//SHOP ABILITIES
        	//Fireball
        	Ability fb = abilities.getAbility("fireball");
        	if(clickedItem.isSimilar(fb.getShopItem())) {
        		purchaseAbility(p, fb);
        	}
        	
        	//Heal pool
        	Ability hp = abilities.getAbility("healpool");
        	if(clickedItem.isSimilar(hp.getShopItem())) {
        		purchaseAbility(p, hp);
        	}
        	
        	//Repulsion
        	Ability sb = abilities.getAbility("repulsion");
        	if(clickedItem.isSimilar(sb.getShopItem())) {
        		purchaseAbility(p, sb);
        	}
        	
        }
        
        if (e.getView().getTitle().equalsIgnoreCase("skill tree")) {
            e.setCancelled(true);
            
            // Check if the clicked item is a skill from the skill tree
            for (Skill skill : Main.skilltrees.get(Main.classtype.get(p.getUniqueId()).getClassName()).getAllSkills()) {
                if (skill.getCustomItem().getItem().isSimilar(clickedItem)) {
                    // Purchase the skill
                    purchaseSkill(p, skill);
                    openSkillTree(p);
                    return;
                }
            }
        }
        
        
        if (e.getView().getTitle().equalsIgnoreCase("abilities")) {
            e.setCancelled(true);

            // Check if the clicked item is one of the player's abilities
            Set<Ability> playerAbilities = Main.abilities.get(p.getUniqueId());
            Ability clickedAbility = null;
            if (playerAbilities != null) {
                for (Ability ability : playerAbilities) {
                    if (ability.getItem().isSimilar(clickedItem)) {
                        clickedAbility = ability;
                        break;
                    }
                }
            }

            if (clickedAbility != null && !selectedAbilities.containsKey(p.getUniqueId())) {
                // Save the selected ability for the player
                selectedAbilities.put(p.getUniqueId(), clickedAbility);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Select an ability slot to bind this ability to!");
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
            } else if (selectedAbilities.containsKey(p.getUniqueId()) && (e.getSlot() == 6 || e.getSlot() == 7 || e.getSlot() == 8)) {
                // Bind the selected ability to the hotbar slot
                Ability selectedAbility = selectedAbilities.get(p.getUniqueId());
                p.getInventory().setItem(e.getSlot(), selectedAbility.getItem());
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);

                // Display the message
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Ability bound to slot number " + ChatColor.DARK_PURPLE + (e.getSlot() - 5) + ChatColor.LIGHT_PURPLE + "!");

                // Remove the selected ability from the HashMap
                selectedAbilities.remove(p.getUniqueId());
            }
        }

        
        if (clickedItem != null && (clickedItem.isSimilar(abilitySlot1.getItem()) || clickedItem.isSimilar(abilitySlot2.getItem()) || clickedItem.isSimilar(abilitySlot3.getItem()))) 
        	e.setCancelled(true);
	
	}
	
	@EventHandler
	public void playerCloseAbilityGUI(InventoryCloseEvent e) {
	    if (e.getInventory().getHolder() instanceof Player) {
	        Player p = (Player) e.getInventory().getHolder();
	        
	        // Check if the inventory is the abilityGUI
	        if (e.getView().getTitle().equals("Abilities") || e.getView().getTitle().equals("Shop") || e.getView().getTitle().equalsIgnoreCase("Skill Tree")) {
	            
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
		if(isAbilityItem(i))
			e.setCancelled(true);
	}
	
	
	
}
