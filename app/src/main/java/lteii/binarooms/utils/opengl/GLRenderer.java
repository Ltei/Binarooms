package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer  implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    private GLShapeTriangle mTriangle;
    private GLShapeSquare mSquare;
    private GLShapeCircle mCircle;

    private float mAngle;


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mTriangle = new GLShapeTriangle(-0.3f,0.0f, 0.3f,0.0f, 0.0f,0.5f);
        //mSquare = GLShapeSquare.newSquare(0.25f, 0.25f, 0.5f);
        mSquare = GLShapeSquare.newLine(0,0, 0.5f,0.5f, 0.1f);
        mCircle = new GLShapeCircle(0.25f, 0.5f, 0.25f);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        mSquare.draw(mMVPMatrix);
        mCircle.draw(mMVPMatrix);
        mTriangle.draw(mMVPMatrix);

        //final float[] scratch = new float[16];
        //Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, 1.0f);
        //Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        //mTriangle.draw(scratch);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, ratio, -ratio, -1, 1, 3, 7);
    }


    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public float getAngle() { return mAngle; }
    public void setAngle(float angle) { this.mAngle = angle; }

}
