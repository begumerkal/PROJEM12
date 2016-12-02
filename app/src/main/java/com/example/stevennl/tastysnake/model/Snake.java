package com.example.stevennl.tastysnake.model;

import java.util.ArrayList;

/**
 * XXX
 * Author: XXX
 */
public class Snake {
    public ArrayList<Pos> body = new ArrayList<>();
    public ArrayList<Point.Type> type = new ArrayList<>();
    Direction orientation;
    int row, column;
    public Snake(int type, int row, int column) {
        switch (type) {
            case 0:
                orientation = Direction.RIGHT;
                for (int i = 10; i >= 0; i --) {
                    body.add(new Pos(0, i));
                }
                break;
            default:
        }
        this.row = row;
        this.column = column;
        genType();
    }
    public void extend() {

    }
    public void genType() {
        type.clear();
        switch (orientation) {
            case UP:
                type.add(0, Point.Type.HEAD_U);
                break;
            case DOWN:
                type.add(0, Point.Type.HEAD_D);
                break;
            case LEFT:
                type.add(0, Point.Type.HEAD_L);
                break;
            case RIGHT:
                type.add(0, Point.Type.HEAD_R);
                break;
        }
        for (int i = 1; i < body.size() - 1; i ++) {
            Pos pre = body.get(i - 1);
            Pos succ = body.get(i + 1);
            Pos now = body.get(i);
            if (pre.onLeftOf(now) && now.onLeftOf(succ))
                type.add(Point.Type.BODY_HOR);
            if (pre.onRightOf(now) && now.onRightOf(succ))
                type.add(Point.Type.BODY_HOR);
            if (pre.onUpOf(now) && now.onUpOf((succ)))
                type.add(Point.Type.BODY_VER);
            if (pre.onDownOf(now) && now.onDownOf(succ))
                type.add(Point.Type.BODY_VER);
            if ((pre.onLeftOf(now) && succ.onUpOf(now)) || (pre.onUpOf(now) && succ.onLeftOf(now)))
                type.add(Point.Type.BODY_L_U);
            if ((pre.onLeftOf(now) && succ.onDownOf(now)) || (pre.onDownOf(now) && succ.onLeftOf(now)))
                type.add(Point.Type.BODY_L_D);
            if ((pre.onRightOf(now) && succ.onUpOf(now)) || (pre.onUpOf(now) && succ.onRightOf(now)))
                type.add(Point.Type.BODY_R_U);
            if ((pre.onRightOf(now) && succ.onDownOf(now)) || (pre.onDownOf(now) && succ.onRightOf(now)))
                type.add(Point.Type.BODY_R_D);
        }
        Pos last = body.get(body.size() - 1);
        Pos butLast = body.get(body.size() - 2);
        if (last.onLeftOf(butLast) || last.onRightOf(butLast))
            type.add(Point.Type.BODY_HOR);
        if (last.onUpOf(butLast) || last.onDownOf(butLast))
            type.add(Point.Type.BODY_VER);
    }
    public boolean move(Direction order) {
        Pos head = body.get(0);
        switch (orientation) {
            case UP:
                if (order == Direction.UP) {
                    body.add(0, head.toUP());
                    orientation = Direction.UP;
                }
                if (order == Direction.LEFT) {
                    body.add(0, head.toLEFT());
                    orientation = Direction.LEFT;
                }
                if (order == Direction.RIGHT) {
                    body.add(0, head.toRIGHT());
                    orientation = Direction.RIGHT;
                }
                if (order == Direction.DOWN) {
                    body.add(0, head.toUP());
                    orientation = Direction.UP;
                }
                break;
            case DOWN:
                if (order == Direction.UP) {
                    body.add(0, head.toDOWN());
                    orientation = Direction.DOWN;
                }
                if (order == Direction.LEFT) {
                    body.add(0, head.toLEFT());
                    orientation = Direction.LEFT;
                }
                if (order == Direction.RIGHT) {
                    body.add(0, head.toRIGHT());
                    orientation = Direction.RIGHT;
                }
                if (order == Direction.DOWN) {
                    body.add(0, head.toDOWN());
                    orientation = Direction.DOWN;
                }
                break;
            case LEFT:
                if (order == Direction.UP) {
                    body.add(0, head.toUP());
                    orientation = Direction.UP;
                }
                if (order == Direction.LEFT) {
                    body.add(0, head.toLEFT());
                    orientation = Direction.LEFT;
                }
                if (order == Direction.RIGHT) {
                    body.add(0, head.toLEFT());
                    orientation = Direction.LEFT;
                }
                if (order == Direction.DOWN) {
                    body.add(0, head.toDOWN());
                    orientation = Direction.DOWN;

                }
                break;
            case RIGHT:
                if (order == Direction.UP) {
                    body.add(0, head.toUP());
                    orientation = Direction.UP;
                }
                if (order == Direction.LEFT) {
                    body.add(0, head.toRIGHT());
                    orientation = Direction.RIGHT;
                }
                if (order == Direction.RIGHT) {
                    body.add(0, head.toRIGHT());
                    orientation = Direction.RIGHT;
                }
                if (order == Direction.DOWN) {
                    body.add(0, head.toDOWN());
                    orientation = Direction.DOWN;

                }
                break;
        }
        body.remove(body.size() - 1);

        genType();
        return checkOut();
    }
    public boolean checkOut() {
        Pos head = body.get(0);
        if (head.getX() >= row || head.getX() < 0 || head.getY() >= column || head.getY() < 0) return false;
        for (int i = 0; i < body.size(); i ++)
            for (int j = i + 1; j < body.size(); j ++)
                if (body.get(i).getX() == body.get(j).getX() && body.get(i).getY() == body.get(j).getY())
                    return false;
        return true;
    }
}
