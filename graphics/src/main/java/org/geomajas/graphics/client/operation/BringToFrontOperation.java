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
package org.geomajas.graphics.client.operation;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Operation that brings an object to the front.
 * 
 * @author Jan De Moerloose
 * 
 */
public class BringToFrontOperation implements GraphicsOperation {

	private GraphicsObject object;
	
	private List<GraphicsObject> externalLabels = new ArrayList<GraphicsObject>();

	private int beforeIndex;

	public BringToFrontOperation(GraphicsObject object, GraphicsService service) {
		this.object = object;
	}

	@Override
	public void execute() {
		externalLabels.clear();
		RenderContainer parent = object.getRenderable().getParent();
		if (parent != null) {
			beforeIndex = parent.indexOf(object.getRenderable());
			parent.bringToFront(object.getRenderable());
		}
	}

	@Override
	public void undo() {
		RenderContainer parent = object.getRenderable().getParent();
		if (parent != null) {
			parent.insert(object.getRenderable(), beforeIndex);
		}
	}

	@Override
	public GraphicsObject getObject() {
		return object;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
