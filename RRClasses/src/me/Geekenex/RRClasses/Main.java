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
			
			// Initialize the skillTrees HashMap
		    skilltrees = new HashMap<>();

		    // Populate the skill trees for each class
		    createAlchemistSkillTree();
		  //  createTankSkillTree();
		  //  createRogueSkillTree();
		  //  createBrawlerSkillTree();
		  //  createDiscipleSkillTree();	
		
	}

	@Override
	public void onDisable() {
		save(classtype, new File(getDataFolder(), "classes.dat"));
		save(classlevel, new File(getDataFolder(), "classlevel.dat"));
		save(abilities, new File(getDataFolder(), "abilities.dat"));
	}

	
	private void createAlchemistSkillTree() {
	    SkillTree alchemistTree = new SkillTree(alchemist);

	    // Create the skills for the Alchemist class
	    //Tier 0 skills
	    Skill skill0_1 = new Skill("Fiery Touch", "Ignites foes when you hit them", 0);
	    skill0_1.setGuiSlot(49);
	    
	    Skill skill1_1 = new Skill("Potion fury", "Surely drinking this is a good idea", 10);
	    skill1_1.addPrerequisite("Fiery Touch");
	    skill1_1.setGuiSlot(40);

	    // Add the skills to the Alchemist skill tree
	    alchemistTree.addSkill(skill0_1);
	    alchemistTree.addSkill(skill1_1);

	    // Add the Alchemist skill tree to the skillTrees HashMap
	    skilltrees.put("alchemist", alchemistTree);
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
