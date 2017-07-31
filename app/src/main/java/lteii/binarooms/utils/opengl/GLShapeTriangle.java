package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLShapeTriangle extends GLShape {

    private static final int NB_VERTEX = 3;
    private static final int VERTEX_BUFFER_SIZE = NB_VERTEX * BYTES_PER_VERTEX;


    private final FloatBuffer vertexBuffer;
    private float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 0.0f };

    public GLShapeTriangle(float x0, float y0, float x1, float y1, float x2, float y2) {
        final float[] coords = new float[] {
                x0, y0, 0.0f,
                x1, y1, 0.0f,
                x2, y2, 0.0f
        };
        final ByteBuffer bb = ByteBuffer.allocateDirect(VERTEX_BUFFER_SIZE);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);
        //GLES20.glLinkProgram(DEFAULT_SHADER_PROGRAM);
    }


    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(DEFAULT_SHADER_PROGRAM);
        final int mPositionHandle = GLES20.glGetAttribLocation(DEFAULT_SHADER_PROGRAM, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, VERTEX_DIMENSION, GLES20.GL_FLOAT, false, BYTES_PER_VERTEX, vertexBuffer);
        final int mColorHandle = GLES20.glGetUniformLocation(DEFAULT_SHADER_PROGRAM, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        final int mMVPMatrixHandle = GLES20.glGetUniformLocation(DEFAULT_SHADER_PROGRAM, "uMVPMatrix");
        GLRenderer.checkGlError("glGetUniformLocation");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        GLRenderer.checkGlError("glUniformMatrix4fv");
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, NB_VERTEX);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
