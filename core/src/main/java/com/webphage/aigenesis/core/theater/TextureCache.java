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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a generic texture cache backed by the {@link TextureAtlas}.
 *
 */
public class TextureCache implements Disposable {

    private static TextureCache instance = null;
    private TextureAtlas textureAtlas = null;
    private Map<String, TextureDefinition> definitions;

    /**
     * Singleton instance.
     *
     * @return An instance of this class.
     */
    public synchronized static TextureCache instance() {
        if (instance == null) {
            instance = new TextureCache();
        }

        return instance;
    }

    private TextureCache() {
        definitions = new HashMap<String, TextureDefinition>();
    }

    /**
     * Pack pack file textures (textures file resides in same directory).
     *
     * @param packFile
     */
    public void load(String packFile) {
        if (textureAtlas == null) {
            textureAtlas = new TextureAtlas(Gdx.files.internal(packFile));
        }
    }

    /**
     * Load predefined textures.
     *
     * This requires texture definitions to be added to the {@link AppActorTextures}
     * structure.
     */
    public void load(List<TextureDefinition> textureDefinitions) {
        if (textureAtlas == null) {
            textureAtlas = new TextureAtlas();
        } else {
            dispose();

            textureAtlas = new TextureAtlas();
        }

        for (TextureDefinition definition : textureDefinitions) {
            try {
                Texture texture = new Texture(Gdx.files.internal(definition.getPath()));
                TextureRegion textureRegion = new TextureRegion(texture);

                textureAtlas.addRegion(definition.getName(), textureRegion);
                definitions.put(definition.getName(), definition);
            } catch (GdxRuntimeException ex) {
                Gdx.app.log(this.getClass().getName(), "Failed to load "+Gdx.files.getLocalStoragePath()+definition.getPath());
            }
        }
    }

    /**
     * Fetch texture region from cache.
     *
     * @param name The texture name.
     *
     * @return The texture region.
     */
    public TextureRegion getTexture(TextureDefinition definition) {
        return textureAtlas.findRegion(definition.getName());
    }

    /**
     * Fetch texture region from cache.
     *
     * @param name The texture name.
     *
     * @return The texture region.
     */
    public TextureDefinition getDefinition(String name) {
        return definitions.get(name);
    }

    /**
     * Dispose of cache data.
     *
     */
    @Override
    public void dispose() {
        definitions.clear();
        textureAtlas.dispose();
    }
}
