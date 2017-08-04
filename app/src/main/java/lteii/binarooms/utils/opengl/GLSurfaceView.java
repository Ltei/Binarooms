package lteii.binarooms.utils.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceView extends android.opengl.GLSurfaceView implements android.opengl.GLSurfaceView.Renderer {

    public interface ShapeDrawer {
        void drawShapes();
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


    private ShapeDrawer shapeDrawer = null;
    private GLColor backgroundColor = null;

    int shaderProgram;
    int positionHandle;
    int colorHandle;
    int mvpMatrixHandle;

    GLCamera camera;

    public GLSurfaceView(Context context) {
        super(context);
    }
    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setup(ShapeDrawer drawer, GLColor backgroundColor) {
        this.shapeDrawer = drawer;
        this.backgroundColor = backgroundColor;
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    // GLSurfaceView.Renderer
    @Override
    public final void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

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
        final long t0 = System.currentTimeMillis();

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(backgroundColor.red(), backgroundColor.green(), backgroundColor.blue(), backgroundColor.alpha());
        GLES20.glUseProgram(shaderProgram);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, camera.mvpMatrix, 0);
        checkGlError("glUniformMatrix4fv");
        if (shapeDrawer != null) shapeDrawer.drawShapes();
        GLES20.glDisableVertexAttribArray(positionHandle);

        Log.d(getClass().getName(), "DRAW TIME : "+(System.currentTimeMillis()-t0)+" ms.");
    }
    @Override
    public final void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        camera = new GLCamera(width, height);
    }


}
