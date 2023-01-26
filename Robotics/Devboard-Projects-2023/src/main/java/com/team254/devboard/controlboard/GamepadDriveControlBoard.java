package com.team254.devboard.controlboard;

import com.team254.devboard.Constants;

public class GamepadDriveControlBoard implements IDriveControlBoard {
    private static GamepadDriveControlBoard mInstance = null;

    public static GamepadDriveControlBoard getInstance() {
        if (mInstance == null) {
            mInstance = new GamepadDriveControlBoard();
        }

        return mInstance;
    }

    public final XboxController mController;

    private GamepadDriveControlBoard() {
        mController = new XboxController(Constants.kDriveGamepadPort);
    }

    @Override
    public double getThrottle() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getTurn() {
        // TODO Auto-generated method stub
        return 0;
    }
}