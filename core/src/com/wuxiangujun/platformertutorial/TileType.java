package com.wuxiangujun.platformertutorial;

import java.util.HashMap;

/**
 * 添加所用到的瓦片类型
 */
public enum TileType {
    GRASS(1, true, "Grass"), 
    DIRT(2, true, "Dirt"),
    SKY(3, false, "Sky"),
    LAVA(4, true, "Lava"), 
    CLOUD(5, true, "Cloud"),
    STONE(6, true, "Stone");
    
    public static final int TILE_SIZE = 16;

    private int id;
    private boolean collidable;

    private String name;
    private float damage;

    private static HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType type : TileType.values()) {
            tileMap.put(type.getId(), type);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    TileType(int id, boolean collidable, String name) {
        this(id, collidable, name, 0);
    }

    TileType(int id, boolean collidable, String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

}
