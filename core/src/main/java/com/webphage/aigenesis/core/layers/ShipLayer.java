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
package com.webphage.aigenesis.core.layers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.webphage.aigenesis.core.drone.Drone;
import com.webphage.aigenesis.core.drone.ShipSprite;
import com.webphage.aigenesis.core.theater.*;

/**
 * Scene layer.
 *
 */
public class ShipLayer extends Layer implements ActorEventObserver {

    private static final float CONTROL_INTERVAL = 0.75f; // Pulse rate.
    public static final float TEXTURE_FRAME_DURATION = 0.5f;
//    private ShipSprite spriteShip;
    private Drone drone;
    private float offsetX;
    private float offsetY;
    // Director of the action.
    private Director director;

    /**
     * Create layer.
     *
     * @param stage
     */
    public ShipLayer(float width, float height) {
        setWidth(width);
        setHeight(height);

        this.director = Director.instance();

        buildElements();

        buildActions();
    }

    /**
     * Called when layer is part of visible view but not yet displayed.
     *
     */
    @Override
    public void enter() {
        // Add this as an event observer.
        director.registerEventHandler(this);
    }

    /**
     * Called when layer is no longer part of visible view.
     *
     */
    @Override
    public void exit() {
        // Remove this as an event observer.
        director.deregisterEventHandler(this);
    }

    /**
     * Build view elements.
     *
     */
    private void buildElements() {
        TextureCache textureCache = TextureCache.instance();

        TextureDefinition definition = textureCache.getDefinition(AppActorTextures.TEXTURE_SHIPS);
        TextureRegion textureRegion = textureCache.getTexture(definition);

        // ---------------------------------------------------------------
        // Space ship
        // ---------------------------------------------------------------
//        spriteShip = new ShipSprite(textureRegion, definition.getRows(), definition.getCols(), TEXTURE_FRAME_DURATION);
//        spriteShip.setX(getWidth() / 2 - spriteShip.getWidth() / 2);
//        spriteShip.setY(spriteShip.getHeight());

//        offsetX = spriteShip.getWidth() / 2;
//        offsetY = gameProperties.getTouchOffset();

//        addActor(spriteShip);
        
        drone = new Drone(textureRegion, definition.getRows(), definition.getCols(), TEXTURE_FRAME_DURATION);
        drone.setX(getWidth() / 2 - drone.getWidth() / 2);
        drone.setY(drone.getHeight());
        addActor(drone);
    }

    /**
     * Create timer action to start layer elements.
     *
     */
    private void buildActions() {
//        CallBackDelayAction callBackDelay = CallBackDelayAction.$(-1, CONTROL_INTERVAL, this);

//        action(callBackDelay);
    }

//    /**
//     * Every delay duration make a decision about what to do next.
//     *
//     */
//    @Override
//    public void onCallBack() {
//        // Launch an asteroid.
//        this.director.sendEvent(AppActorEvents.EVENT_START_PULSE, spriteShip);
//
//        // Play zap.
////        if (gameProperties.isAudioOn()) {
////            SoundCache.instance().get(AppActorSounds.SOUND_PULSE).play(gameProperties.getVolume());
////        }
//    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        float touchX = (x * director.getScaleFactorX()) - offsetX;
        float touchY = director.getHeight() - (y * director.getScaleFactorY()) + offsetY;

        drone.trackToXY(touchX, touchY);

        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        float touchX = (x * director.getScaleFactorX()) - offsetX;
        float touchY = director.getHeight() - (y * director.getScaleFactorY()) + offsetY;

        drone.trackToXY(touchX, touchY);

        return true;
    }

    /**
     * Handle events.
     *
     */
    @Override
    public boolean handleEvent(ActorEvent event) {
        boolean handled = false;

        switch (event.getId()) {
            case AppActorEvents.EVENT_COLLISION_ASTEROID_SHIP:
                handleCollision(event.getActor());
                handled = true;
                break;
            default:
                break;
        }

        return handled;
    }

    /**
     * Handle hitting asteroid.
     *
     * @param actor
     */
    private void handleCollision(Actor actor) {
//        drone.runCollision();
    }
}
