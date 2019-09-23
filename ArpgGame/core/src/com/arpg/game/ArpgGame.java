package com.arpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArpgGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private Hero hero;
	private Monster monster;

	// 1. Персонаж при старте должен иметь случайные координаты
	// 2. Ни монстр, ни персонаж не должны случайно при старте
	// попадать в стену
	// 3. Какой бы базовый класс для монстра и персонажа вы бы сделали?
	// * Необязательно. Попробуйте чуть-чуть полистать материал про
	// вектора

	public Map getMap() {
		return map;
	}

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.map = new Map();
		do
			this.hero = new Hero(this);
		while (!map.isCellPassable(hero.getPosition()));
		do
			this.monster = new Monster(this);
		while(!map.isCellPassable(monster.getPosition()));
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.4f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		hero.render(batch);
		monster.render(batch);
		batch.end();
	}

	public void update(float dt) {
		hero.update(dt);
		monster.update(dt);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
