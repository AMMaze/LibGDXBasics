package com.arpg.game;

import com.arpg.game.utils.ObjectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MonsterController extends ObjectPool<Monster> {
    private GameScreen gameScreen;

    public MonsterController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setup() {
        Monster monster = getActiveElement();
        monster.setup();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Monster monster = activeList.get(i);
            monster.render(batch);
        }
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            Monster monster = activeList.get(i);
            monster.update(dt);
        }
        checkPool();
    }

    @Override
    protected Monster newObject() {
        return new Monster(gameScreen);
    }

    public Monster getNearestMonster(Vector2 pos) {
        Monster closest = null;
        float dist = Float.MAX_VALUE;
        for (int i = 0; i < activeList.size(); i++) {
            Monster monster = activeList.get(i);
            float new_dist = pos.dst2(monster.getPosition());
            if (dist > new_dist) {
                closest = monster;
                dist = new_dist;
            }
        }
        return closest;
    }
}
