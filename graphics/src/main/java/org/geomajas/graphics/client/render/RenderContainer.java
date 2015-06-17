/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.render;

import org.geomajas.annotation.Api;

/**
 * A container of {@link Renderable} objects.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 *
 */
@Api(allMethods = true)
public interface RenderContainer extends Renderable {

	/**
	 * Clear all member {@link Renderable} instances.
	 */
	void clear();

	/**
	 * Is the container empty ?
	 * 
	 * @return true if no members.
	 */
	boolean isEmpty();

	/**
	 * Add the object to the parent container.
	 * 
	 * @param renderable object
	 */
	void add(Renderable renderable);

	/**
	 * Add the object to the parent container.
	 * 
	 * @param renderable object
	 */
	void add(IsRenderable renderable);

	/**
	 * Remove the object from the parent container.
	 * 
	 * @param renderable object
	 * @return true if actually removed.
	 */
	boolean remove(Renderable renderable);

	/**
	 * Remove the object from the parent container.
	 * 
	 * @param renderable object
	 * @return true if actually removed.
	 */
	boolean remove(IsRenderable renderable);

	/**
	 * Bring the object to the front position of this container.
	 * 
	 * @param renderable object
	 */
	void bringToFront(Renderable renderable);

	/**
	 * Bring the object to the front position of this container.
	 * 
	 * @param renderable object
	 */
	void bringToFront(IsRenderable renderable);

	/**
	 * Add the object to the given indexed position of the container. Index 0 means send to back.
	 * 
	 * @param renderable object
	 * @param index
	 * @throws IndexOutOfBoundsException if no such index exists
	 */
	void insert(Renderable renderable, int index);

	/**
	 * Add the object to the given indexed position of the container. Index 0 means send to back.
	 * 
	 * @param renderable object
	 * @param index
	 * @throws IndexOutOfBoundsException if no such index exists
	 */
	void insert(IsRenderable renderable, int index);

	/**
	 * Get the indexed position of this object in this container.
	 * 
	 * @param renderable object
	 * @return the index
	 */
	int indexOf(Renderable renderable);

	/**
	 * Get the indexed position of this object in this container.
	 * 
	 * @param renderable object
	 * @return the index
	 */
	int indexOf(IsRenderable renderable);
}
