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
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.webphage.aigenesis.core.theater.*;

/**
 * Scene layer.
 *
 */
public class SettingsLayer extends Layer {

    private TextureRegion background;
    private Skin skin;

    public SettingsLayer(Stage stage) {
        setWidth(stage.getWidth());
        setHeight(stage.getHeight());

        Gdx.input.setCatchBackKey(true);

        loadTextures();

        buildElements();

    }

    /**
     * Load view textures.
     *
     */
    private void loadTextures() {
        TextureCache textureCache = TextureCache.instance();

        TextureDefinition definition = textureCache.getDefinition(AppActorTextures.TEXTURE_MENU_BACKGROUND);
        background = textureCache.getTexture(definition);

//		skin = new Skin(Gdx.files.internal("data/uiskin32.json"), Gdx.files.internal("data/uiskin32.png"));
    }

    /**
     * Build view elements.
     *
     */
    private void buildElements() {
        
        Preferences preferences = Gdx.app.getPreferences("pref");

        // ---------------------------------------------------------------
        // Background.
        // ---------------------------------------------------------------
        Image image = new Image(background);
        image.setWidth(getWidth());
        image.setHeight(getHeight());

        addActor(image);

        // ---------------------------------------------------------------
        // Table
        // ---------------------------------------------------------------
        BitmapFont font = new BitmapFont();
        LabelStyle labelStyle = new LabelStyle(font, Color.YELLOW);
        final Label titleLabel = new Label("Settings", labelStyle);

        CheckBoxStyle checkboxStyle = new CheckBoxStyle();
        checkboxStyle.font = font;
        final CheckBox checkBox = new CheckBox("", checkboxStyle);
        checkBox.setChecked(preferences.getBoolean("audioOn", true));

        final Label soundLabel = new Label("Sound", labelStyle);

//        SliderStyle sliderStyle = new SliderStyle();
//        sliderStyle.knob = 
//        final Slider slider = new Slider(0, 10, 1, false, sliderStyle);
//        slider.setValue(preferences.getInteger("volume", 10));
        final Label volumeLabel = new Label("Volume", labelStyle);

        final Table table = new Table();

        table.size((int) getWidth(), (int) getHeight());

        table.add(titleLabel).expandX();
        table.row();
        table.add(soundLabel).expandY();
        checkBox.size(20, 20);
        table.add(checkBox);
        table.row();
        table.add(volumeLabel).expandY().expandX();
//        table.add(slider).padRight(100);

        table.pack();

        addActor(table);

        // Handlers
        checkBox.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences properties = Gdx.app.getPreferences("pref");
                boolean setting = checkBox.isChecked();
                properties.putBoolean("audioOn", setting);
                properties.flush();
            }
        });

//		slider.setValueChangedListener(new ValueChangedListener()
//		{
//
//			@Override
//			public void changed(Slider slider, float value)
//			{
//				GameProperties properties = GameProperties.instance();
//
//				if (value == 0)
//				{
//					properties.setVolume(0);
//				}
//				else
//				{
//					properties.setVolume(value/10);
//				}
//			}
//		});
    }

    /**
     * Catch escape key.
     *
     */
    @Override
    public boolean keyUp(int keycode) {
        boolean handled = false;

        if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
            Director.instance().sendEvent(AppActorEvents.EVENT_TRANSITION_TO_MENU_SCENE, this);

            handled = true;
        }

        return handled;
    }
}
