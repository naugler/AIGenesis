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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.webphage.aigenesis.core.AppStats;
import com.webphage.aigenesis.core.theater.Layer;

/**
 * Game statistics view.
 * 
 * Note: I have reused the fps StringBuilder technique from Robert Greens website:
 * http://www.rbgrn.net/content/290-light-racer-20-days-32-33-getting-great -game-performance
 * 
 */
public class StatsLayer extends Layer
{
	private static final String FONT_FILE_SMALL = "data/font.fnt";
	private static final String FONT_IMAGE_SMALL = "data/font.png";
	private static final String FONT_FILE_LARGE = "data/digital-7_32.fnt";
	private static final String FONT_IMAGE_LARGE = "data/digital-7_32.png";

	private static final String TEXT_FPS = "fps:";
	private static final String TEXT_SCORE = "Score:";

	private BitmapFont smallFont;
	private BitmapFont largeFont;

	static final char chars[] = new char[100];
	static final StringBuilder textStringBuilder = new StringBuilder(100);

	private AppStats stats;

	public StatsLayer(float width, float height)
	{
		setWidth(width);
		setHeight(height);

		stats = AppStats.instance();

		buildElements();
	}

	/**
	 * Build view elements.
	 * 
	 */
	private void buildElements()
	{
		smallFont = new BitmapFont(Gdx.files.internal(FONT_FILE_SMALL), Gdx.files.internal(FONT_IMAGE_SMALL), false);
		largeFont = new BitmapFont(Gdx.files.internal(FONT_FILE_LARGE), Gdx.files.internal(FONT_IMAGE_LARGE), false);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		// Frames Per Second.
		textStringBuilder.setLength(0);
		textStringBuilder.append(TEXT_FPS);
		textStringBuilder.append(Gdx.graphics.getFramesPerSecond());
		textStringBuilder.getChars(0, textStringBuilder.length(), chars, 0);

		smallFont.draw(batch, textStringBuilder, 10, 15);

		textStringBuilder.setLength(0);
		textStringBuilder.append(TEXT_SCORE);
		textStringBuilder.append(stats.getScore());
		textStringBuilder.getChars(0, textStringBuilder.length(), chars, 0);

		largeFont.draw(batch, textStringBuilder, 10, getHeight() - 20);
	}
}
