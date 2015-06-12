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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

/**
 * Wraps {@link VectorObjectContainer} as {@link RenderContainer}.
 * 
 * @author Jan De Moerloose
 *
 */
public class VectorRenderContainer extends VectorRenderable implements RenderContainer {

	public VectorRenderContainer() {
		super(new Group());
	}

	public VectorRenderContainer(VectorObjectContainer container) {
		super((VectorObject) container);
	}

	public VectorObjectContainer getContainer() {
		return (VectorObjectContainer) getObject();
	}

	@Override
	public void add(Renderable renderable) {
		getContainer().add(((VectorRenderable) renderable).getObject());
		((VectorRenderable) renderable).setParent(this);
	}

	@Override
	public void add(IsRenderable renderable) {
		add(renderable.getRenderable());
	}

	@Override
	public void clear() {
		getContainer().clear();
	}

	@Override
	public boolean isEmpty() {
		return getContainer().getVectorObjectCount() == 0;
	}

	@Override
	public boolean remove(Renderable renderable) {
		if (renderable.getParent() != this) {
			return false;
		} else {
			getContainer().remove(((VectorRenderable) renderable).getObject());
			((VectorRenderable) renderable).setParent(null);
			return true;
		}
	}

	@Override
	public boolean remove(IsRenderable renderable) {
		return remove(renderable.getRenderable());
	}

	@Override
	public void bringToFront(Renderable renderable) {
		getContainer().bringToFront(((VectorRenderable) renderable).getObject());
	}

	@Override
	public void bringToFront(IsRenderable renderable) {
		bringToFront(renderable.getRenderable());
	}

	@Override
	public void insert(Renderable renderable, int index) {
		getContainer().insert(((VectorRenderable) renderable).getObject(), index);
		((VectorRenderable) renderable).setParent(this);
	}

	@Override
	public void insert(IsRenderable renderable, int index) {
		insert(renderable.getRenderable(), index);
		((VectorRenderable) renderable).setParent(this);
	}

	@Override
	public int indexOf(Renderable renderable) {
		return getContainer().indexOf(((VectorRenderable) renderable).getObject());
	}

	@Override
	public int indexOf(IsRenderable renderable) {
		return indexOf(renderable.getRenderable());
	}

}
