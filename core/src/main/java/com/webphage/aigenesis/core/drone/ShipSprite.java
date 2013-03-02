/*
 * -----------------------------------------------------------------------
 * Copyright 2012 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.webphage.aigenesis.core.drone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

/**
 * We encapsulate our ship sprite and the way it responds to changing position.
 *
 */
public class ShipSprite extends AnimatedSprite {

    private static final String INTERPOLATION_TYPE = "circleOut";
    private static final float ROTATION_DURATION = 1.0f;
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
    public ShipSprite(TextureRegion textureRegion, int rows, int cols, float frameDuration) {
        super(textureRegion, rows, cols, frameDuration);

        // This based on interpolation example in sample tests.
        position = new Vector2(0, 0);
        targetPosition = new Vector2(position);
        nextPosition = new Vector2();

        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        position.x = x;
        targetPosition.set(x, getY());
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        position.y = y;
        targetPosition.set(getX(), y);
    }

    public void trackToXY(float x, float y) {
        Vector2 current = recalcPosition();
        position.set(current);
        targetPosition.set(x, y);
        timer = 0;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        timer += Gdx.graphics.getDeltaTime();
        Vector2 current = recalcPosition();

        setX(current.x);
        setY(current.y);
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
        return nextPosition;
    }

    /**
     * Return our movement interpolation type.
     *
     * @return The Interpolation type object.
     */
    private Interpolation getInterpolation() {
        try {
            return (Interpolation) Interpolation.class.getField(INTERPOLATION_TYPE).get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Handle ship collision with something.
     *
     */
    public void runCollision() {

        // ---------------------------------------------------------------
        // Clear existing actions.
        // ---------------------------------------------------------------
        clearActions();

        // ---------------------------------------------------------------
        // Rotate
        // ---------------------------------------------------------------
        float durationRotate = ROTATION_DURATION;
        setRotation(0);
        RotateByAction rotateBy = new RotateByAction();
        rotateBy.setAmount(360.0f);
        rotateBy.setDuration(durationRotate);
        rotateBy.setActor(this);
        this.addAction(rotateBy);
    }
}
