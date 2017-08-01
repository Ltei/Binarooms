package lteii.binarooms.utils.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceView extends android.opengl.GLSurfaceView implements android.opengl.GLSurfaceView.Renderer {

    public interface ShapeDrawer {
        void drawShapes(float[] mvpMatrix);
    }

    private static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
    private static void checkGlError(String glOperation) {
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("GLRenderer", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }


    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private ShapeDrawer shapeDrawer = null;

    int shaderProgram;
    int positionHandle;
    int colorHandle;
    int mvpMatrixHandle;

    public GLSurfaceView(Context context) {
        super(context);
    }
    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setup(ShapeDrawer drawer) {
        this.shapeDrawer = drawer;
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


    @Override
    public final void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //GLShape.setupDefaultShaderProgram();

        // Setup shader program
        final String defaultVertexShaderCode =
                "uniform mat4 uMVPMatrix;" +
                        "attribute vec4 vPosition;" +
                        "void main() {" +
                        "  gl_Position = uMVPMatrix * vPosition;" +
                        "}";
        final String defaultFragmentShaderCode =
                "precision mediump float;" +
                        "uniform vec4 vColor;" +
                        "void main() {" +
                        "  gl_FragColor = vColor;" +
                        "}";

        final int defaultVertexShader = loadShader(GLES20.GL_VERTEX_SHADER, defaultVertexShaderCode);
        final int defaultFragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, defaultFragmentShaderCode);

        shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram, defaultVertexShader);
        GLES20.glAttachShader(shaderProgram, defaultFragmentShader);
        GLES20.glLinkProgram(shaderProgram);

        // Get shader vars handle
        positionHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        colorHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor");
        mvpMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix");
        checkGlError("glGetUniformLocation");
    }

    @Override
    public final void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        final long t0 = System.currentTimeMillis();
        GLES20.glUseProgram(shaderProgram);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mMVPMatrix, 0);
        checkGlError("glUniformMatrix4fv");
        if (shapeDrawer != null) shapeDrawer.drawShapes(mMVPMatrix);
        GLES20.glDisableVertexAttribArray(positionHandle);
        Log.d(getClass().getName(), "DRAW TIME : "+(System.currentTimeMillis()-t0)+" ms.");
    }

    @Override
    public final void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, ratio, -ratio, -1, 1, 3, 7);
    }


}
