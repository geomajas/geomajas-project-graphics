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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BasePath;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Labeled;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.labeled.LabeledImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link BasePath}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledPath extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BasePath basePath;

	private LabeledImpl labeled;

	public LabeledPath(Coordinate[] coordinates, boolean closed, String text) {
		// create base graphics objects
		basePath = new BasePath(coordinates, closed);
		labeled = new LabeledImpl(basePath, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Strokable.TYPE, basePath);
		if (closed) {
			addRole(Fillable.TYPE, basePath);
		}
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(basePath, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(basePath, this));
		addRole(Labeled.TYPE, labeled);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(basePath);
		renderContainer.add(labeled);
	}

	@Override
	public Object cloneObject() {
		LabeledPath clone = new LabeledPath(copyCoordinatesOfBasePath(), basePath.isClosed(),
				labeled.getTextable().getText());
		CopyUtil.copyStrokableProperties(getRole(Strokable.TYPE), clone.getRole(Strokable.TYPE));
		if (hasRole(Fillable.TYPE) && clone.hasRole(Fillable.TYPE)) {
			CopyUtil.copyFillableProperties(getRole(Fillable.TYPE), clone.getRole(Fillable.TYPE));
		}
		CopyUtil.copyLabeledProperties(this.getRole(Labeled.TYPE), clone.getRole(Labeled.TYPE));
		return clone;
	}

	private Coordinate[] copyCoordinatesOfBasePath() {
		Coordinate[] originalCoodinates = basePath.getCoordinates();
		if (originalCoodinates != null) {
			Coordinate[] coordinates = new Coordinate[originalCoodinates.length];
			for (int i = 0 ; i < originalCoodinates.length ; i++) {
				coordinates[i] = new Coordinate(originalCoodinates[i]);
			}
			return coordinates;
		}
		return null;
	}

	//---------------------------------
	// render section
	//---------------------------------


	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

}
