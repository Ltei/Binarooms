/*package lteii.binarooms.ui;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;

import lteii.binarooms.Database;
import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.utils.MathUtils;
import lteii.binarooms.utils.opengl.GLShape;
import lteii.binarooms.utils.opengl.GLShapeCircle;
import lteii.binarooms.trash.GLSurfaceView;

public class RoomsMapViewV2 extends GLSurfaceView {


    private static class Node {

        private final float x, y;
        private final float bigRayon, smallRayon;
        private final int color;
        private final @Nullable Node parent;

        private Node(float x, float y, float bigRayon, float smallRayon, int color, @Nullable Node parent) {
            this.x = x;
            this.y = y;
            this.bigRayon = bigRayon;
            this.smallRayon = smallRayon;
            this.color = color;
            this.parent = parent;
        }

    }


    private boolean isSetup = false;

    private ArrayList<Node> nodes = null;
    private ArrayList<GLShape> shapes = null;

    private int itemsBackgroundColor;

    public RoomsMapViewV2(Context context) {
        super(context);
    }
    public RoomsMapViewV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    public void setup() {
        final Context context = getContext();

        nodes = new ArrayList<>();
        extractNodes(Database.SOURCE_ROOM, null, 0, 0, MathUtils.dpToPixels(25, context), MathUtils.dpToPixels(20, context), 200, 400);

        shapes = new ArrayList<>();
        for (Node node : nodes) {
            shapes.add(new GLShapeCircle(node.x, node.y, node.smallRayon));
        }

        itemsBackgroundColor = Color.argb(255, 150, 150, 150);
        isSetup = true;
    }
    private void extractNodes(OLDRoom room, @Nullable Node parent, float x, float y, float bigRayon, float smallRayon, float xSpace, float ySpace) {
        final Node dot = new Node(x, y, bigRayon, smallRayon, room.getBackgroundColor(), parent);
        nodes.add(dot);
        if (room.getChild(0) != null) extractNodes(room.getChild(0), dot, x+xSpace, y-ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
        if (room.getChild(1) != null) extractNodes(room.getChild(1), dot, x-xSpace, y-ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
    }

    @Override
    public void drawShapes(float[] mvpMatrix) {
        if (isSetup) {
            for (GLShape shape : shapes) {
                shape.draw(mvpMatrix);
            }
        }
    }



}
*/