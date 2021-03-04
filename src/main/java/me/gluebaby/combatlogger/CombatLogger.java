
package me.gluebaby.combatlogger;

import me.gluebaby.combatlogger.frontend.CommandHandler;
import me.gluebaby.combatlogger.handlers.DamageCompat;
import me.gluebaby.combatlogger.handlers.DataHandler;
import me.gluebaby.combatlogger.handlers.PlayerCompat;
import me.gluebaby.combatlogger.managers.DamageManager;
import me.gluebaby.combatlogger.managers.GuiManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CombatLogger extends JavaPlugin {
	public void onEnable() {
		DataHandler.getInstance().load(this);
		GuiManager.getInstance().setup(this);
		DamageManager.getInstance().setup();
		getServer().getPluginManager().registerEvents((Listener) new DamageCompat(), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new PlayerCompat(), (Plugin) this);
		getCommand("dmghistory").setExecutor((CommandExecutor) new CommandHandler());
	}

	public void onDisable() {
		DamageManager.getInstance().save();
		GuiManager.getInstance().getMenus().clear();
	}
}
