package com.wuxiangujun.platformertutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.wuxiangujun.platformertutorial.entities.Entity;
import com.wuxiangujun.platformertutorial.entities.EntityLoader;

import java.util.ArrayList;

public abstract class AbstractGameMap implements Disposable {

    protected ArrayList<Entity> entities;

    public AbstractGameMap() {
        entities = new ArrayList<Entity>();
        entities.addAll(EntityLoader.loadEntities("basic", this, entities));
    }

    public void render(Camera camera, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void update(float delta) {
        for (Entity entity : entities) {
            entity.update(delta, -9.8f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            EntityLoader.saveEntities("basic", entities);
        }
    }

    /**
     * Gets a tile by pixel position within the game world at specified layer.
     * 获取游戏世界中指定层的像素位置
     *
     * @param layer
     * @param x
     * @param y
     * @return
     */
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    /**
     * 根据坐标的位置获取图块的信息
     *
     * @param layer
     * @param col
     * @param row
     * @return
     */
    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight()) return true;
        // y
        for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
            // x
            for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
                // 游历图层
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if (type != null && type.isCollidable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    private int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLayers();

    @Override
    public void dispose() {
        EntityLoader.saveEntities("basic", entities);
    }
}
