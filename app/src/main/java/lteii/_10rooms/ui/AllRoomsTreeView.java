package lteii._10rooms.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

import lteii._10rooms.Database;
import lteii._10rooms.model.room.Room;
import lteii._10rooms.utils.MathUtils;
import lteii._10rooms.utils.NavigableGraphView;


public class AllRoomsTreeView extends NavigableGraphView {

    private class Node {

        private final int floor;
        private final float x, y;
        private final int color;
        private final @Nullable Node parent;

        private Node(int floor, float x, float y, int color, @Nullable Node parent) {
            this.floor = floor;
            this.x = x;
            this.y = y;
            this.color = color;
            this.parent = parent;
        }
    }


    private boolean isSetup = false;
    private ArrayList<Node> nodes = null;

    private int itemsBackgroundColor;
    private float nodeBigRayon;
    private float nodeSmallRayon;
    private float linkBigWidth;
    private float linkSmallWidth;


    public AllRoomsTreeView(Context context) {
        super(context);
    }
    public AllRoomsTreeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setup() {
        super.setup();

        final Context context = getContext();

        // Extract nodes from rooms
        nodes = new ArrayList<>();
        extractNodes(Database.SOURCE_ROOM, null, 0, 0, 0, 400, 400);

        // Setup graphics
        itemsBackgroundColor = Color.argb(255, 150, 150, 150);
        nodeBigRayon = MathUtils.dpToPixels(25, context);
        nodeSmallRayon = MathUtils.dpToPixels(20, context);
        linkBigWidth = MathUtils.dpToPixels(20, context);
        linkSmallWidth = MathUtils.dpToPixels(10, context);

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

        // Finish
        centerOnNode(nodes.get(0));
        isSetup = true;
    }


    private void extractNodes(Room room, @Nullable Node parent, int floor, float x, float y, float xSpace, float ySpace) {
        final Node dot = new Node(floor, (int)x, (int)y, room.getBackgroundColor(), parent);
        nodes.add(dot);
        if (room.getChild(0) != null) extractNodes(room.getChild(0), dot, floor+1, x+xSpace, y-ySpace, xSpace/2f, ySpace);
        if (room.getChild(1) != null) extractNodes(room.getChild(1), dot, floor+1, x-xSpace, y-ySpace, xSpace/2f, ySpace);
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
                drawCircle(canvas, node.x, node.y, nodeBigRayon, itemsBackgroundColor);

            for (Node node : nodes)
                if (node.parent != null)
                    drawLine(canvas, node.x, node.y, node.parent.x, node.parent.y, linkBigWidth, itemsBackgroundColor);

            for (Node node : nodes)
                if (node.parent != null)
                    drawLine(canvas, node.x, node.y, node.parent.x, node.parent.y, linkSmallWidth, node.color, node.parent.color);

            for (Node node : nodes)
                drawCircle(canvas, node.x, node.y, nodeSmallRayon, node.color);
        }
    }

}
