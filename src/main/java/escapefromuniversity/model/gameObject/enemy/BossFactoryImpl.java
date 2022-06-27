package escapefromuniversity.model.gameObject.enemy;

import java.util.LinkedList;
import java.util.List;

import escapefromuniversity.model.basics.Point2D;
import escapefromuniversity.model.basics.Vector2D;
import escapefromuniversity.model.gameObject.bullet.Bullet;
import escapefromuniversity.model.gameObject.bullet.BulletFactory;
import escapefromuniversity.model.gameObject.bullet.BulletFactoryImpl;
import escapefromuniversity.model.gameObject.GameObjectType;
import escapefromuniversity.model.map.Mapp;

/**
 * 
 * implementation of interface BossFactory.
 *
 */
public class BossFactoryImpl implements BossFactory {

    private final BulletFactory bulletCreate = new BulletFactoryImpl();
    private static final Vector2D VECTOR1 = new Vector2D(50, 50);
    private static final Vector2D VECTOR2 = new Vector2D(-50, -50);


    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss1(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_1_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS1, BossConstant.BOSS_1_LIFE, BossConstant.BOSS_1_SHOOT_DELAY, BossConstant.BOSS_1_DAMAGE, BossConstant.BOSS_1_EXAM, map) {

            @Override
            void shoot() {
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                final Bullet bullet = bulletCreate.createBoss1Bullet(startPosition, this.getDirection(), map);
                this.getMap().addDynamicGameObject(bullet);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss2(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_2_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS2, BossConstant.BOSS_2_LIFE, BossConstant.BOSS_2_SHOOT_DELAY, BossConstant.BOSS_2_DAMAGE, BossConstant.BOSS_2_EXAM, map) {

            @Override
            void shoot() {
                final List<Bullet> bullets = new LinkedList<>();
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                bullets.add(bulletCreate.createBoss2Bullet(this.getObjectPosition(), this.getDirection(), map));
                bullets.add(bulletCreate.createBoss2Bullet(startPosition.sum(VECTOR1), this.getDirection(), map));
                bullets.add(bulletCreate.createBoss2Bullet(startPosition.sum(VECTOR2), this.getDirection(), map));
                bullets.forEach(bul -> {
                    this.getMap().addDynamicGameObject(bul);
                });
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss3(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_3_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS3, BossConstant.BOSS_3_LIFE, BossConstant.BOSS_3_SHOOT_DELAY, BossConstant.BOSS_3_DAMAGE, BossConstant.BOSS_3_EXAM, map) {

            @Override
            void shoot() {
                final List<Bullet> bullets = new LinkedList<>();
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(0, 1), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(0, -1), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(1, 1).normal(), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(-1, 1).normal(), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(1, -1).normal(), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(-1, -1).normal(), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(1, 0), map));
                bullets.add(bulletCreate.createBoss3Bullet(startPosition, new Vector2D(1, 0), map));
                bullets.forEach(bul -> {
                    this.getMap().addDynamicGameObject(bul);
                });
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss4(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_4_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS4, BossConstant.BOSS_4_LIFE, BossConstant.BOSS_4_SHOOT_DELAY, BossConstant.BOSS_4_DAMAGE, BossConstant.BOSS_4_EXAM, map) {

            @Override
            void shoot() {
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                final Bullet bullet = bulletCreate.createBoss4Bullet(startPosition, this.getDirection(), map);
                this.getMap().addDynamicGameObject(bullet);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss5(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_5_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS5, BossConstant.BOSS_5_LIFE, BossConstant.BOSS_5_SHOOT_DELAY, BossConstant.BOSS_5_DAMAGE, BossConstant.BOSS_5_EXAM, map) {

            @Override
            void shoot() {
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                final Bullet bullet = bulletCreate.createBoss5Bullet(startPosition, this.getDirection(), map);
                this.getMap().addDynamicGameObject(bullet);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss createBoss6(final Point2D position, final Vector2D direction, final Mapp map) {
        return new AbstractBoss(BossConstant.BOSS_6_SPEED, position, BossConstant.BOSS_BOX_SIZE, direction, GameObjectType.BOSS6, BossConstant.BOSS_6_LIFE, BossConstant.BOSS_6_SHOOT_DELAY, BossConstant.BOSS_6_DAMAGE, BossConstant.BOSS_6_EXAM, map) {

            @Override
            void shoot() {
                final Point2D startPosition = new Point2D(this.getObjectPosition().getX(), this.getObjectPosition().getY() + this.getObjectHitBox().getHeight());
                final Bullet bullet = bulletCreate.createBoss6Bullet(startPosition, this.getDirection(), map);
                this.getMap().addDynamicGameObject(bullet);
            }
        };
    }

}
