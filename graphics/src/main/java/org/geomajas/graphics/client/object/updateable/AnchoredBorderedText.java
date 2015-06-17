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
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Bordered;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.hasmarker.HasMarkerImpl;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.HasMarkerWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.render.shape.AnchoredTextImpl;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangleObject},
 * and with an {@link org.geomajas.graphics.client.object.updateable.hasmarker.HasMarkerImpl}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredBorderedText extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private AnchoredTextImpl baseText;

	private BorderedImpl bordered;

	private HasMarkerImpl anchored;

	public AnchoredBorderedText(Coordinate textPosition, String text, int margin,  Coordinate anchorCoordinate,
								MarkerShape markerShape) {
		// create base graphics objects
		baseText = new AnchoredTextImpl(textPosition.getX(), textPosition.getY(), text, 0, 0);
		bordered = new BorderedImpl(baseText, margin);
		anchored = new HasMarkerImpl(baseText, anchorCoordinate, markerShape);

		// register updateables
		addUpdateable(bordered);
		addUpdateable(anchored);

		// register roles of group object
		addRole(Textable.TYPE, new TextableWrapperForUpdateable(baseText, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseText, this));
		addRole(Strokable.TYPE, bordered.getStrokable());
		addRole(Fillable.TYPE, bordered.getFillable());
		addRole(Bordered.TYPE, bordered);
		addRole(HasMarker.TYPE, new HasMarkerWrapperForUpdateable(anchored, this));

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(anchored);
		renderContainer.add(bordered);
		renderContainer.add(baseText);
	}

	@Override
	public Object cloneObject() {
		AnchoredBorderedText clone = new AnchoredBorderedText(new Coordinate(baseText.getUserX(),
				baseText.getUserY()), baseText.getText(), bordered.getMargin(), anchored.getMarker().getUserPosition(),
				anchored.getMarkerShape());
		CopyUtil.copyAnchoredProperties(this.getRole(HasMarker.TYPE), clone.getRole(HasMarker.TYPE));
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
