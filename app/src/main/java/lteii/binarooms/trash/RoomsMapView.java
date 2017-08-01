/*package lteii.binarooms.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;

import lteii.binarooms.Database;
import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.utils.MathUtils;
import lteii.binarooms.utils.NavigableGraphView;


public class RoomsMapView extends NavigableGraphView {

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
    private int itemsBackgroundColor;


    public RoomsMapView(Context context) {
        super(context);
    }
    public RoomsMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setup() {
        super.setup();
        final Context context = getContext();

        // Extract nodes from rooms
        nodes = new ArrayList<>();
        extractNodes(Database.SOURCE_ROOM, null, 0, 0,
                MathUtils.dpToPixels(25, context), MathUtils.dpToPixels(20, context),
                200, 400);

        itemsBackgroundColor = Color.argb(255, 150, 150, 150);
        centerOnNode(nodes.get(0));
        isSetup = true;
    }


    private void extractNodes(OLDRoom room, @Nullable Node parent, float x, float y, float bigRayon, float smallRayon, float xSpace, float ySpace) {
        final Node dot = new Node(x, y, bigRayon, smallRayon, room.getBackgroundColor(), parent);
        nodes.add(dot);
        if (room.getChild(0) != null) extractNodes(room.getChild(0), dot, x+xSpace, y-ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
        if (room.getChild(1) != null) extractNodes(room.getChild(1), dot, x-xSpace, y-ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
    }
    private void centerOnNode(Node node) {
        camera.centerX = -node.x;
        camera.centerY = -node.y;
        invalidate();
    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isSetup) {
            for (Node node : nodes)
                drawCircle(canvas, node.x, node.y, node.bigRayon, itemsBackgroundColor);

            for (Node node : nodes)
                if (node.parent != null)
                    drawLine(canvas, node.x, node.y, node.parent.x, node.parent.y, node.bigRayon, itemsBackgroundColor);

            for (Node node : nodes)
                if (node.parent != null)
                    drawLine(canvas, node.x, node.y, node.parent.x, node.parent.y, node.smallRayon, node.color, node.parent.color);

            for (Node node : nodes)
                drawCircle(canvas, node.x, node.y, node.smallRayon, node.color);
        }
    }

}*/




        /*// Rescale nodes positions
        float minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Node dot : nodes) {
            if (dot.x < minX) minX = dot.x;
            if (dot.x > maxX) maxX = dot.x;
            if (dot.y < minY) minY = dot.y;
            if (dot.y > maxY) maxY = dot.y;
        }

        final float newMax = MathUtils.dpToPixels(1000, context);
        for (Node dot : nodes) {
            dot.x = MathUtils.scaleValue(dot.x, minX, maxX, nodeBigRayon, newMax);
            dot.y = MathUtils.scaleValue(dot.y, minY, maxY, nodeBigRayon, newMax);
        }*/