/*    */ package me.gluebaby.combatlogger.handlers;
/*    */ 
/*    */ import me.gluebaby.combatlogger.damagelogger.DamageEvent;
/*    */ import me.gluebaby.combatlogger.managers.DamageManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ public class DamageCompat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onDamage(EntityDamageEvent event) {
/* 21 */     if (event.getEntity() instanceof Player) {
/* 22 */       Player p = (Player)event.getEntity();
/* 23 */       EntityDamageEvent.DamageCause cause = event.getCause();
/* 24 */       double damage = event.getDamage();
/* 25 */       Location loc = p.getLocation();
/* 26 */       if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || cause == EntityDamageEvent.DamageCause.PROJECTILE || cause == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
/*    */         return;
/*    */       }
/*    */ 
/*    */       
/* 31 */       DamageManager.getInstance().logEvent(p, new DamageEvent(cause, 
/* 32 */             System.currentTimeMillis(), loc, damage));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityDamage(EntityDamageByEntityEvent event) {
/* 40 */     if (event.getEntity() instanceof Player) {
/* 41 */       Player p = (Player)event.getEntity();
/* 42 */       EntityDamageEvent.DamageCause cause = event.getCause();
/* 43 */       double damage = event.getDamage();
/* 44 */       Location loc = p.getLocation();
/*    */       
/* 46 */       if (event.getDamager() != null) {
/* 47 */         Entity attacker = event.getDamager();
/* 48 */         String name = attacker.getName();
/* 49 */         EntityType attacktype = attacker.getType();
/* 50 */         if (attacker instanceof Projectile) {
/* 51 */           Projectile source = (Projectile)attacker;
/* 52 */           if (source.getShooter() instanceof Player) {
/* 53 */             Player pshoot = (Player)source.getShooter();
/* 54 */             name = pshoot.getName();
/* 55 */             attacktype = EntityType.PLAYER;
/* 56 */           } else if (source.getShooter() instanceof Entity) {
/* 57 */             Entity eshoot = (Entity)source.getShooter();
/* 58 */             name = eshoot.getName();
/* 59 */             attacktype = eshoot.getType();
/*    */           } else {
/* 61 */             name = "";
/*    */           } 
/*    */         } 
/*    */         
/* 65 */         DamageManager.getInstance().logEvent(p, new DamageEvent(cause, 
/* 66 */               System.currentTimeMillis(), loc, damage, attacktype, name));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guthm\Downloads\CombatData-0.0.1-SNAPSHOT.jar!\me\gluebaby\combatlogger\handlers\DamageCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */