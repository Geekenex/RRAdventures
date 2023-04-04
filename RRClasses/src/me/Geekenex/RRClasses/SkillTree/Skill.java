package me.Geekenex.RRClasses.SkillTree;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.Geekenex.RRClasses.CustomItem;

public class Skill {
	
    private String name;
    private String description;
    private int requiredXPLevel;
    private Set<String> prerequisites;
    private int guiSlot;
    private CustomItem item;
    
    public Skill(String name, String description, int requiredXP) {
        this.name = name;
        this.description = description;
        this.requiredXPLevel = requiredXP;
        this.prerequisites = new HashSet<>();
        item = new CustomItem(Material.PAPER, name, ChatColor.GREEN, description, ChatColor.GRAY);
    }

    // Add a prerequisite skill by its name
    public void addPrerequisite(String prerequisite) {
        prerequisites.add(prerequisite);
        if(!prerequisites.isEmpty()) {
        	item.addLore("XP Level Cost: " + requiredXPLevel, ChatColor.GOLD);
            item.addLore("Prerequisites: " + String.join(", ", prerequisites), ChatColor.RED);
    	}
    }
    
    //Creates the skill item for the GUI
    public void createItem(Boolean unlocked) {
    	if(unlocked) {
    		item.removeLore("XP Level Cost: " + requiredXPLevel, ChatColor.GOLD);
    		item.removeLore("Prerequisites: " + String.join(", ", prerequisites), ChatColor.RED);
    	}
    }
    
    public CustomItem getCustomItem() {
    	return item;
    }

    // Get the name of the skill
    public String getName() {
        return name;
    }

    // Get the description of the skill
    public String getDescription() {
        return description;
    }

    // Get the required XP to unlock the skill
    public int getRequiredXPLevel() {
        return requiredXPLevel;
    }

    // Get the prerequisites for the skill
    public Set<String> getPrerequisites() {
        return prerequisites;
    }

	public int getGuiSlot() {
		return guiSlot;
	}

	public void setGuiSlot(int guiSlot) {
		this.guiSlot = guiSlot;
	}
}