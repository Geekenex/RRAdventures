package me.Geekenex.RRClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.Geekenex.RRClasses.Abilities.Ability;
import me.Geekenex.RRClasses.Abilities.AbilityList;
import me.Geekenex.RRClasses.Classes.PlayerClass;
import me.Geekenex.RRClasses.SkillTree.Skill;
import me.Geekenex.RRClasses.SkillTree.SkillTree;


public class Main extends JavaPlugin {

	public static HashMap<UUID, PlayerClass> classtype;
	public static HashMap<UUID, Integer> classlevel;
	public static HashMap<UUID, Set<Ability>> abilities;
	public static HashMap<String, SkillTree> skilltrees;
	public static HashMap<UUID, Set<Skill>> skills;
	
	//Define classes
	static PlayerClass alchemist = new PlayerClass("alchemist");
	static PlayerClass tank = new PlayerClass("tank");
	static PlayerClass rogue = new PlayerClass("rogue");
	static PlayerClass brawler = new PlayerClass("brawler");
	static PlayerClass disciple = new PlayerClass("disciple");
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		new ClassCommands(this);
		new ClassEvents(this);
		new classesGUI(this);
		new GuiUse(this);
		new InventoryGUI(this);
		new AbilityList(this);
		this.getCommand("alchemist").setExecutor(new ClassCommands(this));
		this.getCommand("clearclass").setExecutor(new ClassCommands(this));
		this.getCommand("tank").setExecutor(new ClassCommands(this));
		this.getCommand("rogue").setExecutor(new ClassCommands(this));
		this.getCommand("brawler").setExecutor(new ClassCommands(this));
		this.getCommand("disciple").setExecutor(new ClassCommands(this));
		this.getCommand("levelget").setExecutor(new ClassCommands(this));
		this.getCommand("clearlevels").setExecutor(new ClassCommands(this));
		this.getCommand("class").setExecutor(new classesGUI(this));
		this.getCommand("skilltokens").setExecutor(new ClassCommands(this));
		DiscipleCD.setupCooldown();
		
		System.out.print("RR Classes is enabled!");
		
		File dir = getDataFolder();
		if(!dir.exists())
		 if(!dir.mkdir())
			 System.out.println("Couldn't create a directory for RR Adventures!");
		
		classtype = (HashMap<UUID, PlayerClass>) load(new File(getDataFolder(), "classes.dat"));
		
		if(classtype == null) {
			classtype = new HashMap<UUID, PlayerClass>();
		}
		
		
		classlevel = (HashMap<UUID, Integer>) load(new File(getDataFolder(), "classlevel.dat"));
		
			if(classlevel == null) {
				classlevel = new HashMap<UUID, Integer>();
		}
			
		abilities = (HashMap<UUID, Set<Ability>>) load(new File(getDataFolder(), "abilities.dat"));
			
			if(abilities == null) {
				abilities = new HashMap<UUID, Set<Ability>>();
		}
			
		skills = (HashMap<UUID, Set<Skill>>) load(new File(getDataFolder(), "skills.dat"));
			
			if(skills == null) {
				skills = new HashMap<UUID, Set<Skill>>();
		}
			
			// Initialize the skillTrees HashMap
		    skilltrees = new HashMap<>();

