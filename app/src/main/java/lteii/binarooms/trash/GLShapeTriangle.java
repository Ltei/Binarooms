/*package lteii.binarooms.trash;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class GLShapeTriangle extends GLShape {

    private static final int nbPoints = 3;


    private FloatBuffer vertexBuffer;
    private ByteBuffer indexBuffer;

    public GLShapeTriangle(float x0, float y0, float x1, float y1, float x2, float y2) {
        final float vertices[] = {
                x0, y0, -10f,
                x1, y1, -10f,
                x2, y2, -10f
        };
        vertexBuffer = makeFloatBuffer(vertices);

        final byte indices[] = { 0, 1, 2 };
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL11.GL_TRIANGLES, nbPoints, GL11.GL_UNSIGNED_BYTE, indexBuffer);
    }

}*/
