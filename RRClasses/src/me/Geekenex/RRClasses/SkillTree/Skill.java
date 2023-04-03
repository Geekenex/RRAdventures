package me.Geekenex.RRClasses.SkillTree;

import java.util.HashSet;
import java.util.Set;

public class Skill {
	
    private String name;
    private String description;
    private int requiredXP;
    private Set<String> prerequisites;
    private int guiSlot;

    public Skill(String name, String description, int requiredXP) {
        this.name = name;
        this.description = description;
        this.requiredXP = requiredXP;
        this.prerequisites = new HashSet<>();
    }

    // Add a prerequisite skill by its name
    public void addPrerequisite(String prerequisite) {
        prerequisites.add(prerequisite);
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
    public int getRequiredXP() {
        return requiredXP;
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