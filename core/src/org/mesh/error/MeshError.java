package org.mesh.error;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class MeshError extends ApplicationAdapter {
	private PerspectiveCamera camera = new PerspectiveCamera();
	private ModelBatch modelBatch = new ModelBatch();
	private ModelBuilder modelBuilder = new ModelBuilder();
	private Environment environment = new Environment();

	@Override
	public void create () {
		camera.position.set(0, 0, 60);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));

		modelBuilder.begin();
		MeshPartBuilder meshPartBuilder = modelBuilder.part("box",
				GL20.GL_TRIANGLES, Usage.Position | Usage.Normal,
				new Material(ColorAttribute.createDiffuse(Color.WHITE)));

		BoundingBox bounds;
		Vector3 min, max;

		min = new Vector3(10, 10, 10);
		max = new Vector3(13, 13, 13);
		bounds = new BoundingBox(min, max);

		BoxShapeBuilder.build(meshPartBuilder, bounds);
	}
}
