package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase{
    VictorSPX compressor = new VictorSPX(8);
    boolean on = true;

    public Compressor(){
        compressor.set(ControlMode.PercentOutput, 1);
    }

    public void stopCompressor(){
        compressor.set(ControlMode.PercentOutput, 0);
    }

    public void startCompressor(){
        compressor.set(ControlMode.PercentOutput, 1);
    }

    public Command changeCompressor(){
        return this.run(()->{if(on){stopCompressor(); on = false;}
        if(!on){startCompressor(); on = true;}});
    }

    @Override
    public void periodic(){
        
    }
}
