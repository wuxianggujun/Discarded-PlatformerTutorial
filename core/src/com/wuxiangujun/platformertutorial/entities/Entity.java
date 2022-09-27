package com.wuxiangujun.platformertutorial.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.wuxiangujun.platformertutorial.AbstractGameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    // y轴上的速度
    protected float velocityY = 0;
    protected AbstractGameMap gameMap;
    // 判断实体是否还在地面
    protected boolean grounded = false;


    public void create(EntitySnapshot entitySnapshot, EntityType entityType, AbstractGameMap gameMap) {
        this.pos = new Vector2(entitySnapshot.getX(), entitySnapshot.getY());
        this.type = entityType;
        this.gameMap = gameMap;
    }

    public void update(float delta, float gravity) {
        float newY = pos.y;

        this.velocityY += gravity * delta * getWeight();
        newY += this.velocityY * delta;

        if (gameMap.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())) {

            if (velocityY < 0) {
                this.pos.y = (float) Math.floor(pos.y);
                grounded = true;
            }
            this.velocityY = 0;
        } else {
            this.pos.y = newY;
            grounded = false;
        }
    }

    public abstract void render(SpriteBatch batch);

    protected void moveX(float amount) {
        float newX = this.pos.x + amount;
        if (!gameMap.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight()))
            this.pos.x = newX;
    }

    public EntitySnapshot getSaveSnapshot() {
        return new EntitySnapshot(type.getId(), pos.x, pos.y);
    }

    public EntityType getType() {
        return type;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getWidth() {
        return type.getWidth();
    }

    public int getHeight() {
        return type.getHeight();
    }

    public float getWeight() {
        return type.getWeight();
    }

}
