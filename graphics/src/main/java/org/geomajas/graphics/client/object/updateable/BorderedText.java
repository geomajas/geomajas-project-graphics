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

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BaseTextObject;
import org.geomajas.graphics.client.object.role.Bordered;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangleObject}.
 *
 * @author Jan Venstermans
 *
 */
public class BorderedText extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BaseTextObject baseText;

	private BorderedImpl bordered;

	public BorderedText(double userX, double userY, String text, int margin) {
		// create base graphics objects
		baseText = new BaseTextObject(userX, userY, text);
		bordered = new BorderedImpl(baseText, margin);

		// register updateables
		addUpdateable(bordered);

		// register roles of group object
		addRole(Textable.TYPE, new TextableWrapperForUpdateable(baseText, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseText, this));
		addRole(Strokable.TYPE, bordered.getStrokable());
		addRole(Fillable.TYPE, bordered.getFillable());
		addRole(Bordered.TYPE, bordered);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(bordered);
		renderContainer.add(baseText);
	}

	@Override
	public Object cloneObject() {
		BorderedText clone = new BorderedText(baseText.getUserPosition().getX(),
				baseText.getUserPosition().getY(), baseText.getText(), bordered.getMargin());
		CopyUtil.copyTextableProperties(this.getRole(Textable.TYPE), clone.getRole(Textable.TYPE));
		CopyUtil.copyBorderedProperties(this.getRole(Bordered.TYPE), clone.getRole(Bordered.TYPE));
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
