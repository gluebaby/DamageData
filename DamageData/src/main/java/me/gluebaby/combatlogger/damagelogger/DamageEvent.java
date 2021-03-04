package me.gluebaby.combatlogger.damagelogger;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements ConfigurationSerializable {
	private EntityDamageEvent.DamageCause cause;
	private long time;
	private EntityType damager;
	private String killername;
	private double damage;
	private Location location;
	private UUID uuid;

	public DamageEvent(EntityDamageEvent.DamageCause cause, long time, Location location, double damage) {
		this.cause = cause;
		this.time = time;
		this.location = location;
		this.damage = damage;
		this.damager = null;
		this.killername = "";
		this.uuid = UUID.randomUUID();
	}

	public DamageEvent(EntityDamageEvent.DamageCause cause, long time, Location location, double damage,
			EntityType damager, String name) {
		this.cause = cause;
		this.time = time;
		this.location = location;
		this.damage = damage;
		this.damager = damager;
		this.killername = name;
		this.uuid = UUID.randomUUID();
	}

	public DamageEvent(Map<String, Object> serialized, UUID id) {
		this.uuid = id;
		this.cause = EntityDamageEvent.DamageCause.valueOf((String) serialized.get("cause"));
		this.time = ((Long) serialized.get("time")).longValue();
		this.location = (Location) serialized.get("location");
		this.damage = ((Double) serialized.get("damage")).doubleValue();
		this.killername = (String) serialized.get("name");
		if (serialized.containsKey("damager")) {
			this.damager = EntityType.valueOf((String) serialized.get("damager"));
		}
	}

	public String getDamagerName() {
		return this.killername;
	}

	public Long getTimeInMillis() {
		return Long.valueOf(this.time);
	}

	public Double getDamage() {
		return Double.valueOf(this.damage);
	}

	public EntityDamageEvent.DamageCause getDamageCause() {
		return this.cause;
	}

	public Location getLocation() {
		return this.location;
	}

	public EntityType getDamagerType() {
		return this.damager;
	}

	public String getFormattedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
		Date result = new Date(this.time);
		return sdf.format(result);
	}

	public UUID getUniqueID() {
		return this.uuid;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("cause", this.cause.toString());
		map.put("time", Long.valueOf(this.time));
		map.put("location", this.location);
		map.put("damage", Double.valueOf(this.damage));
		map.put("name", this.killername);
		if (this.damager != null) {
			map.put("damager", this.damager.toString());
		}
		return map;
	}

	public Material getGuiItem(EntityDamageEvent.DamageCause cause) {
		switch (cause) {
		case DROWNING:
			return Material.WATER_BUCKET;
		case FIRE:
			return Material.FLINT_AND_STEEL;
		case FIRE_TICK:
			return Material.FLINT_AND_STEEL;
		case LAVA:
			return Material.LAVA_BUCKET;
		case ENTITY_EXPLOSION:
			return Material.TNT;
		case BLOCK_EXPLOSION:
			return Material.TNT;
		case CONTACT:
			return Material.CACTUS;
		case PROJECTILE:
			return Material.ARROW;
		case FALL:
			return Material.LEATHER_BOOTS;
		case STARVATION:
			return Material.ROTTEN_FLESH;
		case HOT_FLOOR:
			return Material.MAGMA_BLOCK;
		case SUFFOCATION:
			return Material.SAND;
		case FALLING_BLOCK:
			return Material.ANVIL;
		case FLY_INTO_WALL:
			return Material.ELYTRA;
		case MAGIC:
			return Material.POTION;
		case POISON:
			return Material.SPIDER_EYE;
		case THORNS:
			return Material.SWEET_BERRIES;
		case SUICIDE:
			return Material.BARRIER;
		case VOID:
			return Material.BEDROCK;
		case LIGHTNING:
			return Material.GLASS;
		case WITHER:
			return Material.WITHER_ROSE;
		case ENTITY_ATTACK:
			if (this.damager == EntityType.PLAYER) {
				return Material.DIAMOND_SWORD;
			}
			return Material.ZOMBIE_HEAD;

		case ENTITY_SWEEP_ATTACK:
			if (this.damager == EntityType.PLAYER) {
				return Material.DIAMOND_SWORD;
			}
			return Material.ZOMBIE_HEAD;
		default:
			return Material.NETHER_WART;
		}

	}
}
