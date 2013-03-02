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


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Scene director.
 * 
 * This class routes actor events around observers and updates the view. It also maintains global note of view width and
 * height.
 * 
 */
public class Director implements Disposable
{
	private static final float DEFAULT_CLEAR_COLOUR_RED = 0.0f;
	private static final float DEFAULT_CLEAR_COLOUR_BLUE = 0.0f;
	private static final float DEFAULT_CLEAR_COLOUR_GREEN = 0.0f;
	private static final float DEFAULT_CLEAR_COLOUR_ALPHA = 1.0f;

	private static final boolean DEFAULT_STRETCH = true;

	private static Director instance = null;

	private ActorEventSource eventSource;

	private int width;
	private int height;
	private boolean stretch;

	private Scene scene;

	private float scaleFactorX;
	private float scaleFactorY;

	private SpriteBatch spriteBatch;

	private float clearColourR = DEFAULT_CLEAR_COLOUR_RED;
	private float clearColourB = DEFAULT_CLEAR_COLOUR_BLUE;
	private float clearColourG = DEFAULT_CLEAR_COLOUR_GREEN;
	private float clearColourA = DEFAULT_CLEAR_COLOUR_ALPHA;

	/**
	 * Access singleton instance
	 * 
	 * @return instance of class
	 */
	public synchronized static Director instance()
	{
		if (instance == null)
		{
			instance = new Director();
		}

		return instance;
	}

	/**
	 * Construct Director elements.
	 * 
	 */
	public Director()
	{
		scene = null;

		// This required Graphics context.
		spriteBatch = new SpriteBatch();

		stretch = DEFAULT_STRETCH;

		// Latch onto event source.
		eventSource = ActorEventSource.instance();

		// These are scale factors for adjusting touch events to the actual size
		// of the view-port.
		scaleFactorX = 1;
		scaleFactorY = 1;
	}

	/**
	 * Update main loop.
	 * 
	 */
	public void update()
	{
		// Update events.
		eventSource.update();

		// Update View
		Gdx.gl.glClearColor(clearColourR, clearColourB, clearColourG, clearColourA);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (scene != null)
		{
			float delta = Gdx.graphics.getDeltaTime();

			scene.act(delta);

			scene.draw();
		}
		else
		{
			Gdx.app.log("Director", "WTF! - No scene");
		}
	}

	/**
	 * Set the current scene.
	 * 
	 * @param scene
	 */
	public synchronized void setScene(Scene scene)
	{
		// If already active scene...
		if (this.scene != null)
		{
			// Exit stage left..
			this.scene.exit();
		}

		this.scene = scene;

		if (this.scene != null)
		{
			// Enter stage right..
			this.scene.enter();

			// NOTE: Route input events to the scene.
			Gdx.input.setInputProcessor(scene.getInputMultiplexer());
		}

	}

	/**
	 * Return currently running scene.
	 * 
	 * @return The current scene view.
	 */
	public synchronized Scene getScene()
	{
		return scene;
	}

	/**
	 * Return scene width.
	 * 
	 * @return The width.
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Return scene height.
	 * 
	 * @return The height.
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Set display width/height.
	 * 
	 * @param width
	 * @param height
	 */
	public void setWidthHeight(int width, int height)
	{
		this.width = width;
		this.height = height;

		if (scene != null)
		{
			scene.setViewport(width, height, stretch);
		}
	}

	/**
	 * Set display width.
	 * 
	 * @param width
	 */
	public void setWidth(int width)
	{
		this.width = width;

		if (scene != null)
		{
			scene.setViewport(width, height, stretch);
		}
	}

	/**
	 * Set display height.
	 * 
	 * @param height
	 */
	public void setHeight(int height)
	{
		this.height = height;

		if (scene != null)
		{
			scene.setViewport(width, height, stretch);
		}
	}

	/**
	 * Adjust the scale factors for touch/mouse events to match the size of the stage.
	 * 
	 * @param width
	 *            The new width.
	 * @param height
	 *            The new height.
	 */
	public void recalcScaleFactors(int width, int height)
	{
		scaleFactorX = (float) this.width / width;
		scaleFactorY = (float) this.height / height;
	}

	/**
	 * Is stretch flag set.
	 * 
	 * @return Stretch flag.
	 */
	public boolean isStretch()
	{
		return stretch;
	}

	/**
	 * Set stretch flag.
	 * 
	 * @param stretch
	 */
	public void setStretch(boolean stretch)
	{
		this.stretch = stretch;

		if (scene != null)
		{
			scene.setViewport(width, height, stretch);
		}
	}

	/**
	 * Send event to observers.
	 * 
	 * @param id
	 *            The event id.
	 * @param actor
	 *            The associated actor.
	 */
	public void sendEvent(int id, Actor actor)
	{
		eventSource.sendEvent(id, actor, ActorEvent.DEFAULT_PRIORITY);
	}

	/**
	 * Send event to observers.
	 * 
	 * @param id
	 *            The event id.
	 * @param actor
	 *            The associated actor.
	 * @param priority
	 *            Adopt a priority value. Must be > 0.
	 */
	public void sendEvent(int id, Actor actor, int priority)
	{
		eventSource.sendEvent(id, actor, priority);
	}

	/**
	 * Add event observer event handler.
	 * 
	 * DO NOT PUT THIS INTO THE CONSTRUCTOR. IT MUST GO INTO THE "ENTER" HANDLER.
	 * 
	 * @param observer
	 *            The event observer.
	 */
	public void registerEventHandler(ActorEventObserver observer)
	{
		eventSource.addObserver(observer);
	}

	/**
	 * Remove event observer event handler.
	 * 
	 * DO NOT FORGET TO PUT THIS INTO THE "EXIT" HANDLER IF YOU HAVE MATCHING "REGISTER" IN THE ENTER HANDLER.
	 * 
	 * @param observer
	 *            The event observer.
	 */
	public void deregisterEventHandler(ActorEventObserver observer)
	{
		eventSource.removeObserver(observer);
	}

	/**
	 * Clear all handlers.
	 * 
	 */
	public void clearEventHandlers()
	{
		// Clear all event subscriptions.
		eventSource.clear();
	}

	/**
	 * Return scale factor for touch/mouse events.
	 * 
	 * @return The x scale factor.
	 */
	public float getScaleFactorX()
	{
		return scaleFactorX;
	}

	/**
	 * Return scale factor for touch/mouse events.
	 * 
	 * @return The y scale factor.
	 */
	public float getScaleFactorY()
	{
		return scaleFactorY;
	}

	public SpriteBatch getSpriteBatch()
	{
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch)
	{
		this.spriteBatch = spriteBatch;
	}

	@Override
	public void dispose()
	{
		spriteBatch.dispose();
	}

	public void setEventSource(ActorEventSource eventSource)
	{
		this.eventSource = eventSource;
	}

	public void setClearColourR(float clearColourR)
	{
		this.clearColourR = clearColourR;
	}

	public void setClearColourB(float clearColourB)
	{
		this.clearColourB = clearColourB;
	}

	public void setClearColourG(float clearColourG)
	{
		this.clearColourG = clearColourG;
	}

	public void setClearColourA(float clearColourA)
	{
		this.clearColourA = clearColourA;
	}

}
