/*    */ package me.gluebaby.combatlogger.managers;
/*    */ 
/*    */ import com.hazebyte.base.Base;
/*    */ import com.hazebyte.base.Size;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import me.gluebaby.combatlogger.frontend.DataMenu;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiManager
/*    */ {
/*    */   private static GuiManager gm;
/*    */   private JavaPlugin p;
/*    */   private Map<UUID, Base> menus;
/*    */   
/*    */   public static GuiManager getInstance() {
/* 23 */     if (gm == null) {
/* 24 */       gm = new GuiManager();
/*    */     }
/* 26 */     return gm;
/*    */   }
/*    */   
/*    */   public void setup(JavaPlugin p) {
/* 30 */     this.p = p;
/* 31 */     this.menus = new HashMap<>();
/*    */   }
/*    */   
/*    */   public Map<UUID, Base> getMenus() {
/* 35 */     return this.menus;
/*    */   }
/*    */   
/*    */   public void createGui(UUID id, String name) {
/* 39 */     this.menus.put(id, new DataMenu(this.p, ChatColor.BOLD + "" + ChatColor.DARK_BLUE + name + "'s Damage History", 
/* 40 */           Size.from(54), id));
/*    */   }
/*    */   
/*    */   public Base getGui(UUID id) {
/* 44 */     if (this.menus.containsKey(id)) {
/* 45 */       return this.menus.get(id);
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeGui(UUID id) {
/* 52 */     if (this.menus.containsKey(id)) {
/* 53 */       this.menus.remove(id);
/*    */     }
/*    */   }
/*    */   
/*    */   public String getFormattedType(EntityDamageEvent.DamageCause cause) {
/* 58 */     return (cause.toString().toLowerCase().substring(0, 1).toUpperCase() + cause
/* 59 */       .toString().substring(1).toLowerCase()).replaceAll("_", " ");
/*    */   }
/*    */ }


/* Location:              C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\combatlogger\managers\GuiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */