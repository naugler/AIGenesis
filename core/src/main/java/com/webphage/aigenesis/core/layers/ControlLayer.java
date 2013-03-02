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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.webphage.aigenesis.core.theater.AppActorEvents;
import com.webphage.aigenesis.core.theater.Director;
import com.webphage.aigenesis.core.theater.Layer;

/**
 * Capture back/ESC key layer.
 * 
 */
public class ControlLayer extends Layer
{
	/**
	 * Create layer.
	 * 
	 * @param stage
	 */
	public ControlLayer()
	{
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * Catch escape key.
	 * 
	 */
	@Override
	public boolean keyDown(int keycode)
	{
		boolean handled = false;

		if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
		{
			Director.instance().sendEvent(AppActorEvents.EVENT_TRANSITION_TO_MENU_SCENE, this);

			handled = true;
		}

		return handled;
	}

}
