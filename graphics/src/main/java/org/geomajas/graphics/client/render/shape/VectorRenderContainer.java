package org.geomajas.graphics.client.render.shape;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

public class VectorRenderContainer extends VectorRenderable implements RenderContainer {

	private List<Renderable> renderContainer = new ArrayList<Renderable>();

	public VectorRenderContainer() {
		super(new Group());
	}

	public VectorRenderContainer(VectorObjectContainer container) {
		super((VectorObject) container);
	}

	public VectorObjectContainer getContainer() {
		return (VectorObjectContainer) getObject();
	}

	@Override
	public void addRenderable(Renderable renderable) {
		renderContainer.add(renderable);
		getContainer().add(((VectorRenderable)renderable).getObject());
	}

	@Override
	public void addRenderable(IsRenderable renderable) {
		addRenderable(renderable.getRenderable());
	}

	@Override
	public void clear() {
		getContainer().clear();
		renderContainer.clear();
	}

	@Override
	public boolean isEmpty() {
		return getContainer().getVectorObjectCount() == 0;
	}

	@Override
	public RenderContainer createContainer() {
		VectorRenderContainer container = new VectorRenderContainer();
		return container;
	}

}
