package com.team254.devboard.controlboard;

import com.team254.devboard.Constants;

public class ControlBoard implements IDriveControlBoard, IButtonControlBoard {
    private static ControlBoard mInstance = null;

    public static ControlBoard getInstance() {
        if (mInstance == null) {
            mInstance = new ControlBoard();
        }

        return mInstance;
    }

    private final IDriveControlBoard mDriveControlBoard;
    private final IButtonControlBoard mButtonControlBoard;

    public ControlBoard() {
        mDriveControlBoard = Constants.kUseDriveGamepad ? GamepadDriveControlBoard.getInstance()
                : MainDriveControlBoard.getInstance();
        mButtonControlBoard = GamepadButtonControlBoard.getInstance();
    }    

    @Override
    public double getThrottle() {
        return mDriveControlBoard.getThrottle();
    }

    @Override
    public double getTurn() {
        return mDriveControlBoard.getTurn();
    }
}