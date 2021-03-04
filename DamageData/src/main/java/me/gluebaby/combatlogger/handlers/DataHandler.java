/*    */
package me.gluebaby.combatlogger.handlers;

/*    */
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.java.JavaPlugin;

/*    */
/*    */ public class DataHandler {
	/*    */ private static DataHandler handler;
	/*    */ private FileConfiguration config;
	/*    */ private File file;

	/*    */
	/*    */ public static DataHandler getInstance() {
		/* 15 */ if (handler == null) {
			/* 16 */ handler = new DataHandler();
			/*    */ }
		/* 18 */ return handler;
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */
	/*    */ public void load(JavaPlugin p) {
		/* 25 */ this.file = new File(p.getDataFolder(), "combatdata.yml");
		/* 26 */ if (!this.file.exists()) {
			/*    */ try {
				/* 28 */ p.getDataFolder().mkdir();
				/* 29 */ this.file.createNewFile();
				/* 30 */ } catch (IOException e) {
				/* 31 */ e.printStackTrace();
				/*    */ }
			/*    */ }
		/* 34 */ this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		/* 35 */ if (!this.config.contains("players")) {
			/* 36 */ this.config.createSection("players");
			/*    */ }
		/* 38 */ save();
		/*    */ }

	/*    */
	/*    */ public FileConfiguration getFileConfig() {
		/* 42 */ return this.config;
		/*    */ }

	/*    */
	/*    */ public void save() {
		/*    */ try {
			/* 47 */ this.config.save(this.file);
			/* 48 */ this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
			/* 49 */ } catch (IOException e) {
			/* 50 */ e.printStackTrace();
			/*    */ }
		/*    */ }
	/*    */ }

/*
 * Location:
 * C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\
 * combatlogger\handlers\DataHandler.class Java compiler version: 8 (52.0)
 * JD-Core Version: 1.1.3
 */