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

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Scene is a Stage which has input multiplexer.
 * 
 * Scenes are composed of layers.
 * 
 */
public class Scene extends Stage implements Node
{
	private static final int DEFAULT_LAYER_CAPACITY = 10;
	
	/**
	 * Associated input multiplexer.
	 */
	private InputMultiplexer inputMultiplexer;
	
	/**
	 * Stage elements as nodes. We need this so we can call enter and exit on
	 * actors in order to manage registration and de-registration of event
	 * handlers.
	 */
	private Array<Node> nodes;

	/**
	 * Constructor no parameters.
	 * 
	 */
	public Scene()
	{
		this(Director.instance().getWidth(), Director.instance().getHeight(), Director.instance().isStretch(), Director.instance().getSpriteBatch());
	}
	
	/**
	 * Constructor where we supply our own sprite batch.
	 * 
	 * @param width
	 * @param height
	 * @param stretch
	 * @param batch
	 */
	public Scene(float width, float height, boolean stretch, SpriteBatch batch)
	{
		super(width, height, stretch, batch);
		
		inputMultiplexer = new InputMultiplexer(this);
		
		nodes = new Array<Node>(DEFAULT_LAYER_CAPACITY);
	}

	/**
	 * Get input multiplexer.
	 * 
	 * @return The input multiplexer.
	 */
	public InputMultiplexer getInputMultiplexer()
	{
		return inputMultiplexer;
	}
	
	/**
	 * Add scene layer ensuring it adopts the same size as the owning scene.
	 * 
	 * Note layer in nodes list.
	 * 
	 * @param layer
	 *            The new layer.
	 */
	public void addLayer(Layer layer)
	{
		layer.setWidth(getWidth());
		layer.setHeight(getHeight());
		
		nodes.add(layer);
		
		addActor(layer);
	}
	
	/**
	 * Remove scene layer.
	 * 
	 * @param layer
	 *            The target layer.
	 */
	public void removeLayer(Layer layer)
	{
		int index = nodes.indexOf(layer, false);
		if (index >= 0)
		{
			nodes.removeIndex(index);

                        layer.remove();
		}
	}
	
	/**
	 * Handle pre-display tasks.
	 * 
	 */
	@Override
	public void enter()
	{
		int size = nodes.size;
		for (int i = 0; i < size; i++)
		{
			nodes.get(i).enter();
		}
	}
	
	/**
	 * Handle post-display tasks.
	 * 
	 */
	@Override
	public void exit()
	{
		int size = nodes.size;
		for (int i = 0; i < size; i++)
		{
			nodes.get(i).exit();
		}
	}
	
}
