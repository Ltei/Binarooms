/*package lteii.binarooms.trash;


import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import lteii.binarooms.utils.opengl.GLRenderer;
import lteii.binarooms.utils.opengl.GLShape;

public class OGLShapeCircle extends GLShape {

    private final FloatBuffer vertexBuffer;

    private final float vertices[] = new float[364 * VERTEX_DIMENSION];
    private final int vertexCount = 364;
    private final float color[] = { 0.00f, 0.76953125f, 0.22265625f, 1.0f };

    public OGLShapeCircle(){
        vertices[0] = 0;
        vertices[1] = 0;
        vertices[2] = 0;
        for(int i=0; i<(vertexCount-1); i++){
            vertices[(i * 3)+ 0] = (float) (0.5 * Math.cos((3.14/180) * (float)i) );
            vertices[(i * 3)+ 1] = (float) (0.5 * Math.sin((3.14/180) * (float)i) );
            vertices[(i * 3)+ 2] = 0;
        }

        Log.v("Thread",""+vertices[0]+","+vertices[1]+","+vertices[2]);
        ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vertexByteBuffer.asFloatBuffer();
        vertexBuffer.put(vertices);
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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
*/