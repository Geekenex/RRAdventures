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
	
	//Abilities
	AbilityList abilityList = new AbilityList();
	
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
	
	static int slot2_1 = 28;
	static int slot2_2 = 29;
	static int slot2_3 = 30;
	static int slot2_4 = 31;
	static int slot2_5 = 32;
	static int slot2_6 = 33;
	static int slot2_7 = 34;
	
	static int slot3_1 = 19;
	static int slot3_2 = 20;
	static int slot3_3 = 21;
	static int slot3_4 = 22;
	static int slot3_5 = 23;
	static int slot3_6 = 24;
	static int slot3_7 = 25;
	
	static int slot4_1 = 9;
	static int slot4_2 = 10;
	static int slot4_3 = 11;
	static int slot4_4 = 12;
	static int slot4_5 = 13;
	static int slot4_6 = 14;
	static int slot4_7 = 15;
	static int slot4_8 = 16;
	static int slot4_9 = 17;
	
	static int slot5_1 = 0;
	static int slot5_2 = 3;
	static int slot5_3 = 4;
	static int slot5_4 = 5;
	static int slot5_5 = 8;
	
	
	//Alchemist Skill Tree
	private void createAlchemistSkillTree() {
	    SkillTree alchemistTree = new SkillTree(alchemist);

	    // Create the skills for the Alchemist class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Fiery Touch", "Ignites foes when you hit them", tier0XP, slot0_1);
	    
	    //Tier 1 skills
	    Skill skill1_3 = new Skill("Questionable Potion", "Surely drinking this is a good idea", tier1XP, slot1_3);
	    skill1_3.addPrerequisite(skill0_1);
	    skill1_3.setAbility(abilityList.getAbility("questionablepotion"));
	    
		Skill skill1_2 = new Skill("Quicker Feet","+10% Movement Speed",tier1XP, slot1_2);
		skill1_2.addPrerequisite(skill1_3);
		Skill skill1_1 = new Skill("Heavier Hits","+2 Attack Damage",tier1XP, slot1_1);
		skill1_1.addPrerequisite(skill1_2);
		Skill skill1_4 = new Skill("Resilience","+1 Heart",tier1XP, slot1_4);
		skill1_4.addPrerequisite(skill1_3);
		Skill skill1_5 = new Skill("Skill Increase","Your attacks deal more fire damage for longer",tier1XP, slot1_5);
		skill1_5.addPrerequisite(skill1_4);

		//Tier 2 skills
		Skill skill2_4 = new Skill("Potion Knowledge", "Attacking now inflicts withering", tier2XP, slot2_4);
	    skill2_4.addPrerequisite(skill1_3);
	    

		Skill skill2_3 = new Skill("PlaceholderONE","XX",tier2XP, slot2_3);
		skill2_3.addPrerequisite(skill2_4);
		Skill skill2_5 = new Skill("PlaceholderTWO","XX",tier2XP, slot2_5);
		skill2_5.addPrerequisite(skill2_4);
		
		Skill skill2_2 = new Skill("PlaceholderTHREE","XX",tier2XP, slot2_2);
		skill2_2.addPrerequisite(skill2_3);
		Skill skill2_6 = new Skill("PlaceholderFOUR","XX",tier2XP, slot2_6);
		skill2_6.addPrerequisite(skill2_5);
		
		Skill skill2_1 = new Skill("Flame Concoction","This formula burns enemies in an area to a crisp",tier2XP, slot2_1);
		skill2_1.addPrerequisite(skill2_2);
		skill2_1.setAbility(abilityList.getAbility("flameconcoction"));
		Skill skill2_7 = new Skill("Slimy Coat","Apply this to bounce everything away",tier2XP, slot2_7);
		skill2_7.addPrerequisite(skill2_6);
		skill2_7.setAbility(abilityList.getAbility("slimycoat"));
		
		//Tier 3 Skills
		Skill skill3_4 = new Skill("placeholder5", "XX", tier3XP, slot3_4);
	    skill3_4.addPrerequisite(skill2_4);
	    

		Skill skill3_3 = new Skill("Placeholder6","XX",tier3XP, slot3_3);
		skill3_3.addPrerequisite(skill3_4);
		Skill skill3_5 = new Skill("Placeholder7","XX",tier3XP, slot3_5);
		skill3_5.addPrerequisite(skill3_4);
		
		Skill skill3_2 = new Skill("Placeholder8","XX",tier3XP, slot3_2);
		skill3_2.addPrerequisite(skill3_3);
		Skill skill3_6 = new Skill("Placeholder9","XX",tier3XP, slot3_6);
		skill3_6.addPrerequisite(skill3_5);
		
		Skill skill3_1 = new Skill("Placeholder10","+10% Movement Speed",tier3XP, slot3_1);
		skill3_1.addPrerequisite(skill3_2);
		Skill skill3_7 = new Skill("Placeholder11","Stronger class passive buff",tier3XP, slot3_7);
		skill3_7.addPrerequisite(skill3_6);
		
		//Tier 4 skills
		Skill skill4_5 = new Skill("placeholder12", "XX", tier4XP, slot4_5);
	    skill4_5.addPrerequisite(skill3_4);
	    

		Skill skill4_4 = new Skill("placeholder13", "XX", tier4XP, slot4_4);
	    skill4_4.addPrerequisite(skill4_5);
	    Skill skill4_6 = new Skill("placeholder14", "XX", tier4XP, slot4_6);
	    skill4_6.addPrerequisite(skill4_5);
	    
		Skill skill4_3 = new Skill("placeholder15", "XX", tier4XP, slot4_3);
	    skill4_3.addPrerequisite(skill4_4);
	    Skill skill4_7 = new Skill("placeholder16", "XX", tier4XP, slot4_7);
	    skill4_7.addPrerequisite(skill4_6);
	    
		Skill skill4_2 = new Skill("placeholder17", "XX", tier4XP, slot4_2);
	    skill4_2.addPrerequisite(skill4_3);
	    Skill skill4_8 = new Skill("placeholder18", "XX", tier4XP, slot4_8);
	    skill4_8.addPrerequisite(skill4_7);
	    
		Skill skill4_1 = new Skill("placeholder1722", "XX", tier4XP, slot4_1);
	    skill4_1.addPrerequisite(skill4_2);
	    Skill skill4_9 = new Skill("placeholder1228", "XX", tier4XP, slot4_9);
	    skill4_9.addPrerequisite(skill4_8); 
	    
	    //Tier 5 skills
		Skill skill5_3 = new Skill("placeholder19", "XX", tier5XP, slot5_3);
	    skill5_3.addPrerequisite(skill4_5);
	    
		Skill skill5_2 = new Skill("placeholder20", "XX", tier5XP, slot5_2);
	    skill5_2.addPrerequisite(skill5_3);
		Skill skill5_4 = new Skill("placeholder21", "XX", tier5XP, slot5_4);
	    skill5_4.addPrerequisite(skill5_3);
	    
		Skill skill5_1 = new Skill("placeholder22", "XX", tier5XP, slot5_1);
	    skill5_1.addPrerequisite(skill4_1);
		Skill skill5_5 = new Skill("placeholder23", "XX", tier5XP, slot5_5);
	    skill5_5.addPrerequisite(skill4_9);
	    
	    // Add the skills to the skill tree
	    alchemistTree.addSkill(skill0_1);
	    alchemistTree.addSkill(skill1_1);
	    alchemistTree.addSkill(skill1_2);
	    alchemistTree.addSkill(skill1_3);
	    alchemistTree.addSkill(skill1_4);
	    alchemistTree.addSkill(skill1_5);
	    alchemistTree.addSkill(skill2_1);
	    alchemistTree.addSkill(skill2_2);
	    alchemistTree.addSkill(skill2_3);
	    alchemistTree.addSkill(skill2_4);
	    alchemistTree.addSkill(skill2_5);
	    alchemistTree.addSkill(skill2_6);
	    alchemistTree.addSkill(skill2_7);
	    alchemistTree.addSkill(skill3_1);
	    alchemistTree.addSkill(skill3_2);
	    alchemistTree.addSkill(skill3_3);
	    alchemistTree.addSkill(skill3_4);
	    alchemistTree.addSkill(skill3_5);
	    alchemistTree.addSkill(skill3_6);
	    alchemistTree.addSkill(skill3_7);
	    alchemistTree.addSkill(skill4_1);
	    alchemistTree.addSkill(skill4_2);
	    alchemistTree.addSkill(skill4_3);
	    alchemistTree.addSkill(skill4_4);
	    alchemistTree.addSkill(skill4_5);
	    alchemistTree.addSkill(skill4_6);
	    alchemistTree.addSkill(skill4_7);
	    alchemistTree.addSkill(skill4_8);
	    alchemistTree.addSkill(skill4_9);
	    alchemistTree.addSkill(skill5_1);
	    alchemistTree.addSkill(skill5_2);
	    alchemistTree.addSkill(skill5_3);
	    alchemistTree.addSkill(skill5_4);
	    alchemistTree.addSkill(skill5_5);

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
