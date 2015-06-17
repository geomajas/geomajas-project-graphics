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
import org.geomajas.graphics.client.object.base.BaseImageObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Labeled;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.updateable.labeled.LabeledImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject} that shows a text
 * centered on a {@link BaseImageObject}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledImage extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BaseImageObject baseImage;

	private LabeledImpl labeled;

	/**
	 * Create a labeled image with the specified user space bounds.
	 * 
	 * @param userbounds
	 * @param href
	 * @param text
	 */
	public LabeledImage(Bbox userbounds, String href, String text) {
		this(BboxService.getCenterPoint(userbounds), (int) userbounds.getWidth(), (int) userbounds.getHeight(), href,
				text);
	}

	/**
	 * Create a labeled image with the specified center point in user space.
	 * 
	 * @param userbounds
	 * @param href
	 * @param text
	 */
	public LabeledImage(Coordinate centerPoint, int width, int height, String href, String text) {
		this(centerPoint.getX(), centerPoint.getY(), width, height, href, text);
	}

	/**
	 * Create a labeled image with specified user space dimensions.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param href
	 * @param text
	 */
	public LabeledImage(double x, double y, int width, int height, String href, String text) {
		// create base graphics objects
		baseImage = new BaseImageObject(x, y, width, height, href, true);
		labeled = new LabeledImpl(baseImage, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseImage, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseImage, this));
		addRole(Labeled.TYPE, labeled);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(baseImage);
		renderContainer.add(labeled);
	}

	@Override
	public Object cloneObject() {
		LabeledImage labeledImageClone = new LabeledImage(baseImage.getUserBounds(), baseImage.getHref(), labeled
				.getTextable().getText());
		CopyUtil.copyLabeledProperties(this.getRole(Labeled.TYPE), labeledImageClone.getRole(Labeled.TYPE));
		return labeledImageClone;
	}

	// ---------------------------------
	// render section
	// ---------------------------------

	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

}
