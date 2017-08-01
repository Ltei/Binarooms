package lteii.binarooms.utils.opengl;


public abstract class GLShape {

    protected static final int VERTEX_DIMENSION = 3;
    protected static final int BYTES_PER_VERTEX = VERTEX_DIMENSION * 4; //4 bytes per float
    protected static final int BYTES_PER_DRAW_INDEX = 2; //2 bytes per short


    protected final GLSurfaceView surfaceView;
    protected final GLColor color;

    public GLShape(GLSurfaceView surfaceView, GLColor color) {
        this.surfaceView = surfaceView;
        this.color = color;
    }


    public abstract void draw();

}