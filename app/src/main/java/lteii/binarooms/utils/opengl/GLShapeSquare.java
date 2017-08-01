package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import lteii.binarooms.utils.Vector2;


public class GLShapeSquare extends GLShape {

    private static final int NB_VERTEX = 4;
    private static final int VERTEX_BUFFER_SIZE = NB_VERTEX * BYTES_PER_VERTEX;

    private static final int NB_DRAW_INDEX = 6;
    private static final int DRAW_INDEX_BUFFER_SIZE = NB_DRAW_INDEX * BYTES_PER_DRAW_INDEX;
    private static final short[] DRAW_INDEXES = {0, 1, 2, 0, 2, 3};


    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawIndexBuffer;

    private GLShapeSquare(GLSurfaceView surfaceView, float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3, GLColor color) {
        super(surfaceView, color);

        final float[] coords = new float[] {
                x0, y0, 0f,
                x1, y1, 0f,
                x2, y2, 0f,
                x3, y3, 0f
        };
        final ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(VERTEX_BUFFER_SIZE);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vertexByteBuffer.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);
        final ByteBuffer drawIndexByteBuffer = ByteBuffer.allocateDirect(DRAW_INDEX_BUFFER_SIZE);
        drawIndexByteBuffer.order(ByteOrder.nativeOrder());
        drawIndexBuffer = drawIndexByteBuffer.asShortBuffer();
        drawIndexBuffer.put(DRAW_INDEXES);
        drawIndexBuffer.position(0);
    }
    public static GLShapeSquare newRectangle(GLSurfaceView surfaceView, float centerX, float centerY, float sizeX, float sizeY, GLColor color) {
        final float semiSizeX = sizeX/2f;
        final float semiSizeY = sizeY/2f;
        return new GLShapeSquare(surfaceView,
                centerX-semiSizeX, centerY-semiSizeY,
                centerX-semiSizeX, centerY+semiSizeY,
                centerX+semiSizeX, centerY+semiSizeY,
                centerX+semiSizeX, centerY-semiSizeY,
                color);
    }
    public static GLShapeSquare newSquare(GLSurfaceView surfaceView, float centerX, float centerY, float size, GLColor color) {
        return newRectangle(surfaceView, centerX, centerY, size, size,  color);
    }
    public static GLShapeSquare newLine(GLSurfaceView surfaceView, float x0, float y0, float x1, float y1, float width, GLColor color) {
        final double angle = new Vector2(x1-x0, y1-y0).angleRad();
        final double widthAngle = angle + Math.PI/2.0;

        final float semiWidth = width/2f;

        final float deltaX = (float)(semiWidth*Math.cos(widthAngle));
        final float deltaY = (float)(semiWidth*Math.sin(widthAngle));

        return new GLShapeSquare(surfaceView,
                x0+deltaX, y0+deltaY,
                x0-deltaX, y0-deltaY,
                x1-deltaX, y1-deltaY,
                x1+deltaX, y1+deltaY,
                color);
    }


    @Override
    public void draw() {
        GLES20.glVertexAttribPointer(surfaceView.positionHandle, VERTEX_DIMENSION, GLES20.GL_FLOAT, false, BYTES_PER_VERTEX, vertexBuffer);
        GLES20.glUniform4fv(surfaceView.colorHandle, 1, color.rgba, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, NB_DRAW_INDEX, GLES20.GL_UNSIGNED_SHORT, drawIndexBuffer);
    }
}