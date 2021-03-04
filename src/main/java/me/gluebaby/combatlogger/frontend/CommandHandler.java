/*    */ package me.gluebaby.combatlogger.frontend;
/*    */ 
/*    */ import me.gluebaby.combatlogger.managers.DamageManager;
/*    */ import me.gluebaby.combatlogger.managers.GuiManager;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CommandHandler
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
/* 16 */     if (!(sender instanceof Player)) {
/* 17 */       return true;
/*    */     }
/* 19 */     if (cmd.getName().equalsIgnoreCase("dmghistory")) {
/* 20 */       Player p = (Player)sender;
/* 21 */       if (!p.hasPermission("damagedata.read")) {
/* 22 */         p.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
/*    */       }
/* 24 */       if (!DamageManager.getInstance().hasLogs(p.getUniqueId())) {
/* 25 */         p.sendMessage(ChatColor.RED + "You don't have any logs!");
/* 26 */         return true;
/*    */       } 
/* 28 */       GuiManager.getInstance().createGui(p.getUniqueId(), p.getName());
/* 29 */       GuiManager.getInstance().getGui(p.getUniqueId()).open((HumanEntity)p);
/* 30 */       return true;
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\combatlogger\frontend\CommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */