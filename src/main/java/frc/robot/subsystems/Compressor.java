package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase{
    public enum CompressorStates{
        IDLE,
        ON
    }

    VictorSPX compressor = new VictorSPX(8);
    DigitalInput pressureSensor = new DigitalInput(1);

    CompressorStates currentState = CompressorStates.IDLE;

    public Compressor(){

    }

    public void stopCompressor(){
        compressor.set(ControlMode.PercentOutput, 0);
    }

    public void startCompressor(){
        compressor.set(ControlMode.PercentOutput, 1);
    }

    public Command changeCompressor(){
        return this.runOnce(()->{
            if(currentState == CompressorStates.IDLE) currentState = CompressorStates.ON;
            else if(currentState == CompressorStates.ON) currentState = CompressorStates.IDLE;
        });
    }

    @Override
    public void periodic(){
        switch(currentState){
            case IDLE:
                stopCompressor();
                break;
            case ON:
                if(pressureSensor.get())stopCompressor();
                else{startCompressor();}
                break;
        }
    }
}
