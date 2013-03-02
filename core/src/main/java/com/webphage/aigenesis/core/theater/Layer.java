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
package com.webphage.aigenesis.core.theater;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Represents a visual layer which can receive input.
 *
 * Layers are a group of actors or other groups.
 *
 */
public class Layer extends Group implements InputProcessor, Node {

    @Override
    public void enter() {
        for (Actor actor : getChildren()) {
            if (actor instanceof Layer) {
                Layer layer = (Layer) actor;
                layer.enter();
            }
        }
    }

    @Override
    public void exit() {
        for (Actor actor : getChildren()) {
            if (actor instanceof Layer) {
                Layer layer = (Layer) actor;
                layer.exit();
            }
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).touchDown(x, y, pointer, button);
            }
        }

        return handled;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).touchUp(x, y, pointer, button);
            }
        }

        return handled;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).touchDragged(x, y, pointer);
            }
        }

        return handled;
    }
    
    

    @Override
    public boolean mouseMoved(int x, int y) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).mouseMoved(x, y);
            }
        }

        return handled;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).keyDown(keycode);
            }
        }

        return handled;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).keyUp(keycode);
            }
        }

        return handled;
    }

    @Override
    public boolean keyTyped(char character) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).keyTyped(character);
            }
        }

        return handled;
    }

    @Override
    public boolean scrolled(int amount) {
        boolean handled = false;
        for (Actor a : getChildren()) {
            if (a instanceof Layer) {
                handled = ((Layer)a).scrolled(amount);
            }
        }

        return handled;
    }
}
