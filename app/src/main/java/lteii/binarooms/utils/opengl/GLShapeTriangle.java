package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;


public class GLShapeTriangle extends GLShape {

    private static final int NB_VERTEX = 3;
    private static final int VERTEX_BUFFER_SIZE = NB_VERTEX * BYTES_PER_VERTEX;


    private final FloatBuffer vertexBuffer;

    public GLShapeTriangle(GLSurfaceView surfaceView, float x0, float y0, float x1, float y1, float x2, float y2, GLColor color) {
        super(surfaceView, color);

        final float[] coords = new float[] {
                x0, y0, 0.0f,
                x1, y1, 0.0f,
                x2, y2, 0.0f
        };
        final ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(VERTEX_BUFFER_SIZE);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vertexByteBuffer.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);
    }


    @Override
    public void draw() {
        GLES20.glVertexAttribPointer(surfaceView.positionHandle, VERTEX_DIMENSION, GLES20.GL_FLOAT, false, BYTES_PER_VERTEX, vertexBuffer);
        GLES20.glUniform4fv(surfaceView.colorHandle, 1, color.rgba, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, NB_VERTEX);
    }
}