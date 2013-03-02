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

//import com.netthreads.libgdx.app.layer.AboutLayer;

import com.webphage.aigenesis.core.layers.ControlLayer;
import com.webphage.aigenesis.core.theater.Layer;
import com.webphage.aigenesis.core.theater.Scene;

/**
 * About scene.
 * 
 */
public class AboutScene extends Scene
{
	private Layer controlLayer;
//	private Layer aboutLayer;

	public AboutScene()
	{
		// ---------------------------------------------------------------
		// Control layer
		// ---------------------------------------------------------------
		controlLayer = new ControlLayer();
		getInputMultiplexer().addProcessor(controlLayer);
		addLayer(controlLayer);
//		
//		// ---------------------------------------------------------------
//		// About layer
//		// ---------------------------------------------------------------
//		aboutLayer = new AboutLayer(this);
//		addLayer(aboutLayer);
	}
}
