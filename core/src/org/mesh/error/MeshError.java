package org.mesh.error;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;

public class MeshError extends ApplicationAdapter {
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder = new ModelBuilder();
    private Model model1, model2;
    private ModelInstance modelInstance1, modelInstance2;
	private Environment environment = new Environment();

	@Override
	public void create () {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 0f, 50f);
        camera.fieldOfView = 60;
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, .4f, .4f, .4f, 1));
        environment.add(new PointLight().set(Color.WHITE, 0, 0, 50, 500));

        modelBuilder.begin();
        MeshPartBuilder meshPartBuilder = modelBuilder.part("box1",
        GL20.GL_TRIANGLES, Usage.Position | Usage.Normal,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)));

        BoundingBox bounds;
        Vector3 min, max;

        min = new Vector3(5, 5, 5);
        max = new Vector3(10, 10, 10);
        bounds = new BoundingBox(min, max);

        BoxShapeBuilder.build(meshPartBuilder, bounds);
        model1 = modelBuilder.end();

        model2 = modelBuilder.createBox(5, 5, 5, new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);

        modelInstance1 = new ModelInstance(model1);
        modelInstance1.transform.set(new Matrix4().setTranslation(0, 0, 0));
        modelInstance2 = new ModelInstance(model2);
        modelInstance2.transform.set(new Matrix4().setTranslation(15, 15, 10));
	}

	@Override
	public void render () {
        camera.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        modelBatch.begin(camera);
        modelBatch.render(modelInstance1, environment);
        modelBatch.render(modelInstance2, environment);
        modelBatch.end();
	}
}
