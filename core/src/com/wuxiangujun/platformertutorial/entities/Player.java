package com.wuxiangujun.platformertutorial.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wuxiangujun.platformertutorial.AbstractGameMap;

public class Player extends Entity {
    // x轴上的速度
    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 5;

    Texture image;

    @Override
    public void create(EntitySnapshot entitySnapshot, EntityType entityType, AbstractGameMap gameMap) {
        super.create(entitySnapshot, entityType, gameMap);
        image = new Texture("image/player.png");

    }

    @Override
    public void update(float delta, float gravity) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded) {
            this.velocityY += JUMP_VELOCITY * getWeight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !grounded && this.velocityY > 0) {
            // 跳跃时按下空格，将会跳跃的更高
            this.velocityY += JUMP_VELOCITY * getWeight() * delta;
        }
        super.update(delta, gravity);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX(-SPEED * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX(SPEED * delta);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public EntitySnapshot getSaveSnapshot() {
        EntitySnapshot saveSnapshot = super.getSaveSnapshot();
        return saveSnapshot;
    }
}
