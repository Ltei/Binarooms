package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;


public class GLShapeCircle extends GLShape {

    private static final int NB_VERTEX = 25;
    private static final int VERTEX_BUFFER_SIZE = NB_VERTEX * BYTES_PER_VERTEX;


    private final FloatBuffer vertexBuffer;

    public GLShapeCircle(GLSurfaceView surfaceView, float centerX, float centerY, float rayon, GLColor color) {
        super(surfaceView, color);

        final float[] coords = new float[NB_VERTEX * VERTEX_DIMENSION];
        final double cosMult = 2.0*Math.PI/NB_VERTEX;
        for(int i=0; i<NB_VERTEX; i++){
            coords[(i * 3)+ 0] = centerX + (float) (rayon * Math.cos((double)i*cosMult) );
            coords[(i * 3)+ 1] = centerY + (float) (rayon * Math.sin((double)i*cosMult) );
            coords[(i * 3)+ 2] = 0;
        }

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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, NB_VERTEX);
    }
}