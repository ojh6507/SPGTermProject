package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final MainScene scene;
    private final WarriorHead warriorHead;

    public CollisionChecker(MainScene scene, WarriorHead warrier) {
        this.scene = scene;
        this.warriorHead =warrier;
    }

    @Override
    public void update(float elapsedSeconds) {
        ArrayList<IGameObject> obstacles = scene.objectsAt(MainScene.Layer.obstacle);
        for(int o = obstacles.size() - 1; o>=0; o--){
            Obstacle obs = (Obstacle) obstacles.get(o);
            if(CollisionHelper.collides(obs, this.warriorHead)){
                RectF obstacleRect = obs.getCollisionRect();
                RectF headRect = this.warriorHead.getCollisionRect();

                updateDirectionAfterCollision(obstacleRect, headRect);
                break;
            }

        }
        ArrayList<IGameObject> enemies = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = enemies.size() - 1; e >= 0; e--) {
//            Enemy enemy = (Enemy)enemies.get(e);
//            ArrayList<IGameObject> bullets = scene.objectsAt(MainScene.Layer.bullet);
//            for (int b = bullets.size() - 1; b >= 0; b--) {
//                Bullet bullet = (Bullet)bullets.get(b);
//                if (CollisionHelper.collides(enemy, bullet)) {
//                    Log.d(TAG, "Collision !!");
//                    scene.remove(MainScene.Layer.bullet, bullet);
//                    scene.remove(MainScene.Layer.enemy, enemy);
////                    removed = true;
//                    scene.addScore(enemy.getScore());
//                    break;
//                }
//            }
        }
    }
    public void updateDirectionAfterCollision(RectF obstacleRect, RectF headRect) {
        float incidentX = this.warriorHead.GetDx();
        float incidentY = this.warriorHead.GetDy();

        // 충돌 방향에 따른 법선 벡터 설정
        float normalX = 0;
        float normalY = 0;
        // 가정: 충돌 처리는 가장 큰 편차를 가진 축에 따라 이루어집니다.
        float deltaX = (headRect.left + headRect.width() / 2) - (obstacleRect.left + obstacleRect.width() / 2);
        float deltaY = (headRect.top + headRect.height() / 2) - (obstacleRect.top + obstacleRect.height() / 2);

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // 수평 충돌
            normalX = Math.signum(deltaX);
        } else {
            // 수직 충돌
            normalY = Math.signum(deltaY);
        }

        // 내적 계산
        float dotProduct = incidentX * normalX + incidentY * normalY;

        // 반사 벡터 계산
        float reflectedX = incidentX - 2 * dotProduct * normalX;
        float reflectedY = incidentY - 2 * dotProduct * normalY;

        // 반사된 벡터로 방향 갱신
        this.warriorHead.UpdateDxValue(reflectedX);
        this.warriorHead.UpdateDyValue(reflectedY);
        // 충돌 후 위치 조정을 계산합니다.
        float pushBackDistance = 0.05f; // 적절한 값을 실험을 통해 설정
        if (normalX != 0) {
            this.warriorHead.SetX(pushBackDistance * normalX);
        }
        if (normalY != 0) {
            this.warriorHead.SetY(pushBackDistance * normalY);
        }

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
