package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Constants;

public class CannonSubsystem extends SubsystemBase {
    private final TalonSRX cannonMotor = new TalonSRX(7);
    private final DutyCycleEncoder motorEncoder = new DutyCycleEncoder(0);
    private final PIDController pidController = new PIDController(0.2, 0.0, 0.0);
    
    public CannonSubsystem() {
        cannonMotor.configFactoryDefault();
    }

    public Command openShooter() {
        return this.run(() -> {
            double currentPosition = motorEncoder.get(); 
            double motorOutput = pidController.calculate(currentPosition, Constants.openSetpoint);
            cannonMotor.set(ControlMode.PercentOutput, motorOutput);
        });
    }

    public Command closeShooter(){
        return this.run(() -> {
            double currentPosition = motorEncoder.get(); 
            double motorOutput = pidController.calculate(currentPosition, Constants.closeSetpoint);
            cannonMotor.set(ControlMode.PercentOutput, motorOutput);
        });
    }

}
