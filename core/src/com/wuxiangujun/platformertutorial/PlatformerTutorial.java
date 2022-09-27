package com.wuxiangujun.platformertutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlatformerTutorial extends ApplicationAdapter {
	private OrthographicCamera camera;

	private AbstractGameMap gameMap;
	private SpriteBatch batch;

	float deltaX,deltaY;

	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		gameMap = new CustomGameMap();

		camera = new OrthographicCamera();
		// 将此相机设置为正交投影，以 (viewportWidth/2, viewportHeight/2) 为中心，y 轴指向上方或下方。
		camera.setToOrtho(false,width ,height);
		camera.update();
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.WHITE);
		camera.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera,batch);
		// if (Gdx.input.isTouched()) {
		//     camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
		//     camera.update();
		// }
		// if (Gdx.input.justTouched()){
		//     // 获取屏幕上点击的位置 unproject将屏幕点击坐标转换为游戏世界坐标
		//     Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
		//     TileType type = gameMap.getTileTypeByLocation(0,pos.x, pos.y);
		//     System.out.println("pos = " + pos.x+" "+pos.y);
		//     if (type!=null){
		//         System.out.println("You clicked on tile with id  = " + type.getId()+" "+type.getName()+" "+type.isCollidable());
		//     }
		// }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
	}
}
