package org.firstinspires.ftc.teamcode;

public enum RobotState {

    BASE(0,0,0.31, "Base"),
    SPNM_GRAB(0,459,0.26, "SPNM_GRAB"),
    SPMN_COLLECT(1,1160,0.31, "SPMN_COLLECT"),
    HIGH_SPMN_SETUP(1,2800,0.31,"HIGH_SPMN_SETUP"),
    LOW_SPMN_SETUP(1,1100,0.31,"LOW_SPMN_SETUP"),
    LOW_SPMN_SCORE(1,146,0.31,"SPMN_SCORE"),
    HIGH_SPMN_SCORE(1,1854,1,"SPMN_SCORE"),
    PRE_PICKUP_SMPL(2000,0,0.31,"PRE_PICKUP_SMPL"),
    PICKUP_SMPL(1,0,0.31,"PICKUP_SMPL"),
    SMPL_SETUP(0,2924,0.31,"SMPL_SETUP"),
    SMPL_SCORE(1,2924,0,"SMPL_SCORE");

    private int armPosition;
    private int slidePosition;
    private double bucketPosition;
    private String name;

    //          Ticks,      cm from Base,  0-1
    RobotState(int arm, int slide, double bucket, String name){
        armPosition = arm;
        slidePosition = slide;
        bucketPosition = bucket;
        this.name = name;
    }

    public int getArmPosition(){
        return armPosition;
    }

    public int getSlidePosition() {
        return slidePosition;
    }

    public double getBucketPosition() {
        return bucketPosition;
    }

    public String getName() {
        return name;
    }

    public void setArmPosition(int armPosition) {
        this.armPosition = armPosition;
    }

    public void setSlidePosition(int slidePosition) {
        this.slidePosition = slidePosition;
    }

    public void setBucketPosition(double bucketPosition) {
        this.bucketPosition = bucketPosition;
    }

}
