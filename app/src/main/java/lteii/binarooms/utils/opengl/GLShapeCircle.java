package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLShapeCircle extends GLShape {


    private  int mProgram, mPositionHandle, mColorHandle, mMVPMatrixHandle ;
    private FloatBuffer vertexBuffer;
    private float vertices[] = new float[364 * 3];

    float color[] = { 0.00f, 0.76953125f, 0.22265625f, 1.0f };
    private final int vertexCount = 364 * 3 / VERTEX_DIMENSION;

    public GLShapeCircle(){
        int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, DEFAULT_VERTEX_SHADER_CODE);
        int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, DEFAULT_FRAGMENT_SHADER_CODE);
        vertices[0] = 0;
        vertices[1] = 0;
        vertices[2] = 0;
        for(int i =0; i <363; i++){
            vertices[(i * 3)+ 0] = (float) (0.5 * Math.cos((3.14/180) * (float)i ));
            vertices[(i * 3)+ 1] = (float) (0.5 * Math.sin((3.14/180) * (float)i ));
            vertices[(i * 3)+ 2] = 0;
            //Log.v("Thread",""+vertices[(i*3)+0]+", "+vertices[(i*3)+1]+", "+vertices[(i*3)+2]);
        }

        Log.v("Thread",""+vertices[0]+","+vertices[1]+","+vertices[2]);
        ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vertexByteBuffer.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

    }


    public void draw (float[] mvpMatrix){
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, VERTEX_DIMENSION, GLES20.GL_FLOAT, false, VERTEX_STRIDE, vertexBuffer);
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
