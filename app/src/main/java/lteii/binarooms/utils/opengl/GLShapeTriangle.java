package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLShapeTriangle extends GLShape {


    private final FloatBuffer vertexBuffer;

    private float triangleCoords[] = {
            0.0f,  0.4f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };
    private final int vertexCount = triangleCoords.length / VERTEX_DIMENSION;
    private float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 0.0f };


    public GLShapeTriangle() {
        final ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * BYTES_PER_FLOAT);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);
        GLES20.glLinkProgram(DEFAULT_SHADER_PROGRAM);
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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
