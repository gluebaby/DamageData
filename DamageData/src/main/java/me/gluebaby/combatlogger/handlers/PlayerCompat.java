/*    */ package me.gluebaby.combatlogger.handlers;
/*    */ 
/*    */ import me.gluebaby.combatlogger.managers.GuiManager;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ public class PlayerCompat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onQuit(PlayerQuitEvent event) {
/* 13 */     if (GuiManager.getInstance().getMenus().containsKey(event.getPlayer().getUniqueId()) && 
/* 14 */       GuiManager.getInstance().getGui(event.getPlayer().getUniqueId()) != null)
/* 15 */       GuiManager.getInstance().removeGui(event.getPlayer().getUniqueId()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\combatlogger\handlers\PlayerCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */