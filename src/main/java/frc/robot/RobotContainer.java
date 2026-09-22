// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Subsystems.CannonSubsystem;
import frc.robot.Subsystems.Compressor;
import frc.robot.Subsystems.DriveSubsystem;

public class RobotContainer {
  private final CommandJoystick joystick = new CommandJoystick(0);
  DriveSubsystem drive = new DriveSubsystem();
  CannonSubsystem cannon = new CannonSubsystem();
  Compressor compressor = new Compressor();
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    drive.setDefaultCommand(drive.drive(joystick.getY(), joystick.getTwist()));
    joystick.button(5).and(joystick.trigger()).whileTrue(cannon.openShooter());
    joystick.button(5).or(joystick.trigger()).whileFalse(cannon.closeShooter());
    joystick.button(2).onChange(compressor.changeCompressor());
  }
}
