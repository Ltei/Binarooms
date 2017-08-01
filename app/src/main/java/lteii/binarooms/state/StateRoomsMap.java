package lteii.binarooms.state;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lteii.binarooms.Database;
import lteii.binarooms.R;
import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.utils.opengl.GLColor;
import lteii.binarooms.utils.opengl.GLShape;
import lteii.binarooms.utils.opengl.GLShapeCircle;
import lteii.binarooms.utils.opengl.GLShapeSquare;
import lteii.binarooms.utils.opengl.GLSurfaceView;


public class StateRoomsMap extends State {

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


    private ArrayList<Node> nodes = null;
    private ArrayList<GLShape> shapes = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_rooms_map, container, false);

        final GLSurfaceView glSurfaceView = rootView.findViewById(R.id.all_rooms_treeview);

        nodes = new ArrayList<>();
        extractNodes(Database.SOURCE_ROOM, null, 0, 0, 0.25f, 0.2f, 0.3f, 0.5f);

        final GLColor itemsBackgroundColor = GLColor.rgba(0.5f, 0.5f, 0.5f, 1f);

        shapes = new ArrayList<>();
        for (Node node : nodes)
            shapes.add(new GLShapeCircle(glSurfaceView, node.x, node.y, node.bigRayon, itemsBackgroundColor));
        for (Node node : nodes)
            if (node.parent != null)
                shapes.add(GLShapeSquare.newLine(glSurfaceView, node.x, node.y, node.parent.x, node.parent.y, node.smallRayon, itemsBackgroundColor));
        for (Node node : nodes)
            shapes.add(new GLShapeCircle(glSurfaceView, node.x, node.y, node.smallRayon, GLColor.fromColor(node.color)));

        glSurfaceView.setup(new GLSurfaceView.ShapeDrawer() {
            @Override
            public void drawShapes(float[] mvpMatrix) {for (GLShape shape : shapes) shape.draw();}
        });

        return rootView;
    }

    private void extractNodes(OLDRoom room, @Nullable Node parent, float x, float y, float bigRayon, float smallRayon, float xSpace, float ySpace) {
        final Node dot = new Node(x, y, bigRayon, smallRayon, room.getBackgroundColor(), parent);
        nodes.add(dot);
        if (room.getChild(0) != null) extractNodes(room.getChild(0), dot, x+xSpace, y+ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
        if (room.getChild(1) != null) extractNodes(room.getChild(1), dot, x-xSpace, y+ySpace, bigRayon/2f, smallRayon/2f, xSpace/2f, ySpace/2f);
    }

}
