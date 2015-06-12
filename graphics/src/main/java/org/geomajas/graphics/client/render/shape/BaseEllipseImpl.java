package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.BaseEllipse;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

public class BaseEllipseImpl extends Ellipse implements BaseEllipse {

	private VectorRenderable renderable;

	public BaseEllipseImpl(double userX, double userY, double userRadiusX, double userRadiusY) {
		super(userX, userY, userRadiusX, userRadiusY);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

}
