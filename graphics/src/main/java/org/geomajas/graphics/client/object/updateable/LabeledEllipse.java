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

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BaseEllipse;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.geomajas.graphics.client.object.updateable.labeled.LabeledImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link UpdateableGroupGraphicsObject} that shows a text centered on a {@link BaseEllipse}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledEllipse extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BaseEllipse baseEllipse;

	private LabeledImpl labeled;

	public LabeledEllipse(Bbox ellipseBounds, String text) {
		this(BboxService.getCenterPoint(ellipseBounds).getX(), BboxService.getCenterPoint(ellipseBounds).getY(),
				ellipseBounds.getWidth() / 2, ellipseBounds.getHeight() / 2, text);
	}

	public LabeledEllipse(double ellipseCenterX, double ellipseCenterY,
						  double userRadiusX, double userRadiusY, String text) {
		// create base graphics objects
		baseEllipse = new BaseEllipse(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY);
		labeled = new LabeledImpl(baseEllipse, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseEllipse, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseEllipse, this));
		addRole(Strokable.TYPE, baseEllipse);
		addRole(Fillable.TYPE, baseEllipse);
		addRole(Labeled.TYPE, labeled);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.addRenderable(baseEllipse);
		renderContainer.addRenderable(labeled);
	}

	@Override
	public Object cloneObject() {
		LabeledEllipse clone = new LabeledEllipse(baseEllipse.getUserBounds(), labeled.getTextable().getLabel());
		CopyUtil.copyStrokableProperties(this.getRole(Strokable.TYPE), clone.getRole(Strokable.TYPE));
		CopyUtil.copyFillableProperties(this.getRole(Fillable.TYPE), clone.getRole(Fillable.TYPE));
		CopyUtil.copyLabeledProperties(this.getRole(Labeled.TYPE), clone.getRole(Labeled.TYPE));
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------


	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

}
