package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.BaseRectangle;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

public class BaseRectangleImpl extends Rectangle implements BaseRectangle {

	private VectorRenderable renderable;

	public BaseRectangleImpl(double userX, double userY, double userWidth, double userHeight) {
		super(userX, userY, userWidth, userHeight);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

}
