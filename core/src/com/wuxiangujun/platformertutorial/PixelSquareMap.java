package com.wuxiangujun.platformertutorial;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class PixelSquareMap extends AbstractGameMap {
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;

    public PixelSquareMap() {
        // 加载地图
        tiledMap = new TmxMapLoader().load("map/RPGtmx.tmx");
        // 初始化平铺地图渲染器
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

    }


    @Override
    public void render(Camera camera, SpriteBatch batch) {
        renderer.setView((OrthographicCamera) camera);
        renderer.render();
        
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        super.render(camera,batch);
        batch.end();
    }

    @Override
    public void update(float delta) {
super.update(delta);
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(layer);
        TiledMapTileLayer.Cell cell = tileLayer.getCell(col, row);
        if (cell != null){
            System.out.println("cell = " + cell.toString());
            TiledMapTile tile = cell.getTile();
            if (tile != null){
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }
        return null;
    }

    @Override
    public int getWidth() {
        return  ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    @Override
    public void dispose() {
        super.dispose();
        tiledMap.dispose();
        renderer.dispose();
    }
}
