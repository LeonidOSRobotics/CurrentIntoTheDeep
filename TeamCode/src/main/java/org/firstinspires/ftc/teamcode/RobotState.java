package org.firstinspires.ftc.teamcode;

public enum RobotState {

    BASE(1,0,0.3, "Base"),
    SPNM_GRAB(1,-1,0.3, "SPNM_GRAB"),
    SPMN_COLLECT(1,-1,0.3, "SPMN_COLLECT"),
    HIGH_SPMN_SETUP(1,51,0.3,"HIGH_SPMN_SETUP"),
    LOW_SPMN_SETUP(1,16,0.3,"LOW_SPMN_SETUP"),
    SPMN_SCORE(1,-1,0.3,"SPMN_SCORE"),
    PRE_PICKUP_SMPL(1,0,0.3,"PRE_PICKUP_SMPL"),
    PICKUP_SMPL(1,0,0.3,"PICKUP_SMPL"),
    SMPL_SETUP(1,53,0.3,"SMPL_SETUP"),
    SMPL_SCORE(1,53,0,"SMPL_SCORE");

    private double armPosition;
    private double slidePosition;
    private double bucketPosition;
    private String name;

    //          Ticks,      cm from Base,  0-1
    RobotState(double arm, double slide, double bucket, String name){
        armPosition = arm;
        slidePosition = slide;
        bucketPosition = bucket;
        this.name = name;
    }

    public double getArmPosition(){
        return armPosition;
    }

    public double getSlidePosition() {
        return slidePosition;
    }

    public double getBucketPosition() {
        return bucketPosition;
    }

    public String getName() {
        return name;
    }

    public void setArmPosition(double armPosition) {
        this.armPosition = armPosition;
    }

    public void setSlidePosition(double slidePosition) {
        this.slidePosition = slidePosition;
    }

    public void setBucketPosition(double bucketPosition) {
        this.bucketPosition = bucketPosition;
    }

}
