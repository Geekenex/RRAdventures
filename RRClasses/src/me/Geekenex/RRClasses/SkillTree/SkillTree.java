package me.Geekenex.RRClasses.SkillTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.Geekenex.RRClasses.Classes.PlayerClass;

public class SkillTree implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PlayerClass playerClass;
    private HashMap<String, Skill> skills;

    public SkillTree(PlayerClass pc) {
        playerClass = pc;
        this.skills = new HashMap<>();
    }

    // Add a skill to the skill tree
    public void addSkill(Skill skill) {
        skills.put(skill.getName(), skill);
    }

    // Check if a skill exists in the skill tree
    public boolean hasSkill(String skillName) {
        return skills.containsKey(skillName);
    }

    // Get a skill by its name
    public Skill getSkill(String skillName) {
        return skills.get(skillName);
    }

    // Check if the prerequisites for a skill are met
    public boolean prerequisitesMet(String skillName, Set<String> unlockedSkills) {
        Skill skill = skills.get(skillName);
        if (skill == null) {
            return false;
        }

        Set<String> prerequisites = skill.getPrerequisites();
        return unlockedSkills.containsAll(prerequisites);
    }

    // Get all skills in the skill tree
    public Set<Skill> getAllSkills() {
        return new HashSet<>(skills.values());
    }

    // Get the class name associated with this skill tree
    public String getClassName() {
        return playerClass.getClassName();
    }
    
    public PlayerClass getClassType() {
    	return playerClass;
    }
}
