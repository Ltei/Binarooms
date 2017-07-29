package lteii.binarooms.utils.opengl;


import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class GLShapeCircleV2 extends GLShape {

    private static final int nbPoints = 30;
    private static final int pointsDimension = 2;


    private static final float rgbaValues[] = {
            1, 0, 0, 1,
            1, 0, 0, 1,
            1, 0, 0, 1
    };


    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;

    public GLShapeCircleV2(float centerX, float centerY, float radius) {
        final float[] vertices = new float[nbPoints * pointsDimension];
        int idx = 0;
        vertices[idx++] = centerX;
        vertices[idx++] = centerY;
        for (int i=0; i<nbPoints-1; i++) {
            double radians = 2 * Math.PI * i / (double) (nbPoints-2);
            vertices[idx++] = (float) (centerX + radius * Math.cos(radians));
            vertices[idx++] = (float) (centerY + radius * Math.sin(radians));
        }
        vertexBuffer = makeFloatBuffer(vertices);

        colorBuffer = makeFloatBuffer(rgbaValues);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW); // Front facing is clockwise

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glDrawArrays(GL10.GL_LINE_LOOP, 1, nbPoints-1); // Draw hollow circle
        //gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, nbPoints); // Draw circle as filled shape

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}