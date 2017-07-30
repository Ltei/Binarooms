package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Vector;

import lteii.binarooms.utils.MathUtils;
import lteii.binarooms.utils.Vector2;

public class GLShapeSquare extends GLShape {


    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;

    private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 };
    private float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

    private GLShapeSquare(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        final float[] coords = new float[] {
                x0, y0, 0f,
                x1, y1, 0f,
                x2, y2, 0f,
                x3, y3, 0f
        };
        final ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * BYTES_PER_FLOAT);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);
        final ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * BYTES_PER_SHORT);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
        GLES20.glLinkProgram(DEFAULT_SHADER_PROGRAM);
    }
    public static GLShapeSquare newRectangle(float centerX, float centerY, float sizeX, float sizeY) {
        final float semiSizeX = sizeX/2f;
        final float semiSizeY = sizeY/2f;
        return new GLShapeSquare(centerX-semiSizeX, centerY-semiSizeY,
                centerX-semiSizeX, centerY+semiSizeY,
                centerX+semiSizeX, centerY+semiSizeY,
                centerX+semiSizeX, centerY-semiSizeY);
    }
    public static GLShapeSquare newSquare(float centerX, float centerY, float size) {
        return newRectangle(centerX, centerY, size, size);
    }
    public static GLShapeSquare newLine(float x0, float y0, float x1, float y1, float width) {
        final Vector2 vector = new Vector2(x1-x0, y1-y0);

        final double angle = vector.angleRad();
        final double widthAngle = angle + Math.PI/2.0;

        final float semiWidth = width/2f;

        final Vector2 delta = new Vector2(x0 + (float)(semiWidth*Math.cos(widthAngle)), y0 + (float)(semiWidth*Math.sin(widthAngle)));

        return new GLShapeSquare(x0+delta.x, y0+delta.y,
                x0-delta.x, y0-delta.y,
                x1-delta.x, x1-delta.y,
                x1+delta.x, x1+delta.y);
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
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
