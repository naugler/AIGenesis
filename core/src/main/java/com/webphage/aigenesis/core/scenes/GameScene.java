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

package com.webphage.aigenesis.core.scenes;

//import com.netthreads.libgdx.app.layer.AsteroidLayer;
//import com.netthreads.libgdx.app.layer.ExplosionLayer;
//import com.netthreads.libgdx.app.layer.PulseLayer;
//import com.netthreads.libgdx.app.layer.ShipLayer;
import com.webphage.aigenesis.core.layers.ControlLayer;
import com.webphage.aigenesis.core.layers.ShipLayer;
import com.webphage.aigenesis.core.layers.StarsLayer;
import com.webphage.aigenesis.core.layers.StatsLayer;
import com.webphage.aigenesis.core.theater.Layer;
import com.webphage.aigenesis.core.theater.Scene;
//import com.netthreads.libgdx.app.layer.StatsLayer;

/**
 * Game scene is composed of game element layers.
 */
public class GameScene extends Scene
{
	private Layer controlLayer;
	private Layer starsLayer;
	private Layer shipLayer;
//	private Layer pulseLayer;
//	private Layer asteroidLayer;
//	private Layer explosionLayer;
	private Layer statsLayer;

	/**
	 * Main game scene.
	 * 
	 */
	public GameScene()
	{
		// ---------------------------------------------------------------
		// Control layer
		// ---------------------------------------------------------------
		controlLayer = new ControlLayer();

		// Route input to layer.
		getInputMultiplexer().addProcessor(controlLayer);

		addLayer(controlLayer);

		// ---------------------------------------------------------------
		// Stars layer
		// ---------------------------------------------------------------
		starsLayer = new StarsLayer(getWidth(), getHeight());

		addLayer(starsLayer);

//		// ---------------------------------------------------------------
//		// Pulse layer.
//		// ---------------------------------------------------------------
//		pulseLayer = new PulseLayer(this.width, this.height);
//
//		addLayer(pulseLayer);
//
//		// ---------------------------------------------------------------
//		// Asteroid Layer
//		// ---------------------------------------------------------------
//		asteroidLayer = new AsteroidLayer(this.width, this.height);
//
//		addLayer(asteroidLayer);
//
//		// ---------------------------------------------------------------
//		// Explosion Layer
//		// ---------------------------------------------------------------
//		explosionLayer = new ExplosionLayer(this.width, this.height);
//
//		addLayer(explosionLayer);
//
//		// ---------------------------------------------------------------
//		// Ship layer
//		// ---------------------------------------------------------------
		shipLayer = new ShipLayer(getWidth(), getHeight());

		// Route input to layer.
		getInputMultiplexer().addProcessor(shipLayer);

		addLayer(shipLayer);
//
//		// ---------------------------------------------------------------
//		// Statistics layer
//		// ---------------------------------------------------------------
		statsLayer = new StatsLayer(getWidth(), getHeight());

		addLayer(statsLayer);
	}

}
