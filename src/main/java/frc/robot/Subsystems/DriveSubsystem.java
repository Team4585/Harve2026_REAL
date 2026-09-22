package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveSubsystem extends SubsystemBase{
    private final  WPI_VictorSPX  leftMotor1 = new  WPI_VictorSPX(4);
    private final  WPI_VictorSPX  leftMotor2 = new  WPI_VictorSPX(5);
    private final  WPI_VictorSPX  leftMotor3 = new  WPI_VictorSPX(6);
    private final  WPI_VictorSPX  rightMotor1 = new  WPI_VictorSPX(1);
    private final  WPI_VictorSPX  rightMotor2 = new  WPI_VictorSPX(2);
    private final  WPI_VictorSPX  rightMotor3 = new  WPI_VictorSPX(3);

    private final DifferentialDrive dDrive =
      new DifferentialDrive(leftMotor1::set, rightMotor1::set);
    
    public DriveSubsystem(){
        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);

        leftMotor2.follow(leftMotor1);
        leftMotor3.follow(leftMotor1);
        leftMotor1.setInverted(true);
        leftMotor2.setInverted(true);
        leftMotor3.setInverted(false);
    }

    public Command drive(double translate, double rotate){
        return this.run(()->{
            dDrive.arcadeDrive(translate, rotate);
        });
    }
}
