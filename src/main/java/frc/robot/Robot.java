// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.Arm;

import java.io.Serial;

import javax.xml.crypto.Data;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;

/** This is a sample program to demonstrate the use of arm simulation with existing code. */
public class Robot extends TimedRobot {
  private final Arm m_arm = new Arm();
  private final Joystick m_joystick = new Joystick(Constants.kJoystickPort);
  private Constants.kArmState armState = Constants.kArmState.Lowered;

  @Override
  public void robotInit() {
    DataLogManager.log("ROBOT - INIT");
    DataLogManager.start();
  }

  @Override
  public void simulationPeriodic() {
    m_arm.simulationPeriodic();
  }

  @Override
  public void teleopInit() {
    DataLogManager.log("ROBOT - TELEOP INIT");
    m_arm.loadPreferences();
  }

  @Override
  public void teleopPeriodic() {
    if (true) {
      if (m_joystick.getTriggerPressed()) {
        if (armState == Constants.kArmState.Lowered) {
          m_arm.setSetpoint(Constants.kArmRaisedDegrees);
          armState = Constants.kArmState.Raised;
        } else {
          m_arm.setSetpoint(Constants.kArmLoweredDegrees);
          armState = Constants.kArmState.Lowered;
        }
      }
    } else {
    }
    m_arm.reachSetpoint();
  }

  @Override
  public void close() {
    m_arm.close();
    super.close();
  }

  @Override
  public void disabledInit() {
    // This just makes sure that our simulation code knows that the motor's off.
    m_arm.stop();
  }
}
