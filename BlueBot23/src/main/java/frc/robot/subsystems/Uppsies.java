import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Uppsies extends SubsystemBase{
    
    //Before anthing, we need to establish a new motor
    private final CCSparkMax elevator = new CCSparkMax("Elevator Moter", "em", RobotMap.ELEVATOR, MotorType.kBrushless, IdleMode.kBrake, RobotMap.ELEVATOR_REVERSE, RobotMap.DRIVE_ENCODER);

    //Next we set the speed to somthing (so it moves)
    public void INeedUppsies(double speed){
        elevator.set(speed);

    } // Here, we are creating a command that moves the motor from point a to b
    public RunCommand elevatorUppies(double target) {
        PidController controllerUppsies = new PidController(.5,0,0);
        return new RunCommand(() -> {

            // We are including a PidLoop so it doesn't break the mechanism
            // A pidLoop takes the curret position and the target position to calculate how fast we should be going
            double speed = controllerUppsies.calculate(elevator.getPosition(), target);
            INeedUppsies(speed);

            // "this)" refers to the class we are in
        }, this) {

            // "@Override"
            @Override
            public boolean isFinished() {
                //this isn't finished we need to use something like "return speed > .5" 
                //on another note, an inequality is a boolean
                return target-elevator.getPosition() > 0.1; //THIS MIGHT NOT BE RIGHT
            }

        };
    }

    
}