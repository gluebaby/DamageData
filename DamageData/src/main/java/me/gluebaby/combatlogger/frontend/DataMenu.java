package me.gluebaby.combatlogger.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableList;
import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.Size;

import me.gluebaby.combatlogger.damagelogger.DamageEvent;
import me.gluebaby.combatlogger.managers.DamageManager;
import me.gluebaby.combatlogger.managers.GuiManager;

public class DataMenu extends Base {
	public DataMenu(JavaPlugin plugin, String title, Size size, UUID id) {
		super(plugin, title, size);
		ImmutableList<DamageEvent> immutableList = ImmutableList.copyOf(DamageManager.getInstance().getDamageEvents(id))
				.reverse();

		for (DamageEvent event : immutableList) {
			ItemStack guiitem = new ItemStack(event.getGuiItem(event.getDamageCause()), 1);
			ItemMeta itemmeta = guiitem.getItemMeta();
			itemmeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED
					+ GuiManager.getInstance().getFormattedType(event.getDamageCause()));
			List<String> lore = new ArrayList<>();
			lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "Damage: " + ChatColor.RED + event.getDamage().toString());
			lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "Time: " + ChatColor.RED + event.getFormattedTime());
			lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "Location: " + ChatColor.RED
					+ event.getLocation().getBlockX() + ", " + event.getLocation().getBlockY() + ", "
					+ event.getLocation().getBlockZ());
			if (event.getDamagerType() != null) {
				lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "Name: " + ChatColor.RED + event.getDamagerName());
			}
			itemmeta.setLore(lore);
			guiitem.setItemMeta(itemmeta);
			addIcon(new Button(guiitem));
		}
	}
}
