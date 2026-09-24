package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Constants;

public class CannonSubsystem extends SubsystemBase {
    private final TalonSRX cannonMotor = new TalonSRX(7);
    private final DutyCycleEncoder motorEncoder = new DutyCycleEncoder(0);
    private double setpoint = Constants.closedSetpoint;
    
    public CannonSubsystem() {
        cannonMotor.configFactoryDefault();
    }

    public Command shoot() {
        return this.runOnce(() -> {setpoint = Constants.openSetpoint;}).andThen(this.run(()->{}).withTimeout(Constants.cannonOpenTime)).andThen(this.runOnce(()->{setpoint = Constants.closedSetpoint;}));
    }

    @Override
    public void periodic(){
        double currentPosition = motorEncoder.get();
        double motorOutput = (setpoint - currentPosition) * Constants.shooterKp;
        cannonMotor.set(ControlMode.PercentOutput, motorOutput);
    }
}
