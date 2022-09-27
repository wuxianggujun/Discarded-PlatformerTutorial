package com.wuxiangujun.platformertutorial.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.wuxiangujun.platformertutorial.AbstractGameMap;

import java.util.ArrayList;

public class EntityLoader {
    private static Json json = new Json();

    public static ArrayList<Entity> loadEntities(String id, AbstractGameMap gameMap, ArrayList<Entity> currentEntities) {
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/" + id + ".entities");
        if (file.exists()) {
            EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
            ArrayList<Entity> entities = new ArrayList<Entity>();
            for (EntitySnapshot snapshot : snapshots) {
                entities.add(EntityType.createEntityUsingSnapshot(snapshot, gameMap));
            }
            return entities;
        } else {
            saveEntities(id, currentEntities);
            return currentEntities;
        }
    }

    public static void saveEntities(String id, ArrayList<Entity> entities) {
        ArrayList<EntitySnapshot> snapshots = new ArrayList<EntitySnapshot>();
        for (Entity entity : entities) {
            snapshots.add(entity.getSaveSnapshot());
        }
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/" + id + ".entities");
        file.writeString(json.prettyPrint(snapshots), false);
    }

}
