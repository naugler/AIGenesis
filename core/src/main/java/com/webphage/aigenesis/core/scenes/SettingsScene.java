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

//import com.netthreads.libgdx.app.layer.SettingsLayer;

import com.webphage.aigenesis.core.layers.SettingsLayer;
import com.webphage.aigenesis.core.theater.Layer;
import com.webphage.aigenesis.core.theater.Scene;


/**
 * Settings scene.
 * 
 */
public class SettingsScene extends Scene
{
	private Layer  group;

	public SettingsScene()
	{
            group = new SettingsLayer(this);

            getInputMultiplexer().addProcessor(group);

            addLayer(group);
	}
}
