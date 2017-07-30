package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLShapeCircle extends GLShape {

    private static final int NB_VERTEX = 364;


    private final FloatBuffer vertexBuffer;

    private final float vertices[] = new float[NB_VERTEX * VERTEX_DIMENSION];
    private final float color[] = { 0.00f, 0.76953125f, 0.22265625f, 1.0f };


    public GLShapeCircle(float centerX, float centerY, float rayon){
        vertices[0] = centerX;
        vertices[1] = centerY;
        vertices[2] = 0;
        for(int i=0; i<(NB_VERTEX-1); i++){
            vertices[(i * 3)+ 0] = centerX + (float) (rayon * Math.cos((double)i*Math.PI/180.0) );
            vertices[(i * 3)+ 1] = centerY + (float) (rayon * Math.sin((double)i*Math.PI/180.0) );
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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, NB_VERTEX);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}

