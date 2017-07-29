package lteii.binarooms.utils.opengl;


import java.nio.ByteBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class GLShapeCircle extends GLShape {

    private static final int nbTriangles = 100;


    private final GLShapeTriangle[] triangles;

    public GLShapeCircle(float rayon) {
        triangles = new GLShapeTriangle[nbTriangles];
        for (int i=0; i<nbTriangles; i++) {
            final double angle0 = 2*Math.PI*i/nbTriangles;
            final double angle1 = 2*Math.PI*(i+1)/nbTriangles;
            triangles[i] = new GLShapeTriangle(0, 0,
                    (float)Math.cos(angle0)*rayon, (float)Math.sin(angle0)*rayon,
                    (float)Math.cos(angle1)*rayon, (float)Math.sin(angle1)*rayon);
        }
    }


    @Override
    public void draw(GL10 gl) {
        for (GLShapeTriangle triangle : triangles) {
            triangle.draw(gl);
        }
    }

}
