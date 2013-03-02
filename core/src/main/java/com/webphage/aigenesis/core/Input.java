/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webphage.aigenesis.core;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author naugler
 */
public class Input implements InputProcessor {
    
    private final List<InputListener> listeners = new CopyOnWriteArrayList<InputListener>();
    
    public void addListener(InputListener l) {
        listeners.add(l);
    }
    
    public void removeListener(InputListener l) {
        listeners.remove(l);
    }

    @Override
    public boolean keyDown(int keycode) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().keyDown(null, keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().keyUp(null, keycode);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            InputListener l = it.next();
            l.keyTyped(null, character);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().touchDown(null, screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().touchUp(null, screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().touchDragged(null, screenX, screenY, pointer);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().mouseMoved(null, screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        ListIterator<InputListener> it = listeners.listIterator();
        while (it.hasNext()) {
            it.next().scrolled(null, amount, 0, 0);
        }
        return false;
    }
    
}