		    // Populate the skill trees for each class
		    createAlchemistSkillTree();
		    createTankSkillTree();
		    createRogueSkillTree();
		    createBrawlerSkillTree();
		    createDiscipleSkillTree();
		
	}

	@Override
	public void onDisable() {
		save(classtype, new File(getDataFolder(), "classes.dat"));
		save(classlevel, new File(getDataFolder(), "classlevel.dat"));
		save(abilities, new File(getDataFolder(), "abilities.dat"));
		save(skills, new File(getDataFolder(), "skills.dat"));
	}
	
	//Control Variables
	static int tier0XP = 0;
	static int tier1XP = 10;
	static int tier2XP = 20;
	static int tier3XP = 30;
	static int tier4XP = 40;
	static int tier5XP = 50;
	
	static int slot0_1 = 49;
	static int slot1_1 = 38;
	static int slot1_2 = 39;
	static int slot1_3 = 40;
	static int slot1_4 = 41;
	static int slot1_5 = 42;

	
	//Alchemist Skill Tree
	private void createAlchemistSkillTree() {
	    SkillTree alchemistTree = new SkillTree(alchemist);

	    // Create the skills for the Alchemist class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Fiery Touch", "Ignites foes when you hit them", tier0XP, slot0_1);
	    skill0_1.setUnlocked();
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Potion fury", "Surely drinking this is a good idea", tier1XP, slot1_3);
	    skill1_3.addPrerequisite(skill0_1);
	    
		Skill skill1_2 = new Skill("Quicker Feet","+10% Movement Speed",tier1XP, slot1_2);
		skill1_2.addPrerequisite(skill1_3);
		Skill skill1_1 = new Skill("Heavier Hits","+2 Attack Damage",tier1XP, slot1_1);
		skill1_1.addPrerequisite(skill1_2);
		Skill skill1_4 = new Skill("Resilience","+1 Heart",tier1XP, slot1_4);
		skill1_4.addPrerequisite(skill1_3);
		Skill skill1_5 = new Skill("Skill Increase","Stronger class passive buff",tier1XP, slot1_5);
		skill1_5.addPrerequisite(skill1_4);

	    // Add the skills to the skill tree
	    alchemistTree.addSkill(skill0_1);
	    alchemistTree.addSkill(skill1_1);
	    alchemistTree.addSkill(skill1_2);
	    alchemistTree.addSkill(skill1_3);
	    alchemistTree.addSkill(skill1_4);
	    alchemistTree.addSkill(skill1_5);

	    // Add the Alchemist skill tree to the skillTrees HashMap
	    skilltrees.put("alchemist", alchemistTree);
	}
	
	//Tank Skill Tree
	private void createTankSkillTree() {
	    SkillTree tankTree = new SkillTree(tank);

	    // Create the skills for the Tank class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Hearty", "Grants you extra health", tier0XP, slot0_1);
	    skill0_1.setUnlocked();
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Hefty Bash", "Give foes a big tackle", tier1XP,slot1_3);
	    skill1_3.addPrerequisite(skill0_1);

	    // Add the skills to the skill tree
	    tankTree.addSkill(skill0_1);
	    tankTree.addSkill(skill1_3);

	    // Add the Tank skill tree to the skillTrees HashMap
	    skilltrees.put("tank", tankTree);
	}
	
	//Rogue Skill Tree
	private void createRogueSkillTree() {
	    SkillTree rogueTree = new SkillTree(rogue);

	    // Create the skills for the Rogue class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Swift Feet", "Grants you extra speed", tier0XP, slot0_1);
	    skill0_1.setUnlocked();
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Quick Leap", "Launches you forwards", tier1XP,slot1_3);
	    skill1_3.addPrerequisite(skill0_1);

	    // Add the skills to the skill tree
	    rogueTree.addSkill(skill0_1);
	    rogueTree.addSkill(skill1_3);

	    // Add the Rogue skill tree to the skillTrees HashMap
	    skilltrees.put("rogue", rogueTree);
	}
	
	//Brawler Skill Tree
	private void createBrawlerSkillTree() {
	    SkillTree brawlerTree = new SkillTree(brawler);

	    // Create the skills for the Brawler class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Heavy Hands", "Grants you strength", tier0XP, slot0_1);
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Battle Cry", "Let out a war cry that provides courage", tier1XP, slot1_3);
	    skill1_3.addPrerequisite(skill0_1);

	    // Add the skills to the skill tree
	    brawlerTree.addSkill(skill0_1);
	    brawlerTree.addSkill(skill1_3);

	    // Add the Brawler skill tree to the skillTrees HashMap
	    skilltrees.put("brawler", brawlerTree);
	}
	
	//Brawler Skill Tree
	private void createDiscipleSkillTree() {
	    SkillTree discipleTree = new SkillTree(disciple);

	    // Create the skills for the Brawler class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("God's Favorite", "Grants you a second life upon death", tier0XP, slot0_1);
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Quick Prayer", "A small prayer for the lord you praise", tier1XP, slot1_3);
	    skill1_3.addPrerequisite(skill0_1);

	    // Add the skills to the skill tree
	    discipleTree.addSkill(skill0_1);
	    discipleTree.addSkill(skill1_3);

	    // Add the Brawler skill tree to the skillTrees HashMap
	    skilltrees.put("disciple", discipleTree);
	}
	
	
	public void save(Object o, File f) {
		try {
			if(!f.exists())
				f.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object load(File f) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Object result = ois.readObject();
			ois.close();
			return result;
		} catch(Exception e) {
			return null;
		}
	}



}
