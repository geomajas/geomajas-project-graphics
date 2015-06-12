package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.BaseCircle;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Circle;

public class BaseCircleImpl extends Circle implements BaseCircle {

	private VectorRenderable renderable;

	public BaseCircleImpl(double userX, double userY, double userRadius) {
		super(userX, userY, userRadius);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

}
