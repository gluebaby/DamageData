/*    */ package me.gluebaby.combatlogger.managers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.gluebaby.combatlogger.damagelogger.DamageEvent;
/*    */ import me.gluebaby.combatlogger.handlers.DataHandler;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageManager
/*    */ {
/*    */   private static DamageManager dm;
/*    */   private HashMap<UUID, List<DamageEvent>> damagestorage;
/*    */   
/*    */   public static DamageManager getInstance() {
/* 19 */     if (dm == null) {
/* 20 */       dm = new DamageManager();
/*    */     }
/* 22 */     return dm;
/*    */   }
/*    */   
/*    */   public void setup() {
/* 26 */     this.damagestorage = new HashMap<>();
/* 27 */     for (String player : DataHandler.getInstance().getFileConfig().getConfigurationSection("players")
/* 28 */       .getKeys(false)) {
/* 29 */       List<DamageEvent> templist = new ArrayList<>();
/* 30 */       UUID id = UUID.fromString(player);
/* 31 */       for (String mapid : DataHandler.getInstance().getFileConfig()
/* 32 */         .getConfigurationSection("players." + id + ".logs").getKeys(false)) {
/* 33 */         templist.add(new DamageEvent(
/* 34 */               DataHandler.getInstance().getFileConfig()
/* 35 */               .getConfigurationSection("players." + id + ".logs." + mapid).getValues(true), 
/* 36 */               UUID.fromString(mapid)));
/*    */       }
/*    */       
/* 39 */       this.damagestorage.put(UUID.fromString(player), templist);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasLogs(UUID id) {
/* 46 */     return this.damagestorage.containsKey(id);
/*    */   }
/*    */   
/*    */   public void logEvent(Player player, DamageEvent event) {
/* 50 */     if (!hasLogs(player.getUniqueId())) {
/* 51 */       this.damagestorage.put(player.getUniqueId(), new ArrayList<>());
/*    */     }
/* 53 */     List<DamageEvent> set = this.damagestorage.get(player.getUniqueId());
/* 54 */     set.add(event);
/* 55 */     this.damagestorage.put(player.getUniqueId(), set);
/* 56 */     save();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<DamageEvent> getDamageEvents(UUID id) {
/* 61 */     return this.damagestorage.get(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 66 */     for (UUID id : this.damagestorage.keySet()) {
/*    */       
/* 68 */       for (DamageEvent event : this.damagestorage.get(id))
/*    */       {
/*    */         
/* 71 */         DataHandler.getInstance().getFileConfig()
/* 72 */           .set("players." + id.toString() + ".logs." + event.getUniqueID().toString(), event.serialize());
/*    */       }
/* 74 */       DataHandler.getInstance().save();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\combatlogger\managers\DamageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */