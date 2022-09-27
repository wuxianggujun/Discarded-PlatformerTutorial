package com.wuxiangujun.platformertutorial.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.wuxiangujun.platformertutorial.AbstractGameMap;

import java.util.HashMap;

public enum EntityType {

    PLAYER("Player", Player.class, 14, 32, 40);

    private String id;
    private Class loaderClass;
    private int width, height;

    private float weight;


    EntityType(String id, Class loaderClass, int width, int height, float weight) {
        this.id = id;
        this.loaderClass = loaderClass;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    private static HashMap<String, EntityType> entityTypes = new HashMap<String, EntityType>();

    static {
        for (EntityType entityType : EntityType.values()) {
            entityTypes.put(entityType.getId(), entityType);
        }
    }

    public static Entity createEntityUsingSnapshot(EntitySnapshot entitySnapshot, AbstractGameMap gameMap) {
        EntityType entityType = entityTypes.get(entitySnapshot.type);
        try {
            Entity entity = (Entity) ClassReflection.newInstance(entityType.loaderClass);
            entity.create(entitySnapshot, entityType, gameMap);
            return entity;
        } catch (ReflectionException e) {
            Gdx.app.error("Entity Loader", "Could not load entity of type " + entityType.getId());
            return null;
        }

    }


}
