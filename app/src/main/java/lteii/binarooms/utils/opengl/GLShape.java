package lteii.binarooms.utils.opengl;


import android.opengl.GLES20;

public class GLShape {

    private static final String DEFAULT_VERTEX_SHADER_CODE =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private static final String DEFAULT_FRAGMENT_SHADER_CODE =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    protected static final int DEFAULT_VERTEX_SHADER = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, DEFAULT_VERTEX_SHADER_CODE);
    protected static final int DEFAULT_FRAGMENT_SHADER = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, DEFAULT_FRAGMENT_SHADER_CODE);

    protected static final int DEFAULT_SHADER_PROGRAM;

    static {
        DEFAULT_SHADER_PROGRAM = GLES20.glCreateProgram();
        GLES20.glAttachShader(DEFAULT_SHADER_PROGRAM, DEFAULT_VERTEX_SHADER);
        GLES20.glAttachShader(DEFAULT_SHADER_PROGRAM, DEFAULT_FRAGMENT_SHADER);
        GLES20.glLinkProgram(DEFAULT_SHADER_PROGRAM);
    }

    protected static final int BYTES_PER_FLOAT = 4;
    protected static final int BYTES_PER_SHORT = 2;

    protected static final int VERTEX_DIMENSION = 3;
    protected static final int BYTES_PER_VERTEX = VERTEX_DIMENSION * BYTES_PER_FLOAT;


}
