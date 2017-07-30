package lteii.binarooms.utils.opengl;


public class GLShape {

    protected static final String DEFAULT_VERTEX_SHADER_CODE =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    protected static final String DEFAULT_FRAGMENT_SHADER_CODE =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    protected static final int BYTES_PER_FLOAT = 4;
    protected static final int BYTES_PER_SHORT = 2;

    protected static final int VERTEX_DIMENSION = 3;
    protected static final int VERTEX_STRIDE = VERTEX_DIMENSION * BYTES_PER_FLOAT;


}
