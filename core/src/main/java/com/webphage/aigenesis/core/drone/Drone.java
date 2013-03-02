/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webphage.aigenesis.core.drone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 *
 * @author naugler
 */
public class Drone extends AnimatedSprite {
    
    private static final String INTERPOLATION_TYPE = "circleOut";
    private Vector2 position;
    private Vector2 targetPosition;
    private Vector2 nextPosition;
    private float timer;
    
    /**
     * Ship sprite.
     *
     * @param textureRegion The texture animation.
     * @param rows The rows in animation.
     * @param cols The columns in animation.
     * @param frameDuration The ship animation frame duration.
     */
    public Drone(TextureRegion textureRegion, int rows, int cols, float frameDuration) {
        super(textureRegion, rows, cols, frameDuration);
        
        // This based on interpolation example in sample tests.
        position = new Vector2(0, 0);
        targetPosition = new Vector2(position);
        nextPosition = new Vector2();

        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        timer += Gdx.graphics.getDeltaTime();
        Vector2 current = recalcPosition();

        setX(current.x);
        setY(current.y);
        if (nextPosition.epsilonEquals(targetPosition, 0.01f)) {
            Random r = new Random();
            int seed = r.nextInt();
            int randomXDelta = seed%100;
            seed = r.nextInt();
            int randomYDelta = seed%100;
            Gdx.app.log("howdy", "tracking to "+(targetPosition.x+randomXDelta)+","+(targetPosition.y+randomYDelta));
            trackToXY(Math.abs(targetPosition.x+randomXDelta), Math.abs(targetPosition.y+randomYDelta));
//            position = targetPosition;
//            targetPosition.set(Math.abs(targetPosition.x+randomXDelta), Math.abs(targetPosition.y+randomYDelta));
//            timer = 0;
        }
    }
    
    public void trackToXY(float x, float y) {
        Vector2 current = recalcPosition();
        position.set(current);
        targetPosition.set(x, y);
        timer = 0;
    }
    
    /**
     * Recalculate our new position based on movement type.
     *
     * @return The new position.
     */
    private Vector2 recalcPosition() {
        nextPosition.set(targetPosition);
        nextPosition.sub(position);
        nextPosition.mul(getInterpolation().apply(Math.min(1, timer / 1f)));
        nextPosition.add(position);
//        Gdx.app.log("targetPosition", targetPosition.toString());
//        Gdx.app.log("position", position.toString());
//        Gdx.app.log("nextPosition", nextPosition.toString());
        
        return nextPosition;
    }
    
    /**
     * Return our movement interpolation type.
     *
     * @return The Interpolation type object.
     */
    private Interpolation getInterpolation() {
        try {
            return Interpolation.circleOut;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
