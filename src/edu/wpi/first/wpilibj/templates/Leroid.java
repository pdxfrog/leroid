/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*Lasciate ogni speranza, voi ch'entrate*/

package edu.wpi.first.wpilibj.templates;


//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Leroid extends SimpleRobot {
    
    
    Timer myTimer = new Timer(); 
    
    
    Joystick axisStick;
    Joystick attackStick;
    Victor frontLeft = new Victor(1,1);
    Victor frontRight = new Victor(1,5);
    Victor rearLeft = new Victor(1,2);
    Victor rearRight = new Victor(1,6);
    
    
    RobotDrive driver = new RobotDrive(frontLeft,rearLeft,frontRight,rearRight);
   
    Servo boxServo = new Servo(9);
    Servo bunnyServo = new Servo(10); // This is the Servo that drops the bunny, the PWM cable goes in the PWM section of the sidecar
    Relay compressor = new Relay(1,8); // This is the 
    Relay boxSolUno = new Relay(1,7);
    Relay boxSolDuo = new Relay(1,6);
    
    
  
    
    
    public void robotInit() {
       compressor.setDirection(Direction.kBoth);
        compressor.set(Relay.Value.kOn); 
  //      compressor.start();
        driver.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driver.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
    }
    
    public void disabledInit(){
       
    }
    
    
    
    
    
   
    
    
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
   /**   boxServo.setPosition(.9);
myTimer.reset();
myTimer.start();
while ((myTimer.get())<3){
    getWatchdog().feed();
      driver.mecanumDrive_Polar(.5, 0, 0);
        }
driver.stopMotor();

bunnyServo.setPosition(.9);
while(isAutonomous()) {
    driver.drive(0, 0);
}
**/
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
      //  compressor.start();
        axisStick = new Joystick(1);
        attackStick = new Joystick(2);
        

        double rawSpeed;
        double speed;
        double DEADBAND = .2;
        
        boxSolUno.setDirection(Direction.kBoth);
        boxSolDuo.setDirection(Direction.kBoth);
        
        
        
        while (isOperatorControl()){
            getWatchdog().feed();
            rawSpeed = (axisStick.getMagnitude());
                if (Math.abs(rawSpeed)<DEADBAND){
                    speed = 0;
                }
                else {
                    speed = (rawSpeed-(Math.abs(rawSpeed)/rawSpeed*DEADBAND)/(1-DEADBAND));
                }
                
               
            
            driver.mecanumDrive_Polar((speed)*axisStick.getThrottle(), axisStick.getDirectionDegrees(), (-1)*(axisStick.getTwist()));
          boxServo.setPosition(attackStick.getZ());
          if(Math.abs(attackStick.getX())==1) {   
              boxSolUno.set(Relay.Value.kOn); 
          }
          else {
              boxSolUno.set(Relay.Value.kOff);
          }
         if (Math.abs(attackStick.getY())==1) {
             boxSolDuo.set(Relay.Value.kOn);
         }
         else {
             boxSolDuo.set(Relay.Value.kOff);
         }
               
        }
      
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
       
    }
 
    
    
    }
//joystick object for SideWinder
class SideWinder extends Joystick {
    public SideWinder(int port){
        super(port, 5, 8);
    }

}



