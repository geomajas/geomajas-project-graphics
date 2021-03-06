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
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BaseRectangleObject;
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
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangleObject}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledRectangle extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BaseRectangleObject baseRectangle;

	private LabeledImpl labeled;

	public LabeledRectangle(Bbox userbounds, String text) {
		this(BboxService.getCenterPoint(userbounds), userbounds.getWidth(), userbounds.getHeight(), text);
	}

	public LabeledRectangle(Coordinate centerPoint, double width, double height, String text) {
		this(centerPoint.getX(), centerPoint.getY(), width, height, text);
	}

	public LabeledRectangle(double userX, double userY, double width, double height, String text) {
		// create base graphics objects
		baseRectangle = new BaseRectangleObject(userX, userY, width, height);
		labeled = new LabeledImpl(baseRectangle, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Strokable.TYPE, baseRectangle);
		addRole(Fillable.TYPE, baseRectangle);
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseRectangle, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseRectangle, this));
		addRole(Labeled.TYPE, labeled);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(baseRectangle);
		renderContainer.add(labeled);
	}

	@Override
	public Object cloneObject() {
		Bbox userBounds = baseRectangle.getUserBounds();
		LabeledRectangle labeledRectangleClone = new LabeledRectangle(userBounds, labeled.getTextable().getText());
		CopyUtil.copyStrokableProperties(this.getRole(Strokable.TYPE), labeledRectangleClone.getRole(Strokable.TYPE));
		CopyUtil.copyFillableProperties(this.getRole(Fillable.TYPE), labeledRectangleClone.getRole(Fillable.TYPE));
		CopyUtil.copyLabeledProperties(this.getRole(Labeled.TYPE), labeledRectangleClone.getRole(Labeled.TYPE));
		return labeledRectangleClone;
	}

	//---------------------------------
	// render section
	//---------------------------------


	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

}
